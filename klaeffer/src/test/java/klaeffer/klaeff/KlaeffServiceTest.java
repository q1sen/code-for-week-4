package klaeffer.klaeff;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.query.Query;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJdbcTest
class KlaeffServiceTest {
    @Autowired
    KlaeffRepo klaeffRepo;



//  @Test
//  @DisplayName("Der Service kann einen Klaeff speichern")
//  void test_1() {
//    KlaeffService service = new KlaeffService(klaeffRepo);
//    Klaeff klaeff = new Klaeff((long)1,"qisen","hallo");
//    service.add(klaeff);
//    assertThat(service.getAll()).contains(new Klaeff((long)1,(long)1,"qisen","hallo"));
//  }

  @Test
  @DisplayName("Der Service kann mehrere Klaeff speichern")
  void test_2() {
    System.out.println(klaeffRepo.existsById((long)1));
    KlaeffService service = new KlaeffService(klaeffRepo);
    Klaeff klaeff1 = new Klaeff((long)1,"qisen","hallo");
    Klaeff klaeff2 = new Klaeff((long)2,"qisen","aloha");
    service.add(klaeff1);
    service.add(klaeff2);
    assertThat(service.getAll().get(0)).hasFieldOrPropertyWithValue("username","qisen");
    assertThat(service.getAll().get(0)).hasFieldOrPropertyWithValue("content","hallo");
    assertThat(service.getAll().get(1)).hasFieldOrPropertyWithValue("username","qisen");
    assertThat(service.getAll().get(1)).hasFieldOrPropertyWithValue("content","aloha");
  }

//  @Test
//  @DisplayName("Die Resultate können paginiert werden")
//  void test_3() {
//    KlaeffService service = new KlaeffService(klaeffRepo);
//    for (int i = 0; i < 10; i++) {
//      service.add(new Klaeff((long)i,"user" + i, "stuff" + i));
//    }
//    assertThat(service.getKlaeffs(0, 2).klaeffs())
//        .containsExactlyInAnyOrder(new Klaeff("user9", "stuff9"),
//            new Klaeff("user8", "stuff8"));
//    assertThat(service.getKlaeffs(1, 2).klaeffs())
//        .containsExactlyInAnyOrder(new Klaeff("user7", "stuff7"),
//            new Klaeff("user6", "stuff6"));
//  }
////
//  @Test
//  @DisplayName("Ein negativer Offset wird abgefangen")
//  void test_4() {
//    KlaeffService service = new KlaeffService();
//    assertThat(service.getKlaeffs(-1, 10).klaeffs()).isEmpty();
//  }
//
//  @Test
//  @DisplayName("Ein zu großer Start wird abgefangen")
//  void test_5() {
//    KlaeffService service = new KlaeffService();
//    Klaeff klaeff = new Klaeff("a", "b");
//    service.add(klaeff);
//    assertThat(service.getKlaeffs(8, 10).klaeffs()).containsExactly(klaeff);
//  }
//
//  @Test
//  @DisplayName("Wenn die letzte Seite weniger als max Element enthält, weden nur diese Elemente angezeigt")
//  void test_6() {
//    KlaeffService service = new KlaeffService();
//    Klaeff klaeff = new Klaeff("a", "b");
//    service.add(klaeff);
//    for (int i = 0; i < 11; i++) {
//      service.add(new Klaeff("user" + i, "stuff" + i));
//    }
//    assertThat(service.getKlaeffs(1, 11).klaeffs()).containsExactlyInAnyOrder(klaeff);
//  }
//
//  @Test
//  @DisplayName("Kläffs werden in umgekehrter Einfügereihenfolge angezeigt")
//  void test_7() {
//    KlaeffService service = new KlaeffService();
//    service.add(new Klaeff("a", "whatever"));
//    service.add(new Klaeff("b", "whatever"));
//    service.add(new Klaeff("c", "whatever"));
//    assertThat(service.getKlaeffs(0, 3).klaeffs()
//        .stream().map(Klaeff::userName).toList()).containsExactly("c", "b", "a");
//  }
//
//
}