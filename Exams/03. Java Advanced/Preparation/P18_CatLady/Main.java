package MidExamPreparation.P18_CatLady;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Cymric> cyramicCats = new ArrayList<>();
        List<Siamese> siameseCats = new ArrayList<>();
        List<StreetExtraordinaire> streetCats = new ArrayList<>();

        String input = scanner.nextLine();

        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");
            String type = tokens[0];
            String name = tokens[1];
            double value = Double.parseDouble(tokens[2]);
            switch (type) {
                case "StreetExtraordinaire":
                    StreetExtraordinaire cat = new StreetExtraordinaire(name, value);
                    streetCats.add(cat);
                    break;
                case "Siamese":
                    Siamese sCat = new Siamese(name, value);
                    siameseCats.add(sCat);
                    break;
                case "Cymric":
                    Cymric cCat = new Cymric(name, value);
                    cyramicCats.add(cCat);
                    break;
            }
            input = scanner.nextLine();
        }

        String catTarget = scanner.nextLine();

        Cymric cCat = cyramicCats.stream().filter(c -> c.getName().equals(catTarget)).findFirst().orElse(null);
        Siamese sCat = siameseCats.stream().filter(c -> c.getName().equals(catTarget)).findFirst().orElse(null);
        StreetExtraordinaire stCat = streetCats.stream().filter(c -> c.getName().equals(catTarget)).findFirst().orElse(null);

        if (cCat != null) {
            System.out.println(cCat);
        }
        if (sCat != null) {
            System.out.println(sCat);
        }
        if (stCat != null) {
            System.out.println(stCat);
        }
    }
}
