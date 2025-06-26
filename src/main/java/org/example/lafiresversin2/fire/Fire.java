package org.example.lafiresversin2.fire;


import jakarta.persistence.*;
import org.example.lafiresversin2.sirene.sireneentity.Sirene;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Fire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fireId;

    private double latitude;
    private double longitude;

    private LocalDateTime timestamp; //To do statisstiks later on

    private boolean closed;

    @ManyToMany
    @JoinTable(
            name = "fire_sirene",
            joinColumns = @JoinColumn(name = "fire_id"),
            inverseJoinColumns = @JoinColumn(name = "sirene_id")
    )
    private Set<Sirene> sirenes = new HashSet<>();

    public Fire() {
    }

    public Fire(Long fireId, double latitude,
                double longitude, LocalDateTime timestamp,
                boolean closed, Set<Sirene> sirenes) {
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

    public Set<Sirene> getSirenes() {
        return sirenes;
    }

    public void setSirenes(Set<Sirene> sirenes) {
        this.sirenes = sirenes;
    }
}
