package com.wm.service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.wm.mapper.GoodsPictureMapper;
import com.wm.mapper.PictureMapper;
import com.wm.pojo.Goods;
import com.wm.pojo.GoodsPicture;
import com.wm.pojo.Picture;
import com.wm.service.PictureService;
import com.wm.until.PictureUntil;
import com.wm.vo.PictureVo;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.rmi.server.ExportException;
import java.security.acl.Group;

/**
 * @author wm
 */

@Service
public class PictureServiceImpl implements PictureService {

    private PictureMapper pictureMapper;
    private GoodsPictureMapper goodsPictureMapper;

    @Autowired
    public PictureServiceImpl(PictureMapper pictureMapper, GoodsPictureMapper goodsPictureMapper) {
        this.pictureMapper = pictureMapper;
        this.goodsPictureMapper = goodsPictureMapper;
    }

    @Transactional(rollbackForClassName = "Exception")
    @Override
    public boolean insertForGoods( PictureVo pictureVo) {

        if (pictureVo == null) {
            System.out.println("pictureVo为空");
            return false;
        }
        Picture picture = pictureVo.getPicture();
        MultipartFile pictureFile = pictureVo.getPictureFile();
        String pictureName = System.currentTimeMillis() + pictureFile.getOriginalFilename();
        picture.setSrc(pictureName);

        pictureMapper.insert(picture);

        createGoodsPicture(pictureVo.getGoods(), picture);

        try {
            PictureUntil.upload(pictureFile, pictureName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private void createGoodsPicture(Goods goods, Picture picture) {
        GoodsPicture goodsPicture = new GoodsPicture(null, goods, picture);
        goodsPictureMapper.insert(goodsPicture);

    }

    @Transactional(rollbackForClassName = "Exception")
    @Override
    public boolean deleteByIdAndSrc( PictureVo pictureVo) {

        Integer pictureId;
        String src;
        if (pictureVo == null || pictureVo.getPicture() == null || (pictureId = pictureVo.getPicture().getId()) == null || (src = pictureVo.getPicture().getSrc()) == null) {
            return false;
        }

        if (pictureId <= 0) {
            System.out.println(("pictureId 异常"));
            return false;
        }
        if ("".equals(src)) {
            System.out.println(("src 异常"));
            return false;
        }
        Integer[] pictureIdArray = {pictureId};
        goodsPictureMapper.deleteByPictureIds(pictureIdArray);
        pictureMapper.deleteByIds(pictureIdArray);
        PictureUntil.delete(src);
        return true;
    }

}
