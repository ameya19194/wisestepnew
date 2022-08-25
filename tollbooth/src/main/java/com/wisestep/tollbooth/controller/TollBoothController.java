package com.wisestep.tollbooth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wisestep.tollbooth.domain.TollBoothTransaction;
import com.wisestep.tollbooth.service.TollBoothService;

@RestController
public class TollBoothController {

	@Autowired
	private TollBoothService tollBoothService;

	@GetMapping("/totaltollamount")
	public double getTotalAmountCollected() {
		List<TollBoothTransaction> transactions = tollBoothService.getTollBoothTransactions();
		double total = 0;
		total = transactions.stream().map(t -> t.getTollamount()).reduce(0.0, Double::sum);
		return total;
	}

	@GetMapping("/totaltollamount/{tollBoothId}")
	public double getTotalAmountCollectedByTollBoothId(@PathVariable int tollBoothId) {
		List<TollBoothTransaction> transactions = tollBoothService.getTollBoothTransactions(tollBoothId);
		double total = 0;
		total = transactions.stream().map(t -> t.getTollamount()).reduce(0.0, Double::sum);
		return total;
	}

	@GetMapping("/executetoll/{walletId}/{tollBoothId}")
	public void executeToll(@PathVariable int walletId, @PathVariable int tollBoothId) {
		try {
			tollBoothService.executeToll(walletId, tollBoothId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
