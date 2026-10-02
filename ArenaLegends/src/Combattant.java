import java.util.Arrays;

public abstract class Combattant {

  
    private String nom;
    private int pvMax;
    private int pv;
    private int attaque;
    private int defense;
    private int victoires;
    private int[] historiqueDegats = new int[5];
    private int indexHistorique = 0;

   
    private static int nbCombattants = 0;


    public Combattant(String nom, int pvMax, int attaque, int defense) {
        if (nom == null || nom.length() < 3 || nom.length() > 15) {
            throw new IllegalArgumentException("Le nom doit faire entre 3 et 15 caracteres, recu : " + nom);
        }
        if (pvMax < 50 || pvMax > 300) {
            throw new IllegalArgumentException("pvMax doit etre entre 50 et 300, recu : " + pvMax);
        }
        if (attaque < 5 || attaque > 50) {
            throw new IllegalArgumentException("attaque doit etre entre 5 et 50, recu : " + attaque);
        }
        if (defense < 0 || defense > 30) {
            throw new IllegalArgumentException("defense doit etre entre 0 et 30, recu : " + defense);
        }

        this.nom = nom;
        this.pvMax = pvMax;
        this.pv = pvMax; 
        this.attaque = attaque;
        this.defense = defense;
        this.victoires = 0;

        nbCombattants++;
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public int getPvMax() {
        return pvMax;
    }

    public int getPv() {
        return pv;
    }

    public int getAttaque() {
        return attaque;
    }

    public int getDefense() {
        return defense;
    }

    public int getVictoires() {
        return victoires;
    }

    public static int getNbCombattants() {
        return nbCombattants;
    }


    public int[] getHistoriqueDegats() {
        return Arrays.copyOf(historiqueDegats, historiqueDegats.length);
    }


    public void subirDegats(int d) {
        int degatsReels = Math.max(1, d - defense);
        this.pv = Math.max(0, this.pv - degatsReels);

   
        historiqueDegats[indexHistorique % 5] = degatsReels;
        indexHistorique++;
    }


    protected void subirDegatsBruts(int d) {
        int degatsReels = Math.max(1, d);
        this.pv = Math.max(0, this.pv - degatsReels);

        historiqueDegats[indexHistorique % 5] = degatsReels;
        indexHistorique++;
    }

    public void soigner(int s) {
        if (estKO()) {
            System.out.println(nom + " est K.O. et ne peut pas etre soigne ! fallait pas ecouter les texte!");
            return;
        }
        this.pv = Math.min(pvMax, this.pv + s);
    }

    public void reinitialiserPV() {
        this.pv = this.pvMax;
    }

    public boolean estKO() {
        return this.pv == 0;
    }

    public void ajouterVictoire() {
        this.victoires++;
    }


    public abstract int attaquer(Combattant cible);


    public abstract String getClasse();

    @Override
    public String toString() {
        return getClasse() + " " + nom + " [" + pv + "/" + pvMax + " PV] ATK " + attaque + " DEF " + defense;
    }
}