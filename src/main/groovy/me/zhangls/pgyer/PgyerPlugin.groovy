package me.zhangls.pgyer

import org.gradle.api.Plugin
import org.gradle.api.Project

public class PgyerPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.task("pgyer", type: PgyerTask)
        project.extensions.create("pgyer", Pgyer)
    }
}
