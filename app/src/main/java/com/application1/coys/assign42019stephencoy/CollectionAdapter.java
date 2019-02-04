/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.application1.coys.assign42019stephencoy;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*
* {@link AndroidFlavorAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
* based on a data source, which is a list of {@link AndroidFlavor} objects.
* */
public class CollectionAdapter extends ArrayAdapter<CollectionTab4> {

    private static final String LOG_TAG = CollectionAdapter.class.getSimpleName();


    public CollectionAdapter(Activity context, ArrayList<CollectionTab4> collectionList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, collectionList);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View collectionListItemView = convertView;
        if(collectionListItemView == null) {
            collectionListItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.collection_list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        CollectionTab4 currentCollectionPoint = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) collectionListItemView .findViewById(R.id.town_name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentCollectionPoint.getTownName());

        TextView addressTextView = (TextView) collectionListItemView.findViewById(R.id.address_name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        addressTextView.setText(currentCollectionPoint.getAddress());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) collectionListItemView.findViewById(R.id.phone_number);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(currentCollectionPoint.getPhoneNumber());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) collectionListItemView.findViewById(R.id.list_item_icon);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        iconView.setImageResource(currentCollectionPoint.getImageResourceId());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return collectionListItemView;
    }

}
