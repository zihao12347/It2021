package com.lenovo.it.task.service;

import com.lenovo.it.task.entity.Company;
import com.lenovo.it.task.entity.Emp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jack
 * @since 2021-10-29
 */
public interface EmpService extends IService<Emp> {
    int deteleByEmpId(Integer empId);

    int addEmp(Emp emp);
}
