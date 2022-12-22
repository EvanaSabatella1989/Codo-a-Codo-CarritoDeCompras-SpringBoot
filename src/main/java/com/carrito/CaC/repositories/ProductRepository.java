
package com.carrito.CaC.repositories;

import com.carrito.CaC.entities.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findByCategoryAndIdNot(String category, String ProductId);
    List<Product> findFirst4ByOrderByPriceAsc();
}
