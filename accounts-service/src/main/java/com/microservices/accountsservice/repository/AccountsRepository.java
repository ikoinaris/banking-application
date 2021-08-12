package com.microservices.accountsservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.accountsservice.entity.Accounts;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long>{
	
	Accounts findByCustomerId(int customerId);

}
