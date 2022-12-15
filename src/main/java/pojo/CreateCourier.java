package pojo;

public class CreateCourier {
    private String login;
    private String password;
    private String firstName;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public CreateCourier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }



}
