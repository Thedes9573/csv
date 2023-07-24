package com.csv.csv.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "demand")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class demandModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true,nullable = false)
	private Integer id;
	private String partNo;
	private String partDescription;
	private String category;
	private int multiplier;
	private String makeOrBuy;
	private float itemCost;
	private int leadTime;
	private int supplyDays;
	private float grossDemand;
	
 
	public demandModel() {
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getPartNo() {
		return partNo;
	}


	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}


	public String getPartDescription() {
		return partDescription;
	}


	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public int getMultiplier() {
		return multiplier;
	}


	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}


	public String getMakeOrBuy() {
		return makeOrBuy;
	}


	public void setMakeOrBuy(String makeOrBuy) {
		this.makeOrBuy = makeOrBuy;
	}


	public float getItemCost() {
		return itemCost;
	}


	public void setItemCost(float itemCost) {
		this.itemCost = itemCost;
	}


	public int getLeadTime() {
		return leadTime;
	}


	public void setLeadTime(int leadTime) {
		this.leadTime = leadTime;
	}


	public int getSupplyDays() {
		return supplyDays;
	}


	public void setSupplyDays(int supplyDays) {
		this.supplyDays = supplyDays;
	}


	public float getGrossDemand() {
		return grossDemand;
	}


	public void setGrossDemand(float grossDemand) {
		this.grossDemand = grossDemand;
	}
	




}


