import java.util.*;

class PriceCannotBeNegativeException extends Exception {
    PriceCannotBeNegativeException(String msg) {
        super(msg);
    }
}

class Car {

    private int carId;
    private String cName;
    private double mileage;
    private double price;

    Car(int carId, String cName, double mileage, double price) {

        this.carId = carId;
        this.cName = cName;
        this.mileage = mileage;
        this.price = price;
    }

    // Getters and Setters

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCname() {
        return cName;
    }

    public void setCname(String cName) {
        this.cName = cName;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class Manufacturer {

    private int manufacturerId;
    private String mName;
    private int numberOfCarsProduced;
    private List<Car> cList;

    Manufacturer(int manufacturerId, String mName, int numberOfCarsProduced, List<Car> cList) 
    {
        this.manufacturerId = manufacturerId;
        this.mName = mName;
        this.numberOfCarsProduced = numberOfCarsProduced;
        this.cList = cList;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId( int manufacturerId) 
    {
        this.manufacturerId = manufacturerId;
    }

    public String getMname() {
        return mName;
    }

    public void setMname(String mName) {
        this.mName = mName;
    }

    public int getNumberOfCarsProduced() {
        return numberOfCarsProduced;
    }

    public void setNumberOfCarsProduced(int numberOfCarsProduced) 
    {
        this.numberOfCarsProduced = numberOfCarsProduced;
    }

    public List<Car> getClist() {
        return cList;
    }

    public void setClist(List<Car> cList) {
        this.cList = cList;
    }
}

class Service {

    // Method 1
    public void CalcAverage(List<Manufacturer> mList, String manufacturer) throws PriceCannotBeNegativeException {
        Manufacturer target = null;
        for (Manufacturer m : mList) {
            if (m.getMname().equals(manufacturer)) {
                target = m;
            }
        }

        double avg = 0.0;
        double total = 0.0;
        int count = 0;

        if (target == null) {
            System.out.println("No cars produced by the given manufacturer");
        }

        else {
            for (Car c : target.getClist()) 
                {
                    if (c.getPrice() < 0) { throw new PriceCannotBeNegativeException("Invalid Price: car price cannot be negative.");
                }
                total += c.getPrice();
                count++;
            }

            avg = total / count;
            System.out.println("Average price of cars produced by " + manufacturer + " is: " + avg);
        }
    }

    // Method 2
    public void findCarMax(List<Manufacturer> mList) {
        double max = Double.MIN_VALUE;
        Car target = null;
        for (Manufacturer m : mList) {
            for (Car c : m.getClist()) {
                if (c.getMileage() > max) {
                    max = c.getMileage();
                    target = c;
                }
            }
        }

        System.out.println("Name - " + target.getCname());
        System.out.println("Mileage - " + target.getMileage());
    }
}

public class mock8 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        List<Manufacturer> mList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int mid = Integer.parseInt(sc.nextLine().trim());
            String mname = sc.nextLine().trim();
            int cnum = Integer.parseInt(sc.nextLine().trim());
            List<Car> cList = new ArrayList<>();

            for (int j = 0; j < cnum; j++) {

                int cid = Integer.parseInt(sc.nextLine().trim());
                String cname = sc.nextLine().trim();
                double mileage = Double.parseDouble(sc.nextLine().trim());
                double price = Double.parseDouble(sc.nextLine().trim());

                cList.add(new Car(cid, cname, mileage, price));
            }

            mList.add(new Manufacturer(mid, mname, cnum, cList));
        }

        String manufacturer = sc.nextLine().trim();
        Service serve = new Service();

        try {
            serve.CalcAverage(mList, manufacturer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        serve.findCarMax(mList);
        sc.close();
    }
} 
