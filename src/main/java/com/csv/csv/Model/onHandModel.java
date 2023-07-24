package com.csv.csv.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class onHandModel {
	private String subInventory;
	private String item;
	private String item_Description;
	private String Customer;
	private String sanmina_Stock_Locators;
	private float quantity;
	private float target_Cost;
	private float extended_Target_Cost;
	private float sTD_Cost;
	private float extended_STD_Cost;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true,nullable = false)
	private Integer id;
 
	public onHandModel() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubInventory() {
		return subInventory;
	}

	public void setSubInventory(String subInventory) {
		this.subInventory = subInventory;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getItem_Description() {
		return item_Description;
	}

	public void setItem_Description(String item_Description) {
		this.item_Description = item_Description;
	}

	public String getCustomer() {
		return Customer;
	}

	public void setCustomer(String customer) {
		Customer = customer;
	}

	public String getSanmina_Stock_Locators() {
		return sanmina_Stock_Locators;
	}

	public void setSanmina_Stock_Locators(String sanmina_Stock_Locators) {
		this.sanmina_Stock_Locators = sanmina_Stock_Locators;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public float getTarget_Cost() {
		return target_Cost;
	}

	public void setTarget_Cost(float target_Cost) {
		this.target_Cost = target_Cost;
	}

	public float getExtended_Target_Cost() {
		return extended_Target_Cost;
	}

	public void setExtended_Target_Cost(float extended_Target_Cost) {
		this.extended_Target_Cost = extended_Target_Cost;
	}

	public float getsTD_Cost() {
		return sTD_Cost;
	}

	public void setsTD_Cost(float sTD_Cost) {
		this.sTD_Cost = sTD_Cost;
	}

	public float getExtended_STD_Cost() {
		return extended_STD_Cost;
	}

	public void setExtended_STD_Cost(float extended_STD_Cost) {
		this.extended_STD_Cost = extended_STD_Cost;
	}
}