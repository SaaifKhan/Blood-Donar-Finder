package app.dotinfiny.Bdf.UI.authentication.singupfragment;

public class UserDetail {

    private String Username;
    private String Email;
    private String PhoneNumber;
    private String Password;
    private String id;

    public UserDetail() {
    }

    public UserDetail(String username, String email, String phoneNumber, String password, int id) {
        Username = username;
        Email = email;
        PhoneNumber = phoneNumber;
        Password = password;
        id = id;
    }

    public String getUsername() {
        return Username;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public String getId() {
        return id;
    }
//    }
}


