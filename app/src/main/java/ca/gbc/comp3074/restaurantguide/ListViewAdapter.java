package ca.gbc.comp3074.restaurantguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Restaurant> restaurantList = null;
    private ArrayList<Restaurant> arrayList;

    // set constructor
    public ListViewAdapter(Context context, List<Restaurant> restaurantList) {
        mContext = context;
        this.restaurantList = restaurantList;

        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Restaurant>();
        this.arrayList.addAll(restaurantList);
    }

    public class ViewHolder{
        TextView name;
    }

    // the following 4 methods is generated automatically when the class extends BaseAdapter, but made some modification
    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);

            // locate the TextView in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        // set the result into TextView
        holder.name.setText(restaurantList.get(position).getName());
        return view;
    }

    // filter Class
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        restaurantList.clear();
        if(charText.length() ==0){
            restaurantList.addAll(arrayList);
        }else{
            for(Restaurant wp : arrayList){
                if(wp.getName().toLowerCase(Locale.getDefault()).contains(charText)){
                    restaurantList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    //TODO: filter by Tag
}
