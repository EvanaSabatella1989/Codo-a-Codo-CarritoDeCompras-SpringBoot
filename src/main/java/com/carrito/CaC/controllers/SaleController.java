
package com.carrito.CaC.controllers;

import com.carrito.CaC.entities.Message;
import com.carrito.CaC.entities.Sale;
import com.carrito.CaC.services.SaleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }
    @GetMapping("/client")
    public ResponseEntity<List<Sale>> getByClient(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userName = userDetails.getUsername();
        return new ResponseEntity<>(this.saleService.getSalesByClient(userName), HttpStatus.OK);
    }
    @PostMapping(path = "/create")
    public ResponseEntity<Message> createSale(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
        String userName = userDetails.getUsername();
        this.saleService.createSale(userName);
        return new ResponseEntity<>(new Message("Compra exitosa"), HttpStatus.OK);
    }
}