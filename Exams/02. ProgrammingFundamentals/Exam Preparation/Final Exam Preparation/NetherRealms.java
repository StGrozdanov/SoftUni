package FundamentalsFinalExamPreparation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NetherRealms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> input = Arrays.stream(scanner.nextLine().split("\\s*,\\s*")).collect(Collectors.toList());
        String hpRegex = "[^-\\d*+\\/.\\s]";
        String dmgRegex = "[-+]?[\\d]+(\\.\\d+)?";
        Pattern pattern1 = Pattern.compile(hpRegex);
        Pattern pattern2 = Pattern.compile(dmgRegex);

        Map<String, Map<Integer, Double>> demonStats = new TreeMap<>();

        for (String demon : input) {
            int hpSum = 0;
            double damage = 0;
            Matcher matcher1 = pattern1.matcher(demon);
            while (matcher1.find()) {
                String name = matcher1.group();
                for (int i = 0; i < name.length(); i++) {
                    char current = name.charAt(i);
                    hpSum += current;
                }
            }
            Matcher matcher2 = pattern2.matcher(demon);
            while (matcher2.find()) {
                double num = Double.parseDouble(matcher2.group());
                damage += num;
            }
            for (int i = 0; i < demon.length(); i++) {
                char current = demon.charAt(i);
                if (String.valueOf(current).equals("*") && damage != 0 ||
                        String.valueOf(current).equals("/") && damage != 0) {
                    if (String.valueOf(current).equals("*")) {
                        damage *= 2;
                    } else {
                        damage /= 2;
                    }
                }
            }
            demonStats.put(demon, new LinkedHashMap<>());
            demonStats.get(demon).put(hpSum, damage);
        }
        for (Map.Entry<String, Map<Integer, Double>> entry : demonStats.entrySet()) {
            String name = entry.getKey();
            for (Map.Entry<Integer, Double> value : entry.getValue().entrySet()) {
                int hp = value.getKey();
                double dmg = value.getValue();
                System.out.printf("%s - %d health, %.2f damage%n", name, hp, dmg);
            }
        }
    }
}
