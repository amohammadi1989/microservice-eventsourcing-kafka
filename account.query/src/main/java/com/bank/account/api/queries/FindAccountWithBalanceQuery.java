package com.bank.account.api.queries;

import com.bank.core.queries.BaseQuery;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountWithBalanceQuery extends BaseQuery {
   // private EqualityType equalityType;
    private double balance;
}
