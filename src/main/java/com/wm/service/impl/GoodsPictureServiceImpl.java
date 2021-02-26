package com.wm.service.impl;

import com.wm.mapper.GoodsPictureMapper;
import com.wm.pojo.GoodsPicture;
import com.wm.service.GoodsPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wm
 */

@Service
public class GoodsPictureServiceImpl implements GoodsPictureService {

    private GoodsPictureMapper goodsPictureMapper;

    @Autowired
    public GoodsPictureServiceImpl(GoodsPictureMapper goodsPictureMapper) {
        this.goodsPictureMapper = goodsPictureMapper;
    }

    @Override
    public List<GoodsPicture> selectByGoodsId(Integer goodsId) {
        if (goodsId<=0){
            return null;
        }
        List<GoodsPicture> goodsPictures = goodsPictureMapper.selectByGoodsId(goodsId);
        if (goodsPictures == null){
            return null;
        }
        return goodsPictures;
    }
}
