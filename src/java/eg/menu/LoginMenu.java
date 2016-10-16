package eg.menu;

import eg.dataBaseCheck.DataBaseCheck;
import eg.exceptions.ListIsEmpty;
import eg.exceptions.TablesDoNotExist;
import eg.exceptions.UserNotFound;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

public class LoginMenu extends BaseMenuFrame implements ActionListener {

    private final JTextField loginField;
    private final JTextField passwordField;
    private static final String enterButtonText = "Enter";
    private static final String exitButtonText = "Exit";
    private static final String passwordLabelText = "Password :";
    private static final String loginLabelText = "Login :";
    private static final String passwordFieldActionCommand = "Password";
    private static final String enterFieldActionCommand = "Enter";
    private static final String exitFieldActionCommand = "Exit";

    public LoginMenu() {

        this.setBounds(620, 300, 230, 140);
        this.loginField = new JTextField(12);
        JButton enterButton = new JButton(enterButtonText);
        JButton exitButton = new JButton(exitButtonText);
        JLabel passwordLabel = new JLabel(passwordLabelText, SwingConstants.RIGHT);
        JLabel loginLabel = new JLabel(loginLabelText, SwingConstants.RIGHT);
        this.passwordField = new JTextField(12);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        loginField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        passwordField.setActionCommand(passwordFieldActionCommand);
        enterButton.setActionCommand(enterFieldActionCommand);
        exitButton.setActionCommand(exitFieldActionCommand);
        passwordField.addActionListener(this);
        enterButton.addActionListener(this);
        exitButton.addActionListener(this);

        container.setLayout(new GridLayout(3, 2, 20, 10));
        container.add(loginLabel);
        container.add(loginField);
        container.add(passwordLabel);
        container.add(passwordField);
        container.add(exitButton);
        container.add(enterButton);

        try {
            System.out.println("check begun");
            DataBaseCheck dataBaseCheck = new DataBaseCheck();
        } catch (SQLException e) {
            showMessage(e.getMessage(), inputErrorText);
        } catch (TablesDoNotExist tablesDoNotExist) {
            showMessage(tablesDoNotExist.getMessage(), inputErrorText);
        }

        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(exitFieldActionCommand)) System.exit(0);

        try {
            userService.enter(loginField.getText(), passwordField.getText());
            this.dispose();
        } catch (SQLException | UserNotFound | ListIsEmpty ex) {
            showMessage(ex.getMessage(), messageDialogTitle);
        }
    }
}
