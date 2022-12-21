package klaeffer.domain.shared;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

public class User {
  @Id
  private final Long id;

  @PersistenceCreator
  public User(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  @Override
  public boolean equals(Object object){
    if (this == object) return true;
    if (!(object instanceof User user)) return false;
    return id.equals(user.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

}
