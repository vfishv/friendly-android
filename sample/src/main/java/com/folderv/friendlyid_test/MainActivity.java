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
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String randomFriendlyID = FriendlyId.createFriendlyId();
        UUID uid = FriendlyId.toUuid(randomFriendlyID);
        tv = findViewById(R.id.tv);
        tv.setText(randomFriendlyID + "\n" + uid);
        testFriendlyId(100);
        final int testCount = 10000;
        tv.setOnClickListener(v -> {
            String randomFriendlyID1 = FriendlyId.createFriendlyId();
            UUID uid1 = FriendlyId.toUuid(randomFriendlyID1);
            tv.setText(randomFriendlyID1 + "\n" + uid1);
            testFriendlyId(testCount);
        });

        String fid = FriendlyId.toFriendlyId(UUID.fromString("c3587ec5-0976-497f-8374-61e0c2ea3da5"));
        Log.d(TAG, "fid: " + fid);
        UUID uuid = FriendlyId.toUuid("5wbwf6yUxVBcr48AMbz9cb");
        Log.d(TAG, "uuid: " + uuid);
    }

    private void testFriendlyId(int testCount) {
        new Thread(() -> {
            long time = System.currentTimeMillis();
            boolean allRight = true;
            for (int i = 0; i < testCount; i++) {
                String randomFriendlyID = FriendlyId.createFriendlyId();
                UUID uuid = FriendlyId.toUuid(randomFriendlyID);
                String fid1 = FriendlyId.toFriendlyId(uuid);
                if (TextUtils.equals(randomFriendlyID, fid1)) {
                    Log.d(TAG, "randomFriendlyID: " + randomFriendlyID + ":" + uuid);
                } else {
                    Log.e(TAG, "error: " + randomFriendlyID);
                    allRight = false;
                    break;
                }
            }
            long ms = System.currentTimeMillis() - time;
            String str = "times:" + testCount + " average:" + (1.0 * ms / testCount) + "ms \nall success:" + allRight + (allRight ? "✅" : "❌");
            Log.i(TAG, str);
            String randomFriendlyID = FriendlyId.createFriendlyId();
            UUID uuid = FriendlyId.toUuid(randomFriendlyID);
            tv.post(() -> tv.setText(randomFriendlyID + "\n" + uuid + "\n\n" + str));
        }).start();
    }
}