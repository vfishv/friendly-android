package com.folderv.friendlyid_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.folderv.friendlyid.FriendlyId;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String randomFriendlyID = FriendlyId.createFriendlyId();

        String fid = FriendlyId.toFriendlyId(UUID.fromString("c3587ec5-0976-497f-8374-61e0c2ea3da5"));
        Log.d(TAG, "fid: " + fid);
        UUID uuid = FriendlyId.toUuid("5wbwf6yUxVBcr48AMbz9cb");
        Log.d(TAG, "uuid: " + uuid);

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

    }
}