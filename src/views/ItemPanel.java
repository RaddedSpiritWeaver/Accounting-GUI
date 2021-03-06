package views;

import dataModels.Product;
import services.CartServices;

import javax.swing.*;
import java.awt.*;

public class ItemPanel extends JPanel {

    private MainFrame mainFrame;
    private Product product;
    private boolean checkOutMode = false;

    public ItemPanel(MainFrame mainFrame, Product product) {
        super();
        this.mainFrame = mainFrame;
        this.product = product;
        init(0);
    }

    public ItemPanel(MainFrame mainFrame, Product product, int count) {
        super();
        this.mainFrame = mainFrame;
        this.product = product;
        this.checkOutMode = true;
        init(count);
    }

    private void init(int count) {
        CartServices cartServices = this.mainFrame.getProgram().getCartService();

//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new GridLayout(3, 1));
        this.setSize(200,200);
        this.setBackground(Color.lightGray);
        this.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

        // first row
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.lightGray);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.pink, 1));
        topPanel.setLayout(new GridLayout(1, 2));

        JLabel nameLabel = new JLabel(this.product.getName());

        JLabel priceLabel = new JLabel(String.format("Unit Price : %d", this.product.getPrice()));

        topPanel.add(nameLabel);
        topPanel.add(priceLabel);

        JPanel midPanel = new JPanel();
        midPanel.setBackground(Color.lightGray);
        midPanel.setBorder(BorderFactory.createLineBorder(Color.pink, 1));
//        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.X_AXIS));
        midPanel.setLayout(new GridLayout(1, 2));

        JLabel storeCountLabel = new JLabel(String.format("Storage: %d", cartServices.getInventoryCount(this.product)));

        JLabel cartCountLabel = new JLabel(String.format("Cart: %d", cartServices.getCartCount(this.product)));

        midPanel.add(storeCountLabel);
        midPanel.add(cartCountLabel);

        if(this.checkOutMode)
            midPanel.remove(storeCountLabel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.lightGray);

        if(!this.checkOutMode){
            bottomPanel.setLayout(new GridLayout(1, 2));

            JButton addButton = new JButton("Add");
            addButton.addActionListener(e -> {
                cartServices.addToCart(this.product);
                storeCountLabel.setText(String.format("Storage: %d", cartServices.getInventoryCount(this.product)));
                cartCountLabel.setText(String.format("Cart: %d", cartServices.getCartCount(this.product)));
            });

            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(e -> {
                cartServices.removeFromCart(this.product);
                storeCountLabel.setText(String.format("Storage: %d", cartServices.getInventoryCount(this.product)));
                cartCountLabel.setText(String.format("Cart: %d", cartServices.getCartCount(this.product)));
            });

            bottomPanel.add(addButton);
            bottomPanel.add(removeButton);
        }
        else {
            bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            JLabel totalValue = new JLabel(String.format("Total value: %d", count * product.getPrice()));

            bottomPanel.add(totalValue);
        }

        this.add(topPanel);
        this.add(midPanel);
        this.add(bottomPanel);
    }
}
