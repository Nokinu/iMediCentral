package com.czhang.model.model;

import com.czhang.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Setter
@Getter
@ApiModel(description = "Medical Center Dict")
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "medicenter_dict", indexes = {
        @Index(name = "idx_dict_cdoe", columnList = "dict_code"),
        @Index(name = "idx_parent_id", columnList = "parent_id")
})
public class MediCenterDict extends BaseEntity {

    @ApiModelProperty(value = "id")
    @Id
    private Long id;

    @ApiModelProperty(value = "parent Id")
    @Column(name = "parent_id")
    private Long parentId;

    @ApiModelProperty(value = "dict name")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "dict value")
    @Column(name = "value")
    private String value;

    @ApiModelProperty(value = "dict code")
    @Column(name = "dict_code")
    private String dictCode;

    @ApiModelProperty(value = "if has child data")
    @Transient
    private boolean hasChildren;

}
