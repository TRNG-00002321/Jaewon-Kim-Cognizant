public class Customer {
    public static int numOfCustomers = 0;
    public final int ID;
    public int age;
    public String name;
    public String last;

    public Customer(String name, String last, int age){
        numOfCustomers++;
        this.age = age;
        this.ID = numOfCustomers;
        this.name = name;
        this.last = last;
    }

    public Customer(String name, int age){
        numOfCustomers++;
        this.ID = numOfCustomers;
        this.name = name;
        this.age = age;
    }

    public int sum(int num1, int num2){
        return num1+num2;
    }
    public int sum(int num1, int num2, int num3){
        return num1+num2+num3;
    }
    public int mult(int num1, int num2){
        return num1+num2;
    }

    public int mult(int num1, int num2, int num3){
        return num1+num2+num3;
    }

    public static void main(String[] args) {
        Customer cust1 = new Customer("Bob", 47);
        System.out.println(Customer.numOfCustomers);
        Customer cust2 = new Customer("Bob", "smith", 47);
        System.out.println(Customer.numOfCustomers);

        System.out.println(cust2.sum(1,2));
        System.out.println(cust2.sum(1,2,3));

        System.out.println(cust2.mult(1,2));
        System.out.println(cust2.mult(1,2,3));
    }
}
