package com.wisestep.tollbooth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisestep.tollbooth.domain.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

}
