package br.com.rcurvo.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "TB_CAR")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
    @SequenceGenerator(name = "car_seq", sequenceName = "seq_car", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "Code", length = 255, nullable = false, unique = true)
    private String code;
    @Column(name = "Model", length = 50, nullable = false, unique = true)
    private String model;
    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "car")
    private List<Accessories> accessoriesList;

    public List<Accessories> getAccessoriesList() {
        return accessoriesList;
    }

    public void setAccessoriesList(List<Accessories> accessoriesList) {
        this.accessoriesList = accessoriesList;
    }

    @ManyToOne
    @JoinColumn(
            name = "id_brand_fk",
            foreignKey = @ForeignKey(name = "fk_brand_car"),
            referencedColumnName = "id", nullable = false
    )
    private Brand brand;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
}
