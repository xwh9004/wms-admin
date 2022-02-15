package com.wms.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.WmsAdminApplication;
import com.wms.admin.vo.RegionRackQueryVO;
import com.wms.admin.vo.RegionRackVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = WmsAdminApplication.class)
public class RegionRacksMapperTest {
    @Autowired
    private RegionRacksMapper regionRacksMapper;


    @Test
    public void rackPage(){
        RegionRackQueryVO queryVO = new RegionRackQueryVO();
        Page<RegionRackQueryVO> page = new Page<>(1, 10);
        IPage<RegionRackVO> result = regionRacksMapper.rackPage(queryVO, page);
        log.info("total ={}",result.getTotal());
    }

}
