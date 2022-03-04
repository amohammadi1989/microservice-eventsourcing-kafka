package com.bank.core.queries;
import com.bank.core.domains.BaseEntity;

import java.util.List;
/**
 * Created By: Ali Mohammadi
 * Date: 12 Feb, 2022
 */
@FunctionalInterface
public interface QueriesHandlerMethod<T extends BaseQuery> {
  List<BaseEntity> handle(T query);
}
