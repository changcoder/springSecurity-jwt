package com.chang.chill.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * Created by ChangHeXiang on 2019/7/24.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("T_USER")
public class ChillUser implements Serializable {

    private static final long serialVersionUID = 1L;

    //ID主键
    private Long id;
    //用户名
    private String username;
    //密码
    private String password;
    //创建时间
    private Date createTime;
    //最后登录时间
    private Date loginTime;
    //帐号启用状态：0->禁用；1->启用
    private Integer status;
}
