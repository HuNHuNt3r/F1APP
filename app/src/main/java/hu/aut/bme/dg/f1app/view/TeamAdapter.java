package hu.aut.bme.dg.f1app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import hu.aut.bme.dg.f1app.R;
import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.model.Team;

/**
 * Created by DG on 2016.05.21..
 */
public class TeamAdapter extends ArrayAdapter<Team> {

    HashMap<Team, Long> mIdMap = new HashMap<Team, Long>();

    public TeamAdapter(Context context, int textViewResourceId, List<Team> objects) {
        super(context, textViewResourceId, objects);

        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), objects.get(i).getId());
        }
    }

    @Override
    public long getItemId(int position) {
        Team item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Team team = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_team_item, parent, false);
        }
        // Lookup view for data population
        ImageView teamImage = (ImageView) convertView.findViewById(R.id.teamImage);
        TextView teamName = (TextView) convertView.findViewById(R.id.teamName);
        TextView firstDriverName = (TextView) convertView.findViewById(R.id.firstDriverName);
        TextView secondDriverName = (TextView) convertView.findViewById(R.id.secondDriverName);
        TextView firstDriverNumber = (TextView) convertView.findViewById(R.id.firstDriverNumber);
        TextView secondDriverNumber = (TextView) convertView.findViewById(R.id.secondDriverNumber);

        // Populate the data into the template view using the data object
        byte[] decodedString = Base64.decode(team.teamImage, Base64.DEFAULT);
        Bitmap teamImageBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        teamImage.setImageBitmap(teamImageBitmap);

        teamName.setText(team.teamName);

        if(team.firstDriver != null)
        {
            firstDriverName.setText(team.firstDriver.driverName);
            firstDriverNumber.setText(team.firstDriver.driverNumber);
        }
        else
        {
            firstDriverName.setText("no driver1!");
            firstDriverNumber.setText("-");
        }

        if(team.secondDriver != null)
        {
            secondDriverName.setText(team.secondDriver.driverName);
            secondDriverNumber.setText(team.secondDriver.driverNumber);
        }
        else
        {
            secondDriverName.setText("no driver2!");
            secondDriverNumber.setText("-");
        }

        // Return the completed view to render on screen
        return convertView;
    }

}
