
package com.carrito.CaC.security.entities;

import com.carrito.CaC.security.enums.RoleList;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleList roleName;
    public Role() {
    }
    public Role(@NotNull RoleList roleName) {
        this.roleName = roleName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public RoleList getRoleName() {
        return roleName;
    }
    public void setRoleName(RoleList roleName) {
        this.roleName = roleName;
    }
    
}
