
package com.carrito.CaC.repositories;

import com.carrito.CaC.entities.ShoppingCart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {
    List<ShoppingCart> findByClient_Id(String clientId);
    List<ShoppingCart> findByClient_UserName(String clientEmail);
    void deleteByClient_Id(String clientId);
    Long countByClient_Id(String id);
}
