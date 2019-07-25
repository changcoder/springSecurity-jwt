package com.chang.chill.service;

import com.chang.chill.model.ChillPermission;
import com.chang.chill.model.ChillUser;

import java.util.List;

public interface ChillUserService {

    ChillUser getUserByUsername(String username);

    List<ChillPermission> getPermissionList(Long id);

    ChillUser register(ChillUser chillUserParam);

    String login(String username, String password);
}
