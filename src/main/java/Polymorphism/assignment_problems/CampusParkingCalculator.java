import java.util.*;
import java.util.function.Function;

public class CampusParkingCalculator {
    abstract static class Vehicle {
        protected int hours;

        Vehicle(int hours) {
            this.hours = hours;
        }

        abstract double calculateCharge();
    }

    static class Bike extends Vehicle {
        Bike(int hours) {
            super(hours);
        }

        @Override
        double calculateCharge() {
            return hours * 10.0;
        }
    }

    static class Car extends Vehicle {
        Car(int hours) {
            super(hours);
        }

        @Override
        double calculateCharge() {
            return 30 + (hours - 1) * 20.0;
        }
    }

    static class Truck extends Vehicle {
        Truck(int hours) {
            super(hours);
        }

        @Override
        double calculateCharge() {
            return Math.max(100, hours * 50.0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<Integer, Vehicle>> creators =
                new HashMap<>();

        creators.put("BIKE", Bike::new);
        creators.put("CAR", Car::new);
        creators.put("TRUCK", Truck::new);

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int hours = sc.nextInt();

            Vehicle vehicle = creators.get(type).apply(hours);
            double charge = vehicle.calculateCharge();

            System.out.printf("%s: %.2f%n", type, charge);
            total += charge;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}