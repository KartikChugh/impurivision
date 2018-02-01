package kcps.impurivisionV2;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraActivity extends AppCompatActivity {

    private Camera mCamera;
    private FloatingActionButton captureButton;

    private boolean takenFirstPic = false;
    public static byte[] bmp1, bmp2;
    private CameraPreview mPreview;
    private FrameLayout preview;
    private ImageView outline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideUI();
        setContentView(R.layout.activity_camera);
        captureButton = findViewById(R.id.button_capture);
        captureButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        captureButton.hide();
                        mCamera.takePicture(null, null, mPicture);
                    }
                }
        );
        outline = findViewById(R.id.outline);
        preview = findViewById(R.id.camera_preview);
        ((FrameLayout)findViewById(R.id.parent)).removeView(captureButton);
        preview.removeView(outline);
    }

    private void hideUI() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            if (!takenFirstPic) {
                takenFirstPic = true;
                mCamera.startPreview();
                bmp1 = data;
                Toast t = Toast.makeText(CameraActivity.this, "   Great! Now take a picture with water.   ", Toast.LENGTH_LONG);
                t.setGravity(Gravity.TOP, 0, 30);
                View v =  t.getView();
                v.setBackgroundColor(Color.argb(128, 0, 90, 200));
                t.show();
                captureButton.show();
            } else {
                takenFirstPic = false;
                bmp2 = data;
                startActivity(new Intent(CameraActivity.this, PreviewActivity.class));
                captureButton.show();
            }
        }
    };

    private static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
            // Camera in use
        }
        return c;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
        preview.removeView(mPreview);
        ((FrameLayout)findViewById(R.id.parent)).removeView(captureButton);
        preview.removeView(outline);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCamera = getCameraInstance();
        Camera.Size cam = mCamera.getParameters().getPreviewSize();
        ViewGroup.LayoutParams params = preview.getLayoutParams();
        params.width = cam.height;
        params.height = cam.width;
        mPreview = new CameraPreview(this, mCamera);
        preview.addView(mPreview);
        ((FrameLayout)findViewById(R.id.parent)).addView(captureButton);
        preview.addView(outline);
        outline.bringToFront();
        captureButton.bringToFront();
    }

}
