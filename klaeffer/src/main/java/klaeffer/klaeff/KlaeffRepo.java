package klaeffer.klaeff;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KlaeffRepo extends CrudRepository<Klaeff,Long> {
    List<Klaeff> findAll();
}
