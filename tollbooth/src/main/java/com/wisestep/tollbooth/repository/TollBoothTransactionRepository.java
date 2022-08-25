package com.wisestep.tollbooth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wisestep.tollbooth.domain.TollBoothTransaction;

public interface TollBoothTransactionRepository extends JpaRepository<TollBoothTransaction, Integer> {

	@Query("select t from TollBoothTransaction t where t.tollBoothId = ?1")
	public List<TollBoothTransaction> getTransactionsByTollBooth(int tollBoothId);

}
