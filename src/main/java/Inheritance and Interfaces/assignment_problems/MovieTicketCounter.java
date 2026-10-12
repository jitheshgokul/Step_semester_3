public class MovieTicketCounter {
    abstract static class Ticket {
        private static final double CONVENIENCE_FEE = 20.0;

        abstract double getPrice();

        double calculateAmount(int count) {
            return (getPrice() + CONVENIENCE_FEE) * count;
        }
    }

    static class Regular extends Ticket {
        @Override
        double getPrice() {
            return 150.0;
        }
    }

    static class Premium extends Ticket {
        @Override
        double getPrice() {
            return 250.0;
        }
    }

    static class Recliner extends Ticket {
        @Override
        double getPrice() {
            return 400.0;
        }
    }

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        java.util.Map<String, Ticket> tickets = new java.util.HashMap<>();
        tickets.put("REGULAR", new Regular());
        tickets.put("PREMIUM", new Premium());
        tickets.put("RECLINER", new Recliner());

        int n = sc.nextInt();
        double total = 0.0;

        for (int i = 0; i < n; i++) {
            String seat = sc.next().toUpperCase();
            int count = sc.nextInt();

            Ticket ticket = tickets.get(seat);
            double amount = ticket.calculateAmount(count);

            System.out.printf("%s: %.2f%n", seat, amount);
            total += amount;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}