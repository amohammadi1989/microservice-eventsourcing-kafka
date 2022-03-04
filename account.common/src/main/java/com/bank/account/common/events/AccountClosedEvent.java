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
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountClosedEvent extends BaseEvent {
  private String id;
}
