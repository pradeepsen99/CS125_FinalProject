import org.json.JSONException;

public class MainClass {
    public static void main(String[] args) throws JSONException {
        api hi = new api();

        String[] hello = hi.getRoutes("IU");

        for(int i = 0; i < hello.length; i++){
            System.out.println(hello[i]);
        }


    }
}
