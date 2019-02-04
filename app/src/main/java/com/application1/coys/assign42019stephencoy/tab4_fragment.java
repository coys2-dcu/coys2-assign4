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


public class tab4_fragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "Tab4Fragment";
    private Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View root = inflater.inflate(R.layout.fragment_tab4_fragment, container,false);


        SharedPreferences settings = getActivity().getSharedPreferences("collection_address", Context.MODE_PRIVATE);
        settings.edit().clear().commit();
        return root;

    }

    @Override
    public void onStart() {
        super.onStart();




        // Create an ArrayList of Collection objects
        final ArrayList<CollectionTab4> collectionList = new ArrayList<CollectionTab4>();

        collectionList.add(new CollectionTab4("Naas","123 Main Street", "045123456",  R.drawable.naas));
        collectionList.add(new CollectionTab4("Newbridge","321 Fake Street", "045123456",  R.drawable.newbridge));
        collectionList.add(new CollectionTab4("Sallins","456 Lower Street", "045123456",  R.drawable.sallins));
        collectionList.add(new CollectionTab4("Kilcullen","654 Upper Street", "045123456",  R.drawable.kilcullen));
        collectionList.add(new CollectionTab4("Athy","789 Middle Street", "045123456",  R.drawable.athy));
        collectionList.add(new CollectionTab4("Kildare Town","987 Back Street", "045123456",  R.drawable.kildare));


        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        CollectionAdapter collectionAdapter= new CollectionAdapter(getActivity(), collectionList);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) getView().findViewById(R.id.listview_collection);
        listView.setAdapter(collectionAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                TextView name = (TextView) v.findViewById(R.id.town_name);
                Toast.makeText(getActivity(), name.getText().toString() + " selected for collection.", Toast.LENGTH_SHORT).show();

                SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor edt = pref.edit();
                edt.putString("collection_address", name.getText().toString());
                edt.commit();



            }


        });


    }

    }



