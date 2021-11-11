package com.lenovo.it.task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lenovo.it.task.entity.Company;
import com.lenovo.it.task.entity.Emp;
import com.lenovo.it.task.mapper.CompanyMapper;
import com.lenovo.it.task.mapper.EmpMapper;
import com.lenovo.it.task.service.CompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jack
 * @since 2021-10-29
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Company> findAll(Map map) {
        QueryWrapper<Company> wrapper = new QueryWrapper<>();

        String companyName = (String) map.get("companyName");
        if (StringUtils.isNotEmpty(companyName)){//公司名称模糊查询
            wrapper.like("company_name", companyName);
        }
        String beginYear = (String) map.get("beginYear");
        String endYear =(String) map.get("endYear");
        if (StringUtils.isNotEmpty(beginYear) && StringUtils.isNotEmpty(endYear)){//时间组合查询
            wrapper.gt("create_date", beginYear);
            wrapper.lt("create_date", endYear);
        }
        if (StringUtils.isNotEmpty(endYear)){
            wrapper.lt("create_date", endYear);
        }


        List<Company> list = this.companyMapper.selectList(wrapper);
        return list;
    }

    @Override
    public Company findByCid(Integer companyId) {
        //1.根据id查询公司信息
        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        wrapper.eq("company_id", companyId);
        Company company = this.companyMapper.selectOne(wrapper);
        //根据cid查询员工信息
        QueryWrapper<Emp> empQueryWrapper = new QueryWrapper<>();
        empQueryWrapper.eq("company_id", companyId);
        List<Emp> emps = this.empMapper.selectList(empQueryWrapper);
        company.setEmps(emps);
        return company;
    }

    public int addCompany(Company company){
        int i = this.companyMapper.insert(company);
        return i;
    }
}
