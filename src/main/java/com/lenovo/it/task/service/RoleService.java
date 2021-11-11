package com.lenovo.it.task.service;

import com.lenovo.it.task.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jack
 * @since 2021-10-29
 */
public interface RoleService extends IService<Role> {
    int batchAddRoles(List<Role> roles);
}
