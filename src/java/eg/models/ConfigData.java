package eg.models;

public class ConfigData {
    private String URL;
    private String login;
    private String password;

    public ConfigData(String URL, String login, String password) {
        this.URL = URL;
        this.login = login;
        this.password = password;
    }

    public String getURL() {
        return URL;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
