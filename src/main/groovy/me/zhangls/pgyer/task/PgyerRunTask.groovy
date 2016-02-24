package me.zhangls.pgyer.task
import me.zhangls.pgyer.Pgyer
import okhttp3.internal.Util
import okio.Okio
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
/**
 * Created by zhangls on 2016/1/27.
 */
public class PgyerRunTask extends DefaultTask {

    {
        group = 'pgyer'
        description = "运行APK"
    }
    @TaskAction
    void run() {
        Pgyer pgyer = project.pgyer
        Process tr = Runtime.getRuntime().exec("adb shell am start -n " + pgyer.packageName + "/" + pgyer.lanuchActivity);
        def buffer = Okio.buffer(Okio.source(tr.inputStream));
        println(buffer.readUtf8())
        Util.closeQuietly(buffer)

    }
}
