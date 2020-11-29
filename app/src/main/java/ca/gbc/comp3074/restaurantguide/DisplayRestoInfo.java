package ca.gbc.comp3074.restaurantguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

// TODO LIST:
// TODO 1: Implement Google Maps
// TODO 2: Implement Directions
// TODO 3: Share Email
// TODO 4: Share Facebook
// TODO 5: Share Twitter

public class DisplayRestoInfo extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    private TextView displayName;
    private TextView displayStreet;
    private TextView displayCity;
    private TextView displayCountry;
    private TextView displayPostal;
    private TextView displayTag;
    private TextView displayPhone;
    private TextView displayDesc;
    private RatingBar ratingBar;
    String passId;
    Bundle selectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_resto_info);

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);

        // DISPLAY FETCHED RESTO INFO
        displayName = (TextView)findViewById(R.id.lblRestoName);
        displayStreet = (TextView)findViewById(R.id.txtStreetAddress);
        displayCity = (TextView)findViewById(R.id.txtCityAddress);
        displayCountry = (TextView)findViewById(R.id.txtCountryAddress);
        displayPostal = (TextView)findViewById(R.id.txtPostalAddress);
        displayTag = (TextView)findViewById(R.id.txtTag);
        displayPhone = (TextView)findViewById(R.id.txtPhone);
        displayDesc = (TextView)findViewById(R.id.txtDescription);
        displayDesc = (TextView)findViewById(R.id.txtDescription);

        selectId = getIntent().getExtras();
        if(selectId!=null) {
            // Retrieve id from main activity
            String getId =(String) selectId.get("id");
            // convert string to int
            int idDB = Integer.parseInt(getId);
            passId = idDB + "";

            displayName.setText(databaseHelper.getName(idDB));
            displayStreet.setText(databaseHelper.getStreet(idDB));
            displayCity.setText(databaseHelper.getCity(idDB));
            displayCountry.setText(databaseHelper.getCountry(idDB));
            displayPostal.setText(databaseHelper.getPostal(idDB));
            displayTag.setText(databaseHelper.getTag(idDB));
            displayPhone.setText(databaseHelper.getPhone(idDB));
            displayDesc.setText(databaseHelper.getDesc(idDB));

            ratingBar = (RatingBar) findViewById(R.id.rating);
            ratingBar.setRating(Float.parseFloat(databaseHelper.getRate(idDB)));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // refreshes page (and info) when coming back from editing Restaurant info
        startActivity(getIntent());
    }

    /* --- MENU --- */
    // menu implementation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.resto_info_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // if menu is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                Intent edit = new Intent(DisplayRestoInfo.this, EditResto.class);
                edit.putExtra("id", passId);
                startActivity(edit);
                break;
            case R.id.menu_back:
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
                break;
            case R.id.menu_delete:
                deleteRestaurant();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /* --- DELETES RESTAURANT --- */
    public void deleteRestaurant(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to delete this restaurant?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        // get id
                        String getId =(String) selectId.get("id");
                        int idDB = Integer.parseInt(getId);
                        if (databaseHelper.deleteResto(idDB)) {
                            // go backs to main page
                            finish();
                            // displays alert
                            Toast.makeText(getApplicationContext(), "Restaurant Deleted",
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
        alert.setTitle("Delete Restaurant");
        alert.show();
    }

}