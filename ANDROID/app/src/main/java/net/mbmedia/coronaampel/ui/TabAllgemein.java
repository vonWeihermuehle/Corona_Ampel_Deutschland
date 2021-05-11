package net.mbmedia.coronaampel.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.mbmedia.coronaampel.R;
import net.mbmedia.coronaampel.util.DBHelper;


public class TabAllgemein extends Fragment {

    private DBHelper db;
    private ListView ListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab_allgemein, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new DBHelper(getContext());
        ListView = view.findViewById(R.id.listview_allgemein);
        ListView.setAdapter(new RKIAdapter(getContext(), ((MainActivity)getActivity()).getTOs(), db.getFavourites(), getActivity()));
    }

    public void update(){
        ListView.setAdapter(new RKIAdapter(getContext(), ((MainActivity)getActivity()).getTOs() , db.getFavourites(), getActivity()));
    }



}
