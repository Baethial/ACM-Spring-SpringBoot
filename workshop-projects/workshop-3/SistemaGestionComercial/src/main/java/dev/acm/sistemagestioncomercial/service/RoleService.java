package dev.acm.sistemagestioncomercial.service;

import dev.acm.sistemagestioncomercial.entity.RoleEntity;
import dev.acm.sistemagestioncomercial.persistence.RoleRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleEntity getRoleById(Long id){
        return roleRepository.findById(id).orElse(null);
    }

    public List<RoleEntity> getAllRoles(){
        return roleRepository.findAll();
    }

    public RoleEntity saveRole(RoleEntity roleEntity){
        return roleRepository.save(roleEntity);
    }

    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }

    public RoleEntity updateRole(RoleEntity roleEntity){
        return roleRepository.save(roleEntity);
    }
}
