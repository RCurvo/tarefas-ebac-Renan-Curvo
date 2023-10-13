package br.com.rcurvo.domain;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUCT_QUANTITY")
public class ProductQuantity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="prod_qt_seq")
    @SequenceGenerator(name="prod_qt_seq", sequenceName="sq_prod_qt", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total_sum", nullable = false)
    private BigDecimal totalSum;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sale_fk",
            foreignKey = @ForeignKey(name = "fk_prod_qtd_sale"),
            referencedColumnName = "id", nullable = false
    )
    private Sale sale;

    public ProductQuantity() {
        this.quantity = 0;
        this.totalSum = BigDecimal.ZERO;
    }

    public void addQuantity(Integer quantity) {
        this.quantity += quantity;
        BigDecimal newValue = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
        BigDecimal newTotal = this.totalSum.add(newValue);
        this.totalSum = newTotal;
    }

    public void remove(Integer quantity) {
        this.quantity -= quantity;
        BigDecimal newValue = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
        this.totalSum = this.totalSum.subtract(newValue);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
