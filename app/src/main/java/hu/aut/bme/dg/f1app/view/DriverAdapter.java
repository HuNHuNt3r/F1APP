package hu.aut.bme.dg.f1app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import hu.aut.bme.dg.f1app.R;
import hu.aut.bme.dg.f1app.model.Driver;

/**
 * Created by DG on 2016.05.17..
 */
public class DriverAdapter extends ArrayAdapter<Driver> {

    HashMap<Driver, Long> mIdMap = new HashMap<Driver, Long>();

    public DriverAdapter(Context context, int textViewResourceId, List<Driver> objects) {
        super(context, textViewResourceId, objects);

        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), objects.get(i).getId());
        }
    }

    @Override
    public long getItemId(int position) {
        Driver item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Driver driver = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_driver_item, parent, false);
        }
        // Lookup view for data population
        ImageView driverImage = (ImageView) convertView.findViewById(R.id.driverImage);
        TextView driverName = (TextView) convertView.findViewById(R.id.driverName);
        TextView driverTeam = (TextView) convertView.findViewById(R.id.driverTeam);
        TextView driverNumber = (TextView) convertView.findViewById(R.id.driverNumber);
        // Populate the data into the template view using the data object
        byte[] decodedString = Base64.decode(driver.driverImage, Base64.DEFAULT);
        Bitmap driverImageBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        driverImage.setImageBitmap(driverImageBitmap);

        driverName.setText(driver.driverName);

        if(driver.driverTeam != null)
            driverTeam.setText(driver.driverTeam.teamName);
        else
            driverTeam.setText("no team!");

        driverNumber.setText(driver.driverNumber + "");
        // Return the completed view to render on screen
        return convertView;
    }

}
