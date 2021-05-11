package net.mbmedia.coronaampel.api;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import net.mbmedia.coronaampel.Konfiguration;
import net.mbmedia.coronaampel.to.RkiTO;
import net.mbmedia.coronaampel.ui.MainActivity;
import net.mbmedia.coronaampel.util.DBHelper;
import net.mbmedia.coronaampel.util.HashHelper;
import net.mbmedia.coronaampel.util.LocalStorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static net.mbmedia.coronaampel.api.JsonHelper.getJSON;
import static net.mbmedia.coronaampel.util.HashHelper.getHash;
import static net.mbmedia.coronaampel.util.JsonParser.getID;
import static net.mbmedia.coronaampel.util.JsonParser.getTOs;

public class JsonConnector {

    private Context context;
    private final HashMap<String, String> params = new HashMap<>();
    private final ArrayList<RkiTO> TOs = new ArrayList<>();

    private final DBHelper db;
    private RelativeLayout progressBar;

    private LocalStorage localStorage;

    public JsonConnector(Context c, RelativeLayout progressBar){
        this.context = c;
        this.progressBar = progressBar;
        this.localStorage = new LocalStorage(context);
        this.db = new DBHelper(context);
    }

    public void checkForNewUpdates(){
        final String url = Konfiguration.URL + "/getLastUpdate";
        setupParams();

        new Thread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                String json = getJSON(url, params);
                int lastUpdateId = getID(json);
                if(db.getLastUpdateID() < lastUpdateId){
                    getNewUpdate();
                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        }).start();
    }

    private void getNewUpdate(){
        final String url = Konfiguration.URL + "/get";
        setupParams();

        new Thread(new Runnable() {
            @Override
            public void run() {
                localStorage.setLastUpdateDate(new Date());
                String json = getJSON(url, params);
                ArrayList<RkiTO> list = getTOs(json);
                db.update(list);
                ((MainActivity) context).updateFragments();
                progressBar.setVisibility(View.INVISIBLE);
            }
        }).start();
    }

    private void setupParams(){
        params.clear();
        params.put("hash", getHash());
    }
}
