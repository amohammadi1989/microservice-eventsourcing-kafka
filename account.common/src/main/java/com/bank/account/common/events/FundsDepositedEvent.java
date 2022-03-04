package com.bank.account.common.events;
import com.bank.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
/**
 * Created By: Ali Mohammadi
 * Date: 08 Feb, 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FundsDepositedEvent extends BaseEvent {
 private double amount;
}
