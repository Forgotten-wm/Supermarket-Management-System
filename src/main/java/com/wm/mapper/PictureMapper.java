package com.wm.mapper;

import com.wm.pojo.Picture;
import com.wm.pojo.User;

import java.util.List;

/**
 * @author wm
 */

public interface PictureMapper {

    /**
     * 添加图片信息
     *
     * @param picture 图片信息
     */
    void insert(Picture picture);

    /**
     * 查询所有图片信息
     *
     * @return 所有图片信息
     */
    List<Picture> selectAll();

    /**
     * 查询图片数量
     *
     * @return 图片数量
     */
    int count();

    /**
     * 依据主键查询图片信息
     *
     * @param id 主键
     * @return 图片信息
     */
    Picture selectById(Integer id);

    /**
     * 依据主键修改图片信息
     *
     * @param picture 图片实体
     */
    void updateById(Picture picture);

    /**
     * 遗迹主键删除图片信息
     *
     * @param ids 主键
     */
    void deleteByIds(Integer[] ids);

}

