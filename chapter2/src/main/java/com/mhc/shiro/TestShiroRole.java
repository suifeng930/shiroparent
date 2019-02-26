package com.mhc.shiro;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import sun.security.smartcardio.SunPCSC;

/**
 * @author xiaoma
 * @create 2019-02-26 11:26
 */
public class TestShiroRole {

    public static void main(String[] args) {

        //创建factory工厂
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-role.ini");
        //获取工厂实例  安全管理器
        SecurityManager securityManager = factory.getInstance();

        //将securityManager 托管给  SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);

        //在SecurityUtils.getSubject获取的同时将当这个subject绑定到了当前线程里面。
        Subject subject = SecurityUtils.getSubject();

        //创建UsernamePasswordToken  对象 ，并附初始值
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123456");

        //模拟用户登录（注意异常处理）
        try {
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        //打印验证信息
        System.out.println("用户认证——："+subject.isAuthenticated());

        //校验是否有对应的权限和资源，如果没有则抛出对应的异常处理UnauthorizedException
        subject.checkRole("role1"); //验证权限  role1 =》前面配置的用户权限
        subject.checkPermission("user:create"); //用户可操作的权限
        //用户退出
        subject.logout();
        System.out.println("用户退出："+subject.isAuthenticated());

    }
}
