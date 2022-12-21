package klaeffer.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import klaeffer.config.WebSecurityConfiguration;
import klaeffer.helper.WithMockOAuth2User;
import klaeffer.service.KlaeffService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest
@Import(WebSecurityConfiguration.class)
public class KlaeffPostTest {

  @Autowired
  MockMvc mvc;

  @MockBean
  KlaeffService service;


  @Test
  @WithMockOAuth2User(login = "Elon")
  @DisplayName("Ein valider Kläff wird hinzugefügt")
  void test_1() throws Exception {
    String text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque id leo vitae lectus consectetur bibendum id quis enim. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nunc ultrices odio eu leo convallis finibus. Fusce imperdiet elementum lorem vitae hendrerit. Aliquam erat dapibus nam";
    assertThat(text.length()).isEqualTo(300);
    mvc.perform(post("/")
            .param("text", text)
            .with(csrf()))
        .andExpect(status().is3xxRedirection());
    ArgumentCaptor<OAuth2User> captor = ArgumentCaptor.forClass(OAuth2User.class);
    verify(service).addKlaeff(captor.capture(), eq(text));
    assertThat((String) captor.getValue().getAttribute("login")).isEqualTo("Elon");
  }

  @Test
  @WithMockOAuth2User
  @DisplayName("Zu lange Klaeffs werden nicht hinzugefügt")
  void test_2a() throws Exception {
    String text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque id leo vitae lectus consectetur bibendum id quis enim. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nunc ultrices odio eu leo convallis finibus. Fusce imperdiet elementum lorem vitae hendrerit. Aliquam erat dapibus nam!";
    assertThat(text.length()).isEqualTo(301);
    mvc.perform(post("/")
        .param("text",
            text));
    verify(service, never()).addKlaeff(any(), anyString());
  }

  @Test
  @WithMockOAuth2User
  @DisplayName("Leere Klaeffs werden nicht hinzugefügt")
  void test_2b() throws Exception {
    String text = "";
    mvc.perform(post("/")
        .param("text",
            text));
    verify(service, never()).addKlaeff(any(), anyString());
  }

  @Test
  @WithMockOAuth2User(login = "1234567890123456789012345678901234567890")
  @DisplayName("Ein Klaeff mit Namen über 39 Zeichen wird nicht hinzugefügt")
    // 39 ist die maximale Länge von Namen auf GitHub
  void test_3a() throws Exception {
    mvc.perform(post("/")
        .param("text",
            "Welcome Hubert Blaine Wolfe+585 Sr."));
    // see https://en.wikipedia.org/wiki/Hubert_Blaine_Wolfeschlegelsteinhausenbergerdorff_Sr.
    verify(service, never()).addKlaeff(any(), anyString());
  }

  @Test
  @WithMockOAuth2User(login = "")
  @DisplayName("Ein Klaeff mit leerem Namen wird nicht hinzugefügt")
  void test_3b() throws Exception {
    mvc.perform(post("/")
        .param("text",
            "No name, no post!"));
    verify(service, never()).addKlaeff(any(), anyString());
  }


}
