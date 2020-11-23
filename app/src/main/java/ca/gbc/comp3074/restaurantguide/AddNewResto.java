package ca.gbc.comp3074.restaurantguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.session.MediaController;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AddNewResto extends AppCompatActivity {

//TODO LIST:
//TODO 1. Implement database so inputs will be added/saved
//TODO 2. Tags should be retrieved from database
//TODO 3. Check validations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_resto);

        // DECLARE VARIABLES
        // dropdown
        Spinner dropdown = findViewById(R.id.txtTags);
        //TODO: Replace with database
        String[] tags = new String[]{"1", "2", "three"};
        // adapter to describe how the items are displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tags);
        //set the spinners adapter to the previously created one
        dropdown.setAdapter(adapter);

        // buttons
        Button btn_save = (Button) findViewById(R.id.btnSave);
        Button btn_cancel = (Button) findViewById(R.id.btnCancel);

        // textInputs / EditText
        final EditText name = (EditText) findViewById(R.id.txtName);
        final EditText street = (EditText) findViewById(R.id.txtStreetAddress);
        final EditText city = (EditText) findViewById(R.id.txtCityAddress);
        final EditText postal = (EditText) findViewById(R.id.txtPostalAddress);
        final EditText phone = (EditText) findViewById(R.id.txtPhone);
        final EditText description = (EditText) findViewById(R.id.txtDescription);

        final RatingBar ratingBar = (RatingBar) findViewById(R.id.rating);



        //final TextView description = (TextView) findViewById(R.id.txtDescription);

        //TODO: Replace code if database is implemented
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //description.setText("Rating: " + ratingBar.getRating());
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