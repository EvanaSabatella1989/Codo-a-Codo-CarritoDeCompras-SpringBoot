package com.carrito.CaC.security.services;

import com.carrito.CaC.security.entities.Role;
import com.carrito.CaC.security.enums.RoleList;
import com.carrito.CaC.security.repositories.RoleRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Optional<Role> getByRoleName(RoleList roleName){
        return roleRepository.findByRoleName(roleName);
    }
    
    
}
