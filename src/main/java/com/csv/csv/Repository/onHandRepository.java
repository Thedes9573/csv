package com.csv.csv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csv.csv.Model.onHandModel;

@Repository
public interface onHandRepository extends JpaRepository<onHandModel, Integer>{

}
