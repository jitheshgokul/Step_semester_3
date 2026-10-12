public class ParcelShippingDesk {
    interface Insurable {
        double calculateInsurance(double declaredValue);
    }

    abstract static class Parcel {
        protected double weight;
        protected double declaredValue;

        Parcel(double weight, double declaredValue) {
            this.weight = weight;
            this.declaredValue = declaredValue;
        }

        abstract double calculateCharge();

        double calculateInsurance() {
            if (this instanceof Insurable) {
                return ((Insurable) this)
                        .calculateInsurance(declaredValue);
            }
            return 0.0;
        }

        double calculateTotal() {
            return calculateCharge() + calculateInsurance();
        }
    }

    static class Standard extends Parcel {
        Standard(double weight, double value) {
            super(weight, value);
        }

        @Override
        double calculateCharge() {
            return 40.0 + 10.0 * weight;
        }
    }

    static class Express extends Parcel implements Insurable {
        Express(double weight, double value) {
            super(weight, value);
        }

        @Override
        double calculateCharge() {
            return 80.0 + 15.0 * weight;
        }

        @Override
        public double calculateInsurance(double value) {
            return value * 0.02;
        }
    }

    static class Fragile extends Parcel implements Insurable {
        Fragile(double weight, double value) {
            super(weight, value);
        }

        @Override
        double calculateCharge() {
            return 40.0 + 10.0 * weight + 50.0;
        }

        @Override
        public double calculateInsurance(double value) {
            return value * 0.02;
        }
    }

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        int n = sc.nextInt();
        double grandTotal = 0.0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            double weight = sc.nextDouble();
            double value = sc.nextDouble();

            Parcel parcel;

            switch (type) {
                case "STANDARD":
                    parcel = new Standard(weight, value);
                    break;
                case "EXPRESS":
                    parcel = new Express(weight, value);
                    break;
                case "FRAGILE":
                    parcel = new Fragile(weight, value);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Unknown parcel type: " + type);
            }

            double charge = parcel.calculateCharge();
            double insurance = parcel.calculateInsurance();
            double total = parcel.calculateTotal();

            System.out.printf(
                    "%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                    type, charge, insurance, total);

            grandTotal += total;
        }

        System.out.printf("Grand Total: %.2f%n", grandTotal);
        sc.close();
    }
}