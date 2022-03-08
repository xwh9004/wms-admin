package com.wms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.admin.commom.PageParam;
import com.wms.admin.mapper.*;
import com.wms.admin.service.IReportService;
import com.wms.admin.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IReportServiceImpl implements IReportService {
    @Autowired
    private StorageInDetailRecordMapper storageInDetailRecordMapper;
    @Autowired
    private StorageOutDetailRecordMapper storageOutDetailRecordMapper;
    @Autowired
    private StorageShiftDetailRecordMapper storageShiftDetailRecordMapper;
    @Autowired
    private DiscardDetailRecordMapper discardDetailRecordMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public IPage<ReportRecordVO> storageInList(ReportQueryVO queryVO, PageParam pageParam) {
        Page page = new Page(pageParam.getPage(),pageParam.getLimit());
        return storageInDetailRecordMapper.storageInReportList(queryVO,page);
    }

    @Override
    public IPage<ReportRecordVO> storageOutList(ReportQueryVO queryVO, PageParam pageParam) {
        Page page = new Page(pageParam.getPage(),pageParam.getLimit());
        return storageOutDetailRecordMapper.storageOutReportList(queryVO,page);
    }

    @Override
    public IPage<ReportRecordVO> storageShiftList(ReportQueryVO queryVO, PageParam pageParam) {
        Page page = new Page(pageParam.getPage(),pageParam.getLimit());
        return storageShiftDetailRecordMapper.storageShiftReportList(queryVO,page);
    }

    @Override
    public IPage<ReportRecordVO> discardList(ReportQueryVO queryVO, PageParam pageParam) {
        Page page = new Page(pageParam.getPage(),pageParam.getLimit());
        return discardDetailRecordMapper.discardReportList(queryVO,page);
    }

    @Override
    public IPage<ProductVO> productList(ReportQueryVO queryVO, PageParam pageParam) {
        ProductQueryVO productQueryVO = new ProductQueryVO();
        BeanUtils.copyProperties(queryVO,productQueryVO);
        Page page = new Page(pageParam.getPage(),pageParam.getLimit());
        return productMapper.productPage(productQueryVO,page);
    }

    @Override
    public IPage<ProdStockVO> stockList(ReportQueryVO queryVO, PageParam pageParam) {
        return null;
    }
}
