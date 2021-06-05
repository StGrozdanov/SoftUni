package FundamentalsFinalExamPreparation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WinningTicket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tickets = scanner.nextLine().split("[\\s,]+");
        String criteria = "[\\w\\d]*[@#$^]{6,10}[\\w\\d]*[@#$^]{6,10}[\\w\\d]*";
        Pattern pattern = Pattern.compile(criteria);

        for (String ticket : tickets) {
            if (ticket.length() != 20) {
                System.out.println("invalid ticket");
            } else {
                Matcher matcher = pattern.matcher(ticket);
                if (matcher.find()) {
                    String winningTicket = String.join("", matcher.group().split("[\\w\\d]+"));

                    List<String> monkey = new ArrayList<>();
                    List<String> dies = new ArrayList<>();
                    List<String> dollar = new ArrayList<>();
                    List<String> upper = new ArrayList<>();

                    for (int i = 0; i < winningTicket.length(); i++) {
                        char current = winningTicket.charAt(i);
                        String currentChar = String.valueOf(current);
                        switch (currentChar) {
                            case "@":
                                monkey.add(currentChar);
                                break;
                            case "#":
                                dies.add(currentChar);
                                break;
                            case "$":
                                dollar.add(currentChar);
                                break;
                            case "^":
                                upper.add(currentChar);
                                break;
                        }
                    }
                    double length = 0;
                    String symbol = "";
                    if (monkey.size() < 6) {
                        monkey.clear();
                    } else {
                        length = monkey.size();
                        symbol = monkey.get(0);
                    }
                    if (dies.size() < 6) {
                        dies.clear();
                    } else {
                        length = dies.size();
                        symbol = dies.get(0);
                    }
                    if (dollar.size() < 6) {
                        dollar.clear();
                    } else {
                        length = dollar.size();
                        symbol = dollar.get(0);
                    }
                    if (upper.size() < 6) {
                        upper.clear();
                    } else {
                        length = upper.size();
                        symbol = upper.get(0);
                    }
                    length = length / 2;
                    if (length == 10) {
                        System.out.printf("ticket \"%s\" - 10%s Jackpot!%n", ticket, symbol);
                    } else {
                        System.out.printf("ticket \"%s\" - %.0f%s%n", ticket, length, symbol);
                    }
                } else {
                    System.out.printf("ticket \"%s\" - no match%n", ticket);
                }
            }
        }
    }
}
