package org.example.lafiresversin2.sirene.sireneentity;

import java.util.Set;

public class SireneDTO {

    private long sireneId;
    private double latitude;
    private double longitude;
    private SirenStatus status;
    private Set<Long> fireIds;

    public SireneDTO() {
    }

    public SireneDTO(long sireneId, double latitude, double longitude, SirenStatus status, Set<Long> fireIds) {
        this.sireneId = sireneId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.fireIds = fireIds;
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

    public Set<Long> getFireIds() {
        return fireIds;
    }

    public void setFireIds(Set<Long> fireIds) {
        this.fireIds = fireIds;
    }
}
