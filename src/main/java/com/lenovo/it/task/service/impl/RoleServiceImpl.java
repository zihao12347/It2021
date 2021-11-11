package com.lenovo.it.task.service.impl;

import com.lenovo.it.task.entity.Role;
import com.lenovo.it.task.mapper.RoleMapper;
import com.lenovo.it.task.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jack
 * @since 2021-10-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
   /* @Autowired
    private RoleMapper roleMapper;*/

    @Override
    public int batchAddRoles(List<Role> roles) {

        saveBatch(roles);

//        for (Role role : roles) {
//            this.roleMapper.insert(role);
//        }
        return 1;
    }
}
