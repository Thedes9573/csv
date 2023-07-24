package com.csv.csv.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csv.csv.Model.demandModel;
import com.csv.csv.Repository.demandRepository;

@Service
public class demandService {
	@Autowired
	private demandRepository  demandR;
	
	public void save (demandModel demand) {
		demandR.save(demand);
	}
	public void saveMany (List<demandModel> demands) {
		demandR.saveAll(demands);
	}
	public List<demandModel> recover() {
		List<demandModel> demands=demandR.findAll();
		return demands;
	}

}
