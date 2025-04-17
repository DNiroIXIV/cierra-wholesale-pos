public class Demo {
    public static boolean updateCustomer() {
        try {
            // Read customer detail
            // Process Data
            // Update customer detail
            return true;
        } finally {
            System.out.println("Update Sucess...");
        }
    }

    public static void main(String[] args) {
        updateCustomer();
    }
}
