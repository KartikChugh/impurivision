package kcps.impurivisionV2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button camButton, learnButton, infoButton;
    private final static int PERMS_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camButton = findViewById(R.id.camButton);
        learnButton = findViewById(R.id.learnButton);
        infoButton = findViewById(R.id.infoButton);
    }

    // Used in styles.xml/MainButtons
    public void handleClick(View view) {
        Class activity = null;
        if (view.equals(camButton) && hasPermissions()) {
            activity = CameraActivity.class;
        } else if (view.equals(learnButton)) {
            activity = ImageGridActivity.class;
        } else if (view.equals(infoButton)) {
            Snackbar.make(view.getRootView(), "Work in progress.", Snackbar.LENGTH_LONG).show();
        }
        if (activity == null) return;
        startActivity(new Intent(this, activity));
    }

    private boolean hasPermissions() {
        String camPerm = Manifest.permission.CAMERA, storagePerm = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int granted = PackageManager.PERMISSION_GRANTED;
        List<String> permsNeededList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, camPerm) != granted) {
            permsNeededList.add(camPerm);
        }
        if (ContextCompat.checkSelfPermission(this, storagePerm) != granted) {
            permsNeededList.add(storagePerm);
        }
        if (permsNeededList.size() == 0) return true;
        String[] permsNeeded = permsNeededList.toArray(new String[permsNeededList.size()]);
        ActivityCompat.requestPermissions(MainActivity.this, permsNeeded, PERMS_CODE);
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMS_CODE:
                if (grantResults.length > 0) {
                    for (int i : grantResults) if (i != PackageManager.PERMISSION_GRANTED) return;
                    camButton.performClick();
                }
        }
    }

}
