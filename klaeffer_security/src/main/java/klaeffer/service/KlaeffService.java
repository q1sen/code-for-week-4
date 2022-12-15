package klaeffer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class KlaeffService {
  private LinkedList<Klaeff> klaeffs =
//      randomKlaeffs(74);
      new LinkedList<>();

//  private List<Klaeff> randomKlaeffs(int n) {
//    ArrayList<Klaeff> result = new ArrayList<>();
//    for (int i = 0; i < n; i++) {
//      Klaeff klaeff = new Klaeff("user" + (i + 1), "content " + UUID.randomUUID());
//      result.add(klaeff);
//    }
//    return result;
//  }


  void add(Klaeff klaeff) {
    klaeffs.addFirst(klaeff);
  }

  public KlaeffPage getKlaeffs(int offset, int amount) {
    if (offset < 0) {
      offset = 0;
    }
    int start = offset * amount;
    while (start > klaeffs.size()) {
      start -= amount;
    }
    List<Klaeff> klaeffsOnPage = klaeffs.stream().skip(start).limit(amount).toList();
    boolean more = klaeffs.size() > start + amount;
    return new KlaeffPage(klaeffsOnPage, more);
  }

  public void addKlaeff(String name, String text,String profilSeite) {
    add(new Klaeff(name, text,profilSeite));
  }
}
