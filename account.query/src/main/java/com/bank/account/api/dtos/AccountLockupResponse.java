package com.bank.account.api.dtos;
import com.bank.account.common.dtos.BaseResponse;
import com.bank.account.domain.BankAccount;
import com.bank.core.domains.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/**
 * Created By: Ali Mohammadi
 * Date: 12 Feb, 2022
 */

@Data@SuperBuilder
public class AccountLockupResponse extends BaseResponse {
  private List<BankAccount> bankAccounts;
  public AccountLockupResponse(String msg){
    super(msg);
  }
}
