package com.mhc.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import java.security.Security;

/**
 * @author xiaoma @create 2019-02-26 9:59
 */
public class TestShiro1 {

    public static void main(String[] args) {

        // 1.首先通过IniSecurityManagerFactory和shiro.ini文件生成一个SecurityManager工厂。
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
        //得到安全管理器
        SecurityManager securityManager = factory.getInstance();
        //2。获得该工厂的实例并托管给SecurityUtils这个类，其实就是放在了SecurityUtils这个类的一个static变量中，下面操作的底层还是SecurityManager里面的方法。
        SecurityUtils.setSecurityManager(securityManager);
        //3。通过SecurityUtils得到一个主体对象，此时这个对象里面是没有数据的，也没有通过认证和授权，在SecurityUtils.getSubject获取的同时将当这个subject绑定到了当前线程里面。（如果你再getSubject也是该subject）
        Subject subject = SecurityUtils.getSubject();
        //4.创建一个token绑定用户名和密码。然后调用subject.login进行登录（其实就是调用了SecurityManager进行认证和授权）

        UsernamePasswordToken token=new UsernamePasswordToken("zhang","1234");


        try{
            // 登录的时候如果账号密码异常，会抛出对应的异常。（这里还绑定了session，shiro的session和web中的session是两个概念，后面介绍）
            //这里使用try -catch 主要的原因是防止用户名/密码 输入错误的时候 抛异常，便于排错，以及用户交互体验
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
        }


        //验证是否认证
        System.out.println("用户认证—状态："+subject.isAuthenticated());
        //退出
        subject.logout();
        System.out.println("用户退出状态："+subject.isAuthenticated());
    }


}
