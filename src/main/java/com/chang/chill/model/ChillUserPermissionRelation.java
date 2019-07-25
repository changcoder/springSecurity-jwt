package com.chang.chill.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户权限实体类
 * Created by ChangHeXiang on 2019/7/24.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("T_USER_PERMISSION_RELATION")
public class ChillUserPermissionRelation implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Long adminId;

    private Long permissionId;

    private Integer type;
}
