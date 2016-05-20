package hu.aut.bme.dg.f1app.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.BuildConfig;
import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.R;
import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.model.DriverModel;
import hu.aut.bme.dg.f1app.presenter.DriversPresenter;

/**
 * Created by DG on 2016.04.25..
 */
public class DriversActivity extends AppCompatActivity implements DriversView {

    public static final String KEY_DRIVER = "KEY_DRIVER";

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

//        refreshDrivers();
//        driversPresenter.showDriverDetails(driverId);

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

//        artist = getActivity().getIntent().getStringExtra(MainActivity.KEY_ARTIST);
        driversPresenter.attachView(this);
    }

    @Override
    public void onStop() {

        super.onStop();
        driversPresenter.detachView();
    }

    @Override
    public void refreshDrivers(List<Driver> drivers) {

        final ArrayList<Driver> list = new ArrayList<>();
        for (int i = 0; i < drivers.size(); ++i) {
            list.add(drivers.get(i));
        }
        ((ListView) findViewById(R.id.driverListView)).setAdapter(
                new DriverAdapter(getApplicationContext(), R.layout.list_driver_item, list));

    }

    @Override
    public void showDriverAdd() {

        Intent intent = new Intent(DriversActivity.this, DriverAddActivity.class);
        startActivity(intent);
    }

    @Override
    public void showDriverDetails(int driverId) {

        Intent intent = new Intent(DriversActivity.this, DriverEditActivity.class);
        intent.putExtra(KEY_DRIVER, driverId);
        startActivity(intent);

    }
}
