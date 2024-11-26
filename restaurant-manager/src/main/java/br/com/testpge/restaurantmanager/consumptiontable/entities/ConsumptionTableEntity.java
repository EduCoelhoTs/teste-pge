package br.com.testpge.restaurantmanager.consumptiontable.entities;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.testpge.restaurantmanager.consumptiontable.enums.ConsumptionTableStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "consumption_tables")
public class ConsumptionTableEntity implements Serializable {

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
    private ConsumptionTableStatus status;

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

    public ConsumptionTableStatus getStatus() {
        return status;
    }

    public void setStatus(ConsumptionTableStatus status) {
        this.status = status;
    }
}
