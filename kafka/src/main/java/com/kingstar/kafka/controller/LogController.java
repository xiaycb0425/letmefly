package com.kingstar.kafka.controller;

import com.kingstar.kafka.exception.MyException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiayc
 * @date 2020/8/27 13:22
 */
@RestController
@RequestMapping("/kafka/log")
@Slf4j
public class LogController {

    @GetMapping("query")
    @ApiOperation("测试列表")
    public String query() {
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new MyException("导入异常");
    }

    @GetMapping("init")
    @ApiOperation("初始化数据")
    public String init(@ModelAttribute("serviceId") String serviceId) {
        log.info(serviceId);
        return serviceId;
    }
}
