public class Paladin extends Guerrier {

    public Paladin(String nom, int pvMax, int attaque, int defense) {
        super(nom, pvMax, attaque, defense);
    }

    @Override
    public int attaquer(Combattant cible) {
        int degatsInfliges = super.attaquer(cible);

        int soin = (int) (degatsInfliges * 0.10);
        if (soin > 0) {
            soigner(soin);
            System.out.println(getNom() + " se soigne de " + soin + " PV grace a son doliprane.");
        }

        return degatsInfliges;
    }

    @Override
    public String getClasse() {
        return "Paladin";
    }
}