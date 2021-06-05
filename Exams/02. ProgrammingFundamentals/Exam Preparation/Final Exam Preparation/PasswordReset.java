package FundamentalsFinalExamPreparation;

import java.util.Scanner;

public class PasswordReset {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder password = new StringBuilder(scanner.nextLine());
        String input = scanner.nextLine();
        while (!"Done".equals(input)) {
            String[] tokens = input.split(" ");
            String cmd = tokens[0];
            switch (cmd) {
                case "TakeOdd":
                    StringBuilder newPass = new StringBuilder();
                    for (int i = 0; i < password.length(); i++) {
                        if (i % 2 != 0){
                            char text = password.charAt(i);
                            newPass.append(text);
                        }
                    }
                    password = newPass;
                    System.out.println(newPass);
                    break;
                case "Cut":
                    int index = Integer.parseInt(tokens[1]);
                    int length = Integer.parseInt(tokens[2]);
                    password.replace(index, index+(length), "");
                    System.out.println(password);
                    break;
                case "Substitute":
                    String substring = tokens[1];
                    String substitute = tokens[2];
                    if (password.toString().contains(substring)){
                        String newPassword = password.toString().replace(substring, substitute);
                        password.setLength(0);
                        password.append(newPassword);
                        System.out.println(password);
                    } else {
                        System.out.println("Nothing to replace!");
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.printf("Your password is: %s", password);
    }
}
