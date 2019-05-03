package fr.insalyon.dasi.td.jpa.modele;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Clément TURCAN-JOUVE et Paul GOUX
 */
@Entity
public class Astrologue extends Medium {

    private String formation;
    private String promotion;

    protected Astrologue() {
    }

    public Astrologue(String nom, String descriptif, String formation, String promotion, List<Voyance> historique, List<Employe> listeEmployes) {
        super(nom, descriptif, historique, listeEmployes);
        this.formation = formation;
        this.promotion = promotion;
    }

    public Astrologue(String nom, String descriptif, String formation, String promotion) {
        this(nom, descriptif, formation, promotion, new LinkedList<Voyance>(), new LinkedList<Employe>());
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "Astrologue{" + super.toString() + "formation=" + formation + ", promotion=" + promotion + '}';
    }

}
