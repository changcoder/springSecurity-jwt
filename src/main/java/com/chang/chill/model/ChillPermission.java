package com.chang.chill.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限实体类
 * Created by ChangHeXiang on 2019/7/24.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("T_PERMISSION")
public class ChillPermission implements Serializable {

    private static final long serialVersionUID = 1L;
    //id
    private Long id;
    //父级权限id
    private Long pid;
    //名称
    private String name;
    //权限值
    private String value;
    //图标
    private String icon;
    //权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
    private Integer type;
    //前端资源路径
    private String uri;
    //启用状态；0->禁用；1->启用
    private Integer status;
    //创建时间
    private Date createTime;
    //排序
    private Integer sort;


}
