public class Guerrier extends Combattant {

    private int rage = 0;

    public Guerrier(String nom, int pvMax, int attaque, int defense) {
        super(nom, pvMax, attaque, defense);
    }

    public int getRage() {
        return rage;
    }

    @Override
    public int attaquer(Combattant cible) {
        int degats = getAttaque();

        if (rage >= 100) {
            degats *= 2;
            rage = 0;
            System.out.println(getNom() + " lance un Coup babie ultra-puissant !");
        } else {
            rage = Math.min(100, rage + 20);
        }

        cible.subirDegats(degats);
        return degats;
    }

    @Override
    public String getClasse() {
        return "Guerrier";
    }
}