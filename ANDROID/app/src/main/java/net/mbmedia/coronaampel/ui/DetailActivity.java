package net.mbmedia.coronaampel.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import net.mbmedia.coronaampel.R;
import net.mbmedia.coronaampel.to.RkiTO;
import net.mbmedia.coronaampel.util.LocalStorage;

public class DetailActivity extends AppCompatActivity {

    private RkiTO to;

    private TextView gen;
    private TextView bez;
    private TextView death_rate;
    private TextView cases;
    private TextView deaths;
    private TextView cases_per_100k;
    private TextView cases_per_pop;
    private TextView bl;
    private TextView cases7;
    private TextView recovered;
    private TextView update;

    private LocalStorage localStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Gson gson = new Gson();
        to = gson.fromJson((String) getIntent().getExtras().get("to"), RkiTO.class);
        localStorage = new LocalStorage(this);

        initGUI();
        setTOzuGUI();
    }

    private void setTOzuGUI(){
        gen.setText(to.getGen());
        bez.setText(to.getBez());
        death_rate.setText("Todesrate: " + to.getDeath_rate());
        cases.setText("Fälle: " + to.getCases());
        deaths.setText("Tote: " + to.getDeaths());
        cases_per_100k.setText("Fälle pro 100k: " +to.getCases_per_100k());
        cases_per_pop.setText("Fälle pro Einwohner: " + to.getCases_per_population());
        bl.setText("Bundesland: " + to.getBl());
        cases7.setText("Fälle pro 100k pro 7 Tage: " + to.getCases7_per_100k());
        recovered.setText("Recovered: " + to.getRecovered());
        update.setText("Letztes Update: " + localStorage.getLastUpdateDate());
    }

    private void initGUI(){
        gen = findViewById(R.id.gen);
        bez = findViewById(R.id.bez);
        death_rate = findViewById(R.id.death_rate);
        cases = findViewById(R.id.cases);
        deaths = findViewById(R.id.deaths);
        cases_per_100k = findViewById(R.id.cases_per_100k);
        cases_per_pop = findViewById(R.id.cases_per_pop);
        bl = findViewById(R.id.bl);
        cases7 = findViewById(R.id.cases7);
        recovered = findViewById(R.id.recovered);
        update = findViewById(R.id.update);
    }


}