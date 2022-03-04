package com.bank.core.domains;
import com.bank.core.events.BaseEvent;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created By: Ali Mohammadi
 * Date: 08 Feb, 2022
 */
public abstract class AggregateRoot {
  private String id ;
  private int version;
  private List<BaseEvent> changes =new LinkedList<>();
  private Logger logger=Logger.getLogger( AggregateRoot.class.getName() );
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public int getVersion() {
    return version;
  }
  
  public void setVersion(int version) {
    this.version = version;
  }
  public List<BaseEvent> getUncommittedChanges(){
    return this.changes;
  }
  public void markChangesCommitted(){
    this.changes.clear();
  }
  protected void applyChange(BaseEvent event,Boolean isNewEvent){
    
    try {
      Method method=getClass().getDeclaredMethod( "apply", event.getClass() );
      method.setAccessible( true );
      method.invoke( this,event );
      
    } catch (Exception e) {
      logger.log( Level.WARNING, MessageFormat.format( "Exception was in class with name {0}",
                                                       event.getClass().getName() ) );
    } finally {
      if (isNewEvent)
        changes.add( event );
    }
  }
  public  void raiseEvent(BaseEvent event){
    applyChange( event,true );
  }
  public void replayEvents(Iterable<BaseEvent> events){
    events.forEach( e->applyChange( e,false ) );
  }
}
