package FundamentalsFinalExamPreparation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FancyBarcodes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String criteria = "^@[#]+[A-Z][A-Za-z0-9]{4,}[A-Z]@[#]+$";
        Pattern pattern = Pattern.compile(criteria);

        for (int i = 0; i < n; i++) {
            String text = scanner.nextLine();
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()){
                String validBarcode = matcher.group();
                StringBuilder productGroup = new StringBuilder();
                for (int j = 0; j < validBarcode.length(); j++) {
                    char current = validBarcode.charAt(j);
                    if (Character.isDigit(current)){
                        productGroup.append(current);
                    }
                }
                if (productGroup.toString().isEmpty()){
                    productGroup.append("00");
                }
                System.out.println("Product group: " + productGroup);
            } else {
                System.out.println("Invalid barcode");
            }
        }
    }
}
