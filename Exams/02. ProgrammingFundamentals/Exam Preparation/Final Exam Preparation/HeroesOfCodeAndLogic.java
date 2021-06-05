package FundamentalsFinalExamPreparation;

import java.util.*;

public class HeroesOfCodeAndLogic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<Integer>> heroes = new LinkedHashMap<>();
        int HPCap = 100;
        int MPCap = 200;

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String heroName = tokens[0];
            int HP = Integer.parseInt(tokens[1]);
            int MP = Integer.parseInt(tokens[2]);
            if (HP > HPCap) {
                HP = HPCap;
            }
            if (MP > MPCap) {
                MP = MPCap;
            }
            heroes.put(heroName, new ArrayList<>());
            heroes.get(heroName).add(HP);
            heroes.get(heroName).add(MP);
        }

        String input = scanner.nextLine();

        while (!"End".equals(input)) {
            String[] tokens = input.split(" - ");
            String cmd = tokens[0];
            String heroName = tokens[1];

            switch (cmd) {
                case "CastSpell":
                    int MPNeeded = Integer.parseInt(tokens[2]);
                    String spellName = tokens[3];
                    int currentMP = heroes.get(heroName).get(1);
                    if (currentMP >= MPNeeded) {
                        currentMP -= MPNeeded;
                        System.out.printf("%s has successfully cast %s and now has %d MP!%n",
                                heroName, spellName, currentMP);
                        heroes.get(heroName).set(1, currentMP);
                    } else {
                        System.out.printf("%s does not have enough MP to cast %s!%n",
                                heroName, spellName);
                    }
                    break;
                case "TakeDamage":
                    int damage = Integer.parseInt(tokens[2]);
                    String attacker = tokens[3];
                    int currentHp = heroes.get(heroName).get(0);
                    int HpAfterDamage = currentHp - damage;
                    if (HpAfterDamage > 0) {
                        System.out.printf("%s was hit for %d HP by %s and now has %d HP left!%n",
                                heroName, damage, attacker, HpAfterDamage);
                        heroes.get(heroName).set(0, HpAfterDamage);
                    } else {
                        System.out.printf("%s has been killed by %s!%n", heroName, attacker);
                        heroes.remove(heroName);
                    }
                    break;
                case "Recharge":
                    int MPRecover = Integer.parseInt(tokens[2]);
                    int currentMana = heroes.get(heroName).get(1);
                    int newMP = currentMana + MPRecover;
                    int amountRecovered;
                    if (newMP > MPCap) {
                        newMP = MPCap;
                        amountRecovered = newMP - currentMana;
                    } else {
                        amountRecovered = MPRecover;
                    }
                    heroes.get(heroName).set(1, newMP);
                    System.out.printf("%s recharged for %d MP!%n", heroName, amountRecovered);
                    break;
                case "Heal":
                    int HPRecover = Integer.parseInt(tokens[2]);
                    int currentHP = heroes.get(heroName).get(0);
                    int newHP = currentHP + HPRecover;
                    int amountHPRecovered;
                    if (newHP > HPCap) {
                        newHP = HPCap;
                        amountHPRecovered = newHP - currentHP;
                    } else {
                        amountHPRecovered = HPRecover;
                    }
                    heroes.get(heroName).set(0, newHP);
                    System.out.printf("%s healed for %d HP!%n", heroName, amountHPRecovered);
                    break;
            }
            input = scanner.nextLine();
        }
        if (heroes.size() > 0) {
            heroes.entrySet().stream().sorted((h1, h2) -> {
                int result = h2.getValue().get(0).compareTo(h1.getValue().get(0));
                if (result == 0) {
                    result = h1.getKey().compareTo(h2.getKey());
                }
                return result;
            }).forEach(x -> {
                System.out.println(x.getKey());
                System.out.println("HP: " + x.getValue().get(0));
                System.out.println("MP: " + x.getValue().get(1));
            });
        }
    }
}
