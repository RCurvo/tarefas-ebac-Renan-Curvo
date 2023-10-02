package br.com.rcurvo.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_BRAND")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_seq")
    @SequenceGenerator(name = "brand_seq", sequenceName = "seq_brand", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "Code", length = 255, nullable = false, unique = true)
    private String code;
    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;
    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @OneToMany(mappedBy = "brand")
    private List<Car> carList;

    @OneToMany(mappedBy = "brand")
    private List<Accessories> accessoriesList;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public List<Accessories> getAccessoriesList() {
        return accessoriesList;
    }

    public void setAccessoriesList(List<Accessories> accessoriesList) {
        this.accessoriesList = accessoriesList;
    }
}
