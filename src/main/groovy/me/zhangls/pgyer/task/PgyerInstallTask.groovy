package me.zhangls.pgyer.task

import me.zhangls.pgyer.FileUtils
import okhttp3.internal.Util
import okio.Okio
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created by zhangls on 2016/1/27.
 */
public class PgyerInstallTask extends DefaultTask {

    {
        group = 'pgyer'
        description = "安装APK"
    }

    @TaskAction
    void install() {
        def apkPath = FileUtils.getApkFile(project.getProjectDir()).getAbsolutePath()
        Process tr = Runtime.getRuntime().exec("adb install " + apkPath);
        def buffer = Okio.buffer(Okio.source(tr.getInputStream()))
        println(buffer.readUtf8())
        Util.closeQuietly(buffer)
    }

}
