package fr.insalyon.dasi.td.jpa.modele;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Clément TURCAN-JOUVE et Paul GOUX
 */
@Entity
public class Tarologue extends Medium {

    protected Tarologue() {
    }

    public Tarologue(String nom, String descriptif, List<Voyance> historique, List<Employe> listeEmployes) {
        super(nom, descriptif, historique, listeEmployes);
    }

    public Tarologue(String nom, String descriptif) {
        this(nom, descriptif, new LinkedList<Voyance>(), new LinkedList<Employe>());
    }

    @Override
    public String toString() {
        return "Tarologue{" + super.toString() + '}';
    }

}
