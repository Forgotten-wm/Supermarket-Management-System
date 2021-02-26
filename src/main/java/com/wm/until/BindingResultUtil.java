package com.wm.until;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wm
 */
public class BindingResultUtil {

    @ExceptionHandler(Exception.class)
    public static void print(BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println("爆发了" + bindingResult.getErrorCount() + "个异常！");
            for (ObjectError e : bindingResult.getAllErrors()) {
                System.out.println("爆发异常的对象是：" + e.getObjectName());
                System.out.println("具体异常的内容为：" + e.getDefaultMessage());
            }

        }
    }
}