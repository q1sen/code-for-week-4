package klaeffer.profil;

import org.springframework.stereotype.Service;

@Service
public class ProfilService {
    private ProfilRepo profilRepo;

    public ProfilService(ProfilRepo profilRepo) {
        this.profilRepo = profilRepo;
    }

    public void addProfil(Profil profil){
            profilRepo.save(profil);
    }

    public Profil getProfil(Long id){
        return profilRepo.findById(id).get();
    }
}
