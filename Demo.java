public class Demo {
    public static void main(String[] args) {
        String name = "Niroth";
        int a = 0;
        int[] ar = new int[6];

        try{
            System.out.println("Start outer try...");
            try{
                System.out.println("Start inner try...");
                int b = 24/a;
            }catch (ClassCastException ex){
                System.out.println("inside inner catch...");
            }
            System.out.println("End outer try...");
        }catch(RuntimeException ex){
            System.out.println("inside outer catch...");
        }        
    }    
}