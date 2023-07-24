package com.csv.csv.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csv.csv.Model.targetModel;
import com.csv.csv.Repository.targetRepository;

@Service
public class targetService {
	@Autowired
	private targetRepository  targetR;
	
	public void save (targetModel target) {
		targetR.save(target);
	}
	public void saveMany (List<targetModel> targets) {
		targetR.saveAll(targets);
	}
	public List<targetModel> recover() {
		List<targetModel> targets=targetR.findAll();
		return targets;
	}

}
