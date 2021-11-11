package com.lenovo.it.task.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jack
 * @since 2021-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_emp_roles")
@ApiModel(value="EmpRoles对象", description="")
public class EmpRoles implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer empId;

    private Integer roleId;


}
