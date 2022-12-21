//package klaeffer.web;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//import klaeffer.config.WebSecurityConfiguration;
//import klaeffer.domain.klaeff.Klaeff;
//import klaeffer.domain.shared.User;
//import klaeffer.helper.WithMockOAuth2User;
//import klaeffer.service.KlaeffDetail;
//import klaeffer.service.KlaeffPage;
//import klaeffer.service.KlaeffService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.ui.Model;
//
//
//public class KlaeffAnzeigeTest {
//
//  @Nested
//  @Import({WebSecurityConfiguration.class})
//  @WebMvcTest
//  class UserInfoTests {
//
//    @Autowired
//    MockMvc mvc;
//
//    @MockBean
//    KlaeffService service;
//
//    @Test
//    @WithMockOAuth2User(id = 222222, login = " Erwin_Lindemann")
//    @DisplayName("Kläffs enthalten einen Link auf das Profil")
//    void test_6() throws Exception {
//      when(service.getKlaeffs(anyInt(), anyInt()))
//          .thenReturn(new KlaeffPage(randomKlaeffs(20), true));
//      MvcResult result = mvc.perform(get("/")).andReturn();
//      String html = result.getResponse().getContentAsString();
//      assertThat(html).contains("<form method=\"post\" action=\"/\">");
//      assertThat(html).contains("<input id=\"textid\" type=\"text\" name=\"text\">");
//    }
//
//
//  }
//
//  @Nested
//  @Import(WebSecurityConfiguration.class)
//  @WithMockOAuth2User
//  @WebMvcTest
//  class MvcTests {
//
//    // TODO: Enable Test
//    // TODO: Refactor default Setup
//
//    @Autowired
//    MockMvc mvc;
//
//    @MockBean
//    KlaeffService service;
//
//    @Nested
//    class DefaultSetup {
//
//      @BeforeEach
//      void setup() {
//        when(service.getKlaeffs(anyInt(), anyInt())).thenReturn(new KlaeffPage(randomKlaeffs(200),
//            true));
//      }
//
//      @Test
//      @DisplayName("Die Startseite ist erreichbar")
//      void test_1() throws Exception {
//        mvc.perform(get("/")).andExpect(status().isOk());
//      }
//
//      @Test
//      @DisplayName("Wenn keine Grenze spezifiziert ist, werden 10 Kläffs angezeigt")
//      void test_3a() throws Exception {
//        MvcResult result = mvc.perform(get("/")).andReturn();
//        verify(service).getKlaeffs(0, 10);
//      }
//
//      @Test
//      @DisplayName("Wenn eine Grenze von 17 spezifiziert ist, werden 17e Kläffs angezeigt")
//      void test_3b() throws Exception {
//        MvcResult result = mvc.perform(get("/").param("max", "17")).andReturn();
//        verify(service).getKlaeffs(0, 17);
//      }
//
//      @Test
//      @DisplayName("Wenn kein Offset spezifiziert ist, wird die erste Seite angezeigt")
//      void test_4a() throws Exception {
//        MvcResult result = mvc.perform(get("/")).andReturn();
//        verify(service).getKlaeffs(0, 10);
//      }
//
//      @Test
//      @DisplayName("Wenn ein Offset von 3 spezifiziert ist, wird die 3. (index=2) Seite angezeigt")
//      void test_4b() throws Exception {
//        MvcResult result = mvc.perform(get("/").param("page", "3")).andReturn();
//        verify(service).getKlaeffs(2, 10);
//      }
//
//      @Test
//      @DisplayName("Wenn page höher ist als 1, gibt es einen Link zurück")
//      void test_6() throws Exception {
//        MvcResult result = mvc.perform(get("/").param("page", "2")).andReturn();
//        assertThat(result.getResponse().getContentAsString()).contains("page=1");
//      }
//
//    }
//
//    @Test
//    @DisplayName("Klaeffs werden auf der Seite angezeigt")
//    void test_2() throws Exception {
//      when(service.getKlaeffs(anyInt(), anyInt()))
//          .thenReturn(new KlaeffPage(List.of(
//              new KlaeffDetail("aUser", "theklaeff is here", 22),
//              new KlaeffDetail("anotherUser", "the second klaeff is also here", 45)), false));
//      MvcResult result = mvc.perform(get("/")).andReturn();
//      assertThat(result.getResponse().getContentAsString()).contains("aUser");
//      assertThat(result.getResponse().getContentAsString()).contains("anotherUser");
//      assertThat(result.getResponse().getContentAsString()).contains("theklaeff is here");
//      assertThat(result.getResponse().getContentAsString()).contains(
//          "the second klaeff is also here");
//    }
//
//    @Test
//    @DisplayName("Wenn es weitere klaeffs gibt, wird ein Link angezeigt")
//    void test_5() throws Exception {
//      when(service.getKlaeffs(anyInt(), anyInt()))
//          .thenReturn(new KlaeffPage(randomKlaeffs(20), true));
//      MvcResult result = mvc.perform(get("/")).andReturn();
//      assertThat(result.getResponse().getContentAsString()).contains("page=2");
//    }
//
//    @Test
//    @DisplayName("Das Eingabeformular für Kläffs wird angezeigt")
//    void test_6() throws Exception {
//      when(service.getKlaeffs(anyInt(), anyInt()))
//          .thenReturn(new KlaeffPage(randomKlaeffs(20), true));
//      MvcResult result = mvc.perform(get("/")).andReturn();
//      String html = result.getResponse().getContentAsString();
//      assertThat(html).contains("<form method=\"post\" action=\"/\">");
//      assertThat(html).contains("<input id=\"textid\" type=\"text\" name=\"text\">");
//    }
//
//    @Test
//    @DisplayName("Die Userprofile sind verlinkt")
//    void test_7() throws Exception {
//      when(service.getKlaeffs(anyInt(), anyInt()))
//          .thenReturn(new KlaeffPage(List.of(new KlaeffDetail("Jens", "Hallo", 223344)), false));
//      MvcResult result = mvc.perform(get("/")).andReturn();
//      String html = result.getResponse().getContentAsString();
//      assertThat(html).contains("<a href=\"/profil?id=223344\">Jens</a>");
//    }
//
//
//  }
//
//  private List<KlaeffDetail> randomKlaeffs(int n) {
//    ArrayList<KlaeffDetail> result = new ArrayList<>();
//    for (int i = 0; i < n; i++) {
//      KlaeffDetail klaeff = new KlaeffDetail("user" + i, "content " + UUID.randomUUID(), i);
//      result.add(klaeff);
//    }
//    return result;
//  }
//
//
//  @Test
//  @DisplayName("Klaeffs werden aus dem Service geholt")
//  void test_3() throws Exception {
//    KlaeffService service = mock(KlaeffService.class);
//    KlaeffController controller = new KlaeffController(service);
//    controller.index(mock(Model.class), 1, 22);
//    verify(service).getKlaeffs(0, 22);
//  }
//
//
//}
