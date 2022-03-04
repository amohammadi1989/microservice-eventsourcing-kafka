package com.bank.account.common.events;
import com.bank.account.common.dtos.AccountType;
import com.bank.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
/**
 * Created By: Ali Mohammadi
 * Date: 08 Feb, 2022
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountOpenedEvent extends BaseEvent {
  private String accountHolder;
  private AccountType accountType;
  private Date createAccount;
  private double openingBalance;
}
