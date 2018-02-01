package kcps.impurivisionV2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SafeResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_results);
        Button button = findViewById(R.id.reportButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Submitted! Thank you.", Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed((new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }), 2000);
            }
        });
    }
}
