package net.mbmedia.coronaampel.util;

import net.mbmedia.coronaampel.to.RkiTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {

    //[{"id":15,"zeitpunkt":"Okt. 26, 2020"}]
    public static int getID(String json){
        if((json == null) || (!json.startsWith("["))){
            return 0;
        }

        try {
            JSONArray temp_array = new JSONArray(json);
            JSONObject temp_object = temp_array.getJSONObject(0);

            return temp_object.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }


    //[{"objectid":1,"gen":"Flensburg","bez":"Kreisfreie Stadt","death_rate":"1.64835164835165",".....
    public static ArrayList<RkiTO> getTOs(String json){
        if((json == null) || (!json.startsWith("["))){
            return null;
        }

        ArrayList<RkiTO> TOs = new ArrayList<>();
        try {
            JSONArray temp_array = new JSONArray(json);

            for(int i = 0; i<temp_array.length(); i++){
                JSONObject o = temp_array.getJSONObject(i);
                JSONObject Id = o.getJSONObject("id");

                RkiTO to = new RkiTO();
                to.setObjectid(Id.getInt("objectId"));
                to.setGen(o.getString("gen"));
                to.setBez(o.getString("bez"));
                to.setDeath_rate(o.getString("death_rate"));
                to.setCases(o.getInt("cases"));
                to.setDeaths(o.getInt("deaths"));
                to.setCases_per_100k(o.getString("cases_per_100k"));
                to.setCases_per_population(o.getString("cases_per_population"));
                to.setBl(o.getString("bl"));
                to.setCases7_per_100k(o.getString("cases7_per_100k"));
                to.setRecovered(o.getInt("recoverd"));
                to.setLast_update_id(Id.getInt("lastUpdateId"));

                TOs.add(to);
            }
            return TOs;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
