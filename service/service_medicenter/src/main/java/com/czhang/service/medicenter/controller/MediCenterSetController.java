package com.czhang.service.medicenter.controller;

import com.czhang.common.result.Result;
import com.czhang.model.model.MediCenterSet;
import com.czhang.model.vo.medicenter.MediCenterSetQueryVo;
import com.czhang.service.medicenter.service.impl.MediCenterSetServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Api(tags = "Medical Center Admin Setting Management")
@RestController
@RequestMapping("/admin/center/mediCenterSet")
public class MediCenterSetController {

    private final MediCenterSetServiceImpl mediCenterSetService;

    public MediCenterSetController(MediCenterSetServiceImpl mediCenterSetService) {
        this.mediCenterSetService = mediCenterSetService;
    }

    @ApiOperation(value = "Find All Center Settings")
    @GetMapping("/api/v1/findAll")
    public Result findAllCenterSets() {
        return mediCenterSetService.findAllCenterSets().isEmpty() ? Result.fail() : Result.ok();
    }

    @ApiOperation(value = "Delete Center Setting")
    @DeleteMapping("/api/v1/deleteAll/{id}")
    public Result deleteCenterSet(@PathVariable Long id) {
        return mediCenterSetService.deleteCenterSet(id) ? Result.ok() : Result.fail();
    }

    @ApiOperation(value = "Delete All Center Setting")
    @DeleteMapping("/api/v1/deleteAll")
    public Result batchDeleteCentetSet(@RequestBody List<Long> ids) {
      return mediCenterSetService.batchDeleteCenterSet(ids) ? Result.ok() : Result.fail();
    }

    @ApiOperation(value = "Update Center Status")
    @PutMapping("/api/v1/update/{id}/status/{status}")
    public Result updateCenterSetStatus(@PathVariable Long id,
                                        @PathVariable Integer status) {
        return mediCenterSetService.updateStatusById(status, id) ? Result.ok() : Result.fail();
    }

    @ApiOperation(value = "Find Center Setting By Page and Condition")
    @PostMapping("/api/v1/findPage/{current}/{limit}")
    public Result findCenterSetsByPage(@PathVariable int current,
                                       @PathVariable int limit,
                                       @RequestBody(required = false) MediCenterSetQueryVo mediCenterSetQueryVo) {
          Pageable pageable = PageRequest.of(current-1, limit);
          Page<MediCenterSet> data = mediCenterSetService.findCenterSetsByPage(pageable, mediCenterSetQueryVo);
          return data.isEmpty() ? Result.fail() : Result.ok(data);
    }

    @ApiOperation(value = "To add new center set")
    @PostMapping("/api/v1/addCenterSet")
    public Result addCenterSets(@RequestBody MediCenterSet mediCenterSet) {
        mediCenterSet.setStatus(1);
        mediCenterSet.setSignKey(String.valueOf(new Random().nextInt(100000)));
        MediCenterSet data = mediCenterSetService.addCenterSets(mediCenterSet);
        if(data.getCenterCode().equals(mediCenterSet.getCenterCode())) {
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation(value = "To find Center Set by Id")
    @GetMapping("/api/v1/findAll/{id}")
    public Result findCenterSetById(@PathVariable Long id) {
      MediCenterSet mediCenterSet = mediCenterSetService.findCenterSetsById(id);
      if(mediCenterSet == null) {
          return Result.fail();
      }
      return Result.ok(mediCenterSet);
    }

    @ApiOperation(value = "Update Center Set")
    @PostMapping("/api/v1/update")
    public Result updateCenterSet(@RequestBody MediCenterSet mediCenterSet) {
      MediCenterSet result = mediCenterSetService.updateCenterSet(mediCenterSet);
      return Result.ok(result);
    }

    @ApiOperation(value = "Send Sign Key by ID")
    @GetMapping("/api/v1/sign_key/{id}")
    public Result sendSignKeyById(@PathVariable Long id) {
        MediCenterSet mediCenterSet = mediCenterSetService.findCenterSetsById(id);
        if(mediCenterSet == null) {
            return Result.fail();
        }
        return Result.ok(mediCenterSet.getCenterCode() + ":" + mediCenterSet.getSignKey());
    }

}
