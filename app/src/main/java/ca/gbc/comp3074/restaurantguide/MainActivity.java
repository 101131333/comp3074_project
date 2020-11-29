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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import java.util.ArrayList;

//TODO LIST:
//TODO 1: Search by tags!!!
//TODO 2: list of restaurants (different from the ones saved???) - do we need preloaded list?
//TODO 3: OPTIONAL: Make use of 'Restaurant.java', 'ListViewAdapter.java' model???
//TODO 4: OPTIONAL: Use ‘Filterable’ to filter search
//TODO 5: Validation: proper formatting on address

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // SearchView and ListView
    ListView list;
    SearchView editSearch;

    //ListViewAdapter adapter;
    //ArrayList<Restaurant> arrayList = new ArrayList<Restaurant>();

    // Databases and adapters
    DatabaseHelper databaseHelper;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        // add database values to arraylist
        arrayList = databaseHelper.getAllResto();

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


        list = findViewById(R.id.listview);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // retrieves position of selected item
                String getKey = arrayAdapter.getItem(position).toString();
                // gets id of selected item
                String[] idDB = getKey.split(" - ", 2);
                // Go to view resto page
                Intent intent = new Intent(MainActivity.this, DisplayRestoInfo.class);
                intent.putExtra("id", idDB[0]);
                startActivity(intent);
            }
        });

        //TODO: Restart database for testing purposes - may delete when done
        //databaseHelper.clearDatabase("restaurant_tbl");
        //databaseHelper.clearDatabase("tags_tbl");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // refreshes page (and list) when coming back from other activities
        startActivity(getIntent());
    }


    /* --- MENU --- */
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
                Intent start = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(start);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    /* --- SEARCH BAR --- */
    // this method is created automatically when this class implements SearchView.OnQueryTextListener
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    // Method created automatically when class implements SearchView.OnQueryTextListener
    @Override
    public boolean onQueryTextChange(String newText) {
        listAllRestaurantsName();
        String text = newText;
        arrayAdapter.getFilter().filter(text);
        return false;
    }


    /* --- LIST ALL BUTTON --- */
    // lists all restaurants when button 'List All' is clicked
    public void listAllRestaurantsName(){

        // for displaying DB info - initialize arrayadapter
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item, arrayList);

        // Set arrayAdapter to ListView
        list = findViewById(R.id.listview);
        list.setAdapter(arrayAdapter);

        // checks if list is not empty
        if (arrayList.isEmpty()) {

            //ArrayList<ArrayList> array_list = new ArrayList<ArrayList>();
            //array_list.add(databaseHelper.getAllResto());

            arrayList.addAll(databaseHelper.getAllResto());

            // refresh listview data
            arrayAdapter.notifyDataSetChanged();
            list.invalidateViews();
            list.refreshDrawableState();
        }

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
        listAllRestaurantsName();
        list = findViewById(R.id.listview);
        list.invalidateViews();
        arrayList.clear();
        list.refreshDrawableState();
    }
}