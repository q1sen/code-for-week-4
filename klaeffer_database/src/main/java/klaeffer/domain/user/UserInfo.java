package klaeffer.domain.user;

import klaeffer.domain.shared.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

public class UserInfo {
  @Id
  private final Long id;
  private final User user;
  private final UserName userName;
  private final String image;
  @PersistenceCreator
  public UserInfo(Long id, User user, UserName userName, String image) {
    this.id=id;
    this.user = user;
    this.userName = userName;
    this.image = image;
  }

  public User getUser() {
    return user;
  }

  public String getName() {
    return userName.getName();
  }

  public String getImage() {
    return image;
  }
}
