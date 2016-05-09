package hu.aut.bme.dg.f1app.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.R;
import hu.aut.bme.dg.f1app.presenter.DriverEditPresenter;
import hu.aut.bme.dg.f1app.presenter.TeamEditPresenter;

public class TeamEditActivity extends AppCompatActivity implements TeamEditView {

    @Inject
    TeamEditPresenter teamEditPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        F1Application.injector.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.team_save) {
            teamEditPresenter.saveTeam();
        }
        if (id == R.id.team_delete) {
            teamEditPresenter.deleteTeam();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void saveTeam() {
        Intent intent = new Intent(TeamEditActivity.this, TeamsActivity.class);
        startActivity(intent);
    }

    @Override
    public void deleteTeam() {
        Intent intent = new Intent(TeamEditActivity.this, TeamsActivity.class);
        startActivity(intent);
    }
}
