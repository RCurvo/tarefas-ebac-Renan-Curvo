package br.com.rcurvo.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "TB_SALE")
public class Sale implements Persistent {

    public enum Status{
        STARTED, DONE, CANCELED;
    }

    public static Status getByName(String value){
        for (Status status : Status.values()){
            if(status.name().equals(value)){
                return status;
            }
        }
        return null;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="sale_seq")
    @SequenceGenerator(name="sale_seq", sequenceName="sq_sale", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "id_customer_fk",
            foreignKey = @ForeignKey(name = "fk_sale_customer"),
            referencedColumnName = "id", nullable = false
    )
    private Customer customer;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL/*, fetch = FetchType.EAGER*/)
    private Set<ProductQuantity> products;

    @Column(name = "TOTAL_SUM", nullable = false)
    private BigDecimal totalSum;

    @Column(name = "DATE_SALE", nullable = false)
    private Instant dateSale;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_SALE", nullable = false)
    private Status status;

    public Sale(){
        products = new HashSet<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<ProductQuantity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductQuantity> products) {
        this.products = products;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public Instant getDateSale() {
        return dateSale;
    }

    public void setDateSale(Instant dateSale) {
        this.dateSale = dateSale;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    private void validateStatus() {
        if (this.status == Status.DONE) {
            throw new UnsupportedOperationException("IMPOSSÍVEL ALTERAR VENDA FINALIZADA");
        }
    }

    public void addProduct(Product product, Integer quantity) {
        validateStatus();
        Optional<ProductQuantity> op =
                products.stream().filter(filter -> filter.getProduct().getCode().equals(product.getCode())).findAny();
        if (op.isPresent()) {
            ProductQuantity prodQuan = op.get();
            prodQuan.addQuantity(quantity);
        } else {
            ProductQuantity prod = new ProductQuantity();
            prod.setSale(this);
            prod.setProduct(product);
            prod.addQuantity(quantity);
            products.add(prod);
        }
        recalculateSaleTotal();
    }

    public void removeProduct(Product product, Integer quantity) {
        validateStatus();
        Optional<ProductQuantity> op =
                products.stream().filter(filter -> filter.getProduct().getCode().equals(product.getCode())).findAny();

        if (op.isPresent()) {
            ProductQuantity productQt = op.get();
            if (productQt.getQuantity()>quantity) {
                productQt.remove(quantity);
                recalculateSaleTotal();
            } else {
                products.remove(op.get());
                recalculateSaleTotal();
            }

        }
    }

    public void removeAllProducts() {
        validateStatus();
        products.clear();
        totalSum = BigDecimal.ZERO;
    }

    public void recalculateSaleTotal() {
        BigDecimal totalSum = BigDecimal.ZERO;
        for (ProductQuantity prod : this.products) {
            totalSum = totalSum.add(prod.getTotalSum());
        }
        this.totalSum = totalSum;
    }

    public Integer getProductsQuantity(){
        int result = products.stream()
                .reduce(0,(partialResult, prod) -> partialResult + prod.getQuantity(),Integer::sum);
        return result;
    }
}
