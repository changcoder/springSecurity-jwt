package com.chang.chill.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户角色关系表
 * Created by ChangHeXiang on 2019/7/24.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("T_USER_ROLE_RELATION")
public class ChillUserRoleRelation implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long adminId;
    private Long roleId;
}
