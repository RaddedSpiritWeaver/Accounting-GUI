package views;

import enums.ViewPanels;
import program.Program;
import services.AuthService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private MainFrame mainFrame;

    public LoginPanel(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        init();
    }

    private void init() {
        this.setLayout(null);
//        this.setBackground(Color.blue);

        JPanel midPanel = new JPanel();
        midPanel.setLayout(new GridLayout(3, 1));
        midPanel.setSize(400, 150);
        midPanel.setLocation(100,50);

        JPanel userPanel = new JPanel();
        userPanel.setBackground(Color.darkGray);
        userPanel.setLayout(null);


        JPanel passwordPanel = new JPanel();
        passwordPanel.setBackground(Color.darkGray);
        passwordPanel.setLayout(null);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.darkGray);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        midPanel.add(userPanel);
        midPanel.add(passwordPanel);
        midPanel.add(buttonPanel);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(60, 10, 110, 20);
        userPanel.add(userLabel);

        JTextField userField = new JTextField(20);
        userField.setBounds(170, 10, 110, 20);
        userPanel.add(userField);

        JLabel passwordLabel = new JLabel("password");
        passwordLabel.setBounds(60, 10, 110, 20);
        passwordPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(170, 10, 110, 20);
        passwordPanel.add(passwordField);

        JButton loginButton = new JButton("login");
        loginButton.addActionListener(e -> {
            // get the authentication service
            AuthService authService = this.mainFrame.getProgram().getAuthService();
            if (authService.login(userField.getText(), passwordField.getText())) {
                // todo: add transition to next page
                this.mainFrame.changeView(ViewPanels.Blank);
            }
            else {
                // create a dialog and say failed
                JDialog failedDialog = new JDialog(this.mainFrame, "!!failed!!");
                failedDialog.setSize(200, 100);

                JLabel failedText = new JLabel("failed to login!");
                failedDialog.add(failedText);

                failedDialog.setVisible(true);
            }
        });

        JButton signupButton = new JButton("sign-up");
        signupButton.addActionListener(e -> {
            // go to sign up panel
            this.mainFrame.changeView(ViewPanels.Signup);
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        this.add(midPanel);
    }
}
