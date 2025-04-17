public class Demo {
    public static void main(String[] args) {
        int a = 5;
        String name = "kamal";
        char ch;
        
        System.out.println("Start Main");
        try {
            ch = name.charAt(a);
        } catch (StringIndexOutOfBoundsException e) {
            ch = '\0';
        }
        
        System.out.println("End Main");
    }    
}