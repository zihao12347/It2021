package com.lenovo.it.task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lenovo.it.task.entity.Emp;
import com.lenovo.it.task.entity.EmpRoles;
import com.lenovo.it.task.entity.Role;
import com.lenovo.it.task.mapper.EmpMapper;
import com.lenovo.it.task.mapper.EmpRolesMapper;
import com.lenovo.it.task.mapper.RoleMapper;
import com.lenovo.it.task.service.EmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jack
 * @since 2021-10-29
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpRolesMapper empRolesMapper;
    /**
     * 根据id删除员工
     * @param empId
     * @return
     */
    @Override
    public int deteleByEmpId(Integer empId) {
        QueryWrapper<Emp> empQueryWrapper = new QueryWrapper<>();
        empQueryWrapper.eq("emp_id", empId);
        int i = this.empMapper.delete(empQueryWrapper);
        return i;
    }

    @Override
    @Transactional//开启事务支持
    public int addEmp(Emp emp) {
        emp.setCompanyId(emp.getCompany().getCompanyId());
        int i = this.empMapper.insert(emp);
        Integer companyId = emp.getCompany().getCompanyId();
        Integer[] roles = emp.getRoles();
        for (Integer r : roles) {
            EmpRoles empRoles = new EmpRoles();
            empRoles.setRoleId(r);
            empRoles.setEmpId(emp.getEmpId());
            this.empRolesMapper.insert(empRoles);
        }
        return i;
    }
}
