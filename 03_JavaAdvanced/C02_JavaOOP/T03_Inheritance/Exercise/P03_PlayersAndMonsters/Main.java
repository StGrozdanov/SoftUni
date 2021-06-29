package T03_Inheritance.Exercise.P03_PlayersAndMonsters;

public class Main {
    public static void main(String[] args) {
        BladeKnight knight = new BladeKnight("RevolvE", 400);
        SoulMaster wiz = new SoulMaster("MPAK", 500);

        System.out.println(knight);
        System.out.println(wiz);

    }
}
