import java.util.Random;

public class StatsArene {


    public static double moyenne(int[] t) {
        int somme = 0;
        for (int i = 0; i < t.length; i++) {
            somme += t[i];
        }
        return (double) somme / t.length;
    }


    public static int max(int[] t) {
        int maximum = t[0];
        for (int i = 1; i < t.length; i++) {
            if (t[i] > maximum) {
                maximum = t[i];
            }
        }
        return maximum;
    }


    public static int min(int[] t) {
        int minimum = t[0];
        for (int i = 1; i < t.length; i++) {
            if (t[i] < minimum) {
                minimum = t[i];
            }
        }
        return minimum;
    }


    public static void trierDecroissant(int[] t) {
        int n = t.length;
        int echangestotal = 0;
        boolean echange;

        for (int i = 0; i < n - 1; i++) {
            echange = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (t[j] < t[j + 1]) {

                    int temp = t[j];
                    t[j] = t[j + 1];
                    t[j + 1] = temp;
                    echange = true;
                    echangestotal++;
                }
            }

            if (!echange) {
                break;
            }
        }
        System.out.println("Tri termine en " + echangestotal + " echanges.");
    }


    public static int[] sansDoublons(int[] t) {
        int[] temp = new int[t.length];
        int taille = 0;

        for (int i = 0; i < t.length; i++) {
            boolean existe = false;
            for (int j = 0; j < taille; j++) {
                if (t[i] == temp[j]) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                temp[taille] = t[i];
                taille++;
            }
        }


        int[] resultat = new int[taille];
        for (int i = 0; i < taille; i++) {
            resultat[i] = temp[i];
        }
        return resultat;
    }


    public static char[][] genererGrille() {
        char[][] grille = new char[8][8];
        Random rand = new Random();


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grille[i][j] = '.';
            }
        }


        int obstacles = 0;
        while (obstacles < 6) {
            int r = rand.nextInt(8);
            int c = rand.nextInt(8);
            if (grille[r][c] == '.') {
                grille[r][c] = '#';
                obstacles++;
            }
        }


        placerElement(grille, 'A', rand);
        placerElement(grille, 'B', rand);

        return grille;
    }

    private static void placerElement(char[][] grille, char c, Random rand) {
        while (true) {
            int r = rand.nextInt(8);
            int col = rand.nextInt(8);
            if (grille[r][col] == '.') {
                grille[r][col] = c;
                break;
            }
        }
    }

    public static void afficherGrille(char[][] grille) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(grille[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static int distance(char[][] grille) {
        int xA = -1, yA = -1;
        int xB = -1, yB = -1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grille[i][j] == 'A') {
                    xA = i;
                    yA = j;
                } else if (grille[i][j] == 'B') {
                    xB = i;
                    yB = j;
                }
            }
        }
        return Math.abs(xA - xB) + Math.abs(yA - yB);
    }
}