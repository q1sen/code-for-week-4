package klaeffer.profil;

import org.springframework.data.annotation.Id;

public class Profil{


    @Id
    private final Long Id;
    private final String username;
    private final String avatar_url;

    public Profil(Long Id, String username, String avatar_url){
        this.Id = Id;
        this.username=username;
        this.avatar_url=avatar_url;
    }

    public Long getId() {
        return Id;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
}
