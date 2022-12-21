package klaeffer.profil;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfilRepo extends CrudRepository<Profil,Long> {
    List<Profil> findAll();
}
