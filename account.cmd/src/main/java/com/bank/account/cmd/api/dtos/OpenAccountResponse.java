package com.bank.account.cmd.api.dtos;
import com.bank.account.common.dtos.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Created By: Ali Mohammadi
 * Date: 09 Feb, 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAccountResponse extends BaseResponse {
  private String id;
  public OpenAccountResponse(String id,String messages){
    super(messages);
    this.id=id;
  }
}
