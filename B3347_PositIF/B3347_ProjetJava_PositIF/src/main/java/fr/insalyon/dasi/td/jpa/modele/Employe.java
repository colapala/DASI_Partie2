package fr.insalyon.dasi.td.jpa.modele;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Clément TURCAN-JOUVE et Paul GOUX
 */
@Entity
public class Employe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nom;
    private String prenom;
    private String civilite;
    private String mail;
    private String mdp;
    private String tel;
    @ManyToMany
    private List<Medium> listeMediums;
    @OneToMany(mappedBy = "employe")
    private List<Voyance> listeVoyances;
    private boolean disponible;

    protected Employe() {
    }

    public Employe(String nom, String prenom, String civilite, String mail, String mdp, String tel, List<Medium> listeMediums, List<Voyance> listeVoyances) {
        this.nom = nom;
        this.prenom = prenom;
        this.civilite = civilite;
        this.mail = mail;
        this.mdp = mdp;
        this.listeMediums = listeMediums;
        this.listeVoyances = listeVoyances;
        this.tel = tel;
        this.disponible = true;

    }

    public Employe(String nom, String prenom, String civilite, String mail, String mdp, String tel) {
        this(nom, prenom, civilite, mail, mdp, tel, new LinkedList<Medium>(), new LinkedList<Voyance>());
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public List<Medium> getListeMediums() {
        return listeMediums;
    }

    public void setListeMediums(List<Medium> listeMediums) {
        this.listeMediums = listeMediums;
    }

    public List<Voyance> getListeVoyances() {
        return listeVoyances;
    }

    public void setListeVoyances(List<Voyance> listeVoyances) {
        this.listeVoyances = listeVoyances;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final Employe other = (Employe) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", civilite=" + civilite + ", mail=" + mail + ", mdp=" + mdp + ", tel=" + tel + ", disponible=" + disponible + '}';
    }

}
