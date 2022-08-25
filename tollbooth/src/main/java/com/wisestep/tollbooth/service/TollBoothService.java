package com.wisestep.tollbooth.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisestep.tollbooth.domain.TollBoothTransaction;
import com.wisestep.tollbooth.domain.Wallet;
import com.wisestep.tollbooth.repository.TollBoothTransactionRepository;
import com.wisestep.tollbooth.repository.WalletRepository;

@Service
public class TollBoothService {

	@Autowired
	private TollBoothTransactionRepository tollBoothTransactionRepository;

	@Autowired
	private WalletRepository walletRepository;

	@PostConstruct
	public void initDB() {
		List<Wallet> wallets = new ArrayList<Wallet>();
		wallets.add(new Wallet(1, 1000, "car", 100));
		wallets.add(new Wallet(2, 2000, "bus", 200));
		wallets.add(new Wallet(3, 500, "rickshaw", 0));
		walletRepository.saveAll(wallets);
	}

	public List<TollBoothTransaction> getTollBoothTransactions() {
		return tollBoothTransactionRepository.findAll();
	}

	public List<TollBoothTransaction> getTollBoothTransactions(int tollBoothId) {
		return tollBoothTransactionRepository.getTransactionsByTollBooth(tollBoothId);
	}

	@Transactional
	public void executeToll(int walletId, int tollBoothId) throws Exception {
		Wallet wallet = walletRepository.findById(walletId).get();
		double tollAmount = wallet.getTollamount();
		double balance = wallet.getBalance();
		if (balance >= tollAmount) {
			wallet.setBalance(balance - tollAmount);
		} else {
			throw new Exception("Insufficient balance.");
		}
		TollBoothTransaction transaction = new TollBoothTransaction();
		transaction.setTollamount(tollAmount);
		transaction.setTollBoothId(tollBoothId);
		transaction.setWalletId(walletId);
		transaction.setTimestamp(LocalDateTime.now());
		tollBoothTransactionRepository.save(transaction);
	}
}
