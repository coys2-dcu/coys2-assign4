package com.application1.coys.assign42019stephencoy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class tab2_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "Tab2Fragment";
    private Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View root = inflater.inflate(R.layout.fragment_tab2_fragment, container,false);

        return root;

    }

    @Override
    public void onStart() {
        super.onStart();


        // Create an ArrayList of AndroidFlavor objects
        final ArrayList<AndroidFlavor> androidFlavors = new ArrayList<AndroidFlavor>();
        androidFlavors.add(new AndroidFlavor("Hammers", "Sheffield", R.drawable.hammer));
        androidFlavors.add(new AndroidFlavor("Screwdrivers", "Stanley", R.drawable.screwdriver));
        androidFlavors.add(new AndroidFlavor("Chisels", "Ashley Isles", R.drawable.chisel));
        androidFlavors.add(new AndroidFlavor("Saws", "Axminster", R.drawable.saw));
        androidFlavors.add(new AndroidFlavor("Pliers", "Stanley", R.drawable.pliers));
        androidFlavors.add(new AndroidFlavor("Spanners", "Lie-Nielsen", R.drawable.spanner));
        androidFlavors.add(new AndroidFlavor("Tapes", "Stanley", R.drawable.tape));
        androidFlavors.add(new AndroidFlavor("Planes", "Sheffield", R.drawable.plane));


        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        AndroidFlavorAdapter flavorAdapter = new AndroidFlavorAdapter(getActivity(), androidFlavors);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) getView().findViewById(R.id.listview_flavor);
        listView.setAdapter(flavorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                TextView name = (TextView) v.findViewById(R.id.version_name);
                Toast.makeText(getActivity(), name.getText().toString() + " added", Toast.LENGTH_SHORT).show();

                SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor edt = pref.edit();
                edt.putString("product_selection", name.getText().toString());
                edt.commit();
            }


        });


    }
}


