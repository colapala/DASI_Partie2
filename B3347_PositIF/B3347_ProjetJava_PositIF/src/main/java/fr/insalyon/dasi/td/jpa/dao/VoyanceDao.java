package fr.insalyon.dasi.td.jpa.dao;

import fr.insalyon.dasi.td.jpa.modele.Voyance;

/**
 *
 * @author Clément TURCAN-JOUVE et Paul GOUX
 */
public class VoyanceDao {
     public static void creerVoyance(Voyance v){
        JpaUtil.obtenirEntityManager().persist(v);
    }
     
     public static Voyance updateVoyance(Voyance v){
        return JpaUtil.obtenirEntityManager().merge(v);
    }
}
