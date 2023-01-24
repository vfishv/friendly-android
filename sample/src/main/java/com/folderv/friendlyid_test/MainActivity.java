package com.folderv.friendlyid_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.folderv.friendlyid.FriendlyId;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String randomFriendlyID = FriendlyId.createFriendlyId();
        UUID uid = FriendlyId.toUuid(randomFriendlyID);
        TextView tv= findViewById(R.id.tv);
        tv.setText(randomFriendlyID + "\n" + uid);

        String fid = FriendlyId.toFriendlyId(UUID.fromString("c3587ec5-0976-497f-8374-61e0c2ea3da5"));
        Log.d(TAG, "fid: " + fid);
        UUID uuid = FriendlyId.toUuid("5wbwf6yUxVBcr48AMbz9cb");
        Log.d(TAG, "uuid: " + uuid);

        new Thread(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                for (int i = 0; i < 100000; i++) {
                    String randomFriendlyID = FriendlyId.createFriendlyId();

                    UUID uuid = FriendlyId.toUuid(randomFriendlyID);
                    String fid = FriendlyId.toFriendlyId(uuid);
                    if(!TextUtils.equals(randomFriendlyID, fid)){
                        Log.e(TAG, "error: " + randomFriendlyID);
                    }
                    Log.d(TAG, "randomFriendlyID: " + randomFriendlyID + ":" + uuid);
                }
                Log.i(TAG, "time: " + (System.currentTimeMillis() - time));
            }
        }).start();

    }
}