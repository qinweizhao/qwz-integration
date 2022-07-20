package com.qinweizhao.jpa;

import com.qinweizhao.jpa.entity.Account;
import com.qinweizhao.jpa.entity.AccountDetail;
import com.qinweizhao.jpa.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

@SpringBootTest
class JpaApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    AccountRepository accountRepository;

    @Test
    void get() {
        accountRepository.findById(100).ifPresent(System.out::println);
    }

    @Test
    void save() {
        Account account = new Account();
        account.setUsername("Admin");
        account.setPassword("123456");
        account = accountRepository.save(account);  //返回的结果会包含自动生成的主键值
        System.out.println("插入时，自动生成的主键ID为：" + account.getId());
    }

    @Test
    void delete() {
        accountRepository.deleteById(2);   //根据ID删除对应记录
    }

    @Test
    void page() {
        accountRepository.findAll(PageRequest.of(0, 1)).forEach(System.out::println);  //直接分页
    }


    @Test
    void findAccountByUsername() {
        accountRepository.findAccountByUsername("Admin").forEach(System.out::println);
    }


    @Test
    void updatePasswordById() {
        accountRepository.updatePasswordById(1, "123");
    }


    @Test
    void updatePasswordByUsername() {
        accountRepository.updatePasswordByUsername("test", "123456");
    }


    @Test
    void add() {
        Account account = new Account();
        account.setUsername("qwz");
        account.setPassword("qwz");
        AccountDetail detail = new AccountDetail();
        detail.setAddress("zgsh");
        detail.setPhone("112");
        detail.setEmail("yvkg@qq.com");
        detail.setRealName("wz");
        account.setDetail(detail);//这里就是传入一个对象
        account = accountRepository.save(account);
        System.out.println("插入时，自动生成的主键ID为：" + account.getId() + "，外键ID为：" + account.getDetail().getId());
    }


}
