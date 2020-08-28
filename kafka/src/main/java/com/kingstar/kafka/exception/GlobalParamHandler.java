package com.kingstar.kafka.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

/**
 * @author xiayc
 * @date 2020/8/28 10:55
 */
@RestControllerAdvice
@Slf4j
public class GlobalParamHandler {
    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {

        model.addAttribute("serviceId", UUID.randomUUID());
    }
}
