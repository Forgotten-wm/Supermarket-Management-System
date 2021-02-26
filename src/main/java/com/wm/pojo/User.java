package com.wm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  private Integer id;
  @NotNull
  @NotEmpty
  private String username;
  @NotNull
  @NotEmpty
  private String password;


}
