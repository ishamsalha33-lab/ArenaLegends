import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class Tournoi {

    private ArrayList<Combattant> participants = new ArrayList<>();
    private Random rand = new Random();

   
    private void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean inscrire(Combattant c) {
        if (participants.size() >= 8) {
            System.out.println("Inscription refusée : Tournoi plein (max 8).");
            return false;
        }
        for (Combattant p : participants) {
            if (p.getNom().equalsIgnoreCase(c.getNom())) {
                System.out.println("Inscription refusée : Nom déjà utilisé.");
                return false;
            }
        }
        participants.add(c);
        return true;
    }

    public boolean desinscrire(String nom) {
        Iterator<Combattant> it = participants.iterator();
        while (it.hasNext()) {
            Combattant c = it.next();
            if (c.getNom().equalsIgnoreCase(nom)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public ArrayList<Combattant> getParticipants() {
        return new ArrayList<>(participants);
    }

    public Combattant duel(Combattant a, Combattant b) {
        System.out.println("\n--- DUEL : " + a.getNom() + " VS " + b.getNom() + " ---");
        pause(1000);

       
        Combattant premier, second;
        if (a.getAttaque() > b.getAttaque()) {
            premier = a;
            second = b;
        } else if (b.getAttaque() > a.getAttaque()) {
            premier = b;
            second = a;
        } else {
            premier = rand.nextBoolean() ? a : b;
            second = (premier == a) ? b : a;
        }

        System.out.println(premier.getNom() + " prend l'initiative et attaque en premier !");
        pause(800);

        int tour = 1;
        while (!premier.estKO() && !second.estKO() && tour <= 50) {
            System.out.println("\n[Tour " + tour + "]");
            
           
            premier.attaquer(second);
            System.out.println("  -> " + second.getNom() + " a " + second.getPv() + "/" + second.getPvMax() + " PV");
            pause(600);

           
            if (!second.estKO()) {
                second.attaquer(premier);
                System.out.println("  -> " + premier.getNom() + " a " + premier.getPv() + "/" + premier.getPvMax() + " PV");
                pause(600);
            }

       
            if (rand.nextInt(100) < 15 && !premier.estKO() && !second.estKO()) {
                System.out.println("⚡ Le public s'enflamme et redonne du courage aux combattants !");
                pause(500);
            }

            tour++;
        }

        Combattant vainqueur;
        if (tour > 50 && !premier.estKO() && !second.estKO()) {
            double pctP1 = (double) premier.getPv() / premier.getPvMax();
            double pctP2 = (double) second.getPv() / second.getPvMax();
            vainqueur = (pctP1 >= pctP2) ? premier : second;
            System.out.println("\nFin des 50 tours ! Victoire aux points pour " + vainqueur.getNom());
        } else {
            vainqueur = premier.estKO() ? second : premier;
            System.out.println("\n💥 " + vainqueur.getNom() + " remporte le duel !");
        }

        vainqueur.ajouterVictoire();
        pause(1200);
        return vainqueur;
    }

    public Combattant lancer() {
        if (participants.size() < 2) {
            System.out.println("Pas assez de participants !");
            return null;
        }

        ArrayList<Combattant> enLice = new ArrayList<>(participants);
        Collections.shuffle(enLice);

        int tourNum = 1;
        while (enLice.size() > 1) {
            System.out.println("\n==================================");
            System.out.println("           TOUR " + tourNum);
            System.out.println("==================================");
            pause(1500);

            ArrayList<Combattant> vainqueurs = new ArrayList<>();

            for (int i = 0; i < enLice.size(); i += 2) {
                Combattant a = enLice.get(i);
                Combattant b = enLice.get(i + 1);

                Combattant vainqueur = duel(a, b);
                vainqueur.reinitialiserPV();
                vainqueurs.add(vainqueur);
            }

            enLice = vainqueurs;
            tourNum++;
        }

        Combattant champion = enLice.get(0);
        System.out.println("\n🏆 LE CHAMPION DU TOURNOI EST : " + champion.getNom() + " ! 🏆");
        return champion;
    }

    public ArrayList<Combattant> classement() {
        ArrayList<Combattant> liste = new ArrayList<>(participants);

        for (int i = 0; i < liste.size() - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < liste.size(); j++) {
                if (liste.get(j).getVictoires() > liste.get(maxIdx).getVictoires()) {
                    maxIdx = j;
                }
            }
            Combattant temp = liste.get(i);
            liste.set(i, liste.get(maxIdx));
            liste.set(maxIdx, temp);
        }

        return liste;
    }

    public void statsParClasse() {
        int victoiresGuerrier = 0;
        int victoiresMage = 0;
        int victoiresVoleur = 0;
        int victoiresPaladin = 0;

        for (Combattant c : participants) {
            String classe = c.getClasse();
            if (classe.equals("Guerrier")) victoiresGuerrier += c.getVictoires();
            else if (classe.equals("Mage")) victoiresMage += c.getVictoires();
            else if (classe.equals("Voleur")) victoiresVoleur += c.getVictoires();
            else if (classe.equals("Paladin")) victoiresPaladin += c.getVictoires();
        }

        System.out.println("\n--- VICTOIRES CUMULÉES PAR CLASSE ---");
        System.out.println("Guerriers : " + victoiresGuerrier);
        System.out.println("Mages     : " + victoiresMage);
        System.out.println("Voleurs   : " + victoiresVoleur);
        System.out.println("Paladins  : " + victoiresPaladin);
    }
}