package com.hulk.store.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "movements")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false, foreignKey = @ForeignKey(name = "FK_movement_product"))
    //@JoinColumn(name = "id_product")//,insertable = false, updatable = false)
    private Product product;

    private Integer amount;

    private BigDecimal totalValue;

    private String description;

    @Enumerated(EnumType.STRING)
    private MovementMethod method;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private Date createdAt;

    public Movement() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idMovenment) {
        this.id = idMovenment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getValue() {
        return totalValue;
    }

    public void setValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public MovementMethod getMethod() {
        return method;
    }

    public void setMethod(MovementMethod method) {
        this.method = method;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
