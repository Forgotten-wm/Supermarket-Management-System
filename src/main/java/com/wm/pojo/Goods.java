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
public class Goods implements Serializable {

  private Integer id;
  @NotNull(message = "name is null",groups= GoodsVo.Purchase.class)
  private String name;
  @NotNull(message = "type is null",groups= GoodsVo.Purchase.class)
  private String type;
  @NotNull(message = "price is null",groups= GoodsVo.Purchase.class)
  private double price;
  @NotNull(message = "unit is null",groups= GoodsVo.Purchase.class)
  private String unit;
  @NotNull(message = "productionDate is null",groups= GoodsVo.Purchase.class)
  @Past
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date productionDate;
  @NotNull(message = "expiryDate is null",groups= GoodsVo.Purchase.class)
  @Future
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date expiryDate;
  @NotNull(message = "supplierId is null",groups= GoodsVo.Purchase.class)
  private Supplier supplierId;
  private String information;


}
