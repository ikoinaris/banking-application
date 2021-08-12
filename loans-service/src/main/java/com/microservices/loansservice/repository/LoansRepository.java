package com.microservices.loansservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.loansservice.entity.Loans;

@Repository
public interface LoansRepository extends CrudRepository<Loans, Integer>{
	
	List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}
