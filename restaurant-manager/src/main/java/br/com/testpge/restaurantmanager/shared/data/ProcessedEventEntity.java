package br.com.testpge.restaurantmanager.shared.data;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "processed_events")
public class ProcessedEventEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Column(name = "deleted_at", nullable = true)
    private OffsetDateTime deletedAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Column(nullable = false)
    public String payload;

    @Column(nullable = false)
    public OffsetDateTime timestamp;

    @Column(name = "event_topic", nullable = false)
    public String eventTopic;

    public ProcessedEventEntity(
            UUID id,
            String payload,
            OffsetDateTime timestamp,
            String eventTopic
    ) {
        this.id = id;
        this.payload = payload;
        this.timestamp = timestamp;
        this.eventTopic = eventTopic;
    }

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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getEventTopic() {
        return eventTopic;
    }

    public void setEventTopic(String eventTopic) {
        this.eventTopic = eventTopic;
    }
}
