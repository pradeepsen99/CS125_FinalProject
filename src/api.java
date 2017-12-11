
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;


/**
 * @author Pradeep
 * Copyright (C) 2017 Pradeep Senthil
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
    public String urlToJSON(String hi) {
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
            while ((a = br.readLine()) != null) {
                output += a + "\n";
            }
            conn.disconnect();

            return output;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            JSON = "404 ERROR";
        } catch (IOException e) {
            e.printStackTrace();
            JSON = "404 ERROR";
        }
        return null;
    }


    /**
     * This class returns all of the stops in a String array using the JSON data received from the MTD api.
     *
     * @return String array with all the stops MTD has.
     * @throws JSONException
     */
    public String[] getStops() throws JSONException {
        String stops1 = urlToJSON("https://developer.cumtd.com/api/v2.2/JSON/getstops?key=a2142759b9ac473e8dbdb95572546a7b");

        String jsonString = stops1;
        JSONObject jsnobject = new JSONObject(stops1);
        JSONArray jsonArray = jsnobject.getJSONArray("stops");

        ArrayList<String> stops = new ArrayList<String>();

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject explrObject = jsonArray.getJSONObject(i);
            stops.add(explrObject.getString("stop_id"));
        }


        String[] ret = stops.toArray(new String[0]);

        return ret;
    }

    public String[] getRoutes(String stopID) throws JSONException {
        String routesStr = urlToJSON("https://developer.cumtd.com/api/v2.2/JSON/getdeparturesbystop?key=a2142759b9ac473e8dbdb95572546a7b&stop_id=" + stopID);

        JSONObject jsnobject = new JSONObject(routesStr);
        JSONArray jsonArray = jsnobject.getJSONArray("departures");

        ArrayList<String> routes = new ArrayList<String>();

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject explrObject = jsonArray.getJSONObject(i);
            routes.add(explrObject.getString("stop_id"));
        }


        String[] ret = routes.toArray(new String[0]);

        return ret;

    }


}


