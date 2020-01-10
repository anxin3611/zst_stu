package com.zst.spring.service;

import com.zst.spring.domain.CorporationDO;
import com.zst.spring.repository.CorporationRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZST
 * @version 1.0
 * @date 2020/1/10 15:28
 * @description
 */
@Service
public class CorporationService {
    @Resource
    private CorporationRepository corporationRepository;

    public CorporationDO findAll() {
        return corporationRepository.findAll().get(0);
    }

    public List<CorporationDO> save(CorporationDO corporationDO) {
        CorporationDO save = corporationRepository.save(corporationDO);
        return corporationRepository.findAll();
    }

    public void deleteAll() {
        corporationRepository.deleteAll();
    }
}
