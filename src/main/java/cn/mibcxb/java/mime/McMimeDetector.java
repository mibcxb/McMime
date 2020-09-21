package cn.mibcxb.java.mime;

import java.io.File;

public interface McMimeDetector {
    boolean prepare();

    void release();

    String detect(String filename);

    void detect(File file, McMimeCallback callback);
}
