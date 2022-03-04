package com.bank.account.cmd.api.commands;
import com.bank.account.common.dtos.AccountType;
import com.bank.core.commands.BaseCommand;
import lombok.Data;
/**
 * Created By: Ali Mohammadi
 * Date: 08 Feb, 2022
 */
@Data
public class OpenAccountCommand extends BaseCommand {
  private String accountHolder;
  private AccountType accountType;
  private double openingBalance;
}
