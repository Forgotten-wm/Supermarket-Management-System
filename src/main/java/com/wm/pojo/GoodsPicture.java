package com.wm.pojo;

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
public class GoodsPicture implements Serializable {

  private Integer id;
  private Goods goodsId;
  private Picture pictureId;


}
