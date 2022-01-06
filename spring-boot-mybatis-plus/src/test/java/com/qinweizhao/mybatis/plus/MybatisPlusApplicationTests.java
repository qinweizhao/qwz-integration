package com.qinweizhao.mybatis.plus;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.qinweizhao.mybatis.plus.entity.UserInfo;
import com.qinweizhao.mybatis.plus.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author qinweizhao
 * @since 2022/1/6
 */
@MybatisPlusTest
class MybatisPlusApplicationTests {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Test
    void testList() {
        List<UserInfo> userInfos = userInfoMapper.selectList(null);
        assertThat(userInfos).isNotNull();
        System.out.println(userInfos);
    }
}
