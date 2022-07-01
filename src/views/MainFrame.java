package views;

import dataModels.Product;
import dataModels.enums.ProductType;
import enums.ViewPanels;
import program.Program;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel currentPanel = null;
    private Program program = null;
    // todo: use an enum to store current view
    // todo: user logged in logic
    // todo: userLogin service
    // todo: changePanels service


    public MainFrame(String title, Program program) throws HeadlessException {
        super(title);
        this.program = program;
        init();
        // first panel we see is the login panel
        this.currentPanel = new LoginPanel(this);
        this.setContentPane(this.currentPanel);
        resetVisibility();
    }

    public void resetVisibility() {
        this.setVisible(false);
        this.setVisible(true);
    }

    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.blue);
        this.setSize(600, 600);
        this.setVisible(true);
    }

    private void changePanel(JPanel p){
        this.setContentPane(p);
        this.resetVisibility();
    }

    public void changeView(ViewPanels newView) {
        System.out.println("Changing view to: " + newView.toString());

        if (newView.equals(ViewPanels.Blank))
            changePanel(new JPanel());

        if (newView.equals(ViewPanels.Login))
            changePanel(new LoginPanel(this));

        if (newView.equals(ViewPanels.Signup))
            changePanel(new SignupPanel(this));

        if (newView.equals(ViewPanels.Shopping)) {
//            changePanel(new ItemPanel(this, this.program.getRandomProduct()));
            changePanel(new ShoppingPanel(this));
        }
    }

    // getters and setters

    public Program getProgram() {
        return program;
    }
}
