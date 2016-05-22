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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import hu.aut.bme.dg.f1app.F1Application;
import hu.aut.bme.dg.f1app.R;
import hu.aut.bme.dg.f1app.model.Driver;
import hu.aut.bme.dg.f1app.presenter.DriverEditPresenter;

public class DriverEditActivity extends AppCompatActivity implements DriverEditView {

    private boolean showDriver = true;
    private int driverId = 0;
    private static int RESULT_LOAD_IMAGE = 1;

    @Inject
    DriverEditPresenter driverEditPresenter;

    public DriverEditActivity() {
        F1Application.injector.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        F1Application.injector.inject(this);

        findViewById(R.id.buttonLoadPicture).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                driverEditPresenter.browseDriverImage();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_driver_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.driver_save) {

            ImageView image = (ImageView)findViewById(R.id.driverImageImg);
            Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String driverImageEncoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

            Driver editDriver = new Driver();
            editDriver.driverId = driverId;
            editDriver.driverName = ((EditText) findViewById(R.id.driverNameText)).getText().toString();
            editDriver.driverNumber = Integer.parseInt(((EditText) findViewById(R.id.driverNumberText)).getText().toString());
            editDriver.driverAge = Integer.parseInt(((EditText) findViewById(R.id.driverAgeText)).getText().toString());
            editDriver.driverImage = driverImageEncoded;

            driverEditPresenter.saveDriver(editDriver);
        }
        if (id == R.id.driver_delete) {
            driverEditPresenter.deleteDriver(driverId);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        driverEditPresenter.attachView(this);
        driverId = (int) (this.getIntent().getLongExtra(DriversActivity.KEY_DRIVER, 0));
        if(showDriver)
        {
            driverEditPresenter.showDriver(driverId);
        }

    }

    @Override
    public void onStop() {

        super.onStop();
        driverEditPresenter.detachView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            showDriver = false;
            Uri selectedImage = data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ImageView driverImage = (ImageView)findViewById(R.id.driverImageImg);
            driverImage.setImageBitmap(bitmap);

        }

    }

    public void showDriver(Driver editDriver) {

        EditText driverName = (EditText)findViewById(R.id.driverNameText);
        EditText driverNumber = (EditText)findViewById(R.id.driverNumberText);
        EditText driverTeam = (EditText)findViewById(R.id.driverTeamId);
        EditText driverAge = (EditText)findViewById(R.id.driverAgeText);
        ImageView driverImage = (ImageView)findViewById(R.id.driverImageImg);

        byte[] decodedString = Base64.decode(editDriver.driverImage, Base64.DEFAULT);
        Bitmap driverImageBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        driverImage.setImageBitmap(driverImageBitmap);

        driverName.setText(editDriver.driverName);
        driverNumber.setText(editDriver.driverNumber + "");
        driverAge.setText(editDriver.driverAge + "");

        if(editDriver.driverTeam != null)
            driverTeam.setText(editDriver.driverTeam.teamName);
        else
            driverTeam.setText("no team!");

    }

    public void saveDriver() {
        Intent intent = new Intent(DriverEditActivity.this, DriversActivity.class);
        startActivity(intent);
    }

    public void deleteDriver() {
        Intent intent = new Intent(DriverEditActivity.this, DriversActivity.class);
        startActivity(intent);
    }

    public void browseDriverImage() {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }
}
