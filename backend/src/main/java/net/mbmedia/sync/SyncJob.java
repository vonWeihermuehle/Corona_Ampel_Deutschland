package net.mbmedia.sync;

import net.mbmedia.db.DBService;
import net.mbmedia.db.entities.RKI;
import net.mbmedia.db.entities.RkiUpdate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
public class SyncJob {
    private ApiHelper api;
    private JsonParser parser;
    private DBService db;

    @Scheduled(cron = "0 0 13 00 * *")
    public void run() throws IOException {
        setup();
        List<RKI> list = parser.parse(api.getJson());
        persist(list);
    }

    private void setup(){
        api = new ApiHelper();
        parser = new JsonParser();
        db = new DBService();
    }

    private void setUpdateId(List<RKI> list, int id){
        list.forEach(r -> r.getId().setLastUpdateId(id));
    }

    private void persist(List<RKI> list){
        int id = db.getLastUpdate().getId();
        RkiUpdate update = new RkiUpdate();
        update.setId(id + 1);
        update.setZeitpunkt(new Date());

        setUpdateId(list, id + 1);
        db.persistLastUpdate(update);
        db.persistRKIs(list);
    }
}
