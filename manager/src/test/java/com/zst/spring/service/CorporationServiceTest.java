package com.zst.spring.service;

import com.zst.spring.domain.CorporationDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ZST
 * @version 1.0
 * @date 2020/1/10 16:24
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CorporationServiceTest {
    @Resource
    private CorporationService corporationService;

    @Test
    public void findAll() {
        CorporationDO all = corporationService.findAll();
        assertNotNull(all);
    }

    @Test
    public void save() {
        corporationService.deleteAll();
        CorporationDO corporationDO = new CorporationDO((short) 1, "Acme Paper Corporation");
        List<CorporationDO> save = corporationService.save(corporationDO);
        assertNotNull(save);
    }
}