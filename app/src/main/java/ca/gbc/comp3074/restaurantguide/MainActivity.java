package ca.gbc.comp3074.restaurantguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.BaseAdapter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
    //ArrayList<Restaurant> arrayList = new ArrayList<Restaurant>();

    DatabaseHelper databaseHelper;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    AlertDialog.Builder builder;
    //CustomAdapter customAdapter;
    SearchableAdapter searchableAdapter;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        // add database values to arraylist
        arrayList = databaseHelper.getAllResto();


        // create sample data
        // TODO:this step is just to show the function of SearchView and ListView. Need to be improved when working with database.
        // if working with database, think about using getName() to get each restaurant's name and create the following list
        restaurantList = new String[]{"Tim Hortons", "McDonald's", "KFC", "Wendy's"};

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

        //enables filtering for the contents of the given ListView
        list.setTextFilterEnabled(true);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String[] fieldnames = new String[] {databaseHelper.RESTO_COLUMN_ID, databaseHelper.RESTO_COLUMN_NAME};


                //final TextView name = (TextView) findViewById(R.id.txt);


                /*
                String b = parent.getItemAtPosition(position).toString();
                Cursor data = databaseHelper.getItemID(b);
                int itemID = 0;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                String a = "The ID is: " + itemID;
                //Toast.makeText(MainActivity.this, a, Toast.LENGTH_LONG).show();
                 */

                /*
                Cursor cursor = databaseHelper.getRow(id);
                if (cursor.moveToFirst()) {
                    long idDB = cursor.getLong(databaseHelper.COL_ROWID);
                    String name = cursor.getString(databaseHelper.COL_NAME);

                    String msg = "ID : " + idDB + name;
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                }
                cursor.close();

                 */

                //long abc = getItems(position);
                //String item = (String) list.getItemAtPosition(position);
                long ids = parent.getItemIdAtPosition(position);
                //Cursor cursor = databaseHelper.getResto(ids);
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position).get(0), Toast.LENGTH_LONG).show();
                //String a = Long.toString(abc);
                //Toast.makeText(MainActivity.this, a, Toast.LENGTH_LONG).show();


                //String ags = searchableAdapter.filteredData.get(position);
                //String ag = Long.toString(ags);
                ArrayList<String> name = new ArrayList<>(databaseHelper.getAllResto());

                int actualPosition = name.indexOf(arrayAdapter.getItem(position));
                String a = Long.toString(actualPosition+1);
               //String item = (String) list.getItemAtPosition(position);
                //name.indexOf(position);
                //Toast.makeText(MainActivity.this, a, Toast.LENGTH_LONG).show();


                Intent intent = new Intent(MainActivity.this, second.class);
                intent.putExtra("extra", a);
                startActivity(intent);

                //d = arrayList.toArray(new Integer[arrayList.size()]);

                //long b = databaseHelper.getId(position);
                //String c = Long.toString(b);

                //Toast.makeText(MainActivity.this, a, Toast.LENGTH_LONG).show();
                //Cursor cursor = databaseHelper.getResto(position);
                //Toast.makeText(MainActivity.this, position, Toast.LENGTH_LONG).show();
                //if (cursor.moveToFirst())
                    //Toast.makeText(MainActivity.this, position, Toast.LENGTH_LONG).show();
                //cursor.close();


            }
        });



            //final boolean add = arrayList.add(databaseHelper.getResto(position));
            //Toast.makeText(getApplicationContext(), Toast.LENGTH_LONG).show();

                    /*
                    Intent intent = new Intent(context, DestinationActivityName.class);
intent.putExtra(Key, Value);
startActivity(intent);

            }
        })
        */

    }

    /*
    public void onListItemClick(ListView parent, View view, int position, long id) {
        Intent intent = new Intent(this, TheEndActivity.class);

        intent.putExtra("USER_ID", (int)id);
        startActivity(intent);

        Intent i=new Intent(UsernameList.this, QuizAppActivity.class);
        startActivity(i);
    }

     */



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
        listAllRestaurantsName();
        String text = newText;
        //adapter.filter(text);
        //if (newText.toLowerCase().contains(newText.toLowerCase())) {
            arrayAdapter.getFilter().filter(text);
        //};
        return false;
    }

    public long getItems(int position) {
        return arrayAdapter.getItemId(position);
    }



    /* --- LIST ALL BUTTON --- */
    // lists all restaurants when button 'List All' is clicked
    public void listAllRestaurantsName(){
        // locate the ListView in activity_main.xml
        //list = (ListView) findViewById(R.id.listview);




        // for displaying DB info
        // initialize arrayadapter
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item, arrayList);


        // Set arrayAdapter to ListView
        list = findViewById(R.id.listview);
        list.setAdapter(arrayAdapter);


        List<List<String>> ls2d = new ArrayList<List<String>>();
        List<String> x = new ArrayList<String>();

        x.addAll(databaseHelper.getAllRestoID());
        x.addAll(databaseHelper.getAllResto());
        ls2d.add(x);


        // checks if list is not empty
        if (arrayList.isEmpty()) {
            //for(int i=0; i<databaseHelper.numberOfRows(); i++){
            //databaseHelper.getName();

            String[] fieldnames = new String[] {databaseHelper.RESTO_COLUMN_ID, databaseHelper.RESTO_COLUMN_NAME};

            ArrayList<ArrayList> array_list = new ArrayList<ArrayList>();
            array_list.add(databaseHelper.getAllResto());
            //array_list.addAll(x);
            //arrayList.addAll(array_list);
            arrayList.addAll(databaseHelper.getAllResto());


           //arrayList.addAll(databaseHelper.getAllResto());
            // refresh listview data
            arrayAdapter.notifyDataSetChanged();

            //list.invalidateViews();
            //list.refreshDrawableState();
            /*
            for(int i=0; i<restaurantList.length; i++){
                Restaurant restaurantName = new Restaurant(restaurantList[i]);
                // bind all strings into an array
                arrayList.add(restaurantName);
            }

             */
        }


        // pass results to ListViewAdapter Class
        //adapter = new ListViewAdapter(this, arrayList);

        // bind the Adapter to the ListView
        //list.setAdapter(adapter);

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

    public class SearchableAdapter extends BaseAdapter implements Filterable {

        private List<String>originalData = arrayList;
        private List<String>filteredData = arrayList;
        private LayoutInflater mInflater;
        private ItemFilter mFilter = new ItemFilter();

        public SearchableAdapter(Context context, List<String> data) {
            this.filteredData = data ;
            this.originalData = data ;
            mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return filteredData.size();
        }

        public Object getItem(int position) {
            return filteredData.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // A ViewHolder keeps references to children views to avoid unnecessary calls
            // to findViewById() on each row.
            ViewHolder holder;

            // When convertView is not null, we can reuse it directly, there is no need
            // to reinflate it. We only inflate a new View when the convertView supplied
            // by ListView is null.
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.listview_item, null);

                // Creates a ViewHolder and store references to the two children views
                // we want to bind data to.
                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.textView2);

                // Bind the data efficiently with the holder.

                convertView.setTag(holder);
            } else {
                // Get the ViewHolder back to get fast access to the TextView
                // and the ImageView.
                holder = (ViewHolder) convertView.getTag();
            }

            // If weren't re-ordering this you could rely on what you set last time
            holder.text.setText(filteredData.get(position));

            return convertView;
        }

        class ViewHolder {
            TextView text;
        }

        public Filter getFilter() {
            return mFilter;
        }

        private class ItemFilter extends Filter {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String filterString = constraint.toString().toLowerCase();

                FilterResults results = new FilterResults();

                final List<String> list = originalData;

                int count = list.size();
                final ArrayList<String> nlist = new ArrayList<String>(count);

                String filterableString ;

                for (int i = 0; i < count; i++) {
                    filterableString = list.get(i);
                    if (filterableString.toLowerCase().contains(filterString)) {
                        nlist.add(filterableString);
                    }
                }

                results.values = nlist;
                results.count = nlist.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredData = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }

        }
    }
}