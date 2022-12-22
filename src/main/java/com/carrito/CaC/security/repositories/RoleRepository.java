package com.carrito.CaC.security.repositories;

import com.carrito.CaC.security.entities.Role;
import com.carrito.CaC.security.enums.RoleList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role, Integer> {
    Optional<Role> findByRoleName(RoleList roleName);
    
}
