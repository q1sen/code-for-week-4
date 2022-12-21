//package klaeffer.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//import java.util.List;
//import java.util.Map;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//
//
//class KlaeffServiceTest {
//
//  @Test
//  @DisplayName("Der Service kann einen Klaeff speichern")
//  void test_1() {
//    KlaeffService service = new KlaeffService(profilRepository, klaeffRepository);
//    service.addKlaeff(mkUser(12, "Otto"), "Hallo!!!");
//    assertThat(service.getKlaeffs(0, 2).getKlaeffs())
//        .containsExactly(new KlaeffDetail("Otto", "Hallo!!!", 12));
//  }
//
//  private DefaultOAuth2User mkUser(Integer id, String name) {
//    return new DefaultOAuth2User(
//        List.of(),
//        Map.of("id", id,
//            "login", name),
//        "id");
//  }
//
//  @Test
//  @DisplayName("Der Service kann mehrere Klaeff speichern")
//  void test_2() {
//    KlaeffService service = new KlaeffService(profilRepository, klaeffRepository);
//    service.addKlaeff(mkUser(444, "Otto"), "Hallo!!!");
//    service.addKlaeff(mkUser(4544, "Willi"), "Selber Hallo!!!");
//
//    assertThat(service.getKlaeffs(0, 2).getKlaeffs())
//        .containsExactlyInAnyOrder(
//            new KlaeffDetail("Otto", "Hallo!!!", 444),
//            new KlaeffDetail("Willi", "Selber Hallo!!!", 4544));
//  }
//
//  @Test
//  @DisplayName("Die Resultate können paginiert werden")
//  void test_3() {
//    KlaeffService service = new KlaeffService(profilRepository, klaeffRepository);
//    for (int i = 0; i < 10; i++) {
//      service.addKlaeff(mkUser(i, "user" + i), "stuff" + i);
//    }
//    assertThat(service.getKlaeffs(0, 2).getKlaeffs().stream().map(KlaeffDetail::getName))
//        .containsExactlyInAnyOrder("user9", "user8");
//    assertThat(service.getKlaeffs(1, 2).getKlaeffs().stream().map(KlaeffDetail::getName))
//        .containsExactlyInAnyOrder("user7", "user6");
//  }
//
//  @Test
//  @DisplayName("Ein negativer Offset wird abgefangen")
//  void test_4() {
//    KlaeffService service = new KlaeffService(profilRepository, klaeffRepository);
//    assertThat(service.getKlaeffs(-1, 10).getKlaeffs()).isEmpty();
//  }
//
//  @Test
//  @DisplayName("Ein zu großer Start wird abgefangen")
//  void test_5() {
//    KlaeffService service = new KlaeffService(profilRepository, klaeffRepository);
//    service.addKlaeff(mkUser(4544, "Willi"), "Selber Hallo!!!");
//    assertThat(service.getKlaeffs(8, 10).getKlaeffs()).containsExactly(
//        new KlaeffDetail("Willi", "Selber Hallo!!!", 4544));
//  }
//
//  @Test
//  @DisplayName("Wenn die letzte Seite weniger als max Element enthält, weden nur diese Elemente angezeigt")
//  void test_6() {
//    KlaeffService service = new KlaeffService(profilRepository, klaeffRepository);
//    service.addKlaeff(mkUser(4544, "Willi"), "Selber Hallo!!!");
//    assertThat(service.getKlaeffs(8, 10).getKlaeffs()).containsExactly(
//        new KlaeffDetail("Willi", "Selber Hallo!!!", 4544));
//
//    for (int i = 0; i < 11; i++) {
//      service.addKlaeff(mkUser(i, "user" + i), "stuff" + i);
//    }
//    assertThat(service.getKlaeffs(1, 11).getKlaeffs()).containsExactlyInAnyOrder(
//        new KlaeffDetail("Willi", "Selber Hallo!!!", 4544));
//  }
//
//  @Test
//  @DisplayName("Kläffs werden in umgekehrter Einfügereihenfolge angezeigt")
//  void test_7() {
//    KlaeffService service = new KlaeffService(profilRepository, klaeffRepository);
//    service.addKlaeff(mkUser(1, "a"), "whatever");
//    service.addKlaeff(mkUser(2, "b"), "whatever");
//    service.addKlaeff(mkUser(3, "c"), "whatever");
//    assertThat(service.getKlaeffs(0, 3).getKlaeffs()
//        .stream()
//        .map(KlaeffDetail::getName).toList())
//        .containsExactly("c", "b", "a");
//  }
//
//  @Test
//  @DisplayName("Die User-Infos können aus dem Service abgefragt werden")
//  void test_8() {
//    KlaeffService service = new KlaeffService(profilRepository, klaeffRepository);
//    service.addKlaeff(mkUser(767676, "olaf"), "something something blah blah");
//    assertThat((String) service.userInfo(767676).getName()).isEqualTo("olaf");
//  }
//
//
//}