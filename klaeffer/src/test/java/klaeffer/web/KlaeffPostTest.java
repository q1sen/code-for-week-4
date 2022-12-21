//package klaeffer.web;
//
//import klaeffer.klaeff.KlaeffService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//
//@WebMvcTest
//public class KlaeffPostTest {
//
//  @Autowired
//  MockMvc mvc;
//
//  @MockBean
//  KlaeffService service;
//
//
//  @Test
//  @DisplayName("Ein valider Kläff wird hinzugefügt")
//  void test_1() throws Exception {
//    String text =
//        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque Id leo vitae lectus consectetur bibendum Id quis enim. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nunc ultrices odio eu leo convallis finibus. Fusce imperdiet elementum lorem vitae hendrerit. Aliquam erat dapibus nam";
//    assertThat(text.length()).isEqualTo(300);
//    mvc.perform(post("/")
//            .param("name", "Elon")
//            .param("text", text))
//        .andExpect(status().is3xxRedirection());
//    verify(service).addKlaeff("Elon", text);
//  }
//
//  @Test
//  @DisplayName("Zu lange Klaeffs werden nicht hinzugefügt")
//  void test_2a() throws Exception {
//    String text =
//        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque Id leo vitae lectus consectetur bibendum Id quis enim. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nunc ultrices odio eu leo convallis finibus. Fusce imperdiet elementum lorem vitae hendrerit. Aliquam erat dapibus nam!";
//    assertThat(text.length()).isEqualTo(301);
//    mvc.perform(post("/")
//        .param("name", "Elon")
//        .param("text",
//            text));
//    verify(service, never()).addKlaeff(anyString(), anyString());
//  }
//
//  @Test
//  @DisplayName("Leere Klaeffs werden nicht hinzugefügt")
//  void test_2b() throws Exception {
//    String text = "";
//    mvc.perform(post("/")
//        .param("name", "Elon")
//        .param("text",
//            text));
//    verify(service, never()).addKlaeff(anyString(), anyString());
//  }
//
//  @Test
//  @DisplayName("Ein Klaeff mit Namen über 39 Zeichen wird nicht hinzugefügt")
//    // 39 ist die maximale Länge von Namen auf GitHub
//  void test_3a() throws Exception {
//    String name = "1234567890123456789012345678901234567890";
//    mvc.perform(post("/")
//        .param("name", name)
//        .param("text",
//            "Welcome Hubert Blaine Wolfe+585 Sr."));
//    // see https://en.wikipedia.org/wiki/Hubert_Blaine_Wolfeschlegelsteinhausenbergerdorff_Sr.
//    verify(service, never()).addKlaeff(anyString(), anyString());
//  }
//
//  @Test
//  @DisplayName("Ein Klaeff mit leerem Namen wird nicht hinzugefügt")
//  void test_3b() throws Exception {
//    String text = "";
//    mvc.perform(post("/")
//        .param("name", "")
//        .param("text",
//            "No name, no post!"));
//    verify(service, never()).addKlaeff(anyString(), anyString());
//  }
//
//  @Test
//  @DisplayName("Nach einem gültigen Klaeff wird der Name in Flash-Attribute geschrieben")
//  void test_4() throws Exception {
//    String text = "";
//    mvc.perform(post("/")
//            .param("name", "Elon")
//            .param("text",
//                "Wow! So much better than Twitter!"))
//        .andExpect(flash().attribute("name", "Elon"));
//  }
//
//
//}
