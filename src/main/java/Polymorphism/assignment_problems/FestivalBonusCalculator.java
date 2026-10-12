import java.util.*;
import java.util.function.Function;

public class FestivalBonusCalculator {
    abstract static class Employee {
        protected String name;
        protected double salary;

        Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        abstract double calculateBonus();
    }

    static class FullTime extends Employee {
        FullTime(String name, double salary) {
            super(name, salary);
        }

        @Override
        double calculateBonus() {
            return salary * 0.10;
        }
    }

    static class PartTime extends Employee {
        PartTime(String name, double salary) {
            super(name, salary);
        }

        @Override
        double calculateBonus() {
            return salary * 0.05;
        }
    }

    static class Intern extends Employee {
        Intern(String name, double salary) {
            super(name, salary);
        }

        @Override
        double calculateBonus() {
            return 2000;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<String, Function<Double, Employee>>>
                creators = new HashMap<>();

        creators.put("FULLTIME",
                name -> salary -> new FullTime(name, salary));
        creators.put("PARTTIME",
                name -> salary -> new PartTime(name, salary));
        creators.put("INTERN",
                name -> salary -> new Intern(name, salary));

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            double salary = sc.nextDouble();

            Employee employee =
                    creators.get(type).apply(name).apply(salary);

            double bonus = employee.calculateBonus();

            System.out.printf("%s: %.2f%n", name, bonus);
            total += bonus;
        }

        System.out.printf("Total Bonus: %.2f%n", total);
        sc.close();
    }
}