package constant;

public class CourierCanLogOn {
    private String login;

    private String password;

    public CourierCanLogOn(String login, String password){
        this.login = login;
        this.password = password;
    }

    public CourierCanLogOn(){
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
