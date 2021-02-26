package com.wm.service;

import com.wm.pojo.Supplier;

import java.util.List;

/**
 * @author wm
 */

public interface SupplierService {
    /**
     * 查询所有供货商信息
     * @return 所有供货商信息
     */
    List<Supplier> selectAll();
}
