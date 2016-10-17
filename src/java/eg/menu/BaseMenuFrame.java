package eg.menu;

import eg.main.Main;
import eg.models.User;
import eg.service.HistoryService;
import eg.service.UserService;
import eg.service.VotingService;
import eg.serviceImpl.ServiceFactory;

import javax.swing.*;

public class BaseMenuFrame extends JFrame {

    protected User currentUser;

    protected static final String messageDialogTitle = "Error";
    protected static final String inputErrorText = "Input error!";

    protected HistoryService historyService = ServiceFactory.getHistoryService();
    protected UserService userService = ServiceFactory.getUserService();
    protected VotingService votingService = ServiceFactory.getVotingService();

    protected void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
    }

    protected void logOffButtonActionPerformed(java.awt.event.ActionEvent evt) {
        currentUser = null;
        this.dispose();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Main.setLoginMenu(new LoginMenu());
            }
        });
    }


}