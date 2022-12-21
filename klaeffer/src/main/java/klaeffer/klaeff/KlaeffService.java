package klaeffer.klaeff;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class KlaeffService {
  private KlaeffRepo klaeffRepo;

  public KlaeffService(KlaeffRepo klaeffRepo) {
    this.klaeffRepo = klaeffRepo;
  }
//
//  private List<Klaeff> randomKlaeffs(int n) {
//    ArrayList<Klaeff> result = new ArrayList<>();
//    for (int i = 0; i < n; i++) {
//      Klaeff klaeff = new Klaeff("user" + (i + 1), "content " + UUID.randomUUID());
//      result.add(klaeff);
//    }
//    return result;
//  }


  public void add(Klaeff klaeff) {
      klaeffRepo.save(klaeff);
  }

  public KlaeffPage getKlaeffs(int offset, int amount) {
    if (offset < 0) {
      offset = 0;
    }
    List<Klaeff> klaeffs=klaeffRepo.findAll();
    int start = offset * amount;
    while (start > klaeffs.size()) {
      start -= amount;
    }
    List<Klaeff> klaeffsOnPage = klaeffs.stream().skip(start).limit(amount).toList();
    boolean more = klaeffs.size() > start + amount;
    return new KlaeffPage(klaeffsOnPage, more);
  }
  public List<Klaeff> getAll(){
    return klaeffRepo.findAll();
  }

  public void addKlaeff(String name, String content, Long userID) {
    add(new Klaeff( userID, name, content));
  }
}
