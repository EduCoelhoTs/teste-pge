package br.com.testpge.order.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.testpge.order.enums.OrderItemType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chart_of_account_sub_categories")
public class OrderItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    protected UUID id;

    @Column(name = "deleted_at", nullable = true)
    protected OffsetDateTime deletedAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = true, updatable = false)
    protected OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true)
    protected OffsetDateTime updatedAt;

    @Column(name = "deleted_by", nullable = true)
    protected String deletedBy;

    @Column(name = "is_deleted", nullable = false)
    protected boolean isDeleted = false;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private OrderItemType type;

    @Column(nullable = false)
    private Double price;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OffsetDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(OffsetDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
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

    public OrderItemType getType() {
        return type;
    }

    public void setType(OrderItemType type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        final DecimalFormat df = new DecimalFormat("#.00", DecimalFormatSymbols.getInstance(Locale.US));
        final String formatted = df.format(price);

        this.price = Double.valueOf(formatted);
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        if (order == null) {
            this.order = null;

            return;
        }

        order.getOrderItens().add(this);
        this.order = order;
    }
}
