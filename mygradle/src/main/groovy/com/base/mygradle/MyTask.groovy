package com.base.mygradle;

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction;

/**
 * Created by linbinghuang on 2018/5/28.
 */

public class MyTask extends DefaultTask{

    @TaskAction
    def ss(){
        println 'MyTask'
    }
}
