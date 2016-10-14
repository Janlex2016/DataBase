package eg.main;

import eg.menu.AdminMenu;
import eg.menu.LoginMenu;
import eg.menu.UserMenu;

import javax.swing.*;

public class Main{

    private static LoginMenu loginMenu;
    private static AdminMenu adminMenu;
    private static UserMenu userMenu;

    public static LoginMenu getLoginMenu() {
        return loginMenu;
    }

    public static void setLoginMenu(LoginMenu loginMenu) {
        Main.loginMenu = loginMenu;
    }

    public static AdminMenu getAdminMenu() {
        return adminMenu;
    }

    public static void setAdminMenu(AdminMenu adminMenu) {
        Main.adminMenu = adminMenu;
    }

    public UserMenu getUserMenu() {
        return userMenu;
    }

    public static void setUserMenu(UserMenu userMenu) {
        Main.userMenu = userMenu;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                loginMenu = new LoginMenu();
            }
        });
    }

}
