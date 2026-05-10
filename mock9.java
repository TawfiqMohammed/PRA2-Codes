import java.util.*;

class AverageCannotBeCalculatedException extends Exception {
    AverageCannotBeCalculatedException(String msg) {
        super(msg);
    }
}

class NoMobileDetailsFoundException extends Exception {
    NoMobileDetailsFoundException(String msg) {
        super(msg);
    }
}

class Mobile {

    private int modelNumber;
    private String modelName;
    private double price;
    private int storage;

    Mobile(int modelNumber, String modelName,
           double price, int storage) {

        this.modelNumber = modelNumber;
        this.modelName = modelName;
        this.price = price;
        this.storage = storage;
    }

    // Getters and Setters

    public int getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(int modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }
}

class Brand {

    private int brandId;
    private String brandName;
    private ArrayList<Mobile> mobiles;

    Brand(int brandId, String brandName,
          ArrayList<Mobile> mobiles) {

        this.brandId = brandId;
        this.brandName = brandName;
        this.mobiles = mobiles;
    }

    // Getters and Setters

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public ArrayList<Mobile> getMobiles() {
        return mobiles;
    }

    public void setMobiles(ArrayList<Mobile> mobiles) {
        this.mobiles = mobiles;
    }
}

public class mock9 {

    // Method 1
    static void calculateAveragePrice(ArrayList<Brand> brands) throws AverageCannotBeCalculatedException {

        for (Brand b : brands) {
            if (b.getMobiles().size() == 0) {
                throw new AverageCannotBeCalculatedException("Average cannot be calculated");
            }
        }

        System.out.println("Average Price of each brand:");

        for (Brand b : brands) {
            double sum = 0;
            for (Mobile m : b.getMobiles()) {
                sum += m.getPrice();
            }
            double avg = sum / b.getMobiles().size();
            System.out.println(b.getBrandName() + ":" + avg);
        }
    }

    // Method 2
    static void findMobilesInStorageRange(ArrayList<Brand> brands, int min, int max) throws NoMobileDetailsFoundException {
        ArrayList<String> result = new ArrayList<>();
        for (Brand b : brands) {
            for (Mobile m : b.getMobiles()) {
                if (m.getStorage() >= min && m.getStorage() <= max) {
                    result.add(
                            "Brand: " + b.getBrandName() +
                            ", Model Number: " + m.getModelNumber() +
                            ", Model Name: " + m.getModelName() +
                            ", Price: " + m.getPrice() +
                            ", Storage: " + m.getStorage() + "GB"
                    );
                }
            }
        }

        if (result.size() == 0) {
            throw new NoMobileDetailsFoundException(
                    "No mobile found");
        }

        System.out.println(
                "Mobiles in the storage range ("
                        + min + "GB - "
                        + max + "GB):");

        for (String s : result) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        ArrayList<Brand> brands = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int brandId = Integer.parseInt(sc.nextLine());
            String brandName = sc.nextLine();
            int mobileCount = Integer.parseInt(sc.nextLine());
            ArrayList<Mobile> mobiles = new ArrayList<>();

            for (int j = 0; j < mobileCount; j++) {
                int modelNumber = Integer.parseInt(sc.nextLine());
                String modelName = sc.nextLine();
                double price = Double.parseDouble(sc.nextLine());
                int storage = Integer.parseInt(sc.nextLine());

                mobiles.add(new Mobile(modelNumber, modelName, price, storage));
            }

            brands.add(new Brand(brandId, brandName, mobiles));
        }

        int min = Integer.parseInt(sc.nextLine());

        int max = Integer.parseInt(sc.nextLine());

        try {
            calculateAveragePrice(brands);
        } catch (AverageCannotBeCalculatedException e) {
            System.out.println( "AverageCannotBeCalculatedException: " + e.getMessage());
        }

        try {
            findMobilesInStorageRange(brands, min, max);
        } catch (NoMobileDetailsFoundException e) {
            System.out.println("NoMobileDetailsFoundException: " + e.getMessage());
        }
        sc.close();
    }
}