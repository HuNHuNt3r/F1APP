package hu.aut.bme.dg.f1app.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.R;
import hu.aut.bme.dg.f1app.presenter.TeamsPresenter;

/**
 * Created by DG on 2016.04.25..
 */
public class TeamsActivity extends AppCompatActivity implements TeamsView {

    public static final String KEY_TEAM = "KEY_TEAM";

    @Inject
    TeamsPresenter teamsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        F1Application.injector.inject(this);

        refreshTeams();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teams, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.team_refresh) {
            teamsPresenter.refreshTeams();
        }
        if (id == R.id.team_add) {
            teamsPresenter.showTeamAdd();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

//        artist = getActivity().getIntent().getStringExtra(MainActivity.KEY_ARTIST);
        teamsPresenter.attachView(this);
    }

    @Override
    public void onStop() {

        super.onStop();
        teamsPresenter.detachView();
    }

    @Override
    public void refreshTeams() {
        //refresh list
    }

    @Override
    public void showTeamAdd() {
        Intent intent = new Intent(TeamsActivity.this, TeamAddActivity.class);
        startActivity(intent);
    }

    @Override
    public void showTeamDetails(int teamId) {

        Intent intent = new Intent(TeamsActivity.this, TeamEditActivity.class);
        intent.putExtra(KEY_TEAM, teamId);
        startActivity(intent);
    }
}
