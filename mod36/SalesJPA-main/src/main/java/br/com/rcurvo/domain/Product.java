package br.com.rcurvo.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUCT")
public class Product implements  Persistent{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="prod_seq")
    @SequenceGenerator(name="prod_seq", sequenceName="sq_product", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CODE", nullable = false, length = 10, unique = true)
    private Long code;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false, length = 50)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;



    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
