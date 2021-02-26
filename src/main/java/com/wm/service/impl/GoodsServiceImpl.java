package com.wm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wm.mapper.GoodsMapper;
import com.wm.mapper.GoodsPictureMapper;
import com.wm.mapper.PictureMapper;
import com.wm.pojo.Goods;
import com.wm.pojo.GoodsPicture;
import com.wm.pojo.Picture;
import com.wm.service.GoodsService;
import com.wm.until.PictureUntil;
import com.wm.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.plugin2.ipc.IPCFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author wm
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    private GoodsMapper goodsMapper;
    private GoodsPictureMapper goodsPictureMapper;
    private PictureMapper pictureMapper;

    @Autowired
    public GoodsServiceImpl(GoodsMapper goodsMapper, GoodsPictureMapper goodsPictureMapper, PictureMapper pictureMapper) {
        this.goodsMapper = goodsMapper;
        this.goodsPictureMapper = goodsPictureMapper;
        this.pictureMapper = pictureMapper;
    }


    @Override
    public PageInfo<Goods> paging(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            System.out.println("error：分页数据：页码或页大小为空");
            return null;
        }
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(goodsMapper.selectAll());
    }

    @Override
    public Goods selectById(Integer id) {
        if (id <= 0) {
            throw new RuntimeException("参数异常");
        }
        Goods goods = goodsMapper.selectById(id);
        if (goods == null) {
            throw new RuntimeException("结果异常");
        }
        return goods;

    }

    @Override
    public List<Goods> selectAll() {
        return goodsMapper.selectAll();

    }

    @Transactional(rollbackForClassName = "Exception")
    @Override
    public void deleteByIds(Integer[] ids) {
        if (ids != null && ids.length != 0) {
            for (Integer id : ids) {
                if (id == null) {
                    throw new NullPointerException("ids中有元素为null");
                }

            }
            List<String> srcList = null;
            List<GoodsPicture> goodsPictures = goodsPictureMapper.selectByGoodsIds(ids);

            if (goodsPictures != null && goodsPictures.size() != 0) {
                Integer[] pictureIds = new Integer[goodsPictures.size()];
                for (int i = 0, j = goodsPictures.size(); i < j; i++) {
                    pictureIds[i] = goodsPictures.get(i).getPictureId().getId();
                }


                goodsPictureMapper.deleteByGoodsIds(ids);
                srcList = new ArrayList<>();
                for (Integer pictureId : pictureIds) {
                    srcList.add(pictureMapper.selectById(pictureId).getSrc());
                }
                pictureMapper.deleteByIds(pictureIds);
            }

            goodsMapper.deleteByIds(ids);
            if (srcList != null) {
                for (String s : srcList) {
                    PictureUntil.delete(s);
                }

            }
        }

    }

    @Transactional(rollbackForClassName = "Exception")
    @Override
    public void updateById(Goods goods) {
        if (goods == null) {
            throw new NullPointerException("goods 为空");
        }
        goodsMapper.updateById(goods);
    }


    @Transactional(rollbackForClassName = "Exception")
    @Override
    public void purchase(GoodsVo goodsVo) throws IOException {
        Goods goods = null;
        if (goodsVo == null || (goods = goodsVo.getGoods()) == null) {
            throw new RuntimeException("参数异常");
        }

        goodsMapper.insert(goods);
        MultipartFile[] pictureFiles = null;
        if ((pictureFiles = goodsVo.getPictureFile()) !=null &&pictureFiles.length != 0) {
            String[] title = goodsVo.getPicture().getTitle().split(",");
            String[] information = goodsVo.getPicture().getInformation().split(",");

            for (int i = 0, j = pictureFiles.length; i < j; i++) {
                String pictureName = System.currentTimeMillis() + pictureFiles[i].getOriginalFilename();
                Picture picture = new Picture(null, pictureName, title[i], information[i]);

                pictureMapper.insert(picture);
                insertGoodsPicture(goods, picture);
                PictureUntil.upload(pictureFiles[i], pictureName);
            }
        }


    }

    private void insertGoodsPicture(Goods goods, Picture picture) {
        goodsPictureMapper.insert(new GoodsPicture(null, goods, picture));
    }


}
