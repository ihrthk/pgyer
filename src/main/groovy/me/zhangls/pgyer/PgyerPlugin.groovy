package me.zhangls.pgyer

import org.gradle.api.Plugin
import org.gradle.api.Project

public class PgyerPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.task("pgyerUpload", type: PgyerUploadTask)
        project.task("pgyerInstall", type: PgyerInstallTask)
        project.task("pgyerRun", type: PgyerRunTask)
        project.extensions.create("pgyer", Pgyer)
    }
}
