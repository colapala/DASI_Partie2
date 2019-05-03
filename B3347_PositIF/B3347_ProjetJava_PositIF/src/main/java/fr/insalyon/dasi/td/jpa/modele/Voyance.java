package fr.insalyon.dasi.td.jpa.modele;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Clément TURCAN-JOUVE et Paul GOUX
 */
@Entity
public class Voyance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int status;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date dateVoyance;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Medium medium;
    @ManyToOne
    private Employe employe;

    protected Voyance() {
    }

    public Voyance(int status, String description, Client client, Medium medium, Employe employe, Date date) {
        this.status = status;
        this.description = description;
        this.client = client;
        this.medium = medium;
        this.employe = employe;
        this.dateVoyance = date;
    }

    public Integer getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        this.description = Description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Date getDateVoyance() {
        return dateVoyance;
    }

    public void setDateVoyance(Date dateVoyance) {
        this.dateVoyance = dateVoyance;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voyance other = (Voyance) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Voyance{" + "id=" + id + ", status=" + status + ", description=" + description + ", dateVoyance=" + dateVoyance + ", clientId=" + client.getId() + ", mediumId=" + medium.getId() + ", employeId=" + employe.getId() + '}';
    }

}
