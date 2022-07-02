package views;

import dataModels.User;
import enums.ViewPanels;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {

    private MainFrame mainFrame;
    private User user;

    public ProfilePanel(MainFrame mainFrame, User user) {
        super();
        this.mainFrame = mainFrame;
        this.user = user;
        init();
    }

    private void init() {
        this.setLayout(null);

        JPanel mainContainer = new JPanel();
        mainContainer.setBounds(100,100,400,100);
//        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setLayout(new GridLayout(4, 1));
        mainContainer.setBorder(BorderFactory.createLineBorder(Color.darkGray, 3));
        mainContainer.setBackground(Color.LIGHT_GRAY);

        JLabel usernameLabel = new JLabel(String.format("Username : %s", user.getUsername()));

        JLabel debtLabel = new JLabel(String.format("Debt : %d", user.getDebt()));

        JLabel itemsInCartLabel = new JLabel(String.format("Items in cart : %d", user.getCart().getSize()));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.setBackground(Color.lightGray);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.mainFrame.changeView(ViewPanels.Shopping);
        });

        JButton clearDebtButton = new JButton("Clear Debt");
        clearDebtButton.addActionListener(e -> {
            this.user.setDebt(0);
            debtLabel.setText(String.format("Debt : %d", user.getDebt()));
        });

        buttonPanel.add(backButton);
        buttonPanel.add(clearDebtButton);

        mainContainer.add(usernameLabel);
        mainContainer.add(debtLabel);
        mainContainer.add(itemsInCartLabel);
        mainContainer.add(buttonPanel);

        this.add(mainContainer);
    }

}
