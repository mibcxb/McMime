package cn.mibcxb.java.mime.impl;

import cn.mibcxb.java.mime.McMimeCallback;
import cn.mibcxb.java.mime.McMimeDetector;
import cn.mibcxb.java.mime.McMimeUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static cn.mibcxb.java.mime.McMimeLog.printE;

public class DefaultDetector implements McMimeDetector {
    private final Map<String, String[]> extMap = new HashMap<>();

    @Override
    public boolean prepare() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("file_extensions.properties");
        Properties properties = new Properties();
        try {
            properties.load(stream);
            Set<String> names = properties.stringPropertyNames();
            for (String name : names) {
                String value = properties.getProperty(name);
                if (McMimeUtils.isBlank(value)) {
                    continue;
                }
                String[] values = value.split(",");
                extMap.put(name, values);
            }
        } catch (IOException e) {
            printE(e);
        }

        return true;
    }

    @Override
    public void release() {

    }

    @Override
    public String detect(String filename) {
        if (McMimeUtils.isBlank(filename)) {
            return null;
        }

        int extIdx = filename.lastIndexOf('.');
        if (extIdx < 0 || extIdx + 1 >= filename.length()) {
            return null;
        }

        String ext = filename.substring(extIdx + 1);
        String[] values = extMap.get(ext);
        return values != null ? values[0] : null;
    }

    @Override
    public void detect(File file, McMimeCallback callback) {

    }
}
