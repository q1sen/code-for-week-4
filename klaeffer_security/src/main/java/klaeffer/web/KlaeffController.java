package klaeffer.web;

import java.util.List;
import javax.validation.Valid;
import klaeffer.service.Klaeff;
import klaeffer.service.KlaeffPage;
import klaeffer.service.KlaeffService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class KlaeffController {

  private final KlaeffService service;

  public KlaeffController(KlaeffService service) {

    this.service = service;
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
    KlaeffPage list = service.getKlaeffs(offset, max);
    model.addAttribute("klaeffpage", list);
    model.addAttribute("page", page);
    model.addAttribute("max", max);
    return "main";
  }



  @PostMapping("/")
  public String add(@Valid KlaeffForm form, RedirectAttributes attrs,OAuth2AuthenticationToken authentication) {
    String username=authentication.getPrincipal().getAttribute("login");
    String profilseite=authentication.getPrincipal().getAttribute("html_url");
    service.addKlaeff(username, form.getText(),profilseite);
    attrs.addFlashAttribute("username", username);
    return "redirect:/";
  }


}
