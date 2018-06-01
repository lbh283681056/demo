package com.base.mygradle

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by linbinghuang on 2018/5/28.
 */

public class CookerPlugin implements Plugin<Project>{
    @Override
     void apply(Project project) {
        println "fuckyou"
        project.task('fuck') <<{
            println 'xiba'
        }


    }
}
