public class HomeApplianceEnergyReport {
    interface SaverMode {
        double applySaver(double units);
    }

    abstract static class Appliance {
        protected double hours;

        Appliance(double hours) {
            this.hours = hours;
        }

        abstract double getPowerRating();

        double calculateUnits() {
            return getPowerRating() * hours / 1000.0;
        }

        double calculateCost(double units) {
            return units * 8.0;
        }
    }

    static class Fridge extends Appliance {
        Fridge(double hours) {
            super(hours);
        }

        @Override
        double getPowerRating() {
            return 150.0;
        }
    }

    static class AC extends Appliance implements SaverMode {
        AC(double hours) {
            super(hours);
        }

        @Override
        double getPowerRating() {
            return 1500.0;
        }

        @Override
        public double applySaver(double units) {
            return units * 0.75;
        }
    }

    static class TV extends Appliance {
        TV(double hours) {
            super(hours);
        }

        @Override
        double getPowerRating() {
            return 100.0;
        }
    }

    static class Washer extends Appliance implements SaverMode {
        Washer(double hours) {
            super(hours);
        }

        @Override
        double getPowerRating() {
            return 500.0;
        }

        @Override
        public double applySaver(double units) {
            return units * 0.75;
        }
    }

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine().trim());
        double totalCost = 0.0;

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split("\\s+");

            String type = parts[0].toUpperCase();
            double hours = Double.parseDouble(parts[1]);
            boolean saverRequested =
                    parts.length >= 3
                    && parts[2].equalsIgnoreCase("SAVER");

            Appliance appliance;

            switch (type) {
                case "FRIDGE":
                    appliance = new Fridge(hours);
                    break;
                case "AC":
                    appliance = new AC(hours);
                    break;
                case "TV":
                    appliance = new TV(hours);
                    break;
                case "WASHER":
                    appliance = new Washer(hours);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Unknown appliance: " + type);
            }

            if (saverRequested && !(appliance instanceof SaverMode)) {
                System.out.println(type + ": saver mode not supported");
                continue;
            }

            double units = appliance.calculateUnits();

            if (saverRequested) {
                units = ((SaverMode) appliance).applySaver(units);
            }

            double cost = appliance.calculateCost(units);

            System.out.printf(
                    "%s: Units=%.2f Cost=%.2f%n",
                    type, units, cost);

            totalCost += cost;
        }

        System.out.printf("Total Cost: %.2f%n", totalCost);
        sc.close();
    }
}