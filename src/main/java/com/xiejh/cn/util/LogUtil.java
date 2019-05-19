package com.xiejh.cn.util;

/**
 * Author xiejh
 * Date   2019/5/19 12:27
 **/
public class LogUtil {

    /**
     * 获取当前执行代码所在行的行号
     * @return
     */
    public static int getLineNumber() {
        int level = 1;
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        int lineNumber = stacks[level].getLineNumber();
        return lineNumber;
    }
}
