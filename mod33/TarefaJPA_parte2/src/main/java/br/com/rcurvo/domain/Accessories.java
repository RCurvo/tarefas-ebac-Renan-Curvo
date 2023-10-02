package br.com.rcurvo.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_ACCESSORIES")
public class Accessories {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_seq")
    @SequenceGenerator(name = "acc_seq", sequenceName = "seq_acc", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "Code", length = 255, nullable = false, unique = true)
    private String code;
    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;
    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(
            name = "id_brand_fk",
            foreignKey = @ForeignKey(name = "fk_brand_acc"),
            referencedColumnName = "id", nullable = false
    )
    private Brand brand;

    @ManyToOne
    @JoinColumn(
            name = "id_car_fk",
            foreignKey = @ForeignKey(name = "fk_car_acc"),
            referencedColumnName = "id", nullable = false
    )
    private Car car;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
