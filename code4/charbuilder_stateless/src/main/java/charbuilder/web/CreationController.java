package charbuilder.web;

import charbuilder.character.CharacterClass;
import charbuilder.character.CharacterInfo;

import java.util.Map;
import java.util.Random;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CreationController {


  @GetMapping("/")
  public String index() {
    return "start";
  }


  @GetMapping("/create/1")
  public String createNameForm() {
    return "name";
  }

  @PostMapping("/create/1")
  public String createStep1(String charactername, RedirectAttributes redirectAttributes) {
    if (charactername == null || charactername.isBlank()) return "name";
    redirectAttributes.addAttribute("charactername",charactername);
    return "redirect:/create/2";
  }

  @GetMapping("/create/2")
  public String createClassForm(String charactername,Model m) {
    m.addAttribute("charactername",charactername);
    return "class";
  }

  @PostMapping("/create/2")
  public String createStep3(CharacterClass characterclass,String charactername,RedirectAttributes redirectAttributes) {
    CharacterInfo character=new CharacterInfo(charactername);
    character.setCharacterClass(characterclass);
    redirectAttributes.addFlashAttribute("character",character);
    return "redirect:/create/3";
  }

  @GetMapping("/create/3")
  public String createAttributeForm() {
    return "attributes";
  }

  @PostMapping("/create/3")
  public String createStep3(Model m,
                            String charactername,
                            CharacterClass characterclass,
                            Integer points,
                            @RequestParam("Strength") int str,
                            @RequestParam("Dexterity") int dex,
                            @RequestParam("Constitution") int con,
                            @RequestParam("Intelligence") int intl,
                            @RequestParam("Wisdom") int wis,
                            @RequestParam("Charisma") int cha) {
    // Character fertigstellen und validieren
    CharacterInfo character=new CharacterInfo(charactername);
    m.addAttribute("character",character);
    character.setCharacterClass(characterclass);
    character.setPoints(points);


    character.setAttribute("Strength",str);
    character.setAttribute("Dexterity",dex);
    character.setAttribute("Constitution",con);
    character.setAttribute("Intelligence",intl);
    character.setAttribute("Wisdom",wis);
    character.setAttribute("Charisma",cha);
    // Fehler: Formular wieder anzeigen, Werte müssen erhalten bleiben
    if (!character.isValid()) return "attributes";
    // Erfolg: Ausgabe der Spielerinformationen auf der Konsole und zurück zur Startseite
    System.out.println(character.toString());
    return "redirect:/";
  }


}
