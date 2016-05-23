package hu.aut.bme.dg.f1app.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.R;
import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.presenter.DriversPresenter;

/**
 * Created by DG on 2016.04.25..
 */
public class DriversActivity extends AppCompatActivity implements DriversView {

    public static final String KEY_DRIVER = "KEY_DRIVER";
    private Tracker mTracker;

    @Inject
    DriversPresenter driversPresenter;

    public DriversActivity() {
        F1Application.injector.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        F1Application.injector.inject(this);

        F1Application application = (F1Application) getApplication();
        mTracker = application.getDefaultTracker();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drivers, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.driver_back) {
            driversPresenter.showMain();
        }
        if (id == R.id.driver_refresh) {
            driversPresenter.refreshDrivers();
        }
        if (id == R.id.driver_add) {
            driversPresenter.showDriverAdd();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        driversPresenter.attachView(this);
        driversPresenter.refreshDrivers();

        Log.i("GOOGLE ANALYTICS", "Setting screen name: DriversActivity");
        mTracker.setScreenName("Image~ DriversActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void onStop() {

        super.onStop();
        driversPresenter.detachView();
    }

    public void showMain() {
        Intent intent = new Intent(DriversActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void refreshDrivers(List<Driver> drivers) {

        final ArrayList<Driver> list = new ArrayList<>();
        for (int i = 0; i < drivers.size(); ++i) {
            list.add(drivers.get(i));
        }

        ListView driverListView = (ListView) findViewById(R.id.driverListView);
        driverListView.setAdapter(
                new DriverAdapter(getApplicationContext(), R.layout.list_driver_item, list));
        driverListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                driversPresenter.showDriverDetails(id);
            }

        });

    }

    public void showDriverAdd() {

        Intent intent = new Intent(DriversActivity.this, DriverAddActivity.class);
        startActivity(intent);
    }

    public void showDriverDetails(long driverId) {

        Intent intent = new Intent(DriversActivity.this, DriverEditActivity.class);
        intent.putExtra(KEY_DRIVER, driverId);
        startActivity(intent);

    }
}
