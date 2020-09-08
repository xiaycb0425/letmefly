package com.kingstar.kafka.hutool.io;


import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.LineHandler;
import cn.hutool.core.swing.clipboard.ClipboardUtil;
import org.junit.Test;

import java.awt.datatransfer.Clipboard;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

/**
 * @author xiayc
 * @date 2020/9/4 10:47
 */
public class IoTest {
    @Test
    public void test1() throws FileNotFoundException {
        IoUtil.readLines(this.getClass().getClassLoader().getResourceAsStream("DrtpDefConfig中文.txt"), StandardCharsets.UTF_8, new LineHandler() {
            @Override
            public void handle(String s) {
                System.out.println(s);
            }
        });

        Clipboard clipboard = ClipboardUtil.getClipboard();
        System.out.println(clipboard.getName());

    }

}
