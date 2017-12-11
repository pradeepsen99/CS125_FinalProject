public class MainClass {
    public static void main(String[] args){
        api hi = new api();

        String[] hello = hi.getStops();

        System.out.println("hi");
        for(int i = 0 ; i < hello.length; i++){
            System.out.println(hello[i] + "\n");

        }
    }
}
