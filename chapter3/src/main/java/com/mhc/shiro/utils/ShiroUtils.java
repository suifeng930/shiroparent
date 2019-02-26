package com.mhc.shiro.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @author xiaoma
 * @create 2019-02-26 13:29
 */
public class ShiroUtils {

    /**
     * shiro用户认证登录
     * @param configPath 配置文件路径
     * @param username 用户名
     * @param password 密码
     */
    public static void login(String configPath,String username,String password){
        /**
         * 1.获取factory 工厂对象
         * 2.获取securityManager实例
         * 3.利用SecurityUtils.getSubject获取到 subject对象
         * 4.创建usernamaPasswordToken
         * 5.调用 subject.login()
         */
        Factory<SecurityManager> factory =new IniSecurityManagerFactory(configPath);
        //得到安全管理器
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }
}
