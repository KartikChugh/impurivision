package kcps.impurivisionV2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;

public class TestActivity extends AppCompatActivity {

    private static final String ADDRESS = "palashdesktop.eastus.cloudapp.azure.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    static class FTPTask extends AsyncTask<Void, Void, String> {

        private WeakReference<TestActivity> activityReference;

        FTPTask(TestActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPostExecute(String string) {
            TestActivity activity = activityReference.get();
            if (activity == null) return;
            Toast.makeText(activity, string, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            long ms = -1;
            long initTime = System.currentTimeMillis();
            FTPClient ftp = new FTPClient();
            try {
                ftp.connect(ADDRESS);
                Log.d("FTP", "Connecting...");
                Log.d("FTP", ftp.getReplyString());
                int reply = ftp.getReplyCode();
                if(!FTPReply.isPositiveCompletion(reply)) {
                    Log.e("FTP","No connection");
                } else {
                    Log.d("FTP", "Connected");
                    ftp.enterLocalActiveMode();
                    ftp.setFileType(FTP.BINARY_FILE_TYPE);
                    boolean loggedIn = ftp.login("androidapp", "December12345!");
                    if (!loggedIn) {
                        Log.e("FTP", "Couldn't login");
                    } else {
                        Log.d("FTP", "Logged in");

                        String remote = "test.txt";
                        InputStream inputStream = ftp.retrieveFileStream(remote);
                        BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuilder stringBuilder = new StringBuilder();
                        String inputStr;
                        while ((inputStr = streamReader.readLine()) != null) {
                            stringBuilder.append(inputStr);
                        }
                        inputStream.close();
                        streamReader.close();
                        Log.d("FTP", "Read " + remote);
                        Log.d("FTP", stringBuilder.toString());

                        boolean loggedOut = ftp.logout();
                        if (!loggedOut) {
                            Log.e("FTP", "Couldn't logout");
                        } else {
                            Log.d("FTP", "Logged out");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (ftp.isConnected()) {
                    try {
                        ftp.disconnect();
                        Log.d("FTP", "Disconnected");
                        ms = System.currentTimeMillis() - initTime;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return ms+"";
        }
    }

    public void startClicked(View view) {
        new FTPTask(this).execute();
    }
}
