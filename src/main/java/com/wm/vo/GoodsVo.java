package com.wm.vo;

import com.wm.pojo.Goods;
import com.wm.pojo.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo {
    @Valid
    @NotNull(message = "goods is null",groups=Purchase.class)
    private Goods goods;

    private MultipartFile[] pictureFile;

    private List<Picture> pictures;
    private Picture picture;

    public interface Purchase{}
}
