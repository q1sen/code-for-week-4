package klaeffer.web;

import javax.validation.Valid;

import klaeffer.domain.shared.User;
import klaeffer.repository.ProfilRepository;
import klaeffer.domain.user.UserInfo;
import klaeffer.domain.user.UserName;
import klaeffer.service.KlaeffDetail;
import klaeffer.service.KlaeffPage;
import klaeffer.service.KlaeffService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
                      int max

  ) {
    int offset = page - 1;
    KlaeffPage list = service.getKlaeffs(offset, max);
    model.addAttribute("klaeffpage", list);
    model.addAttribute("page", page);
    model.addAttribute("max", max);
    return "main";
  }

  @GetMapping("/look")
  public @ResponseBody String look(){
    KlaeffPage page=service.getKlaeffs(1,10);
    List<KlaeffDetail> list=page.getKlaeffs();
    String a=list.getClass().getName();
    return a;
  }

  @PostMapping("/")
  public String add(@Valid KlaeffForm form, OAuth2AuthenticationToken authenticationToken,
                    RedirectAttributes attrs) {
    service.addKlaeff(authenticationToken.getPrincipal(), form.getText());
    return "redirect:/";
  }


}
