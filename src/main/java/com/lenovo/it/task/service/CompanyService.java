package com.lenovo.it.task.service;

import com.lenovo.it.task.entity.Company;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jack
 * @since 2021-10-29
 */
public interface CompanyService extends IService<Company> {

    List<Company> findAll(Map map);

    Company findByCid(Integer companyId);

    int addCompany(Company company);
}
