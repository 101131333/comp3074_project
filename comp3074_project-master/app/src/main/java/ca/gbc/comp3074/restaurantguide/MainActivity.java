package ca.gbc.comp3074.restaurantguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

//TODO LIST:
//TODO 1. function of clicking each item if the list and navigate to the desired Restaurant INFO page, like Tim Hortons.
//TODO 2. working with database (need to discuss)
//TODO 3. Don't forget to edit lists with database

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Declare Variables for SearchView and ListView
    ListView list;
    ListViewAdapter adapter;
    SearchView editSearch;
    String[] restaurantList;
    ArrayList<Restaurant> arrayList = new ArrayList<Restaurant>();


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create sample data
        // TODO:this step is just to show the function of SearchView and ListView. Need to be improved when working with database.
        // if working with database, think about using getName() to get each restaurant's name and create the following list
        restaurantList = new String[] {"Tim Hortons", "McDonald's", "KFC", "Wendy's"};

        // create Button for the function of List All
        Button btnListAll = (Button) findViewById(R.id.btn_list_all);
        btnListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listAllRestaurantsName();
            }
        });

        // create Button for the function of Add New
        Button btnAddNew = (Button) findViewById(R.id.btn_add_new);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewRestaurants();
            }
        });

        // create Button for the function of Clear
        Button btnClear = (Button) findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearList();
            }
        });

    }

    /* --- MENU --- */
    //opens About Activity
    private void openAboutActivity(){
        Intent start = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(start);
    }

    // menu implementation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.main_menu, menu);
        return true;
    }

    // if menu is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAbout:
                openAboutActivity();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /* --- SEARCH BAR --- */
    // this method is created automatically when this class implements SearchView.OnQueryTextListener
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    // this method is also created automatically when this class implements SearchView.OnQueryTextListener
    // but made some changes
    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }


    /* --- LIST ALL BUTTON --- */
    // lists all restaurants when button 'List All' is clicked
    public void listAllRestaurantsName(){
        // locate the ListView in activity_main.xml
        list = (ListView) findViewById(R.id.listview);

        // checks if list is not empty
        if (arrayList.isEmpty()) {
            for(int i=0; i<restaurantList.length; i++){
                Restaurant restaurantName = new Restaurant(restaurantList[i]);
                // bind all strings into an array
                arrayList.add(restaurantName);
            }
        }

        // pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arrayList);

        // bind the Adapter to the ListView
        list.setAdapter(adapter);

        // locate the EditText in activity_main.xml (that is, text in SearchView)
        editSearch = (SearchView) findViewById(R.id.search_view);
        editSearch.setOnQueryTextListener(this);
    }


    /* --- ADD NEW BUTTON --- */
    private void AddNewRestaurants(){
        Intent addResto = new Intent(getApplicationContext(), AddNewResto.class);
        startActivity(addResto);
    }


    /* --- CLEAR BUTTON --- */
    private void clearList(){
        list.invalidateViews();
        arrayList.clear();

    }
}