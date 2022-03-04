package com.bank.account.domain;
import com.bank.core.domains.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * Created By: Ali Mohammadi
 * Date: 09 Feb, 2022
 */
@Repository
public interface AccountRepository extends CrudRepository<BankAccount,String> {
  Optional<BankAccount> findByAccountHolder(String accountHolder);
  List<BaseEntity> findByBalanceGreaterThan(double balance);
  List<BaseEntity> findByBalanceLessThan(double balance);
}
