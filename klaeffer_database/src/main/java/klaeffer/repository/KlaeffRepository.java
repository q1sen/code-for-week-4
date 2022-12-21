package klaeffer.repository;

import klaeffer.domain.klaeff.Klaeff;
import klaeffer.domain.shared.User;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
@EnableJdbcRepositories
public interface KlaeffRepository extends CrudRepository<Klaeff,Long> {
    List<Klaeff> findAll();
}
