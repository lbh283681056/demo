package com.base.code.thread;

public interface IWaitWakeTask {

    //运行的主要逻辑
    void run();
    //是否等待
    boolean isWait();
    //唤醒
    void wake(Object... objects);
}
