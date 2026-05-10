import java.util.*;

class Bus {

    private int busId;
    private String busName;
    private double ticketPrice;

    public Bus(int busId, String busName, double ticketPrice) {
        this.busId = busId;
        this.busName = busName;
        this.ticketPrice = ticketPrice;
    }

    // Getters and Setters

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(
            double ticketPrice) {

        this.ticketPrice = ticketPrice;
    }
}

class Customer {
    private int customerId;
    private String name;
    private int age;
    private String gender;
    private List<Bus> busTicketsBooked;

    Customer(int customerId,String name,int age,String gender,List<Bus> busTicketsBooked) {

        this.customerId = customerId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.busTicketsBooked = busTicketsBooked;
    }

    // Getters and Setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(
            int customerId) {

        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Bus> getBusTicketsBooked() {
        return busTicketsBooked;
    }

    public void setBusTicketsBooked(
            List<Bus> busTicketsBooked) {

        this.busTicketsBooked =
                busTicketsBooked;
    }
}

class BusTicketBookingService {

    // Method 1
    public List<Customer> findTotalTicketPrice( List<Customer> customers, String busName) {

        List<Customer> result = new ArrayList<>();

        for (Customer c : customers) {
            for (Bus b : c.getBusTicketsBooked()) {
                if (b.getBusName().equalsIgnoreCase(busName)) 
                    {
                        result.add(c);
                        break;
                    }
                }
            }
            return result;
        }

    // Method 2
    public int numberOfTicketsBooked(List<Customer> customers,int customerId) {
        for (Customer c : customers) {
            if (c.getCustomerId() == customerId) 
                {
                    return c.getBusTicketsBooked().size();
                }
            }
        return 0;
    }
}

public class mock11_1 {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine().trim());
        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            int cid = Integer.parseInt(sc.nextLine().trim());
            String name = sc.nextLine().trim();
            int age = Integer.parseInt(sc.nextLine().trim());
            String gender = sc.nextLine().trim();
            int nb = Integer.parseInt(sc.nextLine().trim());
            List<Bus> bList = new ArrayList<>();

            for (int j = 0; j < nb; j++) {
                int bid = Integer.parseInt(sc.nextLine().trim());
                String bname = sc.nextLine().trim();
                double price = Double.parseDouble(sc.nextLine().trim());
                bList.add(new Bus(bid,bname,price));
            }

            customers.add(new Customer(cid,name,age,gender,bList));
        }

        String inputBusName =sc.nextLine().trim();
        int inputCustomerId =Integer.parseInt(sc.nextLine().trim());
        BusTicketBookingService serve =new BusTicketBookingService();

        // Method 1

        List<Customer> result =serve.findTotalTicketPrice(customers,inputBusName);

        if (result.size() == 0) {
            System.out.println("No matching customers found");
        }

        else {
            double total = 0;
            System.out.println(
                    "Customers who booked tickets in " + inputBusName + " Bus:");

            for (Customer c : result) {
                System.out.println(c.getName());
                for (Bus b : c.getBusTicketsBooked()) {
                    if (b.getBusName().equalsIgnoreCase(inputBusName)) 
                    {
                        total += b.getTicketPrice();
                    }
                }
            }

            System.out.println("Total Ticket Fare of all the customers who booked ticket in " + inputBusName + " Bus: " + total);
        }

        // Method 2

        int tickets = serve.numberOfTicketsBooked(customers, inputCustomerId);

        if (tickets == 0) {
            System.out.println("No matching customers found");
        }

        else {
            System.out.println( "Number of Tickets Booked by CustomerID:" + inputCustomerId + " = " + tickets);
        }
        sc.close();
    }
}