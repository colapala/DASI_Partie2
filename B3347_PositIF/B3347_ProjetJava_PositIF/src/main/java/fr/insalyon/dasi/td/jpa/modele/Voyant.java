package fr.insalyon.dasi.td.jpa.modele;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Clément TURCAN-JOUVE et Paul GOUX
 */
@Entity
public class Voyant extends Medium {

    private String specialite;

    protected Voyant() {
    }

    public Voyant(String nom, String descriptif, String specialite, List<Voyance> historique, List<Employe> listeEmployes) {
        super(nom, descriptif, historique, listeEmployes);
        this.specialite = specialite;
    }

    public Voyant(String nom, String descriptif, String specialite) {
        this(nom, descriptif, specialite, new LinkedList<Voyance>(), new LinkedList<Employe>());
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "Voyant{" + super.toString() + ", specialite=" + specialite + '}';
    }

}
