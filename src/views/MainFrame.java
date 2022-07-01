package views;

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
//        this.add(this.currentPanel);
        this.setContentPane(this.currentPanel);
        resetVisibility();
    }

    private void resetVisibility() {
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
        if (newView.equals(ViewPanels.Blank))
            changePanel(new JPanel());

        if (newView.equals(ViewPanels.Login))
            changePanel(new LoginPanel(this));

        if (newView.equals(ViewPanels.Signup))
            changePanel(new SignupPanel(this));
    }

    // getters and setters

    public Program getProgram() {
        return program;
    }
}
