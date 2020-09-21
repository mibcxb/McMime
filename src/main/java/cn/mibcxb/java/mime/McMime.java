package cn.mibcxb.java.mime;

import cn.mibcxb.java.mime.impl.DefaultDetector;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static cn.mibcxb.java.mime.McMimeLog.logger;

public final class McMime {
    public static final String VERSION = "0.0.1";
    public static final McMime MC_MIME = new McMime();

    public static void initialize() {
        initialize(new DefaultDetector());
    }

    public static void initialize(McMimeDetector detector) {
        MC_MIME.onInit(detector);
    }

    public static boolean isInitialized() {
        return MC_MIME.detector != null;
    }

    public static String getMimeByFilename(String filename) {
        return MC_MIME.detector.detect(filename);
    }

    public static void getMimeByMagicBytes(File file, McMimeCallback callback) {
        MC_MIME.detector.detect(file, callback);
    }

    private McMimeDetector detector;
    private ExecutorService executor;
    private List<Runnable> taskList;

    private McMime() {
        executor = Executors.newCachedThreadPool();
        taskList = new LinkedList<>();
    }

    private McMimeDetector getDetector() {
        return detector;
    }

    private void setDetector(McMimeDetector detector) {
        this.detector = detector;
    }

    private void onInit(McMimeDetector detector) {
        if (detector == null) {
            throw new NullPointerException("The detector cannot be NULL.");
        }

        if (detector.prepare()) {
            logger().info("Prepare new detector done.");
            McMimeDetector previous = getDetector();
            if (previous != null) {
                logger().info("Release old detector start.");
                previous.release();
                logger().info("Release old detector done.");
            }
            setDetector(detector);
            logger().info("New detector is set.");
        } else {
            logger().info("Prepare new detector failed.");
        }
    }

    private void enqueue(File file, McMimeCallback callback){

    }
}