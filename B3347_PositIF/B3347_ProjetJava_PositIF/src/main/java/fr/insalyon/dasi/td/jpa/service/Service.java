package fr.insalyon.dasi.td.jpa.service;

import fr.insalyon.dasi.td.jpa.dao.ClientDao;
import fr.insalyon.dasi.td.jpa.dao.EmployeDao;
import fr.insalyon.dasi.td.jpa.dao.JpaUtil;
import fr.insalyon.dasi.td.jpa.dao.MediumDao;
import fr.insalyon.dasi.td.jpa.dao.VoyanceDao;
import fr.insalyon.dasi.td.jpa.modele.Astrologue;
import fr.insalyon.dasi.td.jpa.modele.Client;
import fr.insalyon.dasi.td.jpa.modele.Employe;
import fr.insalyon.dasi.td.jpa.modele.Medium;
import fr.insalyon.dasi.td.jpa.modele.Tarologue;
import fr.insalyon.dasi.td.jpa.modele.Voyance;
import fr.insalyon.dasi.td.jpa.modele.Voyant;
import fr.insalyon.dasi.td.jpa.util.AstroTest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;
import java.util.Map;
import javax.persistence.RollbackException;
import util.DebugLogger;
import util.Message;

/**
 *
 * @author Clément TURCAN-JOUVE et Paul GOUX
 */
public class Service {

    private final AstroTest astroApi;

    public Service() {
        JpaUtil.init();
        astroApi = new AstroTest();
    }

    /**
     * Incrit un client à Positif. Envoie une message en cas de succès ou
     * d'échec. Le signe du zodiac, chinois, la couleur favorite et l'animal
     * totem sont calculés et remplace les valeurs existante dans le paramètre.
     *
     * @param c Le client à inscrire$
     * @return Le client inscrit avec les informations mises à jour.
     */
    public Client inscrireClient(Client c) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();

            List<String> profil = astroApi.getProfil(c.getPrenom(), c.getDateNaissance());
            c.setSigneZodiac(profil.get(0));
            c.setSigneChinois(profil.get(1));
            c.setCouleur(profil.get(2));
            c.setAnimal(profil.get(3));

            ClientDao.creerClient(c);
            Message.envoyerMail("noreply@positif.com", c.getMail(), "Inscription Positif", "Bonjour " + c.getPrenom() + "\nVotre inscrition a bien été prise en compte.\nCordialement\nL'équipe Positif");

            JpaUtil.validerTransaction();
            return c;
        } catch (RollbackException | IOException e) {
            Message.envoyerMail("noreply@positif.com", c.getMail(), "Inscription Positif", "Bonjour " + c.getPrenom() + "\nVotre inscrition n'a pas été prise en compte.\nCordialement\nL'équipe Positif");
            JpaUtil.annulerTransaction();
            DebugLogger.log("Erreur inscrireClient : ", e);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Connecte un employe.
     *
     * @param mail Mail saisi (utilisé comme identifiant)
     * @param mdp Mot de passe saisi.
     * @return L'employe si celui s'est connecté. Null si le mail et/ou mot de
     * passe sont invalides, ou si une erreur est survenue.
     */
    public Employe connecterEmploye(String mail, String mdp) {
        try {
            JpaUtil.creerEntityManager();
            Employe e = EmployeDao.findEmployeParMail(mail);
            if (e.getMdp().equals(mdp)) {
                return e;
            }
            return null;
        } catch (RollbackException ex) {
            DebugLogger.log("Erreur connexionEmploye : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Connecte un Client.
     *
     * @param mail Mail saisi (utilisé comme identifiant)
     * @param mdp Mot de passe saisi.
     * @return Le client si celui s'est connecté. Null si le mail et/ou mot de
     * passe sont invalides, ou si une erreur est survenue.
     */
    public Client connecterClient(String mail, String mdp) {
        try {
            JpaUtil.creerEntityManager();
            Client c = ClientDao.findClientParMail(mail);
            if (c.getMdp().equals(mdp)) {
                return c;
            }
            return null;
        } catch (RollbackException ex) {
            DebugLogger.log("Erreur connexionClient : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * @deprecated Ajoute un employe. A utiliser seulement pour les tests.
     * @param e l'employe à ajouter.
     */
    public void ajouterEmploye(Employe e) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            EmployeDao.creerEmploye(e);
            JpaUtil.validerTransaction();
        } catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
            try {
                JpaUtil.annulerTransaction();
                DebugLogger.log("Erreur ajouterEmploye : ", ex);
            } catch (RollbackException ex2) {
                DebugLogger.log("Erreur Catch ajouterEmploye : ", ex2);
            }
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * @deprecated Ajoute un medium. A utiliser seulement pour les tests.
     * @param m le médium à ajouter.
     */
    public void ajouterMedium(Medium m) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            MediumDao.creerMedium(m);
            JpaUtil.validerTransaction();
        } catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
            DebugLogger.log("Erreur ajouterMedium : ", ex);
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * @deprecated ajoute une voyance. A utiliser seulement pour les tests.
     * @param v la voyance à ajouter.
     */
    public void ajouterVoyance(Voyance v) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            VoyanceDao.creerVoyance(v);
            v.getClient().getHistorique().add(v);
            ClientDao.updateClient(v.getClient());
            MediumDao.updateMedium(v.getMedium());
            JpaUtil.validerTransaction();
        } catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
            DebugLogger.log("Erreur ajouterVoyance : ", ex);
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * @deprecated met à jour un client. A utiliser seulement pour les tests.
     * @param c le client à mettre à jour
     * @return le client mis à jour, null si une erreur s'est produite.
     */
    public Client mettreAJourClient(Client c) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            c = ClientDao.updateClient(c);
            JpaUtil.validerTransaction();
            return c;
        } catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
            DebugLogger.log("Erreur miseAJourClient : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * @deprecated Met à jour un employé. A utiliser seulement pour les tests.
     * @param e l'employé à mettre à jour
     * @return l'employé mis à jour, null si une erreur est survenue.
     */
    public Employe mettreAJourEmploye(Employe e) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            e = EmployeDao.updateEmploye(e);
            JpaUtil.validerTransaction();
            return e;
        } catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
            DebugLogger.log("Erreur miseAJourEmploye : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * @deprecated Met à jour un medium. A utiliser seulement pour les tests.
     * @param m Le médium à mettre à jour.
     * @return Le médium mis à jour, null si une erreur est survenue.
     */
    public Medium mettreAJourMedium(Medium m) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            m = MediumDao.updateMedium(m);
            JpaUtil.validerTransaction();
            return m;
        } catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
            DebugLogger.log("Erreur miseAJourMedium : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * @deprecated Met à jour une voyance. A utiliser seulement pour les tests.
     * @param v La voyance à mettre à jour.
     * @return La voyance mise à jour, null si une erreur s'est produite.
     */
    public Voyance mettreAJourVoyance(Voyance v) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            v = VoyanceDao.updateVoyance(v);
            JpaUtil.validerTransaction();
            return v;
        } catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
            DebugLogger.log("Erreur miseAJourVoyance : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Permet de retrouver un client par son id.
     *
     * @param id L'id du client
     * @return Le client correspondant à cette id, null s'il n'y en a pas où
     * qu'une erreur est survenue.
     */
    public Client findClientParId(int id) {
        try {
            JpaUtil.creerEntityManager();
            Client c = ClientDao.findClientParId(id);
            return c;
        } catch (RollbackException ex) {
            DebugLogger.log("Erreur findClientParId : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Permet de retrouver un client par son mail (qui est unique).
     *
     * @param mail Le mail du client
     * @return Le client correspondant à cette id, null s'il n'y en a pas où
     * qu'une erreur est survenue.
     */
    public Client findClientParMail(String mail) {
        try {
            JpaUtil.creerEntityManager();
            Client c = ClientDao.findClientParMail(mail);
            return c;
        } catch (RollbackException ex) {
            DebugLogger.log("Erreur findClientParMail : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Permet de retrouver un employe par son id.
     *
     * @param id L'id de l'employé
     * @return L'employé correspondant à cette id, null s'il n'y en a pas où
     * qu'une erreur est survenue.
     */
    public Employe findEmployeParId(int id) {
        try {
            JpaUtil.creerEntityManager();
            Employe c = EmployeDao.findEmployeParId(id);
            return c;
        } catch (RollbackException ex) {
            DebugLogger.log("Erreur findEmployeParId : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Permet de retrouver un employe par son mail.
     *
     * @param mail Le mail du client
     * @return L'employé correspondant à cette id, null s'il n'y en a pas où
     * qu'une erreur est survenue.
     */
    public Employe findEmployeParMail(String mail) {
        try {
            JpaUtil.creerEntityManager();
            Employe c = EmployeDao.findEmployeParMail(mail);
            return c;
        } catch (RollbackException ex) {
            DebugLogger.log("Erreur findEmployeParMail : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Permet d'avoir la liste de tous les médiums.
     *
     * @return La liste des médiums.
     */
    public List<Medium> getTousMediums() {
        try {
            JpaUtil.creerEntityManager();
            List<Medium> lm = MediumDao.getTousMediums();
            return lm;
        } catch (RollbackException ex) {
            DebugLogger.log("Erreur getTousMediums : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Permet d'avoir la liste de tous les voyants.
     *
     * @return La liste des voyants.
     */
    public List<Medium> getTousVoyants() {
        try {
            JpaUtil.creerEntityManager();
            List<Medium> lm = MediumDao.getTousVoyants();
            return lm;
        } catch (RollbackException ex) {
            DebugLogger.log("Erreur getTousVoyants : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Permet d'avoir la liste de tous les astrologues.
     *
     * @return La liste des astrologues.
     */
    public List<Medium> getTousAstrologues() {
        try {
            JpaUtil.creerEntityManager();
            List<Medium> lm = MediumDao.getTousAstrologues();
            return lm;
        } catch (RollbackException ex) {
            DebugLogger.log("Erreur getTousAstrologues : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Permet d'avoir la liste de tous les tarologues.
     *
     * @return La liste des tarologues.
     */
    public List<Medium> getTousTarologues() {
        try {
            JpaUtil.creerEntityManager();
            List<Medium> lm = MediumDao.getTousTarologues();
            return lm;
        } catch (RollbackException ex) {
            DebugLogger.log("Erreur getTousTarologues : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Permet d'avoir la liste de différents type de médiums (Tarologues,
     * Astrologues, Voyants).
     *
     * @param voyant true pour avoir la listes des voyants.
     * @param astro true pour avoir la listes des astrologues.
     * @param taro true pour avoir la listes des voyants.
     * @return la liste des médiums avec les types demandés.
     */
    public List<Medium> getMediumsParType(boolean voyant, boolean astro, boolean taro) {
        List<Medium> lm = new LinkedList<>();
        try {
            JpaUtil.creerEntityManager();
            if (voyant) {
                lm.addAll(MediumDao.getTousVoyants());
            }
            if (astro) {
                lm.addAll(MediumDao.getTousAstrologues());
            }
            if (taro) {
                lm.addAll(MediumDao.getTousTarologues());
            }
            return lm;
        } catch (RollbackException ex) {
            DebugLogger.log("Erreur getMediumsParType : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Permet de trouver un médium par son id.
     *
     * @param id L'id du médium à trouver.
     * @return Le médium correspondant à cette id, null s'il n'y en a pas où
     * qu'une erreur est survenue.
     */
    public Medium findMediumParId(int id) {
        try {
            JpaUtil.creerEntityManager();
            Medium m = MediumDao.findMediumParId(id);
            return m;
        } catch (RollbackException ex) {
            DebugLogger.log("Erreur findMediumParId : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Permet de trouver un médium par son nom (unique).
     *
     * @param nom Le nom du médium
     * @return Le médium correspondant à cette id, null s'il n'y en a pas où
     * qu'une erreur est survenue.
     */
    public Medium findMediumParNom(String nom) {
        try {
            JpaUtil.creerEntityManager();
            Medium m = MediumDao.findMediumParNom(nom);
            return m;
        } catch (RollbackException ex) {
            DebugLogger.log("Erreur findMediumParNom : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Permet de demander une voyance pour un client. Une nouvelle voyance est
     * crée avec un status à 0 et les différentes listes mises à jour. Un
     * employé est choisi automatiquement, il n'est démormais plus disponible.
     * Une notification est envoyée à l'employé
     *
     * @param c Le client qui demande la voyance.
     * @param m Le médium choisi par le client pour la voyance.
     * @return La voyance créée, null si une erreur est survenue.
     */
    public Voyance demanderVoyance(Client c, Medium m) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            Employe e = EmployeDao.getEmployeDisponible(m);
            Date date = new Date();
            Voyance v = new Voyance(0, null, c, m, e, date);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            e.setDisponible(false);
            e.getListeVoyances().add(v);
            c.getHistorique().add(v);
            m.getHistorique().add(v);
            VoyanceDao.creerVoyance(v);
            EmployeDao.updateEmploye(e);
            ClientDao.updateClient(c);
            MediumDao.updateMedium(m);
            JpaUtil.validerTransaction();
            Message.envoyerNotification(e.getTel(), "Voyance demandée le " + dateFormat.format(date) + " pour "
                    + c.getPrenom() + " " + c.getNom() + " (#" + c.getId() + "). Médium à incarner : " + m.getNom());
            return v;
        } catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
            DebugLogger.log("Erreur demanderVoyance : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Retourne le client en attente d'un employé (càd dont le status d'une de
     * ces voyances est à 0).
     *
     * @param e L'employé
     * @return Le client en attente, null si il n'y en a aucun ou qu'une erreur
     * est survenue.
     */
    public Client getClientEnAttente(Employe e) {
        for (Voyance v : e.getListeVoyances()) {
            if (v.getStatus() == 0) {
                return v.getClient();
            }
        }
        return null;
    }

    /**
     * Retourne les prédictions pour un client donné. Le niveau passé en
     * paramètre est utilisé pour les 3 prédictions.
     *
     * @param c Le client
     * @param niveau Le niveau est compris entre 1 (mauvais) et 4 (parfait)
     * @return Retourne une liste de String, indice 0 = Amour, 1 = Santé, 2 =
     * Travail. Retourne null si une erreur est survenue.
     */
    public List<String> getPredictions(Client c, int niveau) {
        try {
            return astroApi.getPredictions(c.getCouleur(), c.getAnimal(), niveau, niveau, niveau);
        } catch (IOException e) {
            DebugLogger.log("Error getPredictions : ", e);
        }
        return null;
    }

    /**
     * Permet de commencer une voyance (déclencher par l'employé). Le status de
     * la voyance passe à 1 et une notification est envoyée au client.
     *
     * @param v La voyance à commencer.
     * @return La voyance mise à jour.
     */
    public Voyance commencerVoyance(Voyance v) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            v.setStatus(1);
            v = VoyanceDao.updateVoyance(v);
            JpaUtil.validerTransaction();
            Message.envoyerNotification(v.getClient().getTel(), "Votre voyance du " + dateFormat.format(v.getDateVoyance()) + " a bien été enregistrée. Vous pouvez dès à présent me contacter au "
                    + v.getEmploye().getTel() + ".\r\n A tout de suite ! Posit'ifement votre, " + v.getMedium().getNom() + ".");
            return v;
        } catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
            DebugLogger.log("Erreur commencerVoyance : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Doit être appelé pour clôre la voyance, c'est à dire une fois que le
     * commentaire à été saisi. Le status de la voyance passe à 2. L'employé est
     * à nouveau disponible.
     *
     * @param v La voyance avec le commentaire.
     * @return La voyance mise à jour, null si une erreur s'est produite.
     */
    public Voyance finirVoyance(Voyance v) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            v.setStatus(2);
            v.getEmploye().setDisponible(true);
            v.setEmploye(EmployeDao.updateEmploye(v.getEmploye()));
            v = VoyanceDao.updateVoyance(v);
            JpaUtil.validerTransaction();
            return v;
        } catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
            DebugLogger.log("Erreur finirVoyance : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Retourne une map des médiums avec le nombre de fois que l'employé l'a
     * interprété.
     *
     * @param e L'employé
     * @return Une map de médium / Integer trié par ordre décroissant du nombre
     * de voyance avec le médium.
     */
    public Map<Medium, Integer> getClassementMediumPourEmploye(Employe e) {
        try {
            JpaUtil.creerEntityManager();
            Map<Medium, Integer> map = MediumDao.getClassementMediumPourEmploye(e);
            return map;
        } catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
            DebugLogger.log("Erreur getClassementMedium : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Retourne une map des médiums avec le nombre de fois qu'ils ont été
     * interprété.
     *
     * @return Une map de médium / Integer trié par ordre décroissant du nombre
     * de voyance avec le médium.
     */
    public Map<Medium, Integer> getClassementMedium() {
        try {
            JpaUtil.creerEntityManager();
            Map<Medium, Integer> map = MediumDao.getClassementMedium();
            return map;
        } catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
            DebugLogger.log("Erreur getClassementMedium : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    /**
     * Retourne une map des employés avec le nombre de fois qu'ils ont réalisé
     * une voyance.
     *
     * @return Une map de médium / Integer trié par ordre décroissant du nombre
     * de voyance avec le médium.
     */
    public Map<Employe, Integer> getClassementEmploye() {
        try {
            JpaUtil.creerEntityManager();
            Map<Employe, Integer> map = EmployeDao.getClassementEmploye();
            return map;
        } catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
            DebugLogger.log("Erreur getClassementMedium : ", ex);
            return null;
        } finally {
            JpaUtil.fermerEntityManager();

        }
    }

    /**
     * Retourne la voyance en attente d'un employé pour un client donnée. C'est
     * à dire une voyance commune au client et à l'employé et qui n'as pas
     * encore commencé (cad status = 0).
     *
     * @param c Le client.
     * @param e L'employé.
     * @return La voyance en attente, null si aucune.
     */
    public Voyance getVoyanceEnAttente(Client c, Employe e) {
        for (Voyance v : c.getHistorique()) {
            if (v.getStatus() == 0 && v.getEmploye().equals(e)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Initialise la base de données.
     */
    public void init() {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Medium v1 = new Voyant("Gwenaël", "Spécialiste des grandes conversations au-delà de TOUTES les frontières.", "Boule de cristal");
        Medium v2 = new Voyant("Professeur Maxwell", "Votre avenir est devant vous : regasdons le ensemble !", "Marc de café");
        Medium t1 = new Tarologue("Mme Irma", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides.");
        Medium t2 = new Tarologue("Endora", "Mes cartes répondrons à toutes vos questions personnelles.");
        Medium a1 = new Astrologue("Serena", "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.", "École Normale Supérieure d'Astrologie (ENS-Astro)", "2006");
        Medium a2 = new Astrologue("Mr M. Histaire-Hyeux", "Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter !", "Institut des Nouveaux Savoirs Astrologiques", "2010");
        List<Medium> lm = new ArrayList<>(Arrays.asList(a1, a2, t1, t2, v1, v2));

        Client c1, c2, c3, c4;
        try {
            c1 = new Client("Labossière", "Patrice", "Monsieur", formatter.parse("26/01/1963"), "38, rue Nationale 750003 Paris", "01 33 75 82 69", "PatriceLabossiere@teleworm.us", "Kuukoh3aigh");
            c2 = new Client("Douffet", "Mathilda", "Madame", formatter.parse("31/01/2000"), "72, rue Lenotre 25700 Rennes", "02 81 48 61 04", "MatildaDouffet@rhyta.com", "ger4ruNgoo");
            c3 = new Client("Cotuand", "Henri", "Monsieur", formatter.parse("24/10/1994"), "96, rue Gustave Eiffel 69140 Rillieux-la-Pape", "04 83 36 20 47", "HenriCotuand@jourrapide.com", "chai0ooQuoo");
            c4 = new Client("Wayne", "Bruce", "Monsieur", formatter.parse("17/02/1957"), "24 route du Manoir, 54700, Batcave City", "07 42 69 21 32", "bruce.wayne@wayne-industrie.com", "joker");
        } catch (ParseException ex) {
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            DebugLogger.log("Erreur inscription client", ex);
            return;
        }
        List<Client> lc = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));
        for (Client c : lc) {
            try {
                List<String> profil;
                profil = astroApi.getProfil(c.getPrenom(), c.getDateNaissance());
                c.setSigneZodiac(profil.get(0));
                c.setSigneChinois(profil.get(1));
                c.setCouleur(profil.get(2));
                c.setAnimal(profil.get(3));
            } catch (IOException ex) {
                DebugLogger.log("Erreur calcul signes", ex);
                JpaUtil.annulerTransaction();
                JpaUtil.fermerEntityManager();
                return;
            }
        }

        Employe e1 = new Employe("Brunnelle", "Karel", "Monsieur", "KarelBrunelle@dayrep.com", "gah7sheR", "04 18 77 77 34");
        Employe e2 = new Employe("Lagacé", "Julienne", "Madame", "JulienneLagace@teleworm.us", "sae7moL7", "04 32 89 30 17");
        Employe e3 = new Employe("Marcheterre", "Catherine", "Madame", "CatherineMarcheterre@jourrapide.com", "test", "02 25 36 70 00");
        List<Employe> le = new ArrayList<>(Arrays.asList(e1, e2, e3));

        for (Medium m : lm) {
            MediumDao.creerMedium(m);
        }
        for (Client c : lc) {
            ClientDao.creerClient(c);
        }
        for (Employe e : le) {
            EmployeDao.creerEmploye(e);
        }

        e1.getListeMediums().addAll(Arrays.asList(a1, a2, t2));
        e2.getListeMediums().addAll(Arrays.asList(a2, v1, v2, t1));
        e3.getListeMediums().addAll(Arrays.asList(t1, a2, v2, t2));
        a1.getlisteEmployes().addAll(Arrays.asList(e1));
        a2.getlisteEmployes().addAll(Arrays.asList(e1, e2, e3));
        v1.getlisteEmployes().addAll(Arrays.asList(e2));
        v2.getlisteEmployes().addAll(Arrays.asList(e2, e3));
        t1.getlisteEmployes().addAll(Arrays.asList(e2, e3));
        t2.getlisteEmployes().addAll(Arrays.asList(e1, e3));

        for (Employe e : le) {
            EmployeDao.updateEmploye(e);
        }
        for (Medium m : lm) {
            MediumDao.updateMedium(m);
        }

        Voyance voy0, voy1, voy2, voy3, voy4, voy5, voy6, voy7, voy8, voy9;
        try {
            voy0 = new Voyance(2, "Client très agréable", c1, a1, e1, formatter.parse("17/03/2019"));
            voy1 = new Voyance(2, null, c2, a2, e3, formatter.parse("15/01/2017"));
            voy2 = new Voyance(2, "Le client à perdu son chat il y a peu", c3, t1, e2, formatter.parse("20/02/2019"));
            voy3 = new Voyance(2, "Penchant violent du client envers les criminels", c4, v1, e2, formatter.parse("21/03/2019"));
            voy4 = new Voyance(2, "Adoration pour les chauffes-souris + trouble profond de l'enfance", c4, v2, e3, formatter.parse("02/03/2019"));
            voy5 = new Voyance(2, null, c4, a1, e1, formatter.parse("21/03/2019"));
            voy6 = new Voyance(2, null, c1, t2, e1, formatter.parse("10/03/2018"));
            voy7 = new Voyance(2, "Le client cherche l'amour éternel", c2, t1, e2, formatter.parse("14/02/2018"));
            voy8 = new Voyance(2, null, c1, v2, e2, formatter.parse("30/10/2018"));
            voy9 = new Voyance(2, null, c1, a2, e1, formatter.parse("14/11/2018"));
        } catch (ParseException ex) {
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            DebugLogger.log("Erreur voyance", ex);
            return;
        }
        List<Voyance> lv = new ArrayList<>(Arrays.asList(voy0, voy1, voy2, voy3, voy4, voy5, voy6, voy7, voy8, voy9));
        for (Voyance v : lv) {
            VoyanceDao.creerVoyance(v);
        }

        c1.getHistorique().addAll(Arrays.asList(voy0, voy6, voy8, voy9));
        c2.getHistorique().addAll(Arrays.asList(voy1, voy7));
        c3.getHistorique().addAll(Arrays.asList(voy2));
        c4.getHistorique().addAll(Arrays.asList(voy3, voy4, voy5));
        e1.getListeVoyances().addAll(Arrays.asList(voy0, voy5, voy6, voy9));
        e2.getListeVoyances().addAll(Arrays.asList(voy2, voy3, voy7, voy8));
        e3.getListeVoyances().addAll(Arrays.asList(voy1, voy4));
        a1.getHistorique().addAll(Arrays.asList(voy0, voy5));
        a2.getHistorique().addAll(Arrays.asList(voy1, voy9));
        t1.getHistorique().addAll(Arrays.asList(voy2, voy7));
        t2.getHistorique().addAll(Arrays.asList(voy6));
        v1.getHistorique().addAll(Arrays.asList(voy3));
        v2.getHistorique().addAll(Arrays.asList(voy4, voy8));
        for (Medium m : lm) {
            MediumDao.updateMedium(m);
        }
        for (Client c : lc) {
            ClientDao.updateClient(c);
        }
        for (Employe e : le) {
            EmployeDao.updateEmploye(e);
        }

        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
}
