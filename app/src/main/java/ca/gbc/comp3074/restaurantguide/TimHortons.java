package ca.gbc.comp3074.restaurantguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

//TODO LIST:
//TODO 1. function of button Direction, need to be fixed. The current location was wrong in the direction when running this function.
//TODO 2. function of Rate
//TODO 3. function of Share: email, facebook, twitter. Click these buttons and open their app in phone
//TODO 4. function of Edit in Menu, navigate to Edit page
//TODO 5. function of Delete in Menu, popup confirmation dialog and have function to delete the record.
public class TimHortons extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_hortons);


        // create button MAP to show the restaurant location, and display a pin at that location
        Button btnMap = findViewById(R.id.btn_map);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //reference: https://developers.google.com/maps/documentation/urls/android-intents
                Uri location = Uri.parse("geo:0,0?q=2900 Warden Ave, Scarborough+View, Ontario");
                Intent openMap = new Intent(Intent.ACTION_VIEW, location);
                openMap.setPackage("com.google.android.apps.maps");
                startActivity(openMap);

            }
        });

        // create button Direction to show the direction from current location to the restaurant
        Button btnDirection = findViewById(R.id.btn_direction);
        btnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Tips
                 * Uri direction = Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345")
                 * in the above, ? mark is the beginning of query, saddr is start point, daddr is end point,
                 * the value can be latitude and longitude, or street address and city
                 * To start the navigation from the current location, remove the saddr parameter and value.
                 * but the when I try the above, the current location is not right.
                 *
                 * google.navigation:q="", can launch Google Maps navigation with turn-by-turn directions
                 * Uri direction = Uri.parse("google.navigation:q=2900 Warden Ave, Scarborough+View, Ontario");
                */

                //Uri direction = Uri.parse("google.navigation:q=2900 Warden Ave, Scarborough+View, Ontario");
                Uri direction = Uri.parse("http://maps.google.com/maps?daddr=2900 Warden Ave, Scarborough+View, Ontario");
                Intent openDirection = new Intent(android.content.Intent.ACTION_VIEW, direction);
                startActivity(openDirection);
            }
        });

    }


    // show the menu of resto_info_menu in TimHortons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.resto_info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_edit:
                //TODO openEdit();
            case R.id.menu_delete:
                //TODO openDelete();
            case R.id.menu_back:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}