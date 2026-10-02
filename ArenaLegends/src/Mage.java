public class Mage extends Combattant {

    private int mana = 100;

    public Mage(String nom, int pvMax, int attaque, int defense) {
        super(nom, pvMax, attaque, defense);
    }

    public int getMana() {
        return mana;
    }

    @Override
    public int attaquer(Combattant cible) {
        int degats;

        if (mana >= 30) {
            mana -= 30;
            degats = getAttaque() * 2;
            System.out.println(getNom() + " lance une Patate enflamme !");
            cible.subirDegatsBruts(degats);
        } else {
            degats = Math.max(1, getAttaque() / 2);
            mana = Math.min(100, mana + 15);
            System.out.println(getNom() + " n'a plus energie bro et frappe avec son baton.");
            cible.subirDegats(degats);
        }

        return degats;
    }

    @Override
    public String getClasse() {
        return "Mage";
    }
}