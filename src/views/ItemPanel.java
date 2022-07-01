package views;

import dataModels.Product;
import services.CartServices;

import javax.swing.*;
import java.awt.*;

public class ItemPanel extends JPanel {

    private MainFrame mainFrame;
    private Product product;

    public ItemPanel(MainFrame mainFrame, Product product) {
        super();
        this.mainFrame = mainFrame;
        this.product = product;
        init();
    }

    private void init() {
        CartServices cartServices = this.mainFrame.getProgram().getCartService();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(200,200);
        this.setBackground(Color.CYAN);

        // first row
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        JLabel nameLabel = new JLabel(this.product.getName());

        JLabel priceLabel = new JLabel(String.format("Price : %d", this.product.getPrice()));

        topPanel.add(nameLabel);
        topPanel.add(priceLabel);

        JPanel midPanel = new JPanel();
        midPanel.setBackground(Color.ORANGE);
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.X_AXIS));

        JLabel storeCountLabel = new JLabel(String.format("Storage: %d", cartServices.getInventoryCount(this.product)));

        JLabel cartCountLabel = new JLabel(String.format("Cart: %d", cartServices.getCartCount(this.product)));

        midPanel.add(storeCountLabel);
        midPanel.add(cartCountLabel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            cartServices.addToCart(this.product);
            storeCountLabel.setText(String.format("Storage: %d", cartServices.getCartCount(this.product)));
            cartCountLabel.setText(String.format("Cart: %d", cartServices.getCartCount(this.product)));
        });

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> {
            cartServices.removeFromCart(this.product);
            storeCountLabel.setText(String.format("Storage: %d", cartServices.getCartCount(this.product)));
            cartCountLabel.setText(String.format("Cart: %d", cartServices.getCartCount(this.product)));
        });

        bottomPanel.add(addButton);
        bottomPanel.add(removeButton);

        this.add(topPanel);
        this.add(midPanel);
        this.add(bottomPanel);
    }
}
