package net.mbmedia.controller;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import net.mbmedia.db.DBService;
import net.mbmedia.db.entities.RKI;
import net.mbmedia.db.entities.RkiUpdate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static net.mbmedia.util.HashHelper.getHash;


@RestController
@Service
public class DataController implements IDataController{

    @Override
    public String get(String hash) {
        if(!checkHash(hash)){
            return "";
        }

        DBService db = new DBService();
        ArrayList<RKI> all = db.getAll();

        Gson gson = new Gson();
        return gson.toJson(all);
    }

    @Override
    public String getLastUpdate(String hash) {
        if(!checkHash(hash)){
            return "";
        }

        DBService db = new DBService();
        RkiUpdate u = db.getLastUpdate();

        Gson gson = new Gson();
        return gson.toJson(ImmutableList.of(u));
    }

    private boolean checkHash(String hash){
        return hash != null && getHash().equals(hash);
    }


}
