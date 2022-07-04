package com.qinweizhao.jpa.repository;

import com.qinweizhao.jpa.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author qinweizhao
 * @since 2022/7/4
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    /**
     * 按照表中的规则进行名称拼接，不用刻意去记，IDEA会有提示
     *
     * @param str str
     * @return List
     */
    List<Account> findAccountByUsername(String str);


    /**
     * 自定义SQL语句,必须在事务环境下运行 必须有DML支持(Modifying)
     * 直接对实体类进行操作 然后实体类映射到表中
     *
     * @param id          id
     * @param newPassword newPassword
     * @return int
     * Transactional 这个注解也可以加到测试类上面 但需要跟进一个@commit提交事务的注解 因为测试类会自动回滚事务
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update Account set password=?2 where id=?1")
    int updatePasswordById(int id, String newPassword);


    /**
     * 使用原生sql语句,也就是对表进行操作
     * @param username username
     * @param password password
     * @return int
     */
    @Modifying
    @Query(value = "update account set password=?2 where name=?1",nativeQuery = true)//开启原生SQL
    @Transactional
    int updatePasswordByUsername(String username,String password);


}
