package game.sbin.com.cameravideoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "Camera Getting Started" ;

    final int PHOTO_INTENT_SIMPLE = 1000;
    final int PHOTO_INTENT_WITH_FILENAME = 1001;
    final int VIDEO_INTENT_SIMPLE = 1002;

    Uri _photoFileUri;
    Uri _videoFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_photo:
                onMenuPhotoSimpleIntent(item);
                break;
            case R.id.action_video:
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onMenuPhotoSimpleIntent(MenuItem item) {
        logMenuChoice(item);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PHOTO_INTENT_SIMPLE);
    }

    public void onMenuPhotoIntentWithFileName(MenuItem item) {
        logMenuChoice(item);

    }


    public void onMenuVideoIntent(MenuItem item) {
        logMenuChoice(item);
    }

    public void onExit(MenuItem item) {
        logMenuChoice(item);

        finish();
    }

    private void logMenuChoice(MenuItem item) {
        CharSequence menuTitle = item.getTitle();
        Log.d(LOG_TAG, "Menu item selected:" + menuTitle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        Bundle extras = null;
        Bitmap imageBitmap = null;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Log.d(LOG_TAG, String.format("requestCode: %d | resultCode: %d", requestCode, resultCode));

        if (resultCode == RESULT_CANCELED){
            Toast.makeText(this, "User Cancelled", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode){
            case PHOTO_INTENT_SIMPLE:
                extras = resultIntent.getExtras();
                imageBitmap = (Bitmap) extras.get("data");
                break;
            default:
                break;
        }

        if (imageBitmap != null){
            imageView.setImageBitmap(imageBitmap);
        }
    }
}
