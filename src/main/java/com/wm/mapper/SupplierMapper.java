package com.wm.mapper;

import com.wm.pojo.Supplier;

import java.util.List;

/**
 * @author wm
 */

public interface SupplierMapper {
    /**
     * 添加供货商信息
     * @param supplier 供货商信息
     */
    void insert(Supplier supplier);

    /**
     * 查询所有供货商信息
     * @return 所有供货商信息
     */
    List<Supplier> selectAll();

    /**
     * 查询供货商数量
     * @return 供货商数量
     */
    int count();

    /**
     * 依据主键查询供货商信息
     * @param id 主键
     * @return 供货商信息
     */
    Supplier selectById(Integer id);

    /**
     * 依据主键修改供货商信息
     * @param supplier 供货商实体
     */
    void updateById(Supplier supplier);

    /**
     * 遗迹主键删除供货商信息
     * @param ids 主键
     */
    void deleteByIds(Integer[] ids);
}
