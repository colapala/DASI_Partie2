package fr.insalyon.dasi.td.jpa.modele;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Clément TURCAN-JOUVE et Paul GOUX
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Medium implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String nom;
    private String descriptif;
    @OneToMany(mappedBy = "medium")
    private List<Voyance> historique;
    @ManyToMany(mappedBy = "listeMediums")
    private List<Employe> listeEmployes;

    protected Medium() {
    }

    public Medium(String nom, String descriptif, List<Voyance> historique, List<Employe> listeEmployes) {
        this.nom = nom;
        this.descriptif = descriptif;
        this.historique = historique;
        this.listeEmployes = listeEmployes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public List<Voyance> getHistorique() {
        return historique;
    }

    public void setHistorique(List<Voyance> historique) {
        this.historique = historique;
    }

    public List<Employe> getlisteEmployes() {
        return listeEmployes;
    }

    public void setlisteEmployes(List<Employe> listeEmployes) {
        this.listeEmployes = listeEmployes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Medium other = (Medium) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Medium{" + "id=" + id + ", nom=" + nom + ", descriptif=" + descriptif + '}';
    }

}
