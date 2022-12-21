package klaeffer.klaeff;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

public class Klaeff{


    @Id
    private Long Id;
    private final Long githubid;
    private final String username;
    private final String content;
    @PersistenceCreator
    public Klaeff(Long Id,Long githubid, String username, String content){
        this.Id=Id;
        this.githubid = githubid;
        this.username=username;
        this.content=content;
    }
    public Klaeff(Long githubid, String username, String content){
        this.githubid = githubid;
        this.username=username;
        this.content=content;
    }

    @Override
    public boolean equals(Object other){
        if (this==other) return true;
        if (!(other instanceof Klaeff klaeff)) return false;
        return Id.equals(klaeff.Id);
    }

    public Long getId() {
        return Id;
    }

    public Long getGithubid() {
        return githubid;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }
}
