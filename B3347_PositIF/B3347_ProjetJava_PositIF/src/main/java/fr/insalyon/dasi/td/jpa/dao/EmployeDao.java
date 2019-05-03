package fr.insalyon.dasi.td.jpa.dao;

import fr.insalyon.dasi.td.jpa.modele.Medium;
import fr.insalyon.dasi.td.jpa.modele.Employe;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Query;

/**
 *
 * @author Clément TURCAN-JOUVE et Paul GOUX
 */
public class EmployeDao {

    public static void creerEmploye(Employe e) {
        JpaUtil.obtenirEntityManager().persist(e);
    }

    public static Employe updateEmploye(Employe e) {
        return JpaUtil.obtenirEntityManager().merge(e);
    }

    public static Employe findEmployeParId(int id) {
        return JpaUtil.obtenirEntityManager().find(Employe.class, id);
    }

    public static List<Employe> findAllEmploye() {
        String requete = "select e from Employe e";
        Query query = JpaUtil.obtenirEntityManager().createQuery(requete);
        return (List<Employe>) query.getResultList();
    }

    public static Employe findEmployeParMail(String mail) {
        String requete = "select e from Employe e where e.mail = :mail";
        Query query = JpaUtil.obtenirEntityManager().createQuery(requete);
        query.setParameter("mail", mail);

        return (Employe) query.getSingleResult();
    }
    
    public static Employe getEmployeDisponible(Medium m) {
        List<Employe> employePossible = findAllEmploye();
        int nbMin = Integer.MAX_VALUE;
        Employe employeMoinsAffecte = null;
        for (Employe e : employePossible) {
            if (e.isDisponible() && e.getListeMediums().contains(m)) {
                if (e.getListeVoyances().size() < nbMin) {
                    nbMin = e.getListeVoyances().size();
                    employeMoinsAffecte = e;
                }
            }
        }
        return employeMoinsAffecte;
    }

    public static Map<Employe, Integer> getClassementEmploye() {
        String requete = "select e from Voyance v join Employe e on v.employe.id = e.id";
        Query query = JpaUtil.obtenirEntityManager().createQuery(requete);
        Map<Employe, Integer> map = new HashMap<>();
        for (Employe e : (List<Employe>) query.getResultList()) {
            map.putIfAbsent(e, 0);
            map.replace(e, map.get(e) + 1);
        }
        final Map<Employe, Integer> sorted = map.entrySet()
                .stream()
                .sorted((Map.Entry.<Employe, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sorted;
    }
}
