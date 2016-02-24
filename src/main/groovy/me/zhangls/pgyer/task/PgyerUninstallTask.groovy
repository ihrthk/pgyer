package me.zhangls.pgyer.task

import me.zhangls.pgyer.Pgyer
import okhttp3.internal.Util
import okio.Okio
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created by zhangls on 2016/2/2.
 */
public class PgyerUninstallTask extends DefaultTask {

    {
        group = 'pgyer'
        description = "卸载APK"
    }
    @TaskAction
    void uninstall() {
        Pgyer pgyer = project.pgyer
        Process tr = Runtime.getRuntime().exec("adb uninstall " + pgyer.packageName);
        def buffer = Okio.buffer(Okio.source(tr.inputStream));
        println(buffer.readUtf8())
        Util.closeQuietly(buffer)

    }
}
