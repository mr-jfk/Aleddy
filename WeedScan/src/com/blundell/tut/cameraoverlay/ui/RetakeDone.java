package com.blundell.tut.cameraoverlay.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.blundell.tut.cameraoverlay.R;
import com.blundell.tut.cameraoverlay.util.BitmapHelper;

public class RetakeDone extends Activity
{
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.retakedone);
    Button localButton = (Button)findViewById(R.id.retakedoneButton);
    ImageView imageView = (ImageView) findViewById(R.id.image_view_captured_image);
	imageView.setImageBitmap(BitmapHelper.decodeSampledBitmap(getIntent().getStringExtra(CameraActivity.EXTRA_IMAGE_PATH),300,250));

    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View view)
      {
        Intent retake = new Intent(getApplicationContext(), CameraActivity.class);
        startActivity(retake);
      }
    });
  }
}