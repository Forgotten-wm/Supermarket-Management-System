package com.wm.controller;

import com.wm.pojo.Picture;
import com.wm.pojo.User;
import com.wm.service.PictureService;
import com.wm.service.UserService;
import com.wm.until.BindingResultUtil;
import com.wm.until.JsonData;
import com.wm.vo.PictureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * @author wm
 */
@RequestMapping("/api/picture/")
@Controller
public class PictureController {

    private PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }


    @ResponseBody
    @RequestMapping("insert-for-goods")
    public JsonData insertForGoods(@Validated(PictureVo.InsertForFoods.class) PictureVo pictureVo,BindingResult bindingResult) {
       if (bindingResult.hasErrors()){
           BindingResultUtil.print(bindingResult);
           return new JsonData(500, "图片上传失败");
       }
        boolean flag = pictureService.insertForGoods(pictureVo);
        return flag ? new JsonData(200, "图片上传成功") : new JsonData(500, "图片上传失败");

    }


    @ResponseBody
    @RequestMapping("delete-by-id-and-src")
    public JsonData deleteByIdAndSrc(@Validated(PictureVo.DeleteByIdAndSrc.class) PictureVo pictureVo,BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            BindingResultUtil.print(bindingResult);
            return new JsonData(500, "图片上传失败");
        }
        boolean flag = pictureService.deleteByIdAndSrc(pictureVo);
        return flag ? new JsonData(200, "图片删除成功") : new JsonData(500, "图片删除失败");
    }
}

