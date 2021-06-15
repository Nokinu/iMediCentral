package com.czhang.model.vo.medicenter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MediCenterSetQueryVo {

    @ApiModelProperty(value = "Medical Center Name")
    private String mediCenterName;

    @ApiModelProperty(value = "Medical Center Code")
    private String mediCenterCode;
}
