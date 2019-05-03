package fr.insalyon.dasi.td.jpa.dao;

import fr.insalyon.dasi.td.jpa.modele.Employe;
import fr.insalyon.dasi.td.jpa.modele.Medium;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.Query;

/**
 *
 * @author Clément TURCAN-JOUVE et Paul GOUX
 */
public class MediumDao {

    public static void creerMedium(Medium m) {
        JpaUtil.obtenirEntityManager().persist(m);
    }

    public static Medium updateMedium(Medium m) {
        return JpaUtil.obtenirEntityManager().merge(m);
    }

    public static List<Medium> getTousMediums() {
        String requete = "select m from Medium m";
        Query query = JpaUtil.obtenirEntityManager().createQuery(requete);
        return (List<Medium>) query.getResultList();
    }

    public static List<Medium> getTousVoyants() {
        String requete = "select v from Voyant v";
        Query query = JpaUtil.obtenirEntityManager().createQuery(requete);
        return (List<Medium>) query.getResultList();
    }

    public static List<Medium> getTousTarologues() {
        String requete = "select t from Tarologue t";
        Query query = JpaUtil.obtenirEntityManager().createQuery(requete);
        return (List<Medium>) query.getResultList();
    }

    public static List<Medium> getTousAstrologues() {
        String requete = "select a from Astrologue a";
        Query query = JpaUtil.obtenirEntityManager().createQuery(requete);
        return (List<Medium>) query.getResultList();
    }

    public static Medium findMediumParId(int id) {
        return JpaUtil.obtenirEntityManager().find(Medium.class, id);
    }

    public static Medium findMediumParNom(String nom) {
        String requete = "select m from Medium m where m.nom = :nom";
        Query query = JpaUtil.obtenirEntityManager().createQuery(requete);
        query.setParameter("nom", nom);

        return (Medium) query.getSingleResult();
    }
    
   public static Map<Medium, Integer> getClassementMediumPourEmploye(Employe e) {
        String requete = "select m from Voyance v join Medium m on v.medium.id = m.id where v.employe.id = :eid";
        Query query = JpaUtil.obtenirEntityManager().createQuery(requete);
        query.setParameter("eid", e.getId());
        Map<Medium, Integer> map = new HashMap<>();
        for (Medium m : (List<Medium>) query.getResultList()) {
            map.putIfAbsent(m, 0);
            map.replace(m, map.get(m) + 1);
        }
        final Map<Medium, Integer> sorted = map.entrySet()
                .stream()
                .sorted((Map.Entry.<Medium, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sorted;
    }
   
   public static Map<Medium, Integer> getClassementMedium() {
        String requete = "select m from Voyance v join Medium m on v.medium.id = m.id";
        Query query = JpaUtil.obtenirEntityManager().createQuery(requete);
        Map<Medium, Integer> map = new HashMap<>();
        for (Medium m : (List<Medium>) query.getResultList()) {
            map.putIfAbsent(m, 0);
            map.replace(m, map.get(m) + 1);
        }
        final Map<Medium, Integer> sorted = map.entrySet()
                .stream()
                .sorted((Map.Entry.<Medium, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sorted;
    }
}
