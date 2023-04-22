## spring-boot-security

### 1. 简介

SpringBoot 集成 SpringSecurity，前端页面使用 thymeleaf 。扩展了 **鉴权失败处理器**、 **验证器**、**登录成功处理器**、**投票器**、**登出成功处理器** 、**登录失败处理器**
。此示例适用于前后不分离场景。

> 访问地址：http://localhost:port
>

### 2. 使用

1. 移除 MockService

2. 完善 UserDetailsServiceImpl

   主要逻辑为通过用户名查询数据库中存储的用户和所属角色以及权限并封装为 UserDetails 实体类。

3. 授权注解使用

    - 开启注解支持 @EnableGlobalMethodSecurity(prePostEnabled = true)

    - 权限检查注解

      @PreAuthorize：方法执行前检查

      ```java
      @PreAuthorize("hasRole('ADMIN')")  
      public void addUser(User user){  
          //如果具有ROLE_ADMIN 权限 则访问该方法  
          ....  
      }
      ```

      @PostAuthorize：方法执行后检查，失败抛异常

      ```java
      @PostAuthorize：允许方法调用，但是，如果表达式结果为false抛出异常  
      //returnObject可以获取返回对象user，判断user属性username是否和访问该方法的用户对象的用户名一样。不一样则抛出异常。  
      @PostAuthorize("returnObject.user.username==principal.username")  
      public User getUser(int userId){  
         //允许进入
      ...  
          return user;
      }
      ```

      @PostFilter：允许方法调用，但是按照表达式过滤方法结果

      ```java
      //将结果过滤，即选出性别为男的用户  
      @PostFilter("returnObject.user.sex=='男' ")  
      public List<User> getUserList(){  
          //允许进入
          ...  
          return user; 
      }
      ```

      @PreFilter：允许方法调用，但必须在进入方法前过滤输入值

      @Secured：拥有指定角色才可以访问方法

      ```java
      @Secured('ADMIN')   等价于    @PreAuthorize("hasRole('ADMIN')")
      ```

### 3.效果

登录成功：首页根据用户所拥有的权限动态显示内容，回显用户名和权限，去掉登录接口显示注销接口。

![2021-11-15_160510](https://img.qinweizhao.com//2021/11/2021-11-15_160510.png)

### 4. 相关

> [Spring Security 的执行流程 (qinweizhao.com)](https://blog.qinweizhao.com/article/14)
>
> [Spring Security 配置 (qinweizhao.com)](https://blog.qinweizhao.com/article/59)
>
> [Spring Security 认证原(qinweizhao.com)](https://blog.qinweizhao.com/article/15)
>

