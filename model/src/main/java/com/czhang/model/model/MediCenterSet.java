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
@ApiModel(description = "Medical Center Setting")
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "medicenter_set")
public class MediCenterSet extends BaseEntity {

    @ApiModelProperty(value = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Center Name")
    @Column(name = "center_name")
    private String centerName;

    @ApiModelProperty(value = "Center Code")
    @Column(name = "center_code", unique = true)
    private String centerCode;

    @ApiModelProperty(value = "API URL")
    @Column(name = "api_url")
    private String apiURL;

    @ApiModelProperty(value = "Sign Key")
    @Column(name = "sign_key")
    private String signKey;

    @ApiModelProperty(value = "Contact Name")
    @Column(name = "contacts_name")
    private String contactName;

    @ApiModelProperty(value = "Contact Phone Number")
    @Column(name = "contacts_phone")
    private String contactPhoneNum;

    @ApiModelProperty(value = "Status")
    @Column(name = "status")
    private Integer status;
}
