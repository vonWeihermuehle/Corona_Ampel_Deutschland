package net.mbmedia.sync;

import org.junit.Test;

import java.io.IOException;

public class SyncJobTest {

    @Test
    public void sync() throws IOException {
        SyncJob job = new SyncJob();
        job.run();
    }


}