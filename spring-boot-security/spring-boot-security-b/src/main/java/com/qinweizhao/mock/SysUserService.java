package com.qinweizhao.mock;

import org.springframework.stereotype.Service;

/**
 * @author qinweizhao
 */
@Service
public class SysUserService {


    public SysUser getUserByName(String userName){
        SysUser sysUser = new SysUser();
        sysUser.setUsername("admin");
        sysUser.setPassword("admin");
        if (!userName.equals(sysUser.getUsername())){
         return null;
        }
        return sysUser;
    }
}


/**
 * mo-用户类
 */
class SysUser {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;

    private String password;
}