package br.com.testpge.order.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.testpge.order.enums.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity implements Serializable {

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

    @Column(name = "consumption_table_id", nullable = false)
    protected String consumptionTableId;

    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private Double total;

    @OneToMany(
            mappedBy = "order",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItemEntity> orderItens = new ArrayList<>();

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

    public String getConsumptionTableId() {
        return consumptionTableId;
    }

    public void setConsumptionTableId(String consumptionTableId) {
        this.consumptionTableId = consumptionTableId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        final DecimalFormat df = new DecimalFormat("#.00", DecimalFormatSymbols.getInstance(Locale.US));
        final String formatted = df.format(total);

        this.total = Double.valueOf(formatted);
    }

    public List<OrderItemEntity> getOrderItens() {
        return this.orderItens;
    }

    public void setOrderItens(Set<OrderItemEntity> orderItens) {
        if (orderItens == null || orderItens.isEmpty()) {
            this.orderItens = new ArrayList<>();

            return;
        }

        this.orderItens = orderItens
                .stream()
                .map(orderItem -> {
                    orderItem.setOrder(this);
                    return orderItem;
                })
                .toList();
    }
}
