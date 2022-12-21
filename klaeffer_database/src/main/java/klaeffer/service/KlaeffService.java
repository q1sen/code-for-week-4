package klaeffer.service;

import java.util.List;
import java.util.Objects;

import klaeffer.domain.klaeff.Content;
import klaeffer.domain.klaeff.Klaeff;
import klaeffer.domain.shared.User;
import klaeffer.domain.user.UserInfo;
import klaeffer.domain.user.UserName;
import klaeffer.repository.KlaeffRepository;
import klaeffer.repository.ProfilRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class KlaeffService {
  private final ProfilRepository profilRepository;
  private final KlaeffRepository klaeffRepository;

  public KlaeffService(ProfilRepository profilRepository, KlaeffRepository klaeffRepository) {
    this.profilRepository = profilRepository;
    this.klaeffRepository = klaeffRepository;
  }


  public KlaeffPage getKlaeffs(int offset, int amount) {
    List<Klaeff> klaeffList=klaeffRepository.findAll();
    if (offset < 0) {
      offset = 0;
    }
    int start = offset * amount;
    while (start > klaeffList.size()) {
      start -= amount;
    }
    List<KlaeffDetail> klaeffsOnPage = klaeffList.stream()
        .skip(start)
        .limit(amount)
        .map(this::toDetail)
        .toList();
    boolean more = klaeffList.size() > start + amount;
    return new KlaeffPage(klaeffsOnPage, more);
  }

  private KlaeffDetail toDetail(Klaeff klaeff) {
    Long id = klaeff.getUser().getId();
    UserInfo userInfo = userInfo(id);
    return new KlaeffDetail(userInfo.getName(), klaeff.getContent(), id);
  }

  public UserInfo userInfo(Long id) {
    return profilRepository.findAll().stream().filter(userInfo -> userInfo.getUser().getId().equals(id))
            .findFirst().get();
  }

  public void addKlaeff(OAuth2User principal, String text) {
    String login = principal.getAttribute("login");
    Integer id = Objects.requireNonNull(principal.getAttribute("id"));
    String image = principal.getAttribute("avatar_url");
    UserInfo userIn=profilRepository.save(
            new UserInfo(
                null,
                    new User((long)id),
                    new UserName(login),
            image));
    klaeffRepository.save(new Klaeff(null,new User((long)id), new Content(text)));
  }
}
