package com.qinweizhao.service;


import com.qinweizhao.dto.ProviderTestDTO;

import java.util.List;

/**
 * @author qinweizhao
 * @since 2021/9/27
 */
public interface IProviderService {
    List<ProviderTestDTO> queryList();
}