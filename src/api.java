
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
     * @param url enters the url into the application.
     */
    public api(String url) {
        urlApi = url;
        urlToJSON();
        JSONarr = JSON.split("\\r?\\n");
    }

    /**
     * This function takes the url entered by the user and returns a string of
     * the data in JSON format.
     *
     * @return String JSON file retrieved from API Call.
     */
    public void urlToJSON() {
        try {
            String mainURL = urlApi;
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
            while ((a = br.readLine()) != null) {
                output += a + "\n";
            }
            conn.disconnect();
            JSON = output;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            JSON = "404 ERROR";
        } catch (IOException e) {
            e.printStackTrace();
            JSON = "404 ERROR";
        }
    }

}


