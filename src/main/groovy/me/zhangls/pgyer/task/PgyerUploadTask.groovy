package me.zhangls.pgyer.task

import me.zhangls.pgyer.Pgyer
import me.zhangls.pgyer.okhttp.OkHttpUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public class PgyerUploadTask extends DefaultTask {

    {
        group = 'pgyer'
        description = "上传APK"
    }

    @TaskAction
    void upload() {
        try {
            Pgyer pgyer = project.pgyer
            def result = OkHttpUtils.getInstance().upload(pgyer);
            println "result: ${result.toString()}"
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

}