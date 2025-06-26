package org.example.lafiresversin2.sirene.sireneentity;


import jakarta.persistence.*;
import org.example.lafiresversin2.fire.fireentity.Fire;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Sirene {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long sireneId;

    private double latitude;
    private double longitude;

    @Enumerated(EnumType.STRING)
    private SirenStatus status; //enum

    @ManyToMany(mappedBy = "sirenes")
    private Set <Fire> fires = new HashSet<>();


    public Sirene() {
    }

    //for init data testing
    public Sirene(long sireneId, double latitude, double longitude, SirenStatus status, Set<Fire> fires) {
        this.sireneId = sireneId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.fires = fires;
    }

    public long getSireneId() {
        return sireneId;
    }

    public void setSireneId(long sireneId) {
        this.sireneId = sireneId;
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

    public SirenStatus getStatus() {
        return status;
    }

    public void setStatus(SirenStatus status) {
        this.status = status;
    }

    public Set<Fire> getFires() {
        return fires;
    }

    public void setFires(Set<Fire> fires) {
        this.fires = fires;
    }
}
