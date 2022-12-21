package klaeffer.domain.user;

public class UserName{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public UserName(String name) {
        this.name = name;
    }
}
