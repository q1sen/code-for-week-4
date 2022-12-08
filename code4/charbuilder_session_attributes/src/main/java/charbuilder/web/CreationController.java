package charbuilder.web;

import charbuilder.character.CharacterClass;
import charbuilder.character.CharacterInfo;
import java.util.Random;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("character")
public class CreationController {


  @GetMapping()
  public String index() {
    return "start";
  }

  @GetMapping("/create/1")
  public String createNameForm() {
    return "name";
  }

  @PostMapping("/create/1")
  public String createStep1(@RequestParam("charactername") String charactername,Model model) {
    if (charactername == null || charactername.isBlank()) return "name";
    model.addAttribute("character",new CharacterInfo(charactername));
    return "redirect:/create/2";
  }


  @GetMapping("/create/2")
  public String createClassForm() {
    return "class";
  }

  @PostMapping("/create/2")
  public String createStep3(@ModelAttribute("character") CharacterInfo character,CharacterClass characterclass) {
    character.setCharacterClass(characterclass);
    return
        "redirect:/create/3";
  }

  @GetMapping("/create/3")
  public String createAttributeForm() {
    return "attributes";
  }

  @PostMapping("/create/3")
  public String createStep3(@RequestParam("Strength") int str,
                            @RequestParam("Dexterity") int dex,
                            @RequestParam("Constitution") int con,
                            @RequestParam("Intelligence") int intl,
                            @RequestParam("Wisdom") int wis,
                            @RequestParam("Charisma") int cha,
                            @ModelAttribute("character") CharacterInfo character) {
    // Character fertigstellen und validieren
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
