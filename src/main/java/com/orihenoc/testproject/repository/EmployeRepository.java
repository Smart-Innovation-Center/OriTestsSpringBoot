package com.orihenoc.testproject.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orihenoc.testproject.entity.Employe;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {

	//Ceci pour retourner la liste des rôles en fonction du libellé
			//List<Employe> findByName(String firstName, String lastName);
}
