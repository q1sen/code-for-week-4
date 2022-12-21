package klaeffer.web;


import klaeffer.domain.shared.User;
import klaeffer.domain.user.UserInfo;
import klaeffer.service.KlaeffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfilController {

  private final KlaeffService service;

  public ProfilController(KlaeffService service) {
    this.service = service;
  }

  @GetMapping("/profil")
  public String profil(Long id, Model model) {
    UserInfo userInfo = service.userInfo(id);
    model.addAttribute("user", userInfo);
    return "profil";
  }

}
