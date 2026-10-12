public class EmployeeCompanyInfo {
    String empName;
    double salary;

    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    EmployeeCompanyInfo(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {
        EmployeeCompanyInfo e1 =
                new EmployeeCompanyInfo("Divya", 65000);

        EmployeeCompanyInfo e2 =
                new EmployeeCompanyInfo("Arjun", 40000);

        EmployeeCompanyInfo e3 =
                new EmployeeCompanyInfo("Priya", 55000);

        System.out.println("3 Employee objects created");
        EmployeeCompanyInfo.printCompanyInfo();
    }
}