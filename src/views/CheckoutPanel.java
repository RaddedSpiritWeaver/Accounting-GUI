package views;

import dataModels.Cart;
import dataModels.Product;
import dataModels.User;
import enums.ViewPanels;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class CheckoutPanel extends JPanel {

    static int offThreshold = 5000;

    private User user;
    private MainFrame mainFrame;

    // needed for using action listener lambda
    private long subtotalValue = 0;

    public CheckoutPanel(User user, MainFrame mainFrame) {
        super();
        this.user = user;
        this.mainFrame = mainFrame;
        init();
    }

    private void init() {
        long rawSubtotal = user.getCart().subtotal();
        this.subtotalValue = rawSubtotal;
        LinkedList<Product> items = user.getCart().getProducts();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();

        JLabel pageTitle = new JLabel("check out");

        topPanel.add(pageTitle);

        int cols = 3;
        int rows = (items.size() / cols) + 1;
        JPanel middleContainer = new JPanel();
        middleContainer.setLayout(new GridLayout(rows, cols));
        int delta = cols * rows - items.size();
        // todo: better data passing
        for(Product p : items)
            middleContainer.add(new ItemPanel(this.mainFrame, p, user.getCart().getCount(p)));
        for(int i = 0; i < delta; i++)
            middleContainer.add(new JPanel());

        JScrollPane midPanel = new JScrollPane(middleContainer);
        midPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        midPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        JPanel subtotalPanel = new JPanel();
        subtotalPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel subtotalLabel = new JLabel(String.format("Subtotal: %d", rawSubtotal));

        subtotalPanel.add(subtotalLabel);
        if(rawSubtotal > offThreshold) {
            JButton applyDiscount = new JButton("use discount");
            applyDiscount.addActionListener(e -> {
                long discounted = rawSubtotal * 9 / 10;
                this.setSubtotalValue(discounted);
                subtotalLabel.setText(String.format("Subtotal: %d", this.subtotalValue));
            });
            subtotalPanel.add(applyDiscount);
        }

        JPanel botPanel = new JPanel();
        botPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton payNowButton = new JButton("Pay now");
        payNowButton.addActionListener(e -> {
            // clear the cart
            this.user.setCart(new Cart(this.user));

            System.out.println(this.user.toString());

            mainFrame.changeView(ViewPanels.Shopping);
        });

        JButton payLaterButton = new JButton("Pay later");
        payLaterButton.addActionListener(e -> {
            // clear the cart
            this.user.setCart(new Cart(this.user));

            // add users debt
            this.user.setDebt(this.user.getDebt() - this.subtotalValue);

            System.out.println("DEBT!!!!!!!!!!!!! -->" + user.getDebt());

            mainFrame.changeView(ViewPanels.Shopping);
        });

        JButton backButton = new JButton("back");
        backButton.addActionListener(e -> {
            this.mainFrame.changeView(ViewPanels.Shopping);
        });

        botPanel.add(payNowButton);
        botPanel.add(payLaterButton);
        botPanel.add(backButton);

        this.add(topPanel);
        this.add(midPanel);
        this.add(subtotalPanel);
        this.add(botPanel);
    }

    public void setSubtotalValue(long subtotalValue) {
        this.subtotalValue = subtotalValue;
    }
}
