package ca.gbc.comp3074.restaurantguide;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
// TODO LIST:
//TODO 1: add ‘Back’ button here BUT make sure values are retrieved and displayed

public class EditResto extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    private EditText name;
    private EditText street;
    private EditText city;
    private EditText country;
    private EditText postal;
    private Spinner tag;
    private EditText phone;
    private EditText desc;
    private RatingBar ratingBar;
    String passId;
    Bundle selectId;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resto);

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);

        // Find in xml file
        name = (EditText) findViewById(R.id.txtName);
        street = (EditText) findViewById(R.id.txtStreetAddress);
        city = (EditText) findViewById(R.id.txtCityAddress);
        country = (EditText) findViewById(R.id.txtCountryAddress);
        postal = (EditText) findViewById(R.id.txtPostalAddress);
        tag = (Spinner) findViewById(R.id.txtTags);
        phone = (EditText) findViewById(R.id.txtPhone);
        desc = (EditText) findViewById(R.id.txtDescription);

        selectId = getIntent().getExtras();
        if (selectId != null) {
            // Retrieve id from main activity
            String getId = (String) selectId.getString("id");
            int idDB = Integer.parseInt(getId);
            passId = idDB +"";

            // Fetch and Display retrieved resto info from DB
            name.setText(databaseHelper.getName(idDB));
            street.setText(databaseHelper.getStreet(idDB));
            city.setText(databaseHelper.getCity(idDB));
            country.setText(databaseHelper.getCountry(idDB));
            postal.setText(databaseHelper.getPostal(idDB));
            phone.setText(databaseHelper.getPhone(idDB));
            desc.setText(databaseHelper.getDesc(idDB));

            // dropdown
            Spinner dropdown = findViewById(R.id.txtTags);
            // insert preloaded tags
            databaseHelper.insertTags();
            List<String> tags = databaseHelper.getTags();
            // adapter to describe how the items are displayed
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tags);
            //set the spinners adapter to the previously created one
            dropdown.setAdapter(adapter);
            tag.setSelection(adapter.getPosition(databaseHelper.getTag(idDB)));

            ratingBar = (RatingBar) findViewById(R.id.rating);
            ratingBar.setRating(Float.parseFloat(databaseHelper.getRate(idDB)));
        }

        // Button functions
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSave();
            }
        });
    }


    /* --- CANCEL BUTTON --- */
    private void goBack(){
        Intent back = new Intent(getApplicationContext(), DisplayRestoInfo.class);
        back.putExtra("id", passId);
        startActivity(back);
    }


    /* --- SAVE BUTTON --- */
    private void goSave(){
        final String getName = name.getText().toString();
        final String getStreet = street.getText().toString();
        final String getCity = city.getText().toString();
        final String getCountry = country.getText().toString();
        final String getPostal = postal.getText().toString();
        final String getTag = tag.getSelectedItem().toString();
        final String getPhone = phone.getText().toString();
        final String getDescription = desc.getText().toString();
        final String getRate = String.valueOf(ratingBar.getRating());

        // Validation - if at least 1 is empty
        if ((getName.isEmpty()) && (getStreet.isEmpty()) && (getCity.isEmpty()) && (getCountry.isEmpty())
                && (getPostal.isEmpty()) && (getPhone.isEmpty()) && (getDescription.isEmpty())) {
            Toast.makeText(getApplicationContext(),"All fields must be filled in",
                    Toast.LENGTH_LONG).show();
        }

        builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to save the new information?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        // adds new restaurant info to database
                        if (databaseHelper.updateResto(1, getName, getStreet, getCity, getCountry,
                                getPostal, getTag, getPhone, getRate, getDescription)) {

                            // go backs to main page
                            finish();
                            // displays alert
                            Toast.makeText(getApplicationContext(), "New Restaurant Info Saved",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    //  Action for 'NO' Button
                    public void onClick(DialogInterface dialog, int id) {dialog.cancel();}
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Edit Restaurant");
        alert.show();
    }
}

