package me.zhangls.pgyer;

import java.io.File;

/**
 * Created by zhangls on 2016/2/5.
 */
public class FileUtils {
    public static File getApkFile(File rootDir) {
        File pgyerDir = new File(rootDir, "pgyer");
        if (!pgyerDir.exists()) {
            pgyerDir.mkdir();
        }
        return new File(pgyerDir, "temp.apk");
    }
}
