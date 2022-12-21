//package klaeffer.web;
//
//import klaeffer.config.WebSecurityConfiguration;
//import klaeffer.domain.shared.User;
//import klaeffer.domain.user.UserInfo;
//import klaeffer.helper.WithMockOAuth2User;
//import klaeffer.service.KlaeffService;
//import org.junit.jupiter.api.Nested;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//
//@Import({WebSecurityConfiguration.class})
//@WebMvcTest
//public class ProfilSeiteTest {
//
//  @Autowired
//  MockMvc mvc;
//
//  @MockBean
//  KlaeffService service;
//
//
//  @Nested
//  class MockedService {
//
//
//    @Test
//    @WithMockOAuth2User
//    @DisplayName("Die Profilseite ist aufrufbar")
//    void test_() throws Exception {
//      when(service.userInfo(2)).thenReturn(
//          new UserInfo(new User(2),
//              "LarryLipinsky",
//              "https://www.omdb.org/image/default/20652.jpeg?v=2"));
//
//      MvcResult result = mvc.perform(get("/profil")
//              .param("id", "2"))
//          .andExpect(status().isOk())
//          .andReturn();
//
//      String html = result.getResponse().getContentAsString();
//
//      assertThat(html.contains("<h1>LarryLipinsky</h1>"));
//      assertThat(html.contains("<img src=\"https://www.omdb.org/image/default/20652.jpeg?v=2\">"));
//    }
//  }
//
//
//}
