import java.util.Random;

public class Voleur extends Combattant {

    private int esquive;
    private Random rand = new Random();

    public Voleur(String nom, int pvMax, int attaque, int defense, int esquive) {
        super(nom, pvMax, attaque, defense);
        if (esquive < 10 || esquive > 40) {
            throw new IllegalArgumentException("L'esquive doit etre entre 10 et 40%, recu : " + esquive);
        }
        this.esquive = esquive;
    }

    @Override
    public int attaquer(Combattant cible) {
        int degats = getAttaque();

        if (rand.nextInt(100) < 25) {
            degats *= 2;
            System.out.println(getNom() + " execute une Attaque deux fois plus babie !");
        }

        cible.subirDegats(degats);
        return degats;
    }

    @Override
    public void subirDegats(int d) {
        if (rand.nextInt(100) < esquive) {
            System.out.println(getNom() + " a dispa l'attaque !");
        } else {
            super.subirDegats(d);
        }
    }

    @Override
    public String getClasse() {
        return "Voleur";
    }
}