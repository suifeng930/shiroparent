package com.mhc.shiro;

import com.mhc.shiro.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author xiaoma
 * @create 2019-02-26 13:39
 */
public class JdbcRealmTest {

    public static void main(String[] args) {

        //调用认证登录方法
        ShiroUtils.login("classpath:shiro-jdbc.ini","zhang","123456");

        //获取subject对象
        Subject subject = SecurityUtils.getSubject();

        //查看是否认证通过
        System.out.println("用户认证--："+subject.isAuthenticated());

        //是否有role1角色
        System.out.println(subject.hasRole("role1"));

        /**
         * 原因是JdbcRealm里面有个permissionsLookupEnabled默认是false，所以不会查询对应的资源权限，我们在配置文件中打开这个查询。
         * #jdbcRealm.permissionsLookupEnabled=true
         */
        boolean permitted = subject.isPermitted("user:create"); //查询数据库中是否有权限
        System.out.println(permitted);


    }
}
