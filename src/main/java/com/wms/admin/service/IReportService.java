package com.wms.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.admin.commom.PageParam;
import com.wms.admin.vo.*;

public interface IReportService {
     IPage<ReportRecordVO> storageInList(ReportQueryVO queryVO, PageParam pageParam);

     IPage<ReportRecordVO> storageOutList(ReportQueryVO queryVO, PageParam pageParam) ;

     IPage<ReportRecordVO> storageShiftList(ReportQueryVO queryVO, PageParam pageParam);

     IPage<ReportRecordVO>  discardList(ReportQueryVO queryVO, PageParam pageParam) ;

     IPage<ProductVO> productList(ReportQueryVO queryVO, PageParam pageParam) ;

     IPage<ProdStockVO> stockList(ReportQueryVO queryVO, PageParam pageParam) ;

     IPage<ReportRecordVO> inventoryList(ReportQueryVO queryVO, PageParam pageParam);
}
