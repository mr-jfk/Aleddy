package com.blundell.tut.cameraoverlay.ui;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import com.blundell.tut.cameraoverlay.FromXML;
import com.blundell.tut.cameraoverlay.R;
import com.blundell.tut.cameraoverlay.ui.widget.CameraPreview;
import com.blundell.tut.cameraoverlay.util.CameraHelper;
import com.blundell.tut.cameraoverlay.util.MediaHelper;
import java.io.File;

public class CameraActivity extends Activity
  implements Camera.PictureCallback
{
  public static String EXTRA_IMAGE_PATH = "com.blundell.tut.cameraoverlay.ui.CameraActivity.EXTRA_IMAGE_PATH";
  private Camera camera;
  private CameraPreview cameraPreview;

  private void initCameraPreview()
  {
    this.cameraPreview = ((CameraPreview)findViewById(R.id.camera_preview));
    this.cameraPreview.init(this.camera);
  }

  private void releaseCamera()
  {
    if (this.camera != null)
    {
      this.camera.release();
      this.camera = null;
    }
  }

  private static String savePictureToFileSystem(byte[] paramArrayOfByte)
  {
    File localFile = MediaHelper.getOutputMediaFile();
    MediaHelper.saveToFile(paramArrayOfByte, localFile);
    return localFile.getAbsolutePath();
  }

  @FromXML
  public void onCaptureClick(View paramView)
  {
    this.camera.takePicture(null, null, this);
  }

  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_camera);
    ((ImageButton)findViewById(R.id.capturebutton)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View view)
      {
        onCaptureClick(view);
      }
    });
    this.camera = CameraHelper.getCameraInstance();
    if (CameraHelper.cameraAvailable(this.camera))
    {
      initCameraPreview();
      return;
    }
    finish();
  }

  protected void onPause()
  {
    super.onPause();
    releaseCamera();
  }

  public void onPictureTaken(byte[] paramArrayOfByte, Camera paramCamera)
  {
	  Intent retake = new Intent(getApplicationContext(), RetakeDone.class);
	  String str = savePictureToFileSystem(paramArrayOfByte);
	  retake.putExtra(EXTRA_IMAGE_PATH, str);
	  startActivity(retake);
	  finish();
  }
}