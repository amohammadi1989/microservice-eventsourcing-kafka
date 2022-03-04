package com.bank.account.domain;
import com.bank.account.common.dtos.AccountType;
import com.bank.core.domains.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
/**
 * Created By: Ali Mohammadi
 * Date: 09 Feb, 2022
 */
@Entity(name = "Account")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankAccount extends BaseEntity {
  @Id
  private String id;
  private String accountHolder;
  private Date creationDate;
  private AccountType accountType;
  private double balance;
  
}
