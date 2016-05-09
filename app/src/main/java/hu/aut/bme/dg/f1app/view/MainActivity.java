package hu.aut.bme.dg.f1app.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orm.SugarContext;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.BuildConfig;
import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.R;
import hu.aut.bme.dg.f1app.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(BuildConfig.FLAVOR.equals("full")){
            SugarContext.init(this);
            Log.d("F1APP", "sugar orm is in use");
        }else{
            Log.d("F1APP", "mock version is in use");
        }



        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        F1Application.injector.inject(this);

        findViewById(R.id.showDriversButton).setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                mainPresenter.showDrivers();
            }
        });

        findViewById(R.id.showTeamsButton).setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                mainPresenter.showTeams();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();

        if(BuildConfig.FLAVOR.equals("full")) {

            Log.d("F1APP", "sugar orm was in use");
            SugarContext.terminate();
        }

    }

    @Override
    public void showDrivers() {
        Intent intent = new Intent(MainActivity.this, DriversActivity.class);
//        intent.putExtra(KEY_ARTIST,artistSearchTerm);
        startActivity(intent);
    }

    @Override
    public void showTeams() {
        Intent intent = new Intent(MainActivity.this, TeamsActivity.class);
//        intent.putExtra(KEY_ARTIST,artistSearchTerm);
        startActivity(intent);
    }


}
