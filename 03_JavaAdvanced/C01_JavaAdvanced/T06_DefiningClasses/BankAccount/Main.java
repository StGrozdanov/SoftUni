package T06_DefiningClasses.Lab.BankAccount;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<Integer, BankAccount> idAccounts = new LinkedHashMap<>();

        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];
            switch (command) {
                case "Create":
                    BankAccount account = new BankAccount();
                    idAccounts.put(account.getId(), account);
                    System.out.printf("Account ID%d created%n", account.getId());
                    break;
                case "Deposit":
                    int id = Integer.parseInt(tokens[1]);
                    double amount = Double.parseDouble(tokens[2]);
                    if (idAccounts.containsKey(id)) {
                        idAccounts.get(id).deposit(amount);
                        System.out.printf("Deposited %.0f to ID%d%n", amount, id);
                    } else {
                        System.out.println("Account does not exist");
                    }
                    break;
                case "SetInterest":
                    double interest = Double.parseDouble(tokens[1]);
                    BankAccount.setInterestRate(interest);
                    break;
                case "GetInterest":
                    int interestId = Integer.parseInt(tokens[1]);
                    int years = Integer.parseInt(tokens[2]);
                    if (idAccounts.containsKey(interestId)) {
                        double result = idAccounts.get(interestId).getInterestRate(years);
                        System.out.println(new DecimalFormat("0.00").format(result));
                    } else {
                        System.out.println("Account does not exist");
                    }
                    break;
            }
            input = scanner.nextLine();
        }
    }
}
