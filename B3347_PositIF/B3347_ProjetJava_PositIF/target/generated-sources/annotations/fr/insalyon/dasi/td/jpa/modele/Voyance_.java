package fr.insalyon.dasi.td.jpa.modele;

import fr.insalyon.dasi.td.jpa.modele.Client;
import fr.insalyon.dasi.td.jpa.modele.Employe;
import fr.insalyon.dasi.td.jpa.modele.Medium;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-03T17:35:02")
@StaticMetamodel(Voyance.class)
public class Voyance_ { 

    public static volatile SingularAttribute<Voyance, Employe> employe;
    public static volatile SingularAttribute<Voyance, String> description;
    public static volatile SingularAttribute<Voyance, Client> client;
    public static volatile SingularAttribute<Voyance, Date> dateVoyance;
    public static volatile SingularAttribute<Voyance, Integer> id;
    public static volatile SingularAttribute<Voyance, Medium> medium;
    public static volatile SingularAttribute<Voyance, Integer> status;

}