package com.zst.spring.service;

import com.zst.spring.base.BaseService;
import com.zst.spring.domain.CorporationDO;
import com.zst.spring.mapstruct.CorporationConvert;
import com.zst.spring.repository.CorporationRepository;
import com.zst.spring.base.BaseResponse;
import com.zst.spring.vo.CorporationResponseVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/10 15:28
 * @description corporation service 企业
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CorporationService extends BaseService {
    @Resource
    private CorporationRepository corporationRepository;

    @Resource
    private CorporationConvert corporationConvert;

    /**
     * 查询所有
     *
     * @return 所有实体的集合
     */
    public BaseResponse<List<CorporationResponseVO>> findAll() {
        return BaseResponse.sucess(handleSerialNum(), corporationConvert.convert(corporationRepository.findAll()));
    }

    /**
     * 根据id 查询实体
     *
     * @param id id
     * @return 查询后的实体对象
     */
    public BaseResponse<CorporationResponseVO> findById(Short id) {
        CorporationDO corporationDO = null;
        Optional<CorporationDO> byId = corporationRepository.findById(id);
        if (byId.isPresent()) {
            corporationDO = byId.get();
        }
        return BaseResponse.sucess(handleSerialNum(), corporationConvert.convert(corporationDO));
    }

    /**
     * 保存实体
     *
     * @param corporationDO 待保存的实体
     * @return 保存后的实体
     */
    public List<CorporationDO> save(CorporationDO corporationDO) {
        CorporationDO save = corporationRepository.save(corporationDO);
        return corporationRepository.findAll();
    }

    /**
     * 删除全部数据
     */
    public void deleteAll() {
        corporationRepository.deleteAll();
    }

    /**
     * 修改
     *
     * @param corporationDO 待修改的实体
     * @return 修改后的实体
     */
    public CorporationDO update(CorporationDO corporationDO) {
        return corporationRepository.save(corporationDO);
    }
}
