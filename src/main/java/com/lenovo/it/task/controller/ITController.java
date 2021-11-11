package com.lenovo.it.task.controller;


import com.lenovo.it.task.constant.MessageConstant;
import com.lenovo.it.task.entity.Company;
import com.lenovo.it.task.entity.Emp;
import com.lenovo.it.task.entity.Role;
import com.lenovo.it.task.entity.bo.ResponseResult;
import com.lenovo.it.task.service.CompanyService;
import com.lenovo.it.task.service.EmpService;
import com.lenovo.it.task.service.RoleService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.message.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jack
 * @since 2021-10-29
 */
@RestController
@RequestMapping("/it2021/yh")
public class ITController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmpService empService;

    @Autowired
    private RoleService roleService;

    /**
     * 9.查询公司列表:
     *      并且支持下列条件的组合查询：
     * @return
     */
    @GetMapping("/listCompanies")
    public ResponseResult findAll(@RequestParam(name = "companyName",required = false) String companyName,
                                  @RequestParam(name = "beginYear",required = false) String beginYear,
                                  @RequestParam(name = "endYear",required = false) String endYear
    ){
        HashMap<String, String> map = new HashMap<>();
        map.put("companyName", companyName);
        map.put("beginYear", beginYear);
        map.put( "endYear", endYear);

        List<Company> list=this.companyService.findAll(map);
        if (CollectionUtils.isEmpty(list)){
            return new ResponseResult(MessageConstant.ERROR,MessageConstant.FINDALLCOMPANY_ERROR_INFO,null);
        }
        return new ResponseResult(MessageConstant.SUCCESS,MessageConstant.FINDALLCOMPANY_SUCCESS_INFO,list);
    }


    /**
     * 10.根据 companyId 查询公司信息，包含员工列表
     * @param companyId
     * @return
     */
    @GetMapping("/{companyId}")
    public ResponseResult findByCid(@PathVariable(name = "companyId") Integer companyId){
        Company company=this.companyService.findByCid(companyId);
        ArrayList<Object> list = new ArrayList<>();
        if (company == null) {
            return new ResponseResult(MessageConstant.ERROR,MessageConstant.FINDCOMPANY_ERROR_INFO,null);
        }
        return new ResponseResult(MessageConstant.SUCCESS,MessageConstant.FINDCOMPANY_SUCCESS_INFO,company);
    }


    /**
     * 11.添加单个公司信息，
     * @param company
     * @return
     */
    @PostMapping("/addCompany")
    public ResponseResult addCompany(Company company){
        int i=this.companyService.addCompany(company);
        if (1<=0){
            return new ResponseResult(MessageConstant.ERROR, MessageConstant.ADDCOMPANY_ERROR_INFO,null);
        }
        return new ResponseResult(MessageConstant.SUCCESS,MessageConstant.ADDCOMPANY_SUCCESS_INFO,null);
    }

    /**
     * 12批量添加角色
     * @return
     */
    @PostMapping("/betchAddRole")
    public ResponseResult batchAddRoles(@RequestBody List<Role> roles){
        int i=this.roleService.batchAddRoles(roles);
        if (1<=0){
            return new ResponseResult(MessageConstant.ERROR, MessageConstant.ADDROLES_ERROR_INFO,null);
        }
        return new ResponseResult(MessageConstant.SUCCESS,MessageConstant.ADDROLES_SUCCESS_INFO,null);


    }


    /**
     * 13.删除员工
     * @param empId
     * @return
     */
    @DeleteMapping("/{empId}")
    public ResponseResult deleteEmp(@PathVariable(name = "empId") Integer empId){
        int i=this.empService.deteleByEmpId(empId);
        if (1<=0){
            return new ResponseResult(MessageConstant.ERROR,MessageConstant.DELETEEMP_ERROR_INFO,null);
        }
        return new ResponseResult(MessageConstant.SUCCESS,MessageConstant.DELETEEMP_SUCCESS_INFO,null);
    }

    /**
     * 1414.	添加员工， 必须同时添加至少一个角色
     * @param emp
     * @return
     */
    @PostMapping()
    public ResponseResult addEmp(@RequestBody Emp emp){
        int i=this.empService.addEmp(emp);
        if (1<=0){
            return new ResponseResult(MessageConstant.ERROR,MessageConstant.ADDEMP_ERROR_INFO,null);
        }
        return new ResponseResult(MessageConstant.SUCCESS,MessageConstant.ADDEMP_SUCCESS_INFO,null);

    }

}

