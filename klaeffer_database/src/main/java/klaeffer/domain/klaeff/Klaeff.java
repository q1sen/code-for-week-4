package klaeffer.domain.klaeff;

import klaeffer.domain.shared.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

public class Klaeff {
  @Id
  private final Long id;

  private final User user;
  private final Content content;
  @PersistenceCreator
  public Klaeff(Long id, User user, Content content) {
    this.id = id;
    this.user = user;
    this.content = content;
  }


  public User getUser() {
    return user;
  }

  public String getContent() {
    return content.getContent();
  }
}
