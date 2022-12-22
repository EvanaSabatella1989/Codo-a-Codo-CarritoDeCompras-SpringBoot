
package com.carrito.CaC.repositories;

import com.carrito.CaC.entities.Sale;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,String> {
    List<Sale> findByClient_UserName(String userName);
}
