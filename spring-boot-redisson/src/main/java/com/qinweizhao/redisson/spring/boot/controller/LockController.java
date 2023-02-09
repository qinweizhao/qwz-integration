package com.qinweizhao.redisson.spring.boot.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author qinweizhao
 * @since 2023-02-09
 */
@RestController
public class LockController {


    @Resource
    private RedissonClient redisson;

    @RequestMapping("/lock")
    public void lock(HttpServletResponse response) throws InterruptedException {
        RLock lock = redisson.getLock("test-lock");

        if (!lock.isLocked()) {
            boolean b = lock.tryLock(1, 300, TimeUnit.SECONDS);
            if (b) {
                System.out.println("加锁成功" + Thread.currentThread());
            }
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


    /**
     * 不可取（存在问题）
     *
     * @param response response
     * @throws InterruptedException InterruptedException
     */
    @RequestMapping("/lock2")
    public void lock2(HttpServletResponse response) throws InterruptedException {
        long id = Thread.currentThread().getId();
        System.out.println("进入线程" + id);

        RLock lock = redisson.getLock("test-lock");
        boolean b = lock.tryLock(1, 300, TimeUnit.SECONDS);

        if (b) {
            System.out.println("加锁成功" + id);
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


    @RequestMapping("/lock3")
    public void lock3(HttpServletResponse response) throws InterruptedException {
        RLock lock = redisson.getLock("test-lock");

        if (lock.isLocked()) {
            System.out.println("加锁成功" + Thread.currentThread());
        }
        boolean b = lock.tryLock(1, 300, TimeUnit.SECONDS);
        if (b) {
            System.out.println("加锁成功" + Thread.currentThread());
        }

    }


}
