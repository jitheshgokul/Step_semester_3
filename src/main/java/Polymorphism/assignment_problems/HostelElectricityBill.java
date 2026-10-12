import java.util.*;
import java.util.function.Function;

public class HostelElectricityBill {
    abstract static class Room {
        protected int units;

        Room(int units) {
            this.units = units;
        }

        abstract double calculateBill();
    }

    static class Single extends Room {
        Single(int units) {
            super(units);
        }

        @Override
        double calculateBill() {
            return units * 8.0;
        }
    }

    static class Shared extends Room {
        private final int occupants;

        Shared(int units, int occupants) {
            super(units);
            this.occupants = occupants;
        }

        @Override
        double calculateBill() {
            return units * 6.0 / occupants;
        }
    }

    static class AC extends Room {
        AC(int units) {
            super(units);
        }

        @Override
        double calculateBill() {
            return units * 10.0 + 200;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<Integer, Room>> creators =
                new HashMap<>();

        creators.put("SINGLE", Single::new);
        creators.put("AC", AC::new);

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int units = sc.nextInt();

            Room room;

            if (type.equals("SHARED")) {
                int occupants = sc.nextInt();
                room = new Shared(units, occupants);
            } else {
                room = creators.get(type).apply(units);
            }

            double bill = room.calculateBill();

            System.out.printf("%s: %.2f%n", type, bill);
            total += bill;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}