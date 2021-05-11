package net.mbmedia.coronaampel.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import net.mbmedia.coronaampel.R;
import net.mbmedia.coronaampel.util.DBHelper;
import net.mbmedia.coronaampel.to.RkiTO;

import java.util.ArrayList;
import java.util.List;

public class RKIAdapter extends ArrayAdapter<RkiTO> {


    private Context context;
    private List<RkiTO> toListe;
    private List<Integer> favs;
    private Fragment fragment;
    private DBHelper db;
    private Activity activity;


    public RKIAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<RkiTO> list, ArrayList<Integer> favs, Activity activity) {
        super(context, 0 , list);
        this.context = context;
        this.favs = favs;
        this.toListe = list;
        this.activity = activity;
        db = new DBHelper(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        final RkiTO to = toListe.get(position);


        TextView gen = listItem.findViewById(R.id.list_text1);
        TextView bez = listItem.findViewById(R.id.list_text2);
        ToggleButton btn = listItem.findViewById(R.id.toggleButton);
        LinearLayout ll = listItem.findViewById(R.id.item_layout);

        gen.setText(to.getGen());
        bez.setText(to.getBez());

        btn.setChecked(favs.contains(to.getObjectid()));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(favs.contains(to.getObjectid())){
                    db.delFavourite(to.getObjectid());
                }else {
                    db.addFavourite(to.getObjectid());
                }

                for(Fragment f : ((MainActivity)activity).fragmentManager.getFragments()){
                     if(f.getClass() == TabFavouriten.class){
                        ((TabFavouriten)f).update();
                    }
                }

            }
        });

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                Gson gson = new Gson();
                String json = gson.toJson(to);
                intent.putExtra("to", json);
                context.startActivity(intent);
            }
        });


        return listItem;
    }
}
