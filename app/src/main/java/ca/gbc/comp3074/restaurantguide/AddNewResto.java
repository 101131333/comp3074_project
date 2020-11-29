package ca.gbc.comp3074.restaurantguide;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddNewResto extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_resto);

        // ASSIGN VARIABLES
        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);
        // add database values to arraylist
        arrayList = databaseHelper.getAllResto();

        // for displaying DB info - initialize arrayadapter
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, arrayList);

        // alert builder
        builder = new AlertDialog.Builder(this);

        // dropdown
        Spinner dropdown = findViewById(R.id.txtTags);
        // insert preloaded tags
        databaseHelper.insertTags();
        List<String> tags = databaseHelper.getTags();
        // adapter to describe how the items are displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tags);
        //set the spinners adapter to the previously created one
        dropdown.setAdapter(adapter);

        // buttons
        Button btn_save = (Button) findViewById(R.id.btnSave);
        Button btn_cancel = (Button) findViewById(R.id.btnCancel);

        // textInputs / EditText
        final TextView name = (TextView) findViewById(R.id.txtName);
        final TextView street = (TextView) findViewById(R.id.txtStreetAddress);
        final TextView city = (TextView) findViewById(R.id.txtCityAddress);
        final TextView country = (TextView) findViewById(R.id.txtCountryAddress);
        final TextView postal = (TextView) findViewById(R.id.txtPostalAddress);
        final Spinner tag = (Spinner) findViewById(R.id.txtTags);
        final TextView phone = (TextView) findViewById(R.id.txtPhone);
        final TextView description = (TextView) findViewById(R.id.txtDescription);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.rating);

        /* --- SAVE BUTTON --- */
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get text from Edittext
                final String getName = name.getText().toString();
                final String getStreet = street.getText().toString();
                final String getCity = city.getText().toString();
                final String getCountry = country.getText().toString();
                final String getPostal = postal.getText().toString();
                final String getTag = tag.getSelectedItem().toString();
                final String getPhone = phone.getText().toString();
                final String getDescription = description.getText().toString();
                final String getRate = String.valueOf(ratingBar.getRating());

                // Validation - if at least 1 is empty
                if ((getName.isEmpty()) && (getStreet.isEmpty()) && (getCity.isEmpty()) && (getCountry.isEmpty())
                        && (getPostal.isEmpty()) && (getPhone.isEmpty()) && (getDescription.isEmpty())) {
                    Toast.makeText(getApplicationContext(),"All fields must be filled in",
                            Toast.LENGTH_LONG).show();
                }

                // Asks user for confirmation
                builder.setMessage("Do you want to save new restaurant?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // adds new restaurant info to database
                                if (databaseHelper.addResto(getName, getStreet, getCity, getCountry,
                                        getPostal, getTag, getPhone, getRate, getDescription)) {

                                    // go backs to main page
                                    finish();
                                    // displays alert
                                    Toast.makeText(getApplicationContext(), "New Restaurant Saved",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        // Action for 'NO' Button
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) { dialog.cancel();}
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Add New Restaurant");
                alert.show();
            }
        });

        /* --- CANCEL BUTTON --- */
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backToMain);
            }
        });
    }
}