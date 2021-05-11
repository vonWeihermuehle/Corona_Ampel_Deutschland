package net.mbmedia.sync;


import net.mbmedia.db.entities.RKI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {


    public ArrayList<RKI> parse(String json_input_string) throws JSONException {
        JSONArray temp_array = new JSONArray(json_input_string);
        ArrayList<RKI> objects = new ArrayList<>();

        if(temp_array.length() > 0) {

            for (int i = 0; i < temp_array.length(); i++) {
                JSONObject object = temp_array.getJSONObject(i);

                JSONObject temp_object = object.getJSONObject("attributes");

                RKI tmp = new RKI();
                RKI.RkiID tmpId = new RKI.RkiID();
                tmpId.setObjectId(temp_object.getInt("OBJECTID"));
                tmp.setId(tmpId);
                tmp.setGen(temp_object.optString("GEN"));
                tmp.setBez(temp_object.optString("BEZ"));
                tmp.setAgs(temp_object.optString("AGS_0"));
                tmp.setEwz(String.valueOf(temp_object.optLong("EWZ")));
                tmp.setKfl(String.valueOf(temp_object.optDouble("KFL")));
                tmp.setDeath_rate(String.valueOf(temp_object.optDouble("death_rate")));
                tmp.setCases(temp_object.optInt("cases"));
                tmp.setDeaths(temp_object.optInt("deaths"));
                tmp.setCases_per_100k(String.valueOf(temp_object.optDouble("cases_per_100k")));
                tmp.setCases_per_population(String.valueOf((temp_object.optDouble("cases_per_population"))));
                tmp.setBl(temp_object.optString("BL"));
                tmp.setBl_id(temp_object.optInt("BL_ID"));
                tmp.setCounty(temp_object.optString("county"));
                //last update
                tmp.setCases7_per_100k(String.valueOf(temp_object.optDouble("cases7_per_100k")));
                tmp.setRecoverd(temp_object.optInt("recovered"));
                objects.add(tmp);
            }
        }
        return objects;
    }
}

