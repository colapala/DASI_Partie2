package fr.insalyon.dasi.td.jpa.positif;

import fr.insalyon.dasi.td.jpa.modele.Astrologue;
import fr.insalyon.dasi.td.jpa.modele.Client;
import fr.insalyon.dasi.td.jpa.modele.Employe;
import fr.insalyon.dasi.td.jpa.modele.Medium;
import fr.insalyon.dasi.td.jpa.modele.Tarologue;
import fr.insalyon.dasi.td.jpa.modele.Voyance;
import fr.insalyon.dasi.td.jpa.modele.Voyant;
import fr.insalyon.dasi.td.jpa.service.Service;
import fr.insalyon.dasi.td.jpa.util.Saisie;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import util.DebugLogger;

/**
 *
 * @author Clément TURCAN-JOUVE et Paul GOUX
 */
public class Positif {

    public static void main(String args[]) throws Exception {
        //testInscriptionClient();
        //testConnexionEmploye();
        //testConnexionClient();
        //testGetTousMediums();
        //testGetTousVoyants();
        //testGetTousAstrologues();
        //testGetTousTarologues();
        //testGetMediumParNom();
        //testGetMediumsParType();
        //testDemanderVoyance();
        //testGetClientEnAttente();
        //testPredictions();
        //testCommencerVoyance();
        //testFinirVoyance();
        //testGetClassementMediumPourEmploye();
        //testGetClassementMedium();
        //testGetClassementEmploye();
        testComplet();
    }

    public static void testInscriptionClient() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = sdf.parse("21/12/2012");
        Client c = new Client("Jacque", "Jean", "Animal", d, "Villeurbanne", "052554", "bonjoour.mal", "admin123azerty");
        Service s = new Service();
        DebugLogger.log(s.inscrireClient(c).toString());
        //---- Fin IHM
        DebugLogger.log(s.findClientParMail(c.getMail()).toString());
    }

    public static void testConnexionClient() {
        Client c = new Client("Jacque", "Jean", "Animal", new Date(1984, 1, 4), "Villeurbanne", "052554", "bonjoour.mal", "admin123azerty");
        Service s = new Service();
        s.inscrireClient(c);

        DebugLogger.log(s.connecterClient("bonjoour.mal", "admin123azerty").toString());
    }

    public static void testConnexionEmploye() {
        Employe e = new Employe("BRES", "Steph", "M", "@insa-lyon", "mdp123", "05871256");
        Service s = new Service();
        s.ajouterEmploye(e);

        DebugLogger.log(s.connecterEmploye("@insa-lyon", "mdp123").toString());
    }

    public static void testGetTousMediums() {
        Medium m = new Voyant("Karmen", "Cool", "Bonjour, ceci est le descriptif");
        Medium t = new Tarologue("Jean-Jacque", "joue aux cartes");
        Service s = new Service();
        s.ajouterMedium(m);
        s.ajouterMedium(t);
        s.getTousMediums().forEach((a) -> {
            DebugLogger.log(a.toString());
        });
    }

    public static void testGetTousVoyants() {
        Medium m = new Voyant("Karmen", "Cool", "Bonjour, ceci est le descriptif");
        Medium t = new Voyant("Jean-Jacque", "joue aux cartes", "yo");
        Medium t2 = new Astrologue("Jean-Jacque", "joue aux cartes", "yo", "2018");
        Service s = new Service();
        s.ajouterMedium(m);
        s.ajouterMedium(t);
        s.ajouterMedium(t2);
        s.getTousVoyants().forEach((a) -> {
            DebugLogger.log(a.toString());
        });
    }

    public static void testGetTousAstrologues() {
        Medium m = new Astrologue("Karmen", "Cool", "Bonjour, ceci est le descriptif", "2014");
        Medium m2 = new Voyant("Karmen", "Cool", "Bonjour, ceci est le descriptif");
        Medium t = new Astrologue("Jean-Jacque", "joue aux cartes", "yo", "2018");
        Service s = new Service();
        s.ajouterMedium(m);
        s.ajouterMedium(m2);
        s.ajouterMedium(t);
        s.getTousAstrologues().forEach((a) -> {
            DebugLogger.log(a.toString());
        });
    }

    public static void testGetTousTarologues() {
        Medium m = new Tarologue("Karmen", "Cool");
        Medium t = new Tarologue("Jean-Jacque", "joue aux cartes");
        Medium m2 = new Voyant("Karmen", "Cool", "Bonjour, ceci est le descriptif");
        Service s = new Service();
        s.ajouterMedium(m);
        s.ajouterMedium(m2);
        s.ajouterMedium(t);
        s.getTousTarologues().forEach((a) -> {
            DebugLogger.log(a.toString());
        });
    }

    public static void testGetMediumParNom() {
        Medium m = new Tarologue("Karmen", "Cool");
        Service s = new Service();
        s.ajouterMedium(m);
        DebugLogger.log(s.findMediumParNom("Karmen").toString());
    }

    public static void testGetMediumsParType() {
        Medium m = new Astrologue("Karmen", "Cool", "oui", "non");
        Medium t = new Tarologue("Jean-Jacque", "joue aux cartes");
        Medium m2 = new Voyant("Karmen", "Cool", "Bonjour, ceci est le descriptif");
        Service s = new Service();
        s.ajouterMedium(m);
        s.ajouterMedium(m2);
        s.ajouterMedium(t);
        s.getMediumsParType(true, false, true).forEach((a) -> {
            DebugLogger.log(a.toString());
        });
    }

    public static void testDemanderVoyance() {
        Medium m = new Astrologue("Karmen", "Cool", null, null);
        Medium t = new Tarologue("Jean-Jacque", "joue aux cartes", null, null);
        Employe e1 = new Employe("BRES", "Steph", "M", "bs.@insa-lyon", "mdp123", "517881556");
        Employe e2 = new Employe("MARANZANA", "Math", "M", "mm.@insa-lyon", "mdp123", "54654");
        Service s = new Service();
        s.ajouterMedium(m);
        s.ajouterMedium(t);
        s.ajouterEmploye(e1);
        s.ajouterEmploye(e2);

        List<Medium> lm = new LinkedList<>();
        lm.add(m);
        lm.add(t);
        e1.setListeMediums(lm);
        e2.setListeMediums(lm);

        List<Employe> le = new LinkedList<>();
        le.add(e1);
        le.add(e2);
        m.setlisteEmployes(le);
        t.setlisteEmployes(le);

        Client c = new Client("Jacque", "Jean", "Animal", new Date(1984, 1, 4), "Villeurbanne", "052554", "bonjoour.mal", "admin123azerty", null);

        Voyance v = new Voyance(0, "test", c, m, e2, new Date());

        s.inscrireClient(c);
        s.mettreAJourMedium(m);
        s.mettreAJourMedium(t);
        s.mettreAJourEmploye(e1);
        s.mettreAJourEmploye(e2);
        s.ajouterVoyance(v);

        //e1 attendu car e2 a déjà une voyance
        Voyance vv = s.demanderVoyance(c, m);
        DebugLogger.log(vv.toString());
        DebugLogger.log("Employé choisi = " + vv.getEmploye().getId());
    }

    public static void testGetClientEnAttente() {
        Medium m = new Astrologue("Karmen", "Cool", null, null);
        Medium t = new Tarologue("Jean-Jacque", "joue aux cartes", null, null);
        Employe e1 = new Employe("BRES", "Steph", "M", "bs.@insa-lyon", "mdp123", "517881556");
        Employe e2 = new Employe("MARANZANA", "Math", "M", "mm.@insa-lyon", "mdp123", "54654");
        Service s = new Service();
        s.ajouterMedium(m);
        s.ajouterMedium(t);
        s.ajouterEmploye(e1);
        s.ajouterEmploye(e2);

        List<Medium> lm = new LinkedList<>();
        lm.add(m);
        lm.add(t);
        e1.setListeMediums(lm);
        e2.setListeMediums(lm);

        List<Employe> le = new LinkedList<>();
        le.add(e1);
        le.add(e2);
        m.setlisteEmployes(le);
        t.setlisteEmployes(le);

        Client c = new Client("Jacque", "Jean", "Animal", new Date(1984, 1, 4), "Villeurbanne", "052554", "bonjoour.mal", "admin123azerty", null);

        Voyance v = new Voyance(1, "test", c, m, e2, new Date());

        s.inscrireClient(c);
        s.mettreAJourMedium(m);
        s.mettreAJourMedium(t);
        s.mettreAJourEmploye(e1);
        s.mettreAJourEmploye(e2);
        s.ajouterVoyance(v);

        Voyance vv = s.demanderVoyance(c, m);

        // Client attendu c 
        Client cAttente = s.getClientEnAttente(vv.getEmploye());
        DebugLogger.log(cAttente.toString());
        for (Voyance vvv : cAttente.getHistorique()) {
            DebugLogger.log(vvv.toString());
        }
    }

    public static void testPredictions() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = sdf.parse("21/12/2012");
        Client c = new Client("Jacque", "Jean", "Animal", d, "Villeurbanne", "052554", "bonjoour.mal", "admin123azerty");
        Service s = new Service();
        s.inscrireClient(c);

        for (String str : s.getPredictions(c, 1)) {
            DebugLogger.log(str);
        }

        for (String str : s.getPredictions(c, 42)) {
            DebugLogger.log(str);
        }
    }

    public static void testCommencerVoyance() {
        Medium m = new Astrologue("Karmen", "Cool", null, null);
        Medium t = new Tarologue("Jean-Jacque", "joue aux cartes", null, null);
        Employe e1 = new Employe("BRES", "Steph", "M", "bs.@insa-lyon", "mdp123", "517881556");
        Employe e2 = new Employe("MARANZANA", "Math", "M", "mm.@insa-lyon", "mdp123", "54654");
        Service s = new Service();
        s.ajouterMedium(m);
        s.ajouterMedium(t);
        s.ajouterEmploye(e1);
        s.ajouterEmploye(e2);

        List<Medium> lm = new LinkedList<>();
        lm.add(m);
        lm.add(t);
        e1.setListeMediums(lm);
        e2.setListeMediums(lm);

        List<Employe> le = new LinkedList<>();
        le.add(e1);
        le.add(e2);
        m.setlisteEmployes(le);
        t.setlisteEmployes(le);

        Client c = new Client("Jacque", "Jean", "Animal", new Date(1984, 1, 4), "Villeurbanne", "052554", "bonjoour.mal", "admin123azerty", null);

        Voyance v = new Voyance(0, "test", c, m, e2, new Date());

        s.inscrireClient(c);
        s.mettreAJourMedium(m);
        s.mettreAJourMedium(t);
        s.mettreAJourEmploye(e1);
        s.mettreAJourEmploye(e2);
        s.ajouterVoyance(v);

        //e1 attendu car e2 a déjà une voyance
        Voyance vv = s.demanderVoyance(c, m);
        Voyance vvv = s.commencerVoyance(vv);

        DebugLogger.log(vvv.toString());
    }

    public static void testFinirVoyance() {
        Medium m = new Astrologue("Karmen", "Cool", null, null);
        Medium t = new Tarologue("Jean-Jacque", "joue aux cartes", null, null);
        Employe e1 = new Employe("BRES", "Steph", "M", "bs.@insa-lyon", "mdp123", "517881556");
        Employe e2 = new Employe("MARANZANA", "Math", "M", "mm.@insa-lyon", "mdp123", "54654");
        Service s = new Service();
        s.ajouterMedium(m);
        s.ajouterMedium(t);
        s.ajouterEmploye(e1);
        s.ajouterEmploye(e2);

        List<Medium> lm = new LinkedList<>();
        lm.add(m);
        lm.add(t);
        e1.setListeMediums(lm);
        e2.setListeMediums(lm);

        List<Employe> le = new LinkedList<>();
        le.add(e1);
        le.add(e2);
        m.setlisteEmployes(le);
        t.setlisteEmployes(le);

        Client c = new Client("Jacque", "Jean", "Animal", new Date(1984, 1, 4), "Villeurbanne", "052554", "bonjoour.mal", "admin123azerty", null);

        Voyance v = new Voyance(0, "test", c, m, e2, new Date());

        s.inscrireClient(c);
        s.mettreAJourMedium(m);
        s.mettreAJourMedium(t);
        s.mettreAJourEmploye(e1);
        s.mettreAJourEmploye(e2);
        s.ajouterVoyance(v);

        //e1 attendu car e2 a déjà une voyance
        Voyance vv = s.demanderVoyance(c, m);
        Voyance vvv = s.commencerVoyance(vv);

        vvv.setDescription("C'etait cool.");
        Voyance vvvv = s.finirVoyance(vvv);

        DebugLogger.log(vvvv.toString());
    }

    public static void testGetClassementMediumPourEmploye() {
        Medium m = new Astrologue("Karmen", "Cool", null, null);
        Medium t = new Tarologue("Jean-Jacque", "joue aux cartes", null, null);
        Employe e1 = new Employe("BRES", "Steph", "M", "bs.@insa-lyon", "mdp123", "517881556");
        Client c = new Client("Jacque", "Jean", "Animal", new Date(1984, 1, 4), "Villeurbanne", "052554", "bonjoour.mal", "admin123azerty", null);
        Service s = new Service();
        s.ajouterMedium(m);
        s.ajouterMedium(t);
        s.ajouterEmploye(e1);
        s.inscrireClient(c);
        List<Medium> lm = new LinkedList<>();
        lm.add(m);
        lm.add(t);
        e1.setListeMediums(lm);

        List<Employe> le = new LinkedList<>();
        le.add(e1);
        m.setlisteEmployes(le);
        t.setlisteEmployes(le);

        Voyance v1 = new Voyance(2, "test", c, t, e1, new Date());
        Voyance v2 = new Voyance(2, "test", c, m, e1, new Date());
        Voyance v3 = new Voyance(2, "test", c, m, e1, new Date());

        s.ajouterVoyance(v2);
        s.ajouterVoyance(v3);
        s.ajouterVoyance(v1);

        for (Map.Entry<Medium, Integer> entry : s.getClassementMediumPourEmploye(e1).entrySet()) {
            DebugLogger.log(entry.getKey() + "/" + entry.getValue());
        }
    }

    public static void testGetClassementMedium() {
        Medium m = new Astrologue("Karmen", "Cool", null, null);
        Medium t = new Tarologue("Jean-Jacque", "joue aux cartes", null, null);
        Employe e1 = new Employe("BRES", "Steph", "M", "bs.@insa-lyon", "mdp123", "517881556");
        Employe e2 = new Employe("MARANZANA", "Math", "M", "mm.@insa-lyon", "mdp123", "54654");
        Service s = new Service();
        s.ajouterMedium(m);
        s.ajouterMedium(t);
        s.ajouterEmploye(e1);
        s.ajouterEmploye(e2);

        List<Medium> lm = new LinkedList<>();
        lm.add(m);
        lm.add(t);
        e1.setListeMediums(lm);
        e2.setListeMediums(lm);

        List<Employe> le = new LinkedList<>();
        le.add(e1);
        le.add(e2);
        m.setlisteEmployes(le);
        t.setlisteEmployes(le);

        Client c = new Client("Jacque", "Jean", "Animal", new Date(1984, 1, 4), "Villeurbanne", "052554", "bonjoour.mal", "admin123azerty", null);

        Voyance v1 = new Voyance(0, "test", c, m, e1, new Date());
        Voyance v2 = new Voyance(0, "test", c, t, e2, new Date());
        Voyance v3 = new Voyance(0, "test", c, m, e2, new Date());

        s.inscrireClient(c);
        s.mettreAJourMedium(m);
        s.mettreAJourMedium(t);
        s.mettreAJourEmploye(e1);
        s.mettreAJourEmploye(e2);
        s.ajouterVoyance(v1);
        s.ajouterVoyance(v2);
        s.ajouterVoyance(v3);

        for (Map.Entry<Medium, Integer> entry : s.getClassementMedium().entrySet()) {
            DebugLogger.log(entry.getKey() + "/" + entry.getValue());
        }
    }

    public static void testGetClassementEmploye() {
        Medium m = new Astrologue("Karmen", "Cool", null, null);
        Medium t = new Tarologue("Jean-Jacque", "joue aux cartes", null, null);
        Employe e1 = new Employe("BRES", "Steph", "M", "bs.@insa-lyon", "mdp123", "517881556");
        Employe e2 = new Employe("MARANZANA", "Math", "M", "mm.@insa-lyon", "mdp123", "54654");
        Service s = new Service();
        s.ajouterMedium(m);
        s.ajouterMedium(t);
        s.ajouterEmploye(e1);
        s.ajouterEmploye(e2);

        List<Medium> lm = new LinkedList<>();
        lm.add(m);
        lm.add(t);
        e1.setListeMediums(lm);
        e2.setListeMediums(lm);

        List<Employe> le = new LinkedList<>();
        le.add(e1);
        le.add(e2);
        m.setlisteEmployes(le);
        t.setlisteEmployes(le);

        Client c = new Client("Jacque", "Jean", "Animal", new Date(1984, 1, 4), "Villeurbanne", "052554", "bonjoour.mal", "admin123azerty", null);

        Voyance v1 = new Voyance(0, "test", c, m, e1, new Date());
        Voyance v2 = new Voyance(0, "test", c, t, e2, new Date());
        Voyance v3 = new Voyance(0, "test", c, m, e2, new Date());

        s.inscrireClient(c);
        s.mettreAJourMedium(m);
        s.mettreAJourMedium(t);
        s.mettreAJourEmploye(e1);
        s.mettreAJourEmploye(e2);
        s.ajouterVoyance(v1);
        s.ajouterVoyance(v2);
        s.ajouterVoyance(v3);

        for (Map.Entry<Employe, Integer> entry : s.getClassementEmploye().entrySet()) {
            DebugLogger.log(entry.getKey() + "/" + entry.getValue());
        }
    }

    public static void testComplet() {
        Service s = new Service();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        DebugLogger.log("=== Initialisation ==");
        s.init();

        DebugLogger.log("=== Inscription Client ==");
        String nom = Saisie.lireChaine("Nom : ");
        String prenom = Saisie.lireChaine("Prenom : ");
        String civilite = Saisie.lireChaine("Civilite : ");
        String mdp = Saisie.lireChaine("Mot de passe : ");
        String dateN = Saisie.lireChaine("Date Naissance (dd/MM/yyyy) : ");
        String adresse = Saisie.lireChaine("Adresse : ");
        String tel = Saisie.lireChaine("Téléphone : ");
        String mail = Saisie.lireChaine("Mail (identifiant) : ");

        try {
            Client c = new Client(nom, prenom, civilite, formatter.parse(dateN), adresse, tel, mail, mdp);
            s.inscrireClient(c);
        } catch (ParseException ex) {
            DebugLogger.log("Erreur new Client", ex);
            return;
        }

        DebugLogger.log("=== Connexion Client ===");
        mail = Saisie.lireChaine("Identifiant (mail) : ");
        mdp = Saisie.lireChaine("Mot de passe : ");
        Client c = s.connecterClient(mail, mdp);
        DebugLogger.log("Bonjour " + c.getCivilite() + " " + c.getPrenom() + " " + c.getNom());
        Saisie.pause();

        DebugLogger.log("=== Liste Mediums ===");
        List<Integer> valPossibles = new ArrayList<>();
        List<Medium> mDispo = s.getTousMediums();
        for (int i = 0; i < mDispo.size(); i++) {
            valPossibles.add(i);
            DebugLogger.log(i + " > " + mDispo.get(i).getNom());
        }

        DebugLogger.log("=== Demander Voyance ===");
        int choix = Saisie.lireInteger("Choix médium : ", valPossibles);
        Voyance v = s.demanderVoyance(c, mDispo.get(choix));
        DebugLogger.log(v.toString());
        DebugLogger.log("employé choisi : " + v.getEmploye().toString());

        DebugLogger.log("=== Connexion Employé ===");
        mail = Saisie.lireChaine("Identifiant (mail) : ");
        mdp = Saisie.lireChaine("Mot de passe : ");
        Employe e = s.connecterEmploye(mail, mdp);
        DebugLogger.log("Bonjour " + e.getCivilite() + " " + e.getPrenom() + " " + e.getNom());
        Saisie.pause();

        DebugLogger.log("=== Client Attente ===");
        Client cAttente = s.getClientEnAttente(e);
        DebugLogger.log("Vous avez un client en attente : " + cAttente);
        Saisie.pause();

        DebugLogger.log("=== Voyance Attente ===");
        Voyance vAttente = s.getVoyanceEnAttente(cAttente, e);
        DebugLogger.log("Vous avez une voyance en attente : " + vAttente);
        Saisie.pause();

        DebugLogger.log("=== Commencer Voyance ===");
        Voyance vCommencer = s.commencerVoyance(vAttente);
        DebugLogger.log(vCommencer.toString());
        Saisie.pause();

        DebugLogger.log("=== Prédictions ===");
        int niveau = Saisie.lireInteger("Niveau de la prédiction : ", Arrays.asList(1, 2, 3, 4));
        List<String> p = s.getPredictions(cAttente, niveau);
        DebugLogger.log("Amour : " + p.get(0));
        DebugLogger.log("Santé : " + p.get(1));
        DebugLogger.log("Travail : " + p.get(2));
        Saisie.pause();

        DebugLogger.log("=== Finir Voyance ===");
        String description = Saisie.lireChaine("Commentaire : ");
        vCommencer.setDescription(description);
        Voyance vFini = s.finirVoyance(vCommencer);
        DebugLogger.log(vFini.toString());
        Saisie.pause();

        DebugLogger.log("=== Classement des médiums de l'employé ===");
        Map<Medium, Integer> classementMedEmp = s.getClassementMediumPourEmploye(e);
        for (Map.Entry<Medium, Integer> entry : classementMedEmp.entrySet()) {
            DebugLogger.log(entry.getKey().getNom() + " - " + entry.getValue());
        }
        Saisie.pause();

        DebugLogger.log("=== Classement des médiums de l'entreprise ===");
        Map<Medium, Integer> classementMed = s.getClassementMedium();
        for (Map.Entry<Medium, Integer> entry : classementMed.entrySet()) {
            DebugLogger.log(entry.getKey().getNom() + " - " + entry.getValue());
        }
        Saisie.pause();

        DebugLogger.log("=== Classement des employés de l'entreprise ===");
        Map<Employe, Integer> classementEmp = s.getClassementEmploye();
        for (Map.Entry<Employe, Integer> entry : classementEmp.entrySet()) {
            DebugLogger.log(entry.getKey().getNom() + " - " + entry.getValue());
        }
        Saisie.pause();

    }
}
