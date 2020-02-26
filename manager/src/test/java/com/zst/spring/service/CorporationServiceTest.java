package com.zst.spring.service;

import com.zst.spring.domain.CorporationDO;
import com.zst.spring.vo.CorporationResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Item233
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
        List<CorporationDO> all = (List<CorporationDO>) corporationService.findAll().getData();
        System.out.println(all.get(0).toString());
        assertNotNull(all);
    }

    @Test
    public void save() {
        corporationService.deleteAll();
        CorporationDO corporationDO = new CorporationDO((short) 1, "Acme Paper Corporation");
        List<CorporationDO> save = corporationService.save(corporationDO);
        assertNotNull(save);
    }

    @Test
    public void testFindAll() {
    }

    @Test
    public void findById() {
        CorporationResponseVO byId = getCorporationDO();
        assertNotNull(byId);
    }

    private CorporationResponseVO getCorporationDO() {
        return corporationService.findById((short) 1).getData();
    }

    @Test
    public void testSave() {
    }

    @Test
    public void deleteAll() {
    }

    @Test
    public void update() {
        CorporationResponseVO corporationDO = getCorporationDO();
        corporationDO.setCorpName(null);
//        CorporationDO save = corporationService.update(corporationDO);

    }
}