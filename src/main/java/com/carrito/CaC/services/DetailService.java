
package com.carrito.CaC.services;

import com.carrito.CaC.entities.Detail;
import com.carrito.CaC.repositories.DetailRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DetailService {

    private final DetailRepository detailRepository;

    @Autowired
    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }
    public void createDetail(Detail detail){
        this.detailRepository.save(detail);
    }
    public List<Detail> getDetailBySale(String saleId){
        return this.detailRepository.findBySale_Id(saleId);
    }
}
