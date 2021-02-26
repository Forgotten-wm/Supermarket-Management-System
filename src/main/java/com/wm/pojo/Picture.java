package com.wm.pojo;

import com.wm.vo.PictureVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture implements Serializable {

    @NotNull(message = "picture.id is null", groups = PictureVo.DeleteByIdAndSrc.class)
    private Integer id;
    @NotNull(message = "picture.src is null", groups = PictureVo.DeleteByIdAndSrc.class)
    private String src;
    private String title;
    private String information;


}
