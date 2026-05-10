import java.util.*;

class Material {
    String materialid;
    String materialName;
    String category;
    double qualityrating;

    public Material(String materialid, String materialName, String category, double qualityrating) {
        this.materialid = materialid;
        this.materialName = materialName;
        this.category = category;
        this.qualityrating = qualityrating;
    }

    public String getMaterialid() { return materialid; }
    public void setMaterialid(String materialid) { this.materialid = materialid; }
    public String getMaterialName() { return materialName; }
    public void setMaterialName(String materialName) { this.materialName = materialName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getQualityrating() { return qualityrating; }
    public void setQualityrating(double qualityrating) { this.qualityrating = qualityrating; }
}

class Supplier {
    String supplierid;
    String supplierName;
    List<Material> materialList;

    public Supplier(String supplierid, String supplierName, List<Material> materialList) {
        this.supplierid = supplierid;
        this.supplierName = supplierName;
        this.materialList = materialList;
    }

    public String getSupplierid() { return supplierid; }
    public void setSupplierid(String supplierid) { this.supplierid = supplierid; }
    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    public List<Material> getMaterialList() { return materialList; }
    public void setMaterialList(List<Material> materialList) { this.materialList = materialList; }
}

public class mock7 {

    // Method 1: Find supplier with highest material count for given category
    public static void FindSupplierWithHighestMaterialCount(List<Supplier> suppliers, String category) {
        String bestSupplier = null;
        int maxCount = 0;

        for (Supplier s : suppliers) {
            int count = 0;
            for (Material m : s.getMaterialList()) {
                if (m.getCategory().equalsIgnoreCase(category)) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                bestSupplier = s.getSupplierid();
            }
        }

        if (maxCount == 0) {
            System.out.println("No supplier supplies materials for " + category + " category");
        } else {
            System.out.println("Supplier supplying most number of materials for " +
                category + " category:" + bestSupplier);
        }
    }

    // Method 2: Find average quality rating for given supplier and category
    public static void FindSupplierQualityRating(List<Supplier> suppliers, String supplierid, String category) {
        // Find supplier
        Supplier found = null;
        for (Supplier s : suppliers) {
            if (s.getSupplierid().equalsIgnoreCase(supplierid)) {
                found = s;
                break;
            }
        }

        if (found == null) {
            System.out.println("No supplier found with ID:" + supplierid);
            return;
        }

        double total = 0;
        int count = 0;
        for (Material m : found.getMaterialList()) {
            if (m.getCategory().equalsIgnoreCase(category)) {
                total += m.getQualityrating();
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No materials are supplied by " + supplierid +
                " for " + category + " category");
        } else {
            double avg = total / count;
            System.out.println("Average quality rating of " + supplierid +
                " for " + category + " category:" + String.format("%.1f", avg));
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int numSuppliers = Integer.parseInt(sc.nextLine().trim());
        List<Supplier> suppliers = new ArrayList<>();

        for (int i = 0; i < numSuppliers; i++) {
            String supplierid = sc.nextLine().trim();
            String supplierName = sc.nextLine().trim();
            int numMaterials = Integer.parseInt(sc.nextLine().trim());

            List<Material> materials = new ArrayList<>();
            for (int j = 0; j < numMaterials; j++) {
                String materialid = sc.nextLine().trim();
                String materialName = sc.nextLine().trim();
                String category = sc.nextLine().trim();
                double qualityrating = Double.parseDouble(sc.nextLine().trim());
                materials.add(new Material(materialid, materialName, category, qualityrating));
            }
            suppliers.add(new Supplier(supplierid, supplierName, materials));
        }

        String categoryForCount = sc.nextLine().trim();
        String supplieridForRating = sc.nextLine().trim();
        String categoryForRating = sc.nextLine().trim();

        FindSupplierWithHighestMaterialCount(suppliers, categoryForCount);
        FindSupplierQualityRating(suppliers, supplieridForRating, categoryForRating);
        sc.close();
    }
}