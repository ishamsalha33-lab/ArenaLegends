import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int choix = -1;

        do {
            System.out.println("\n--- ARENA LEGENDS --- (Par FAI)");
            System.out.println("1. Lancer un de pour la destiner");
            System.out.println("2. Calculer un rang parmis les meilleurs");
            System.out.println("3. Test de coup critique je sais pas quoi dire en vrai");
            System.out.println("4. Statistiques de l'arene en sah !");
            System.out.println("5. Lancer le Tournoi complet oe en fait jsp");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");

            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
            } else {
                System.out.println("Oops ! Veuillez entrer un nombre valide !");
                scanner.next();
                continue;
            }

            switch (choix) {
                case 1:
                    lancerDe(scanner, random);
                    break;
                case 2:
                    calculerRang(scanner);
                    break;
                case 3:
                    testerCoupCritique(random);
                    break;
                case 4:
                    testerStatsArene();
                    break;
                case 5:
                    lancerTournoi();
                    break;
                case 0:
                    System.out.println("Ciao !");
                    break;
                default:
                    System.out.println("Mince ! Option invalide.");
            }
        } while (choix != 0);

        scanner.close();
    }

    private static void lancerDe(Scanner scanner, Random random) {
        int faces = 0;
        while (faces < 4 || faces > 20) {
            System.out.print("Nombre de faces du de (entre 4 et 20) : ");
            if (scanner.hasNextInt()) {
                faces = scanner.nextInt();
                if (faces < 4 || faces > 20) {
                    System.out.println("Oh Non! Nombre incorrect. Recommencez SVP.");
                }
            } else {
                System.out.println("Veuillez entrer un nombre.");
                scanner.next();
            }
        }
        int resultat = random.nextInt(faces) + 1;
        System.out.println("Resultat du de a " + faces + " faces : " + resultat);
    }

    private static void calculerRang(Scanner scanner) {
        System.out.print("Entrez le nombre de points : ");
        if (!scanner.hasNextInt()) {
            System.out.println("Merde Saisie invalide.");
            scanner.next();
            return;
        }
        int pts = scanner.nextInt();

        if (pts < 0) {
            System.out.println("euhhh chef!? Les points ne peuvent pas etre negatifs. reflechis et recommence");
        } else if (pts < 100) {
            System.out.println("Rang : Bronze t'es Eteint!");
        } else if (pts <= 499) {
            System.out.println("Rang : Argent toujours aussi guez");
        } else if (pts <= 1499) {
            System.out.println("Rang : Or bof tu pue quand meme");
        } else {
            System.out.println("Rang : Legende il es bon il es bon");
        }
    }

    private static void testerCoupCritique(Random random) {
        int totalCritiques = 0;
        int serieActuelle = 0;
        int plusLongueSerie = 0;

        for (int i = 0; i < 10000; i++) {
            boolean estCritique = random.nextInt(100) < 15;

            if (estCritique) {
                totalCritiques++;
                serieActuelle++;
                if (serieActuelle > plusLongueSerie) {
                    plusLongueSerie = serieActuelle;
                }
            } else {
                serieActuelle = 0;
            }
        }

        double pourcentage = (totalCritiques / 10000.0) * 100;
        System.out.println("Nombre de coups critiques : " + totalCritiques + " / 10000");
        System.out.println("Pourcentage reel : " + pourcentage + "%");
        System.out.println("Plus longue critical streak : " + plusLongueSerie);
    }

    private static void testerStatsArene() {
        int[] scores = {42, 87, 15, 99, 63, 87, 5, 71, 99, 34, 50, 28};

        System.out.println("\n--- STATISTIQUES DE L'ARENE ---");
        System.out.println("Moyenne : " + StatsArene.moyenne(scores));
        System.out.println("Max : " + StatsArene.max(scores));
        System.out.println("Min : " + StatsArene.min(scores));

        int[] sansDoublons = StatsArene.sansDoublons(scores);
        System.out.print("Tableau sans doublons (" + sansDoublons.length + " cases) : ");
        for (int val : sansDoublons) {
            System.out.print(val + " ");
        }
        System.out.println();

        System.out.println("\nTri a bulles decroissant :");
        StatsArene.trierDecroissant(scores);

        System.out.println("\n--- GRILLE DE L'ARENE ---");
        char[][] grille = StatsArene.genererGrille();
        StatsArene.afficherGrille(grille);
        System.out.println("Distance de Manhattan entre A et B : " + StatsArene.distance(grille));
    }

    private static void lancerTournoi() {
        Tournoi tournoi = new Tournoi();

        tournoi.inscrire(new Guerrier("Haaland", 150, 25, 10));
        tournoi.inscrire(new Guerrier("Brobbey", 160, 22, 12));
        tournoi.inscrire(new Mage("Kevin DeBruyne", 100, 30, 5));
        tournoi.inscrire(new Mage("Cherki", 95, 35, 4));
        tournoi.inscrire(new Voleur("Messi", 110, 20, 8, 30));
        tournoi.inscrire(new Voleur("Ronaldo", 105, 24, 6, 25));
        tournoi.inscrire(new Paladin("Mbappe", 140, 20, 15));
        tournoi.inscrire(new Paladin("Manstantuono", 145, 18, 18));

        tournoi.lancer();

        System.out.println("\n--- CLASSEMENT DU TOURNOI ---");
        for (Combattant c : tournoi.classement()) {
            System.out.println(c.getNom() + " (" + c.getClasse() + ") - Victoires : " + c.getVictoires());
        }

        tournoi.statsParClasse();
    }
}