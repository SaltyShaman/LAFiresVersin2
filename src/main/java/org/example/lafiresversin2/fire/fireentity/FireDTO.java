package org.example.lafiresversin2.fire.fireentity;

import org.example.lafiresversin2.sirene.sireneentity.SireneDTO;

import java.time.LocalDateTime;
import java.util.Set;

public class FireDTO {

    private Long fireId;
    private double latitude;
    private double longitude;
    private LocalDateTime timestamp;
    private boolean closed;
    private Set<SireneDTO> sirenes;

    public FireDTO() {
    }

    public FireDTO(Long fireId, double latitude, double longitude,
                   LocalDateTime timestamp, boolean closed,
                   Set<SireneDTO> sirenes) {
        this.fireId = fireId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.closed = closed;
        this.sirenes = sirenes;
    }

    public Long getFireId() {
        return fireId;
    }

    public void setFireId(Long fireId) {
        this.fireId = fireId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Set<SireneDTO> getSirenes() {
        return sirenes;
    }

    public void setSirenes(Set<SireneDTO> sirenes) {
        this.sirenes = sirenes;
    }
}
