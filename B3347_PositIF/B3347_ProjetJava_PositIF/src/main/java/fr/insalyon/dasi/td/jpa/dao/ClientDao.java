package fr.insalyon.dasi.td.jpa.dao;

import fr.insalyon.dasi.td.jpa.modele.Client;
import javax.persistence.Query;

/**
 *
 * @author Clément TURCAN-JOUVE et Paul GOUX
 */
public class ClientDao {
    public static void creerClient(Client c){
        JpaUtil.obtenirEntityManager().persist(c);
    }
    
    public static Client updateClient(Client c){
        return JpaUtil.obtenirEntityManager().merge(c);
    }
    
    public static Client findClientParId(int id)
    {
        return JpaUtil.obtenirEntityManager().find(Client.class, id);
    }
    
    public static Client findClientParMail(String mail)
    {
        String requete = "select c from Client c where c.mail = :mail";
        Query query = JpaUtil.obtenirEntityManager().createQuery(requete);
        query.setParameter("mail", mail);
        
        return (Client)query.getSingleResult();
    }
}
