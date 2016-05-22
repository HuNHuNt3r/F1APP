package hu.aut.bme.dg.f1app.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.R;
import hu.aut.bme.dg.f1app.model.Team;
import hu.aut.bme.dg.f1app.presenter.DriverEditPresenter;
import hu.aut.bme.dg.f1app.presenter.TeamEditPresenter;

public class TeamEditActivity extends AppCompatActivity implements TeamEditView {

    private boolean showTeam = true;
    private int teamId = 0;
    private static int RESULT_LOAD_IMAGE = 1;

    @Inject
    TeamEditPresenter teamEditPresenter;

    public TeamEditActivity() {
        F1Application.injector.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        F1Application.injector.inject(this);

        findViewById(R.id.buttonLoadPicture).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                teamEditPresenter.browseTeamImage();
            }
        });

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

            ImageView image = (ImageView)findViewById(R.id.teamImageImg);
            Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String teamImageEncoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

            Team editTeam = new Team();
            editTeam.teamId = teamId;
            editTeam.teamName = ((EditText) findViewById(R.id.teamNameText)).getText().toString();
            editTeam.teamLeader = ((EditText) findViewById(R.id.teamLeaderText)).getText().toString();
            editTeam.teamImage = teamImageEncoded;

            teamEditPresenter.saveTeam(editTeam);
        }
        if (id == R.id.team_delete) {
            teamEditPresenter.deleteTeam(teamId);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        teamEditPresenter.attachView(this);
        teamId = (int) (this.getIntent().getLongExtra(TeamsActivity.KEY_TEAM, 0));
        if(showTeam)
        {
            teamEditPresenter.showTeam(teamId);
        }

    }

    @Override
    public void onStop() {

        super.onStop();
        teamEditPresenter.detachView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            showTeam = false;
            Uri selectedImage = data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ImageView teamImage = (ImageView)findViewById(R.id.teamImageImg);
            teamImage.setImageBitmap(bitmap);

        }

    }

    public void showTeam(Team editTeam) {

        EditText teamName = (EditText)findViewById(R.id.teamNameText);
        EditText teamLeader = (EditText)findViewById(R.id.teamLeaderText);
        EditText firstDriver = (EditText)findViewById(R.id.firstDriverId);
        EditText secondDriver = (EditText)findViewById(R.id.secondDriverId);
        ImageView teamImage = (ImageView)findViewById(R.id.teamImageImg);

        byte[] decodedString = Base64.decode(editTeam.teamImage, Base64.DEFAULT);
        Bitmap teamImageBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        teamImage.setImageBitmap(teamImageBitmap);

        teamName.setText(editTeam.teamName);
        teamLeader.setText(editTeam.teamLeader);

        if(editTeam.firstDriver != null)
            firstDriver.setText(editTeam.firstDriver.driverName);
        else
            firstDriver.setText("no driver1!");

        if(editTeam.secondDriver != null)
            secondDriver.setText(editTeam.secondDriver.driverName);
        else
            secondDriver.setText("no driver2!");

    }

    public void saveTeam() {
        Intent intent = new Intent(TeamEditActivity.this, TeamsActivity.class);
        startActivity(intent);
    }

    public void deleteTeam() {
        Intent intent = new Intent(TeamEditActivity.this, TeamsActivity.class);
        startActivity(intent);
    }

    public void browseTeamImage() {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }
}
