
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author Pradeep Copyright (C) 2017 Pradeep Senthil
 */
public class api {

    /**
     * Stores the JSON string format.
     */
    public String JSON = "";

    /**
     * Splits the main string into an array seperated by new line.
     */
    public String[] JSONarr;

    /**
     *
     */
    public String urlApi = "";


    /**
     * Constructor for api class.
     *
     */
    public api() {

    }

    /**
     * This function takes the url entered by the user and returns a string of
     * the data in JSON format.
     *
     * @return String JSON file retrieved from API Call.
     */
    public String[] urlToJSON(String hi) {
        try {
            String mainURL = hi;
            URL url = new URL(mainURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/xml");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output = "";
            String a;
            int i = 0;
            ArrayList<String> arr = new ArrayList<String>();
            while ((a = br.readLine()) != null) {
                output += a + "\n";
                arr.add(a);
            }
            conn.disconnect();
            JSON = output;

            String[] ret = arr.toArray(new String[0]);
            return ret;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            JSON = "404 ERROR";
        } catch (IOException e) {
            e.printStackTrace();
            JSON = "404 ERROR";
        }
        return null;
    }

    public String[] getStops(){
        String[] stops1 = urlToJSON("https://developer.cumtd.com/api/v2.2/JSON/getroutes?key=a2142759b9ac473e8dbdb95572546a7b");

        ArrayList<Object> hi = new ArrayList<Object>();

        for(int i = 0; i < stops1.length; i++){
            if(stops1[i].contains("trip_headsign")){
                hi.add(stops1[i]);
            }
        }
        String[] return1 = hi.toArray(new String[0]);
        return return1;
    }


}


