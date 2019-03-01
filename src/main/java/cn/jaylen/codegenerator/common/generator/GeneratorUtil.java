package cn.jaylen.codegenerator.common.generator;

import ch.qos.logback.classic.Logger;
import cn.jaylen.codegenerator.entity.AgileComponent;
import cn.jaylen.codegenerator.util.DatabaseUtil;
import cn.jaylen.codegenerator.util.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ljl
 * @create 2018-09-03 11:11
 * @desc 代码生成工具类
 **/
public class GeneratorUtil {

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    private VelocityEngine ve = new VelocityEngine();
    private static GeneratorUtil generatorUtil = null;
    private String packagePath;
    private String javaPath;
//    private String moduleName;
    private String webPath;

    private GeneratorUtil(String packagePath){
        logger.info("模板引擎初始化……");
        // 初始化模板引擎
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.setProperty("input.encoding","UTF-8");
        ve.setProperty("output.encoding","UTF-8");
        ve.init();
        this.packagePath = packagePath;
//        this.javaPath = this.getClass().getClassLoader().getResource("").getPath() +
//                "src/main/java/" + packagePath.replace('.', '/');
        this.javaPath = "d:/code/" + packagePath.replace('.', '/');
        this.webPath = "D:\\code\\";
    }

    private GeneratorUtil(String packagePath, String moduleName){
        logger.info("模板引擎初始化……");
        // 初始化模板引擎
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.setProperty("input.encoding","UTF-8");
        ve.setProperty("output.encoding","UTF-8");
        ve.init();
        this.packagePath = packagePath;
//        this.moduleName = moduleName;
        this.javaPath = "d:/code/" + packagePath.replace('.', '/');
//        this.javaPath = this.getClass().getClassLoader().getResource("").getPath() +
//                "src/temp/java/" + moduleName + "/" + packagePath.replace('.', '/');
//        this.webPath = this.getClass().getClassLoader().getResource("").getPath() +
//                "src/temp/web/" + moduleName;
        this.webPath = "D:\\code\\";
    }

    public static GeneratorUtil getGeneratorUtil(String packagePath){
//        if (generatorUtil == null) {
//            generatorUtil = new GeneratorUtil(packagePath);
//        }
        return new GeneratorUtil(packagePath);
    }

    /**
     * 生成mybatis-generator配置文件
     */
    public boolean generateConfig(List<String> tableNames, Map jdbc){
        logger.info("生成mybatis-generator配置文件……");
        // 加载模板
        Template entityTpt = ve.getTemplate("velocity/generatorConfig.vm");

        // 设置模板填充内容
        VelocityContext ctx = new VelocityContext();
        Map<String, String> map = new HashMap<>();
        ctx.put("jdbc", jdbc);
        ctx.put("packagePath", packagePath);
        ctx.put("tableNames", tableNames);

        // 获取文件存放地址
        String rootPath = GeneratorUtil.class.getClassLoader().getResource("generatorConfig.xml").getPath();
        return merge(entityTpt,ctx,  rootPath);
    }

    /**
     * 生成mybatis-generator配置文件
     */
    public boolean generateConfig(String[] tableNames, Map jdbc){
        logger.info("生成mybatis-generator配置文件……");
        // 加载模板
        Template entityTpt = ve.getTemplate("velocity/generatorConfig.vm");

        // 设置模板填充内容
        VelocityContext ctx = new VelocityContext();
        Map<String, String> map = new HashMap<>();
        ctx.put("jdbc", jdbc);
        ctx.put("packagePath", packagePath);
        ctx.put("tableNames", tableNames);

        // 获取文件存放地址
        String rootPath = GeneratorUtil.class.getClassLoader().getResource("generatorConfig.xml").getPath();
        return merge(entityTpt,ctx,  rootPath);
    }

    /**
     *  生成controller
     * @param className
     */
    public boolean generateController(String className){
        logger.info("生成"+className+"Controller……");
        // 加载模板
        Template entityTpt = ve.getTemplate("velocity/mvcTemp/controller.vm");
        // 设置模板填充内容
        VelocityContext ctx = new VelocityContext();
        ctx.put("packagePath", packagePath);
        ctx.put("className", className);
        String rootPath = this.javaPath + "/controller";
        File dir = new File(rootPath);
        dir.mkdirs();
        return merge(entityTpt,ctx,  rootPath + "/" + StringUtils.toUpperCaseFirstOne(className) + "Controller.java");
    }

    /**
     * 生成service
     * @param className
     * @return
     */
    public boolean generateService(String className) {
        logger.info("生成" +className+ "Service接口……");
        // 加载模板
        Template entityTpt = ve.getTemplate("velocity/mvcTemp/service.vm");
        // 设置模板填充内容
        VelocityContext ctx = new VelocityContext();
        ctx.put("packagePath", packagePath);
        ctx.put("className", className);
        String rootPath = this.javaPath + "/service";
        File dir = new File(rootPath);
        dir.mkdirs();
        return merge(entityTpt,ctx,  rootPath + "/" + StringUtils.toUpperCaseFirstOne(className) + "Service.java");
    }

    /**
     * 生成serviceImpl
     * @param className
     * @return
     */
    public boolean generateServiceImpl(String className) {
        logger.info("生成" + className + "Service实现类……");
        // 加载模板
        Template entityTpt = ve.getTemplate("velocity/mvcTemp/serviceImpl.vm");
        // 设置模板填充内容
        VelocityContext ctx = new VelocityContext();
        ctx.put("packagePath", packagePath);
        ctx.put("className", className);
        String rootPath = this.javaPath + "/service/impl";
        File dir = new File(rootPath);
        dir.mkdirs();
        return merge(entityTpt,ctx,  rootPath + "/" + StringUtils.toUpperCaseFirstOne(className) + "ServiceImpl.java");
    }

    /**
     * 生成entity实体类
     * @param columns : 列数据集合
     * @param className : 类名
     * @return
     */
    public boolean generateEntity(List<Map<String,Object>> columns, String className, String packagePath) {
        logger.info("生成" + className + "实体类……");
        // 加载模板
        Template entityTpt = ve.getTemplate("velocity/mvcTemp/entity.vm");
        // 设置模板填充内容
        VelocityContext ctx = new VelocityContext();
        ctx.put("columns", columns);
        ctx.put("packagePath",packagePath);
        ctx.put("className", className);
        String rootPath = this.javaPath + "/entity";
        File dir = new File(rootPath);
        dir.mkdirs();
        return merge(entityTpt,ctx,  rootPath + "/" + StringUtils.toUpperCaseFirstOne(className) + ".java");
    }

    public boolean generateIndex(String entityName, List<AgileComponent> componentList) {
        logger.info("生成前端index页面……");
        // 加载模板
        Template entityTpt = ve.getTemplate("velocity/index.vm");
        // 设置模板填充内容
        VelocityContext ctx = new VelocityContext();
        String moduleName = StringUtils.toLowerCaseFirstOne(entityName);
        ctx.put("moduleName", moduleName);
        ctx.put("entityName", entityName);
        ctx.put("componentList", componentList);
        String rootPath = this.webPath;
        File dir = new File(rootPath);
        dir.mkdirs();
        return merge(entityTpt,ctx,  rootPath + "/"+ moduleName +".vue");
    }

    /**
     * mybatis代码生成器
     * @return
     * @throws Exception
     */
    public void mybatis_generator() throws Exception {
        logger.info("生成mybatis-generator代码……");
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        //获取类路径下的配置文件
        URL url = GeneratorUtil.class.getClassLoader().getResource("generatorConfig.xml");
        File configFile = new File(url.getFile());
//        File configFile = new File(this.javaPath);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }

    // 将模板和数据合并，然后写入文件
    private boolean merge(Template template, VelocityContext ctx, String path) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path, "UTF-8");
            template.merge(ctx, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } finally {
            writer.close();
        }
        return true;
    }
}
