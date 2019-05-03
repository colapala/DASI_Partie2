package fr.insalyon.dasi.td.jpa.modele;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Clément TURCAN-JOUVE et Paul GOUX
 */
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nom;
    private String prenom;
    private String civilite;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private String adresse;
    private String tel;
    @Column(unique = true)
    private String mail;
    private String signeZodiac;
    private String signeChinois;
    private String couleur;
    private String animal;
    private String mdp;
    @OneToMany(mappedBy = "client")
    private List<Voyance> historique;

    protected Client() {
    }

    public Client(String nom, String prenom, String civilite, Date dateNaissance, String adresse, String tel, String mail, String mdp, List<Voyance> historique) {
        this.nom = nom;
        this.prenom = prenom;
        this.civilite = civilite;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
        this.signeZodiac = null;
        this.signeChinois = null;
        this.couleur = null;
        this.animal = null;
        this.mdp = mdp;
        this.historique = historique;
    }

    public Client(String nom, String prenom, String civilite, Date dateNaissance, String adresse, String tel, String mail, String mdp) {
        this(nom, prenom, civilite, dateNaissance, adresse, tel, mail, mdp, new LinkedList<Voyance>());
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSigneZodiac() {
        return signeZodiac;
    }

    public void setSigneZodiac(String signeZodiac) {
        this.signeZodiac = signeZodiac;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public List<Voyance> getHistorique() {
        return historique;
    }

    public void setHistorique(List<Voyance> historique) {
        this.historique = historique;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final Client other = (Client) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", civilite=" + civilite + ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + ", tel=" + tel + ", mail=" + mail + ", signeZodiac=" + signeZodiac + ", signeChinois=" + signeChinois + ", couleur=" + couleur + ", animal=" + animal + ", mdp=" + mdp + '}';
    }

}
