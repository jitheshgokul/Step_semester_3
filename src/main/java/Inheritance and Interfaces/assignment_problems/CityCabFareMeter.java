public class CityCabFareMeter {
    interface NightService {
        double applyNightCharge(double fare);
    }

    abstract static class Cab {
        private static final double MINIMUM_FARE = 100.0;
        protected double km;

        Cab(double km) {
            this.km = km;
        }

        abstract double getRatePerKm();

        double calculateFare() {
            return Math.max(MINIMUM_FARE, km * getRatePerKm());
        }
    }

    static class Mini extends Cab {
        Mini(double km) {
            super(km);
        }

        @Override
        double getRatePerKm() {
            return 10.0;
        }
    }

    static class Sedan extends Cab implements NightService {
        Sedan(double km) {
            super(km);
        }

        @Override
        double getRatePerKm() {
            return 14.0;
        }

        @Override
        public double applyNightCharge(double fare) {
            return fare * 1.20;
        }
    }

    static class SUV extends Cab implements NightService {
        SUV(double km) {
            super(km);
        }

        @Override
        double getRatePerKm() {
            return 18.0;
        }

        @Override
        public double applyNightCharge(double fare) {
            return fare * 1.20;
        }
    }

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        int n = sc.nextInt();
        double total = 0.0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            double km = sc.nextDouble();
            String time = sc.next().toUpperCase();

            Cab cab;

            switch (type) {
                case "MINI":
                    cab = new Mini(km);
                    break;
                case "SEDAN":
                    cab = new Sedan(km);
                    break;
                case "SUV":
                    cab = new SUV(km);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Unknown cab type: " + type);
            }

            if (time.equals("NIGHT") && !(cab instanceof NightService)) {
                System.out.println(
                        type + ": night service not available");
                continue;
            }

            double fare = cab.calculateFare();

            if (time.equals("NIGHT")) {
                fare = ((NightService) cab).applyNightCharge(fare);
            }

            System.out.printf("%s: %.2f%n", type, fare);
            total += fare;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}