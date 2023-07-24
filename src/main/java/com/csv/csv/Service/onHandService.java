package com.csv.csv.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csv.csv.Model.onHandModel;
import com.csv.csv.Repository.onHandRepository;

@Service
public class onHandService {
	@Autowired
	private onHandRepository onHandR;
	
	public void save (onHandModel inventory) {
		onHandR.save(inventory);
	}
	public void saveMany (List<onHandModel> inventories) {
		onHandR.saveAll(inventories);
	}
	public List<onHandModel> recover() {
		List<onHandModel> inventories=onHandR.findAll();
		return inventories;
	}
}
