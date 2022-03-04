package com.bank.account.cmd.api.commands;
import com.bank.core.commands.BaseCommand;
import lombok.Data;
/**
 * Created By: Ali Mohammadi
 * Date: 08 Feb, 2022
 */
@Data
public class WithdrawFundsCommand extends BaseCommand {
 private double amount;
}
