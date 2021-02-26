package com.wm.pojo;

import com.wm.vo.GoodsVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCount implements Serializable {

  private Integer id;
  private Goods goodsId;
  private Integer count;


}
