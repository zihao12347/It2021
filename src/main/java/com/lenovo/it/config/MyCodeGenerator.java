package com.lenovo.it.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * mybatis的代码生成器
 */
public class MyCodeGenerator {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //获取当前项目的路径
        String projectPath = System.getProperty("user.dir");
        //设置生成的代码文件的路径
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Jack");//作者
        gc.setOpen(false);//代码生成后是否自动打开所在的文件
        gc.setSwagger2(true);//生成swagger2注解
        gc.setBaseResultMap(true);//在mapper.xml配置文件中，生成映射的所有字段
        gc.setFileOverride(true);// 是否覆盖之前生成的文件
        //设置实体类的名称:%s=表名
        gc.setEntityName("%s");
        gc.setMapperName("%sMapper");//设置mapper接口
        gc.setXmlName("%sMapper");//设置mapper配置的名称
        gc.setServiceName("%sService");//设置业务逻辑类接口
        gc.setServiceImplName("%sServiceImpl");//设置业务逻辑接口实现类
        mpg.setGlobalConfig(gc);//将全局配置设置到代码生成器对象中

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/db03?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);


        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("task");//设置模块名称
        pc.setParent("com.lenovo.it");//设置包名称
        mpg.setPackageInfo(pc);



        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);



        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //表名生成策略：下划线转驼峰 tb_user  ==>  tbUser
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //列明生成策略：下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);//实体类是否支持lombok
        strategy.setRestControllerStyle(true);//控制器是否使用@RestController
        // 公共父类
        //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        //设置要生成的表名,多个逗号隔开
        //strategy.setInclude("t_user");
        //按照前缀生成表
        strategy.setLikeTable(new LikeTable("t_"));
        //设置控制器的映射规则：驼峰转连接符tUser  --> @RequestMapping("t_User")
        //strategy.setControllerMappingHyphenStyle(true);
        //设置表的前缀替换,将表的前缀去除
        strategy.setTablePrefix("t_");
        mpg.setStrategy(strategy);
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());使用默认的volocity模板引擎
        mpg.execute();
    }
}
