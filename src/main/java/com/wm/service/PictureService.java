package com.wm.service;

import com.wm.vo.PictureVo;

import java.io.IOException;
import java.rmi.server.ExportException;

/**
 * @author wm
 */

public interface PictureService {
    /**
     * 为商品添加一张图片
     * @param pictureVo 图片vo
     * @return 是否成功
     */
    boolean insertForGoods(PictureVo pictureVo);

    /**
     * 为商品添加一张图片
     * @param pictureVo 图片vo
     * @return 是否成功
     */
    boolean deleteByIdAndSrc(PictureVo pictureVo) ;

}
