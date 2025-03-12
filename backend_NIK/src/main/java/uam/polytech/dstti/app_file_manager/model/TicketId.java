package uam.polytech.dstti.app_file_manager.model;

import jakarta.persistence.Entity;

import java.io.Serializable;
import java.util.Objects;

public class TicketId implements Serializable {
    private Long localisationId;
    private Long serviceId;

    public TicketId() {

    }

    public TicketId(Long localisationId, Long serviceId) {
        this.localisationId = localisationId;
        this.serviceId = serviceId;
    }
    public Long getLocalisationId() {
        return localisationId;
    }
    public void setLocalisationId(Long localisationId) {
        this.localisationId = localisationId;
    }
    public Long getServiceId() {
        return serviceId;
    }
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketId that = (TicketId) o;
        return Objects.equals(localisationId, that.localisationId) && Objects.equals(serviceId, that.serviceId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(localisationId, serviceId);
    }

}
