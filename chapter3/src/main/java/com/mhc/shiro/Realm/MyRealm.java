package com.mhc.shiro.Realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xiaoma
 * @create 2019-02-26 14:47
 * 自定义提交realm
 */
public class MyRealm extends AuthorizingRealm {


    //认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取传递过来的token 
        UsernamePasswordToken upToken= (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());

        //模拟用户名密码是否正确
        if (!"zhang".equals(username)){
            //用户名不匹配  抛异常
            throw new UnknownAccountException();
        }
        if (!"123456".equals(password)){
            //密码不正确抛异常
            throw new IncorrectCredentialsException();
        }
        //验证通过
        return new SimpleAuthenticationInfo(username,password,getName());
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //获取用户名
        String username= (String) getAvailablePrincipal(principals);

        //模拟从数据库中查询出来对应的角色和 权限
        Set<String> roles=new HashSet<String>(); //模拟角色
        roles.add("role1");
        roles.add("role2");

        Set<String > permissions=new HashSet<String>();//模拟权限
        permissions.add("user:create");
        permissions.add("user:delete");

        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setRoles(roles); //设定角色
        info.setStringPermissions(permissions); //设定权限
        return info;
    }
}
