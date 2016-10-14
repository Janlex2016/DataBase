package eg.models;

import eg.models.enums.Access;

public class User {
    
    private int id;
    private String name;
    private String login;
    private String password;
    private Access access;
    
    public User() {
    }

    public User(int id, String name, String login, String password, String access) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.access = Access.valueOf(access);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
       this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getLogin(){
        return login;
    }
    
    public void setLogin(String login){
        this.login = login;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }    
}
