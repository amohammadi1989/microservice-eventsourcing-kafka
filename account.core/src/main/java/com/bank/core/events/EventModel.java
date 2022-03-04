package com.bank.core.events;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
/**
 * Created By: Ali Mohammadi
 * Date: 08 Feb, 2022
 */
@Data
@Builder
@Document(collection = "eventStore")
public class EventModel {
  @Id
  private String id;
  private Date timeStamp;
  private String aggregationIdentifier;
  private String aggregationType;
  private int version;
  private String eventType;
  private BaseEvent event;
}
