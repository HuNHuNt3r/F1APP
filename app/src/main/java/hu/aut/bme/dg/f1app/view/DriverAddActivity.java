package hu.aut.bme.dg.f1app.view;

import android.content.Intent;
import android.database.Cursor;
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
import hu.aut.bme.dg.f1app.presenter.DriverAddPresenter;

public class DriverAddActivity extends AppCompatActivity implements DriverAddView {

    private static int RESULT_LOAD_IMAGE = 1;

    @Inject
    DriverAddPresenter driverAddPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        F1Application.injector.inject(this);

        findViewById(R.id.buttonLoadPicture).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                driverAddPresenter.browseDriverImage();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_driver_add, menu);
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

            driverAddPresenter.saveDriver(((EditText) findViewById(R.id.driverNameText)).getText().toString(),
                                            Integer.parseInt(((EditText) findViewById(R.id.driverNumberText)).getText().toString()),
                                            Integer.parseInt(((EditText) findViewById(R.id.driverAgeText)).getText().toString()),
                                            driverImageEncoded);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        driverAddPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        driverAddPresenter.detachView();
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

            ImageView imageView = (ImageView) findViewById(R.id.driverImageImg);
            imageView.setImageBitmap(bitmap);

        }

    }

    public void saveDriver() {

        Intent intent = new Intent(DriverAddActivity.this, DriversActivity.class);
        startActivity(intent);
    }

    public void browseDriverImage() {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }
}
