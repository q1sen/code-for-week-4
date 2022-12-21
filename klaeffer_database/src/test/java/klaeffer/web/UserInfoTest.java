//package klaeffer.web;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//import java.util.List;
//import java.util.Map;
//import klaeffer.service.KlaeffService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//
//
//public class UserInfoTest {
//
//
//  @Test
//  @DisplayName("Die Userinfos werden aus dem Token extrahiert")
//  void test_() {
//    DefaultOAuth2User user = new DefaultOAuth2User(List.of(),
//        Map.of("id", 2,
//            "login", "LarryLipinsky",
//            "avatar_url", "https://www.omdb.org/image/default/20652.jpeg?v=2")
//        , "id");
//
//    KlaeffService service = new KlaeffService(profilRepository, klaeffRepository);
//    service.addKlaeff(user, "*sniff* Müller?!?");
//    assertThat(service.userInfo(2).getImage()).isEqualTo(
//        "https://www.omdb.org/image/default/20652.jpeg?v=2");
//  }
//
//}
