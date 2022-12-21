package klaeffer.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import klaeffer.klaeff.Klaeff;
import klaeffer.klaeff.KlaeffPage;
import klaeffer.klaeff.KlaeffService;
import klaeffer.profil.ProfilService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.Model;


public class KlaeffAnzeigeTest {

  @Nested
  @WebMvcTest
  class MvcTests {

    // TODO: Enable Test
    // TODO: Refactor default Setup

    @Autowired
    MockMvc mvc;

    @MockBean
    KlaeffService service;

    @Nested
    class DefaultSetup {

      @BeforeEach
      void setup() {
        when(service.getKlaeffs(anyInt(), anyInt())).thenReturn(new KlaeffPage(randomKlaeffs(200),
            true));
      }

      @Test
      @DisplayName("Die Startseite ist erreichbar")
      void test_1() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
      }

      @Test
      @DisplayName("Wenn keine Grenze spezifiziert ist, werden 10 Kläffs angezeigt")
      void test_3a() throws Exception {
        MvcResult result = mvc.perform(get("/")).andReturn();
        verify(service).getKlaeffs(0, 10);
      }

      @Test
      @DisplayName("Wenn eine Grenze von 17 spezifiziert ist, werden 17e Kläffs angezeigt")
      void test_3b() throws Exception {
        MvcResult result = mvc.perform(get("/").param("max", "17")).andReturn();
        verify(service).getKlaeffs(0, 17);
      }

      @Test
      @DisplayName("Wenn kein Offset spezifiziert ist, wird die erste Seite angezeigt")
      void test_4a() throws Exception {
        MvcResult result = mvc.perform(get("/")).andReturn();
        verify(service).getKlaeffs(0, 10);
      }

      @Test
      @DisplayName("Wenn ein Offset von 3 spezifiziert ist, wird die 3. (index=2) Seite angezeigt")
      void test_4b() throws Exception {
        MvcResult result = mvc.perform(get("/").param("page", "3")).andReturn();
        verify(service).getKlaeffs(2, 10);
      }

      @Test
      @DisplayName("Wenn page höher ist als 1, gibt es einen Link zurück")
      void test_6() throws Exception {
        MvcResult result = mvc.perform(get("/").param("page", "2")).andReturn();
        assertThat(result.getResponse().getContentAsString()).contains("page=1");
      }

    }

    @Test
    @DisplayName("Klaeffs werden auf der Seite angezeigt")
    void test_2() throws Exception {
      when(service.getKlaeffs(anyInt(), anyInt()))
          .thenReturn(new KlaeffPage(List.of(
              new Klaeff((long)1,"aUser", "theklaeff is here"),
              new Klaeff((long)1,"anotherUser", "the second klaeff is also here")), false));
      MvcResult result = mvc.perform(get("/")).andReturn();
      assertThat(result.getResponse().getContentAsString()).contains("aUser");
      assertThat(result.getResponse().getContentAsString()).contains("anotherUser");
      assertThat(result.getResponse().getContentAsString()).contains("theklaeff is here");
      assertThat(result.getResponse().getContentAsString()).contains(
          "the second klaeff is also here");
    }

    @Test
    @DisplayName("Wenn es weitere klaeffs gibt, wird ein Link angezeigt")
    void test_5() throws Exception {
      when(service.getKlaeffs(anyInt(), anyInt()))
          .thenReturn(new KlaeffPage(randomKlaeffs(20), true));
      MvcResult result = mvc.perform(get("/")).andReturn();
      assertThat(result.getResponse().getContentAsString()).contains("page=2");
    }

    @Test
    @DisplayName("Das Eingabeformular für Kläffs wird angezeigt")
    void test_6() throws Exception {
      when(service.getKlaeffs(anyInt(), anyInt()))
          .thenReturn(new KlaeffPage(randomKlaeffs(20), true));
      MvcResult result = mvc.perform(get("/")).andReturn();
      String html = result.getResponse().getContentAsString();
      assertThat(html).contains("<form method=\"post\" action=\"/\">");
      assertThat(html).contains("<input Id=\"nameid\" type=\"content\" name=\"name\" value=\"\">");
      assertThat(html).contains("<input Id=\"textid\" type=\"content\" name=\"content\">");
    }

    @Test
    @DisplayName("Wenn ein Name im Model ist, wird der übernommen")
    void test_7() throws Exception {
      when(service.getKlaeffs(anyInt(), anyInt()))
          .thenReturn(new KlaeffPage(randomKlaeffs(20), true));
      MvcResult result = mvc.perform(get("/").flashAttr("name", "elonmusk")).andReturn();
      String html = result.getResponse().getContentAsString();
      assertThat(html).contains(
          "<input Id=\"nameid\" type=\"content\" name=\"name\" value=\"elonmusk\">");
    }


  }

  private List<Klaeff> randomKlaeffs(int n) {
    ArrayList<Klaeff> result = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      Klaeff klaeff = new Klaeff((long)1,"user" + (i + 1), "content " + UUID.randomUUID());
      result.add(klaeff);
    }
    return result;
  }


  @Test
  @DisplayName("Klaeffs werden aus dem Service geholt")
  void test_3() throws Exception {
    KlaeffService service = mock(KlaeffService.class);
    ProfilService profilService=mock(ProfilService.class);
    OAuth2AuthenticationToken token=mock(OAuth2AuthenticationToken.class);
    when(token.getPrincipal().getAttribute(any())).thenReturn("hi");
    KlaeffController controller = new KlaeffController(service,profilService);
    controller.index(mock(Model.class), 1, 22,token);
    verify(service).getKlaeffs(0, 22);
  }


}
