package com.blundell.tut.cameraoverlay.ui;

import com.blundell.tut.cameraoverlay.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Start extends Activity
{
  @SuppressLint("NewApi")
private boolean cameraNotDetected()
  {
    return !getPackageManager().hasSystemFeature("android.hardware.camera");
  }

  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);
    if (cameraNotDetected());
    
    
    TextView cameraDescriptionTextView = (TextView) findViewById(R.id.text_view_camera_description);
    
    ((ImageButton)findViewById(R.id.button1start)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent cameraback = new Intent(Start.this.getApplicationContext(), CameraActivity.class);
        startActivity(cameraback);
      }
    });
  
    ((ImageButton)findViewById(R.id.buttongarden)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View newView)
      {
        Intent startGarden = new Intent(Start.this.getApplicationContext(), GardenStart.class);
        startActivity(startGarden);
      }


    });
  
  
  
  
  
  
  
  
  }
}