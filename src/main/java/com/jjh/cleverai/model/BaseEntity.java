package com.jjh.cleverai.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Boolean deleted;


    private Integer version;

    private String createdBy;


    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;
}
