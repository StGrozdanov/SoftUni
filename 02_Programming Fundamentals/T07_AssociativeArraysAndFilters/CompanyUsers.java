package AssociativeArraysFiltersExercise12;

import java.util.*;

public class CompanyUsers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> companyData = new LinkedHashMap<>();
        String input = scanner.nextLine();

        while(!"End".equals(input)){
            String[] tokens = input.split(" -> ");
            String company = tokens[0];
            String employee = tokens[1];

            companyData.putIfAbsent(company, new ArrayList<>());
            if(!companyData.get(company).contains(employee)){
                companyData.get(company).add(employee);
            }
            input = scanner.nextLine();
        }
        companyData.entrySet().stream().sorted((c1, c2) -> c1.getKey().compareTo(c2.getKey())).
                forEach(x -> {
                    System.out.println(x.getKey());
                    x.getValue().forEach(y -> System.out.println("-- " + y));
                });
    }
}