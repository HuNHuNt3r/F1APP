package hu.aut.bme.dg.f1app.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.R;
import hu.aut.bme.dg.f1app.model.Team;
import hu.aut.bme.dg.f1app.presenter.TeamsPresenter;

/**
 * Created by DG on 2016.04.25..
 */
public class TeamsActivity extends AppCompatActivity implements TeamsView {

    public static final String KEY_TEAM = "KEY_TEAM";

    @Inject
    TeamsPresenter teamsPresenter;

    public TeamsActivity() {
        F1Application.injector.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        F1Application.injector.inject(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teams, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.team_back) {
            teamsPresenter.showMain();
        }
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

        teamsPresenter.attachView(this);
        teamsPresenter.refreshTeams();
    }

    @Override
    public void onStop() {

        super.onStop();
        teamsPresenter.detachView();
    }

    public void showMain() {
        Intent intent = new Intent(TeamsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void refreshTeams(List<Team> teams) {

        final ArrayList<Team> list = new ArrayList<>();
        for (int i = 0; i < teams.size(); ++i) {
            list.add(teams.get(i));
        }

        ListView teamListView = (ListView) findViewById(R.id.teamListView);
        teamListView.setAdapter(
                new TeamAdapter(getApplicationContext(), R.layout.list_team_item, list));
        teamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                teamsPresenter.showTeamDetails(id);
            }

        });

    }

    public void showTeamAdd() {

        Intent intent = new Intent(TeamsActivity.this, TeamAddActivity.class);
        startActivity(intent);
    }

    public void showTeamDetails(long teamId) {

        Intent intent = new Intent(TeamsActivity.this, TeamEditActivity.class);
        intent.putExtra(KEY_TEAM, teamId);
        startActivity(intent);

    }
}
