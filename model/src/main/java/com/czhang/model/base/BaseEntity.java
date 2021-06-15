package com.czhang.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

    @ApiModelProperty(value = "Create Time")
    @Column(name = "create_time", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",insertable = false,updatable = false)
    @Generated(GenerationTime.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "Update Time")
    @Column(name = "update_time", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",insertable = false,updatable = false)
    @Generated(GenerationTime.INSERT)
    private Date updateTime;

    @ApiModelProperty(value = "Logic Delete(0-Deleted,1-Exist)")
    @Column(name = "is_deleted")
    @Generated(GenerationTime.INSERT)
    private Integer isDeleted;

    @ApiModelProperty(value = "Other Properties")
    @Transient
    private Map<String,Object> param = new HashMap<>();

}
