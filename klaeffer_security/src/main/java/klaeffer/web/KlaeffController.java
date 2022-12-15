package klaeffer.web;

import javax.validation.Valid;

import klaeffer.service.KlaeffPage;
import klaeffer.service.KlaeffService;
import klaeffer.service.Profil;
import klaeffer.service.ProfilService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class KlaeffController {

  private final KlaeffService klaeffService;
  private final ProfilService profilService;

  public KlaeffController(KlaeffService klaeffService, ProfilService profilService) {

    this.klaeffService = klaeffService;
    this.profilService = profilService;
  }

  @GetMapping("/")
  public String index(Model model,
                      @RequestParam(name = "page", required = false, defaultValue = "1")
                      int page,
                      @RequestParam(name = "max", required = false, defaultValue = "10")
                      int max,
                      OAuth2AuthenticationToken authentication
  ) {

    String username=authentication.getPrincipal().getAttribute("login");
    model.addAttribute("username",username);
    int offset = page - 1;
    KlaeffPage list = klaeffService.getKlaeffs(offset, max);
    model.addAttribute("klaeffpage", list);
    model.addAttribute("page", page);
    model.addAttribute("max", max);
    return "main";
  }

  @GetMapping("/profil")
  public String profilSeite(Model model,String userId
  ) {
    model.addAttribute("profil",profilService.getProfil(userId));
    return "profil";
  }



  @PostMapping("/")
  public String add(@Valid KlaeffForm form, RedirectAttributes attrs,OAuth2AuthenticationToken authentication) {
    System.out.println(authentication);
    String username=authentication.getPrincipal().getAttribute("login");
    String userId=authentication.getPrincipal().getAttribute("id").toString();
    String avatar=authentication.getPrincipal().getAttribute("avatar_url");
    String userType=authentication.getPrincipal().getAttribute("type");
    String profilseite=authentication.getPrincipal().getAttribute("html_url");
    profilService.addProfil(new Profil(username,userId,avatar,userType,profilseite));
    klaeffService.addKlaeff(username, form.getText(),userId);
    attrs.addFlashAttribute("username", username);
    return "redirect:/";
  }


}
