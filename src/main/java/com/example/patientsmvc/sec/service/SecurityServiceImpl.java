package com.example.patientsmvc.sec.service;

import com.example.patientsmvc.sec.entities.AppRole;
import com.example.patientsmvc.sec.entities.AppUser;
import com.example.patientsmvc.sec.repositories.AppRoleRepository;
import com.example.patientsmvc.sec.repositories.AppUserRepository;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("Le mot de passe ne correspond pas");
        String hashedPWD=passwordEncoder.encode(password);
        AppUser appUser= new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole = appRoleRepository.findByRoleName(roleName);

        if(appRole != null) throw new RuntimeException("Ce Role est déjà existant!!!");
        appRole = new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole = appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
    AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser == null) throw new RuntimeException("Cet utilisateur n'existe pas!!");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole == null) throw new RuntimeException("Ce Role n'existe pas!!");

        appUser.getAppRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
    AppUser appUser=appUserRepository.findByUsername(username);
    if(appUser==null) throw  new RuntimeException("Cet utilisateur n'existe pas!!");
    AppRole appRole = appRoleRepository.findByRoleName(roleName);
    if(appRole == null) throw new RuntimeException("Ce Role n'existe pas!!");

        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
}
