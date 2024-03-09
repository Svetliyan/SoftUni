package entities.ex2_SalesDatabase;

import entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private int quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    private Set<Sale> sales;
}
