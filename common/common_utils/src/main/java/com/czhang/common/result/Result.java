package com.czhang.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Generic API Result")
public class Result<T> {

    @ApiModelProperty(value = "Result Code")
    private Integer resultCode;

    @ApiModelProperty(value = "Result Message")
    private String resultMessage;

    @ApiModelProperty(value = "Result")
    private T result;

    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (data != null)
            result.setResult(data);
        return result;
    }

    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);
        result.setResultCode(resultCodeEnum.getCode());
        result.setResultMessage(resultCodeEnum.getMessage());
        return result;
    }

    public static <T> Result<T> build(Integer code, String message) {
        Result<T> result = build(null);
        result.setResultCode(code);
        result.setResultMessage(message);
        return result;
    }

    public static<T> Result<T> ok(){
        return Result.ok(null);
    }

    /**
     * 操作成功
     * @param data
     * @param <T>
     * @return
     */
    public static<T> Result<T> ok(T data){
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static<T> Result<T> fail(){
        return Result.fail(null);
    }

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static<T> Result<T> fail(T data){
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }

    public Result<T> message(String msg){
        this.setResultMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setResultCode(code);
        return this;
    }

    public boolean isOk() {
        if(this.getResultCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue()) {
            return true;
        }
        return false;
    }
}
