package com.lotus.admin;

import com.lotus.admin.beetl.ParamFunction;
import com.lotus.admin.interceptor.MenuInterceptor;
import com.lotus.admin.qiniu.QiniuParam;
import com.lotus.admin.resolver.ExceptionResolver;
import com.lotus.admin.shiro.ShiroAuthServeice;
import com.lotus.rpc.service.system.OptionRpcService;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement(proxyTargetClass = true)
@EnableCaching
@ComponentScan({"com.lotus.admin.web"})
@Import(value = {RpcApiWebServiceConfig.class})
@PropertySource("classpath:db.properties")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private OptionRpcService optionService;

    @Bean
    public QiniuParam qiniuParam() {
        return new QiniuParam();
    }

    @Bean
    public ShiroAuthServeice shiroAuthServeice() {
        return new ShiroAuthServeice();
    }

    @Bean(initMethod = "init", name = "beetlGroupUtilConfiguration")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ResourcePatternResolver patternResolver =
                ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        try {
            Resource resource = patternResolver.getResource("classpath:/");
            File file = resource.getFile();
            String path = file.getPath();//这个可以获取class路径
            System.out.println(path + "这个路径也可以配置 beetl，是官网的列子，但是目录不在webapp下，所以没有使用，万一现在的方案有问题的话，改用这个。");
            // WebAppResourceLoader 配置root路径是关键

            // 这个办法现在不行了 哭哭哭哭哭哭哭 不知道为什么 webApplicationContext 取到的一直是空的
            //WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            //String webPath = webApplicationContext.getServletContext().getRealPath("/");//获取web项目路径

            //下面的方法不知道能坚持多久
            WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader(System.getProperty("lotus_web.root"));

            beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //读取配置文件信息
        beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("classpath:beetl.properties"));
        return beetlGroupUtilConfiguration;
    }

    @Bean
    public BeetlSpringViewResolver beetlSpringViewResolver(@Qualifier("beetlGroupUtilConfiguration") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setPrefix("/");
        beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }

    @Bean
    public ParamFunction paramFunction(){
        return new ParamFunction();
    }

    @Bean(name = "groupTemplate")
    public GroupTemplate getGroupTemplate(@Qualifier("beetlGroupUtilConfiguration") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        GroupTemplate groupTemplate =   beetlGroupUtilConfiguration.getGroupTemplate();
        // 设置beetl全局变量
        Map<String, Object> shared = new HashMap<String, Object>();
        shared.put("appTitle", optionService.get("app_title"));
        groupTemplate.setSharedVars(shared);
        //注册beetl function
        groupTemplate.registerFunction("param", paramFunction());
        return groupTemplate;
    }

    /**
     * 异常处理器
     *
     * @return
     */
    @Bean
    public ExceptionResolver exceptionResolver() {
        ExceptionResolver resolver = new ExceptionResolver();
        resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        Properties mappings = new Properties();
        //key是异常类型,value是返回的视图名称
        mappings.setProperty(NoHandlerFoundException.class.getName(), "redirect:/404");
        resolver.setExceptionMappings(mappings);// None by default

        resolver.setDefaultErrorView("exception/500");// No default
        resolver.setExceptionAttribute("exception"); // Default is "exception"
        resolver.setWarnLogCategory(getClass().getName()); // No default

        return resolver;
    }

    @Bean
    public MenuInterceptor menuInterceptor() {
        return new MenuInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //请求相应统一处理拦截其
        registry.addInterceptor(menuInterceptor());
        super.addInterceptors(registry);
    }

    //配置静态资源处理,这个好像默认在根目录下的就可以访问
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //这个也是静态资源处理器,有上面的配置,下面的其实可以不配之
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        super.addResourceHandlers(registry);
    }
}