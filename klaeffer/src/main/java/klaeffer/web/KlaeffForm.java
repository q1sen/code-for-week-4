package klaeffer.web;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class KlaeffForm {
  @NotEmpty
  @Size(max = 39)
  private String username;
  @NotEmpty
  @Size(max = 300)
  private String content;

  public KlaeffForm(String username, String content) {
    this.username = username;
    this.content = content;
  }

  public String getUsername() {
    return username;
  }

  public void setUserName(String username) {
    this.username = username;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
