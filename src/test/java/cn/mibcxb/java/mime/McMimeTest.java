package cn.mibcxb.java.mime;

import org.junit.Assert;
import org.junit.Test;

public class McMimeTest {
    @Test
    public void testInit() {
        McMime.initialize();
        Assert.assertTrue(McMime.isInitialized());
    }

    @Test
    public void testDetect() {
        McMime.initialize();
        String mime = McMime.getMimeByFilename("aaa.mp3");
        Assert.assertEquals("audio/mpeg", mime);
    }
}
