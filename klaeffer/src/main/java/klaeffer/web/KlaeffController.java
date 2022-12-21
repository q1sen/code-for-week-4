package klaeffer.web;

import javax.validation.Valid;

import klaeffer.klaeff.KlaeffPage;
import klaeffer.klaeff.KlaeffService;
import klaeffer.profil.Profil;
import klaeffer.profil.ProfilService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class KlaeffController {

  private final KlaeffService service;
  private final ProfilService profilService;

  public KlaeffController(KlaeffService service, ProfilService profilService) {

    this.service = service;
    this.profilService = profilService;
  }

  @GetMapping("/profil")
  public String profil(Model model,Long id
  ) {
    model.addAttribute("profil",profilService.getProfil(id));
    return "profil";
  }

  @GetMapping("/")
  public String index(Model model,
                      @RequestParam(name = "page", required = false, defaultValue = "1")
                      int page,
                      @RequestParam(name = "max", required = false, defaultValue = "10")
                      int max,
                      OAuth2AuthenticationToken token

  ) {
    String username = token.getPrincipal().getAttribute("login");
    model.addAttribute("username" , username);
    int offset = page - 1;
    KlaeffPage list = service.getKlaeffs(offset, max);
    model.addAttribute("klaeffpage", list);
    model.addAttribute("page", page);
    model.addAttribute("max", max);
    return "main";
  }

  @PostMapping("/")
  public String add(@Valid KlaeffForm form, RedirectAttributes attrs,OAuth2AuthenticationToken token) {
    System.out.println(token);
    String userName=token.getPrincipal().getAttribute("login");
    Integer userID=token.getPrincipal().getAttribute("id");
    String avatar_url=token.getPrincipal().getAttribute("avatar_url");
    service.addKlaeff(form.getUsername(), form.getContent(),(long)userID);
    profilService.addProfil(new Profil((long)userID,userName,avatar_url));
    attrs.addFlashAttribute("name", form.getUsername());
    return "redirect:/";
  }

}
