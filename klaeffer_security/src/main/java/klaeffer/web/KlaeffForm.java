package klaeffer.web;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class KlaeffForm {
  @NotEmpty
  @Size(max = 300)
  private String text;

  public KlaeffForm(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
