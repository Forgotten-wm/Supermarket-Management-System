package com.wm.controller;

import com.github.pagehelper.PageInfo;
import com.wm.pojo.Goods;
import com.wm.pojo.Supplier;
import com.wm.service.SupplierService;
import com.wm.until.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wm
 */
@RequestMapping("/api/supplier/")
@Controller
public class SupplierController {
    private SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @ResponseBody
    @RequestMapping("selectAll")
    public JsonData paging() {
        List<Supplier> suppliers = supplierService.selectAll();
        if (suppliers== null){
            return new JsonData(500,"商家信息查询失败");
        }
        return new JsonData(suppliers);

    }
}
