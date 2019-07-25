package com.chang.chill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chang.chill.model.ChillPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 查询用户权限
 * Created by ChangHeXiang on 2019/7/24.
 */
public interface ChillUserRolePermissionMapper extends BaseMapper<ChillPermission> {
    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<ChillPermission> getPermissionList(@Param("userId") Long userId);
}
