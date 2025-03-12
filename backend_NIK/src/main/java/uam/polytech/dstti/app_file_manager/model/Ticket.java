package uam.polytech.dstti.app_file_manager.model;

import jakarta.persistence.*;

@Entity
@IdClass(TicketId.class)
public class Ticket {
    @Id
    private Long localisationId;
    @Id
    private Long serviceId;

    private int numeroTicket;
    private int positionInFile;
    private int nbrPersonneAvant;
    private int currentNumber;

    public Ticket() {
    }

   public Ticket(Long localisationId, Long serviceId, int numeroTicket, int positionInFile, int nbrPersonneAvant, int currentNumber) {
       this.localisationId = localisationId;
       this.serviceId = serviceId;
       this.numeroTicket = numeroTicket;
       this.positionInFile = positionInFile;
       this.nbrPersonneAvant = nbrPersonneAvant;
       this.currentNumber = currentNumber;
   }

    public int getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(int numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public int getPositionInFile() {
        return positionInFile;
    }

    public void setPositionInFile(int positionInFile) {
        this.positionInFile = positionInFile;
    }

    public int getNbrPersonneAvant() {
        return nbrPersonneAvant;
    }

    public void setNbrPersonneAvant(int nbrPersonneAvant) {
        this.nbrPersonneAvant = nbrPersonneAvant;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
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

}
