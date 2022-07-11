package com.orihenoc.testproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orihenoc.testproject.entity.Employe;
//import com.orihenoc.testproject.entity.Employe;
import com.orihenoc.testproject.entity.Projet;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long>{

	//Ceci pour retourner la liste des rôles en fonction du libellé
		List<Projet> findByTitle(String title);
		//Ceci pour retourner la liste des rôles en fonction du statut actif ou incatif
		//List<Projet> findByActive(Boolean active);
		//Ceci pour retrouver les rôles par l'id d'une permission
		//List<Employe> findProjetsByEmployeId(Long employe_id);
}
	