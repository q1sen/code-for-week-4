package klaeffer.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProfilService {
    private Map<String,Profil> userProfil=new HashMap<>();
    public void addProfil(Profil profil){
//        userProfil.putIfAbsent(profil.id(),profil); We cant just use that,cause maybe in the futher we will change profil with same ID.
        userProfil.putIfAbsent(profil.id(),profil);
        if (userProfil.get(profil.id())!=profil)
            userProfil.put(profil.id(), profil);
    }

    public Profil getProfil(String userId){
        return userProfil.get(userId);
    }
}
