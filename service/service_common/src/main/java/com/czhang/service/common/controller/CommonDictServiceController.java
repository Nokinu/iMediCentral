package com.czhang.service.common.controller;

import com.czhang.common.result.Result;
import com.czhang.model.model.MediCenterDict;
import com.czhang.service.common.service.impl.CommonDictServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Data Dict API")
@RestController
@RequestMapping("/admin/center/mediCenterDict")
public class CommonDictServiceController {

    private final CommonDictServiceImpl commonDictService;


    public CommonDictServiceController(CommonDictServiceImpl commonDictService) {
        this.commonDictService = commonDictService;
    }

    @ApiOperation(value = "Find Child Data Name by dict code and value")
    @GetMapping("/api/v1/getDataName/{dictCode}/{value}")
    public Result getDataName(@PathVariable String dictCode, String value) {
        return Result.ok(commonDictService.getDictName(dictCode, value));
    }

    @ApiOperation(value = "Find Child Data List by ID")
    @GetMapping("/api/v1/findChildData/{id}")
    public Result findChildData(@PathVariable  Long id) {
        List<MediCenterDict> result = commonDictService.findChildData(id);
        return result.isEmpty() ? Result.fail() : Result.ok(result);
    }

    @ApiOperation(value = "Find the child data by dict code")
    @GetMapping("/api/v1/findByDictCode/{dictCode}")
    public Result findByDictCode(@PathVariable String dictCode) {
        List<MediCenterDict> result = commonDictService.findByDictCode(dictCode);
        return result.isEmpty() ? Result.fail() : Result.ok(result);
    }
}
