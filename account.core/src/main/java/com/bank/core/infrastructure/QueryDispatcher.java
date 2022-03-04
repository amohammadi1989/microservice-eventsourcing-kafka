package com.bank.core.infrastructure;
import com.bank.core.commands.BaseCommand;
import com.bank.core.commands.CommandHandlerMethod;
import com.bank.core.domains.BaseEntity;
import com.bank.core.queries.BaseQuery;
import com.bank.core.queries.QueriesHandlerMethod;

import java.util.List;
public interface QueryDispatcher {
  <T extends BaseQuery> void registerHandler(Class<T> type, QueriesHandlerMethod<T> handler);
  <U extends BaseEntity> List<U> send(BaseQuery command);
}
