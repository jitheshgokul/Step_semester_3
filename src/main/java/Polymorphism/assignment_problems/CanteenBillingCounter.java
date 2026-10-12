import java.util.*;
import java.util.function.Function;

public class CanteenBillingCounter {
    abstract static class Customer {
        protected double amount;

        Customer(double amount) {
            this.amount = amount;
        }

        abstract double calculateFinalAmount();
    }

    static class Student extends Customer {
        Student(double amount) {
            super(amount);
        }

        @Override
        double calculateFinalAmount() {
            return amount * 0.90;
        }
    }

    static class Staff extends Customer {
        Staff(double amount) {
            super(amount);
        }

        @Override
        double calculateFinalAmount() {
            return amount * 0.95;
        }
    }

    static class Guest extends Customer {
        Guest(double amount) {
            super(amount);
        }

        @Override
        double calculateFinalAmount() {
            return amount + 10;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<Double, Customer>> creators =
                new HashMap<>();

        creators.put("STUDENT", Student::new);
        creators.put("STAFF", Staff::new);
        creators.put("GUEST", Guest::new);

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amount = sc.nextDouble();

            Customer customer = creators.get(type).apply(amount);
            double result = customer.calculateFinalAmount();

            System.out.printf("%s: %.2f%n", type, result);
            total += result;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}