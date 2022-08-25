package com.wisestep.tollbooth.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Wallet {
	
	@Id
	private int id;
	private double balance;
	private String vehicleType;
	private double tollamount;

}
