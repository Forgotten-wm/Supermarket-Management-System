package com.wm.vo;

import com.wm.pojo.GoodsCount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCountVo {
    private Integer[] ids;
    private Integer[] counts;
}
