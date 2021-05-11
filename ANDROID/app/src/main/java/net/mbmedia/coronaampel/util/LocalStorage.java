package net.mbmedia.coronaampel.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalStorage {

    private final String UPDATE_FLAG = "update";
    private final String PREF = "MB-MediaCoronaAmpelPref";
    private final Context context;

    public LocalStorage(Context c){
        this.context = c;
    }

    public void setLastUpdateDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences(PREF, 0);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(UPDATE_FLAG, dateFormat.format(date));
        editor.apply();
    }

    public String getLastUpdateDate(){
        SharedPreferences pref = context.getSharedPreferences(PREF, 0);
        return pref.getString(UPDATE_FLAG,"null");
    }
}
