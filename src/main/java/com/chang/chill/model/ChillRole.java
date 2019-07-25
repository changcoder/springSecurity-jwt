package com.chang.chill.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色实体类
 * Created by ChangHeXiang on 2019/7/24.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("T_ROLE")
public class ChillRole implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    //名称
    private String name;
    //描述
    private String description;
    //后台用户数量
    private Integer adminCount;
    //创建时间
    private Date createTime;
    //启用状态：0->禁用；1->启用
    private Integer status;
    //排序
    private Integer sort;
}
