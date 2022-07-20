package com.qinweizhao.dubbo.consumer.service;

import com.qinweizhao.dubbo.api.dto.ProviderTestDTO;
import com.qinweizhao.dubbo.api.service.IProviderService;
import com.qinweizhao.dubbo.api.vo.ResultVO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qinweizhao
 * @since 2021/9/27
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    // Dubbo远程调用注解
    @Reference
    private IProviderService providerService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVO getList() {
        // 远程调用
        List<ProviderTestDTO> providerTestDTOS = (List<ProviderTestDTO>) providerService.queryList();
        return new ResultVO.Builder<>().code(200).message("success").data(providerTestDTOS).build();
    }
}