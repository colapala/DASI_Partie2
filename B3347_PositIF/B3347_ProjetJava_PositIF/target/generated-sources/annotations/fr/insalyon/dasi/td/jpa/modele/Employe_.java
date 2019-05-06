package fr.insalyon.dasi.td.jpa.modele;

import fr.insalyon.dasi.td.jpa.modele.Medium;
import fr.insalyon.dasi.td.jpa.modele.Voyance;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-06T14:16:10")
@StaticMetamodel(Employe.class)
public class Employe_ { 

    public static volatile SingularAttribute<Employe, String> mail;
    public static volatile ListAttribute<Employe, Medium> listeMediums;
    public static volatile SingularAttribute<Employe, String> mdp;
    public static volatile SingularAttribute<Employe, String> tel;
    public static volatile SingularAttribute<Employe, Integer> id;
    public static volatile SingularAttribute<Employe, String> nom;
    public static volatile SingularAttribute<Employe, String> prenom;
    public static volatile SingularAttribute<Employe, String> civilite;
    public static volatile ListAttribute<Employe, Voyance> listeVoyances;
    public static volatile SingularAttribute<Employe, Boolean> disponible;

}