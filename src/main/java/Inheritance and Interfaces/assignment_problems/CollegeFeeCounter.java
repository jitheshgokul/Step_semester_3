public class CollegeFeeCounter {
    abstract static class Student {
        private static final double TRANSPORT_FEE = 12000.0;
        protected String name;

        Student(String name) {
            this.name = name;
        }

        abstract double getTuitionFee();

        double getHostelFee() {
            return 0.0;
        }

        abstract boolean usesBus();

        double calculateFee() {
            double total = getTuitionFee() + getHostelFee();

            if (usesBus()) {
                total += TRANSPORT_FEE;
            }

            return total;
        }
    }

    static class DayScholar extends Student {
        DayScholar(String name) {
            super(name);
        }

        @Override
        double getTuitionFee() {
            return 40000.0;
        }

        @Override
        boolean usesBus() {
            return true;
        }
    }

    static class Hosteller extends Student {
        Hosteller(String name) {
            super(name);
        }

        @Override
        double getTuitionFee() {
            return 40000.0;
        }

        @Override
        double getHostelFee() {
            return 60000.0;
        }

        @Override
        boolean usesBus() {
            return false;
        }
    }

    static class Scholar extends Student {
        Scholar(String name) {
            super(name);
        }

        @Override
        double getTuitionFee() {
            return 20000.0;
        }

        @Override
        boolean usesBus() {
            return true;
        }
    }

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        int n = sc.nextInt();
        double totalCollected = 0.0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            String name = sc.next();

            Student student;

            switch (type) {
                case "DAY_SCHOLAR":
                    student = new DayScholar(name);
                    break;
                case "HOSTELLER":
                    student = new Hosteller(name);
                    break;
                case "SCHOLAR":
                    student = new Scholar(name);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Unknown student type: " + type);
            }

            double fee = student.calculateFee();

            System.out.printf("%s: %.2f%n", name, fee);
            totalCollected += fee;
        }

        System.out.printf(
                "Total Collected: %.2f%n", totalCollected);
        sc.close();
    }
}