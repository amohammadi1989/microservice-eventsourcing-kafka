package com.bank.core.commands;
import com.bank.core.messages.Message;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Created By: Ali Mohammadi
 * Date: 08 Feb, 2022
 */
@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {
  public BaseCommand(String id){
    super(id);
  }
}
