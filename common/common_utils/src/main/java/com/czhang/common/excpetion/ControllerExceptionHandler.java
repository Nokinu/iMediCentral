package com.czhang.common.excpetion;

import com.czhang.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result HandlerError(Exception e) {
      e.printStackTrace();
      return Result.fail();
    }
}
