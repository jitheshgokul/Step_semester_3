import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;

public class StreamingPlanRenewal {
    abstract static class Plan {
        abstract int getValidityDays();

        LocalDate getRenewalDate(LocalDate startDate) {
            return startDate.plusDays(getValidityDays());
        }
    }

    static class Basic extends Plan {
        @Override
        int getValidityDays() {
            return 30;
        }
    }

    static class Standard extends Plan {
        @Override
        int getValidityDays() {
            return 90;
        }
    }

    static class Premium extends Plan {
        @Override
        int getValidityDays() {
            return 365;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<Void, Plan>> creators =
                new HashMap<>();

        creators.put("BASIC", unused -> new Basic());
        creators.put("STANDARD", unused -> new Standard());
        creators.put("PREMIUM", unused -> new Premium());

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            LocalDate startDate = LocalDate.parse(sc.next());

            Plan plan = creators.get(type).apply(null);
            LocalDate renewalDate = plan.getRenewalDate(startDate);

            System.out.printf("%s: %s%n", name, renewalDate);
        }

        sc.close();
    }
}