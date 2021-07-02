package T04_InterfacesAndAbstraction.Exercise.P05_Telephony;

import java.util.List;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder builder = new StringBuilder();
        for (String url : urls) {
            if (URLIsValid(url)) {
                builder.append(String.format("Browsing: %s!", url));
                builder.append(System.lineSeparator());
            } else {
                builder.append("Invalid URL!");
            }
        }
        return builder.toString().trim();
    }

    @Override
    public String call() {
        StringBuilder builder = new StringBuilder();
        for (String number : numbers) {
            if (numberIsValid(number)) {
                builder.append(String.format("Calling... %s", number));
                builder.append(System.lineSeparator());
            } else {
                builder.append("Invalid number!");
            }
        }
        return builder.toString().trim();
    }

    private static boolean numberIsValid(String number) {
        for (int i = 0; i < number.length(); i++) {
            char current = number.charAt(i);
            if (!Character.isDigit(current)) {
                return false;
            }
        }
        return true;
    }

    private static boolean URLIsValid(String url) {
        for (int i = 0; i < url.length(); i++) {
            char current = url.charAt(i);
            if (Character.isDigit(current)) {
                return false;
            }
        }
        return true;
    }
}
