package edu.imunet.equipmentmaterials.config.auth;

import edu.imunet.equipmentmaterials.util.TokenUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

//    @Autowired
//    UserService userService;
    public UserRealm() {
        super();
        System.out.println("UserRealm");
    }



    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("执行了授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");

        //拿到当前的登录对象
        Subject subject = SecurityUtils.getSubject();
//        User user = (User) subject.getPrincipal();

        //设置当前用户的权限
//        info.addStringPermission(user.getPerm());

        return info;

    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证"+token.getPrincipal());
        int userId = Integer.parseInt(TokenUtils.verify((String) token.getPrincipal()));
        return new SimpleAuthenticationInfo("user", token.getCredentials(), "");
    }
}