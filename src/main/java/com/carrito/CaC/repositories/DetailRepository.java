
package com.carrito.CaC.repositories;

import com.carrito.CaC.entities.Detail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepository extends JpaRepository<Detail, String> {
    List<Detail> findBySale_Id(String saleId);
}
