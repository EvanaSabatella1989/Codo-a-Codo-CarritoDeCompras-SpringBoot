
package com.carrito.CaC.entities;

import com.carrito.CaC.security.entities.User;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Getter @Setter
    private String id;
    @NotNull
    @Getter @Setter
    private Double total;
    @NotNull
    @Getter @Setter
    @Column(columnDefinition = "DATE")
    private Date date;
@ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @Getter @Setter
    private User client;

    public Sale(double total, Date date, User client) {
        this.total = total;
        this.date = date;
        this.client = client;
    }
}
