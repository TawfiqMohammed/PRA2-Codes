import java.util.*;

class customer {
    private int cid;
    private String cname;

    public customer(int cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public int getCid() { return cid; }
    public void setCid(int cid) { this.cid = cid; }

    public String getCname() { return cname; }
    public void setCname(String cname) { this.cname = cname; }
}

class subscription {
    private String sname;
    private double amount;
    private int valid;
    private List<customer> cList;

    public subscription(String sname, double amount, int valid, List<customer> cList) {
        this.sname = sname;
        this.amount = amount;
        this.valid = valid;
        this.cList = cList;
    }

    public String getSname() { return sname; }
    public void setSname(String sname) { this.sname = sname; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public int getValid() { return valid; }
    public void setValid(int valid) { this.valid = valid; }

    public List<customer> getClist() { return cList; }
    public void setClist(List<customer> cList) { this.cList = cList; }
}

class Service {

    public void calcTotalAmnt(List<subscription> subs) {

        @SuppressWarnings("rawtypes")
        Set<String> result = new LinkedHashSet<>();
        for (subscription S : subs) {
            for (customer C : S.getClist()) {
                result.add(C.getCname());
            }
        }

        List<String> names = new ArrayList<>(result);

        System.out.println("Total amount after discount for each customer:");

        for (String s : names) {
            int id = 0;
            double total = 0.0;

            for (subscription S : subs) {
                for (customer C : S.getClist()) {
                    if (C.getCname().equalsIgnoreCase(s)) {
                        id = C.getCid();
                        total += S.getAmount();
                    }
                }
            }

            total = total - (total * 5 / 100);

            System.out.println("Customer ID: " + id + ", Total Amount: " + total);
        }
    }

    public void getSubDetails(int cid, List<subscription> subs) {

        customer targetCustomer = null;

        @SuppressWarnings("rawtypes")
        Set<String> result = new LinkedHashSet<>();

        for (subscription S : subs) {
            for (customer C : S.getClist()) {

                if (C.getCid() == cid) {
                    targetCustomer = C;
                    result.add(S.getSname());
                }
            }
        }

        if (targetCustomer != null) {

            System.out.println("Subscriptions for Customer ID " + cid + ":");

            List<String> res = new ArrayList<>(result);

            for (String S : res) {
                System.out.println("- " + S);
            }

        } else {
            System.out.println("No customer found with ID: " + cid);
        }
    }
}

public class Main {

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine().trim());

        List<subscription> SList = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            String sname = sc.nextLine().trim();
            double amount = Double.parseDouble(sc.nextLine().trim());
            int valid = Integer.parseInt(sc.nextLine().trim());
            int nc = Integer.parseInt(sc.nextLine().trim());

            List<customer> CList = new ArrayList<>();

            for (int j = 0; j < nc; j++) {
                int cid = Integer.parseInt(sc.nextLine().trim());
                String cname = sc.nextLine().trim();

                CList.add(new customer(cid, cname));
            }

            SList.add(new subscription(sname, amount, valid, CList));
        }

        Service serve = new Service();

        int inputS = Integer.parseInt(sc.nextLine().trim());

        serve.calcTotalAmnt(SList);
        serve.getSubDetails(inputS, SList);

        sc.close();
    }
}
