package me.zhangls.pgyer

import me.zhangls.pgyer.task.PgyerDownloadTask
import me.zhangls.pgyer.task.PgyerInstallTask
import me.zhangls.pgyer.task.PgyerRunTask
import me.zhangls.pgyer.task.PgyerUninstallTask
import me.zhangls.pgyer.task.PgyerUploadTask
import org.gradle.api.Plugin
import org.gradle.api.Project

public class PgyerPlugin implements Plugin<Project> {
    void apply(Project project) {

        project.task("pgyerUpload", type: PgyerUploadTask)
        project.task("pgyerDownload", type: PgyerDownloadTask)
        project.task("pgyerUninstall", type: PgyerUninstallTask)
        project.task("pgyerInstall", type: PgyerInstallTask)
        project.task("pgyerRun", type: PgyerRunTask)
        project.extensions.create("pgyer", Pgyer)
    }
}
