package com.mhc.shiro;

import com.mhc.shiro.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author xiaoma
 * @create 2019-02-26 15:06
 * 自定义realm 测试
 */
public class MyRealmTest {

    public static void main(String[] args) {

        //用户登录
        ShiroUtils.login("classpath:shiro-myrealm.ini","zhang","123456");

        //获取  subject对象
        Subject subject = SecurityUtils.getSubject();

        //验证是否通过认证
        System.out.println("用户认证--："+subject.isAuthenticated());

        //验证角色
        System.out.println("用户角色认证："+subject.hasRole("role1"));
        //验证权限
        System.out.println("用户权限认证："+subject.isPermitted("user:create"));



    }
}
