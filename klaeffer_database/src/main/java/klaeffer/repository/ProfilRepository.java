package klaeffer.repository;

import klaeffer.domain.klaeff.Klaeff;
import klaeffer.domain.shared.User;
import klaeffer.domain.user.UserInfo;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
@EnableJdbcRepositories
public interface ProfilRepository extends CrudRepository<UserInfo,Long> {
    List<UserInfo> findAll();
}
