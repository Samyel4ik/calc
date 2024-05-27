import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическую операцию");
        String input = scanner.nextLine();

        System.out.println(calc(input));
    }

    public static String calc(String input) {
        try {

            validateExpression(input);
            String[] array = convertingStringToArray(input);


            int number1 = Integer.parseInt(array[0]);
            int number2 = Integer.parseInt(array[2]);

            isValidNumber(number1);
            isValidNumber(number2);

            int result = switch (array[1]) {
                case "+" -> number1 + number2;
                case "-" -> number1 - number2;
                case "*" -> number1 * number2;
                case "/" -> number1 / number2;
                default -> 0;
            };

            return String.valueOf(result);
        } catch (Exception e) {
            return "Ошибка: " + e.getMessage();
        }
    }

    public static String[] convertingStringToArray(String input) {
        return input.split(" ");
    }

    public static void isValidNumber(int number) throws Exception {
        if (!(number >= 1 && number <= 10 && number % 1 == 0)) {
            throw new Exception("Число должно быть целым и находиться в диапазоне от 1 до 10");
        }
    }
    public static boolean validateExpression(String expression) {

        String regex = "^\\s*(\\d+)\\s*([+\\-*/])\\s*(\\d+)\\s*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);

        if (!matcher.matches()) {
            try {
                throw new Exception("Неверный формат либо знак : " + expression);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return matcher.matches();
    }
}