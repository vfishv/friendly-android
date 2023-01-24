package com.folderv.friendlyid;

import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

import java.util.UUID;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String randomFriendlyID = FriendlyId.createFriendlyId();

        String fid = FriendlyId.toFriendlyId(UUID.fromString("c3587ec5-0976-497f-8374-61e0c2ea3da5"));
        assertEquals("5wbwf6yUxVBcr48AMbz9cb", fid);
        UUID uuid = FriendlyId.toUuid("5wbwf6yUxVBcr48AMbz9cb");
        assertEquals("c3587ec5-0976-497f-8374-61e0c2ea3da5", uuid.toString());
    }

    @Test
    public void addition_isCorrect2() {
        String randomFriendlyID = FriendlyId.createFriendlyId();

        UUID uuid = FriendlyId.toUuid(randomFriendlyID);
        String fid = FriendlyId.toFriendlyId(uuid);
        assertEquals(randomFriendlyID, fid);
    }
}