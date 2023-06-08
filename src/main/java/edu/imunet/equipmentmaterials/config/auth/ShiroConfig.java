package edu.imunet.equipmentmaterials.config.auth;

import jakarta.servlet.Filter;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securitymanager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //添加shiro的内置过滤器
        LinkedHashMap<String, Filter> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("jwt", new JWTFilter());
        linkedHashMap.put("resource", new ResourceFilter());
        bean.setFilters(linkedHashMap);
        bean.setSecurityManager(defaultWebSecurityManager);
        /**
         *  anon:无需认证就可访问  *  authc:必须认证才能访问*  User:必须拥有 记住我 功能才能用
         *  perms：拥有对某个资源的权限才能访问*  role:拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        //授权，正常的情况下，没有授权会跳转到未授权页面
        filterMap.put("/resource/*","jwt,resource");//admin请求下的都需要认证
        filterMap.put("/admin/add", "perms[user:add]");
        filterMap.put("/admin/update", "perms[user:update]");
        filterMap.put("/**","anon");

        bean.setFilterChainDefinitionMap(filterMap);
        //如果没有认证、设置登录的请求
        bean.setLoginUrl("/toLogin");
        //如果没有授权，跳转到未收取页面
        bean.setUnauthorizedUrl("/ungrant");
        return bean;

    }

    //DefaultWebSecurityManager
    @Bean(name = "securitymanager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securitymanager = new DefaultWebSecurityManager();
        //关联UserRealm
        securitymanager.setRealm(userRealm);
        return securitymanager;
    }

    //创建realm对象，需要自定义安装
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setAuthenticationTokenClass(JwtToken.class);
        userRealm.setCachingEnabled(true);
        userRealm.setAuthenticationCachingEnabled(true);
        userRealm.setAuthorizationCachingEnabled(true);
        userRealm.setAuthenticationCacheName("authenticationCache");
        userRealm.setAuthorizationCacheName("authorizationCache");
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        userRealm.setCacheManager(new EhCacheManager());
        return userRealm;
    }
}
