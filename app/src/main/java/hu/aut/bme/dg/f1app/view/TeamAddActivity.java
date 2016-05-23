package hu.aut.bme.dg.f1app.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.R;
import hu.aut.bme.dg.f1app.presenter.TeamAddPresenter;

public class TeamAddActivity extends AppCompatActivity implements TeamAddView {

    private static int RESULT_LOAD_IMAGE = 1;
    private Tracker mTracker;

    @Inject
    TeamAddPresenter teamAddPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        F1Application.injector.inject(this);

        F1Application application = (F1Application) getApplication();
        mTracker = application.getDefaultTracker();

        findViewById(R.id.buttonLoadPicture).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                teamAddPresenter.browseTeamImage();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team_add, menu);
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

            teamAddPresenter.saveTeam(((EditText) findViewById(R.id.teamNameText)).getText().toString(),
                                        ((EditText) findViewById(R.id.teamLeaderText)).getText().toString(),
                                        teamImageEncoded);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        teamAddPresenter.attachView(this);

        Log.i("GOOGLE ANALYTICS", "Setting screen name: TeamAddActivity");
        mTracker.setScreenName("Image~ TeamAddActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    protected void onStop() {
        super.onStop();
        teamAddPresenter.detachView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ImageView imageView = (ImageView) findViewById(R.id.teamImageImg);
            imageView.setImageBitmap(bitmap);

        }

    }

    public void saveTeam() {

        Intent intent = new Intent(TeamAddActivity.this, TeamsActivity.class);
        startActivity(intent);
    }

    public void browseTeamImage() {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }
}
