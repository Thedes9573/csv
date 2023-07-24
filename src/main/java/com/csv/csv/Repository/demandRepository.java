package com.csv.csv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csv.csv.Model.demandModel;

@Repository
public interface demandRepository extends JpaRepository<demandModel, Integer>{

}
