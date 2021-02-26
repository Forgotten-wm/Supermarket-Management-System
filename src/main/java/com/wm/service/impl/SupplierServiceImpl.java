package com.wm.service.impl;

import com.wm.mapper.SupplierMapper;
import com.wm.pojo.Supplier;
import com.wm.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wm
 */
@Service
public class SupplierServiceImpl implements SupplierService {
    private SupplierMapper supplierMapper;

    @Autowired
    public SupplierServiceImpl(SupplierMapper supplierMapper) {
        this.supplierMapper = supplierMapper;
    }

    @Override
    public List<Supplier> selectAll() {

        return supplierMapper.selectAll();
    }
}
