public class Demo {
    public static void main(String[] args) {
        int a = 3, b = 0, c;
        int[] ar = new int[3];
        System.out.println("Start Main");
        try {
            ar[a] = 100;    
        } catch (ArrayIndexOutOfBoundsException e) {
            ar = new int[a+1];
        }        
        System.out.println("End Main");
    }    
}