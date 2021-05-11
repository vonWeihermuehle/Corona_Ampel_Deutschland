package net.mbmedia.coronaampel.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.RelativeLayout;
import com.google.android.material.tabs.TabLayout;
import net.mbmedia.coronaampel.R;
import net.mbmedia.coronaampel.util.DBHelper;
import net.mbmedia.coronaampel.api.JsonConnector;
import net.mbmedia.coronaampel.to.RkiTO;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RelativeLayout progressBar;
    private Context context = this;
    private ArrayList<RkiTO> TOs;
    public FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTabLayout();

        progressBar = findViewById(R.id.progress);

        viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        this.fragmentManager = getSupportFragmentManager();

        checkForUpdate();

        DBHelper db = new DBHelper(context);

        TOs = db.getAll();

    }


    public ArrayList<RkiTO> getTOs() {
        return TOs;
    }

    public void updateFragments(){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(Fragment f : ((MainActivity)context).fragmentManager.getFragments()){
                    if(f.getClass() == TabFavouriten.class){
                        ((TabFavouriten)f).update();
                    }else if(f.getClass() == TabAllgemein.class){
                        ((TabAllgemein)f).update();
                    }
                }
            }
        });
    }

    private void checkForUpdate(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                JsonConnector jsonConnector = new JsonConnector(context, progressBar);
                jsonConnector.checkForNewUpdates();
            }
        });

    }

    private void initTabLayout(){
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                for(Fragment f : getSupportFragmentManager().getFragments()){
                     if(f.getClass() == TabAllgemein.class){
                        ((TabAllgemein)f).update();
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



}