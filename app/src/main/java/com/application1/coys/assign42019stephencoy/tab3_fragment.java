package com.application1.coys.assign42019stephencoy;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class tab3_fragment extends Fragment {
    //
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "Tab3Fragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_tab3_fragment, container, false);

        SharedPreferences colAdd = getActivity().getPreferences(Context.MODE_PRIVATE);
        colAdd.edit().remove("collection_address").commit();

        SharedPreferences product  = getActivity().getPreferences(Context.MODE_PRIVATE);
        product.edit().remove("product_selection").commit();

        meditOptional = (EditText) root.findViewById(R.id.editOptional);
        mSpinner = (Spinner) root.findViewById(R.id.spinner);
        mCustomerName = (EditText) root.findViewById(R.id.editCustomer);
        radioGroup1 = root.findViewById(R.id.radioGroup);
        ImageView imageView  = (ImageView) root.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View arg0) {
            dispatchTakePictureIntent();
             }
        });
        Button b = (Button)root.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0)
            {
                sendEmail();
            }
        });

        return root;

    }

    Uri mPhotoURI;
    File mPhotoFile = null;
    Spinner mSpinner;
    EditText mCustomerName;
    EditText meditOptional;
    RadioGroup radioGroup1;
    RadioButton radioButton2;
    String choice;
    private static boolean wasTouched;
    static final int REQUEST_TAKE_PHOTO = 2;

    @Override
    public void onStart() {

        super.onStart();
        meditOptional.setImeOptions(EditorInfo.IME_ACTION_DONE);
        meditOptional.setRawInputType(InputType.TYPE_CLASS_TEXT);

        //initialise spinner using the integer array
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.ui_time_entries, R.layout.spinner_days);
        mSpinner.setAdapter(adapter);
        // Set the Adapter for the spinner
        // Set an onTouchListener on the spinner because
        // onItemSelected() can be called multiple times by framework
        mSpinner.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                wasTouched = true;
                v.performClick();
                return false;
            }
        });

        // Set an onItemSelectedListener on the spinner
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                if (wasTouched) {

                    // Display a Toast message indicating the currently selected item
                    Toast.makeText(
                            parent.getContext(),
                            "You selected : "
                                    + parent.getItemAtPosition(pos).toString(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Creates a temporary file
     * @return Temp file and path for image storage once taken by camera intent
     */
    public File createTempFile(){
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new
                Date());
        String imageFileName = "My_Image_" + timeStamp + "_";
        //we should get a general reference to externalstorage for images.
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //declare an image file
        File myImage = null;
        //try catch, ensure it doesn't crash if the file fails to be taken
        try
        {
            //make an empty file
            myImage = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e)
        {
            String error = String.valueOf(e);
            Log.e(TAG, error);
            //toaster alert to let the user know there's an issue
            Toast toast = Toast.makeText(getActivity(), "Please try retaking your photo!",
                    Toast.LENGTH_LONG);
            toast.show();
        }
        return myImage;
    }

    public void dispatchTakePictureIntent(){
    //start the intent.
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    // check to see if the phone actually has a camera.
 if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null)
    {
        // first let’s call our new method to get the photofile
        mPhotoFile = createTempFile();
        // Continue only if the File was successfully created
        if (mPhotoFile != null)
        {
            //here we grab the Uri, (note we are using the authority it's our applicationID again)
            mPhotoURI = FileProvider.getUriForFile(getActivity(), "com.application1.coys.assign42019stephencoy", mPhotoFile);
            //take the photo replacing the file at the location.
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }else{
            //toaster alert to let the user know the image is missing
            Toast toast = Toast.makeText(getActivity(), "There was a problem saving please retry", Toast.LENGTH_LONG);
            toast.show();
        }
    }else{
        //toaster alert to let the user know there's an issue with the camera
        Toast toast = Toast.makeText(getActivity(), "There seems to be an issue with your camera", Toast.LENGTH_LONG);
        toast.show();
    }
}


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //also can give user a message that everything went ok
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            //let user know that image saved
            //I have strings in strings.xml but have hardcoded here to copy/paste to students if needed
            CharSequence text = "Image Taken successfully";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(getActivity(), text, duration);
            toast.show();
        }
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Notification!").setMessage("Image saved successfully.").setPositiveButton("OK", null).show();

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setCancelable(false);
        dialog.setTitle("Display Image");
        dialog.setMessage("Would you like to display the image?" );
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                ImageView imageView  = (ImageView) getView().findViewById(R.id.imageView);
                File photo = mPhotoFile;
                String filePath = photo.getPath();
                Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                imageView.setImageBitmap(bitmap);
            }
        })
                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();
        
        }

    private String createOrderSummary() {

        // get selected radio button from radioGroup
        int selectedId = radioGroup1.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioButton2 = getView().findViewById(selectedId);
        //String s = getResources().getString(R.string.colAddress);

        choice = radioButton2.getText().toString();

        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String name = pref.getString("collection_address", "empty");
        String tool = pref.getString("product_selection", "empty");

        String orderMessage = getString(R.string.customer_name) + ": " + mCustomerName.getText().toString();
        orderMessage += "\n" + "\n" + getString(R.string.order_message_1);
        orderMessage += "\n" + "\n" + "The tool I have selected is " + tool;
        orderMessage += "\n" + "I would like my order to be available for " + choice + " in " + ((CharSequence) mSpinner.getSelectedItem()).toString() + " days";
        orderMessage += "\n" + "Address: " + meditOptional.getText().toString() + name ;
        orderMessage += "\n" + getString(R.string.order_message_end) + "\n" + mCustomerName.getText().toString();
        return orderMessage;

        //update screen
    }


    public void sendEmail() {
        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String name = pref.getString("collection_address", "");
        String product = pref.getString("product_selection", "");
        //check that Name is not empty, and ask do they want to continue
        String addressField = meditOptional.getText().toString();
        String customerName = mCustomerName.getText().toString();
        if (customerName.matches("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Notification!").setMessage("Customer Name not set.").setPositiveButton("OK", null).show();
        }
         else if (addressField.matches("") && (name.matches("")))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Notification!").setMessage("Delivery/Collection Address not set.").setPositiveButton("OK", null).show();

        }
        else if (product.matches(""))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Notification!").setMessage("Please select a product from the Product Tab").setPositiveButton("OK", null).show();

        }
        else
            {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.to_email)});
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
            intent.putExtra(Intent.EXTRA_STREAM, mPhotoURI);
            intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }


}




