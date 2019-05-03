package fr.insalyon.dasi.td.jpa.modele;

import fr.insalyon.dasi.td.jpa.modele.Employe;
import fr.insalyon.dasi.td.jpa.modele.Voyance;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-03T17:35:02")
@StaticMetamodel(Medium.class)
public abstract class Medium_ { 

    public static volatile ListAttribute<Medium, Employe> listeEmployes;
    public static volatile SingularAttribute<Medium, Integer> id;
    public static volatile SingularAttribute<Medium, String> nom;
    public static volatile SingularAttribute<Medium, String> descriptif;
    public static volatile ListAttribute<Medium, Voyance> historique;

}