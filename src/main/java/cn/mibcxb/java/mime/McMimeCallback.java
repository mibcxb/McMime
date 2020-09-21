package cn.mibcxb.java.mime;

public interface McMimeCallback {
    /**
     * @param flag true - mimetype has been detected, false - something wrong when detecting, see detail in info.
     * @param info the description of detection, it's success when flag is true.
     * @param mime the mimetype of target, it's NULL when flag is false.
     */
    void onDetected(boolean flag, String info, String mime);
}
