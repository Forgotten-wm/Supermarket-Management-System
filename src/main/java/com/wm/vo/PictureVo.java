package com.wm.vo;

import com.wm.pojo.Goods;
import com.wm.pojo.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author wm
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PictureVo implements Serializable {


    @NotNull(message = "pictureFile is null", groups = InsertForFoods.class)
    private MultipartFile pictureFile;
    @Valid
    @NotNull(message = "picture is null", groups = InsertForFoods.class)
    private Picture picture;
    @NotNull(message = "goods is null", groups = InsertForFoods.class)
    private Goods goods;

    public interface InsertForFoods {
    }

    public interface DeleteByIdAndSrc {
    }
}
