package com.wm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier implements Serializable {

  private Integer id;
  private String name;
  private String loc;


}
