package com.bank.core.messages;
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
public abstract class Message {
  private  String id;
}
