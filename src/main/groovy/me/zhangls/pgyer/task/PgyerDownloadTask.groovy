package me.zhangls.pgyer.task

import me.zhangls.pgyer.FileUtils
import me.zhangls.pgyer.Pgyer
import me.zhangls.pgyer.okhttp.OkHttpUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created by zhangls on 2016/2/2.
 */
public class PgyerDownloadTask extends DefaultTask {

    {
        group = 'pgyer'
        description = "下载APK"
    }

    @TaskAction
    void download() {
        try {
            Pgyer pgyer = project.pgyer
            File apkFile = FileUtils.getApkFile(project.getProjectDir());
            def result = OkHttpUtils.getInstance().download(pgyer, apkFile);
            println(result)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
