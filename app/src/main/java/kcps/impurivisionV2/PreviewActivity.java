package kcps.impurivisionV2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class PreviewActivity extends AppCompatActivity {

    private TextView progressText;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        progressText = findViewById(R.id.progressText);
        imageView = findViewById(R.id.img);
        setProgressText("Cropping Images", 1000);
        Bitmap bitmapInit = BitmapFactory.decodeByteArray(CameraActivity.bmp1, 0, CameraActivity.bmp1.length);
        bitmapInit = Bitmap.createBitmap(bitmapInit, 420, 0, 1080, 1080);
        Bitmap bitmapPost = BitmapFactory.decodeByteArray(CameraActivity.bmp2, 0, CameraActivity.bmp2.length);
        bitmapPost = Bitmap.createBitmap(bitmapPost, 420, 0, 1080, 1080);
        setProgressText("Subtracting Images", 2000);
        new DifferenceTask(this).execute(bitmapInit, bitmapPost);
    }

    private void setProgressText(final String text, int delay) {
        Handler h = new Handler();
        h.postDelayed((new Runnable() {
            @Override
            public void run() {
                progressText.setText(text);
            }
        }), delay);
    }

    static class DifferenceTask extends AsyncTask<Bitmap, Void, int[]> {

        private WeakReference<PreviewActivity> activityReference;
        private int width, height;

        DifferenceTask(PreviewActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPostExecute(int[] colorsDiff) {
            PreviewActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;
            activity.setProgressText("Wrapping Up", 2000);
            //activity.imageView.setImageBitmap(Bitmap.createBitmap(colorsDiff, width, height, Bitmap.Config.ARGB_8888));
        }

        @Override
        protected int[] doInBackground(Bitmap... bitmaps) {
            width = bitmaps[0].getWidth();
            height = bitmaps[0].getHeight();
            int colorsInit[] = new int[width * height];
            bitmaps[0].getPixels(colorsInit, 0, width, 0, 0, width, height);
            int colorsPost[] = new int[width * height];
            bitmaps[1].getPixels(colorsPost, 0, width, 0, 0, width, height);
            int colorsDiff[] = new int[width * height];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int index = y * width + x;
                    int post = colorsPost[index], init = colorsInit[index];
                    int r = Color.red(post) - Color.red(init);
                    int g = Color.green(post) - Color.green(init);
                    int b = Color.blue(post) - Color.blue(init);
                    final int R = width/2;
                    boolean out = Math.pow((x - R), 2) + Math.pow((y - R), 2) > Math.pow(R, 2);
                    colorsDiff[index] = out ? 0 : Color.rgb(r, g, b);
                }
            }
            bitmaps[0].recycle();
            bitmaps[1].recycle();
            return colorsDiff;
        }
    }

}

