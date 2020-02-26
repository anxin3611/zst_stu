package com.zst.spring.mapstruct;

import com.zst.spring.domain.CorporationDO;
import com.zst.spring.vo.CorporationResponseVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/2/26 13:07
 * @description ：企业信息转换类
 */
@Mapper(componentModel = "spring", imports = {LocalDateTime.class, Date.class, ZoneId.class})
public interface CorporationConvert {

    /**
     * 转换方法
     *
     * @param corporationDO 企业相关DO对象
     * @return com.zst.spring.vo.CorporationResponseVO 企业相关VO返回对象
     * @date 2020/2/26 13:23
     */
    @Mapping(target = "corpName", source = "name")
    CorporationResponseVO convert(CorporationDO corporationDO);

    List<CorporationResponseVO> convert(List<CorporationDO> corporationDO);
}
