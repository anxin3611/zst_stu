package com.zst.spring.service;

import com.zst.spring.base.BasePageResponse;
import com.zst.spring.base.BaseService;
import com.zst.spring.base.BasePageRequest;
import com.zst.spring.domain.CorporationDO;
import com.zst.spring.mapstruct.CorporationConvert;
import com.zst.spring.repository.CorporationRepository;
import com.zst.spring.base.BaseResponse;
import com.zst.spring.vo.request.CorpListRequest;
import com.zst.spring.vo.response.CorporationResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public BaseResponse<List<CorporationResponse>> findAll(BasePageRequest<CorpListRequest> request) {
        CorpListRequest bean = request.getBean();
        if (StringUtils.isBlank(request.getSerialNum())) {
            request.setSerialNum(handleSerialNum());
        }
        Integer currentPage = request.getCurrentPage();
        Integer pageSize = request.getPageSize();
        Sort corpId = Sort.by(Sort.Direction.DESC, "corpId");
        PageRequest pageRequest = PageRequest.of(request.getCurrentPage() - 1, pageSize, corpId);
        Page<CorporationDO> all = corporationRepository.findAll(pageRequest);
        List<CorporationDO> content = all.getContent();
        return new BasePageResponse<List<CorporationResponse>, CorpListRequest>().response(corporationConvert.convert(content), request, all.getTotalElements(), content.size());
    }

    /**
     * 根据id 查询实体
     *
     * @param id id
     * @return 查询后的实体对象
     */
    public BaseResponse<CorporationResponse> findById(Short id) {
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
