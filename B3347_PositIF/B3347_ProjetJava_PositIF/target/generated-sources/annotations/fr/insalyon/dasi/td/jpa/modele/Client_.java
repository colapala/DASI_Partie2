package fr.insalyon.dasi.td.jpa.modele;

import fr.insalyon.dasi.td.jpa.modele.Voyance;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-06T14:16:09")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> mail;
    public static volatile SingularAttribute<Client, Date> dateNaissance;
    public static volatile SingularAttribute<Client, String> couleur;
    public static volatile SingularAttribute<Client, String> nom;
    public static volatile SingularAttribute<Client, String> adresse;
    public static volatile SingularAttribute<Client, String> mdp;
    public static volatile SingularAttribute<Client, String> animal;
    public static volatile SingularAttribute<Client, String> tel;
    public static volatile SingularAttribute<Client, Integer> id;
    public static volatile SingularAttribute<Client, String> prenom;
    public static volatile SingularAttribute<Client, String> signeChinois;
    public static volatile SingularAttribute<Client, String> signeZodiac;
    public static volatile ListAttribute<Client, Voyance> historique;
    public static volatile SingularAttribute<Client, String> civilite;

}