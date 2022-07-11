package com.orihenoc.testproject.controller;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.orihenoc.testproject.entity.Projet;
import com.orihenoc.testproject.repository.EmployeRepository;
import com.orihenoc.testproject.repository.ProjetRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class EmployeProjectController {

	@Autowired
	EmployeRepository employeRepository;
	
	@Autowired
	ProjetRepository projetRepository;
	
//	@GetMapping("/employes/{employe_id}/projets")
//	  public ResponseEntity<List<Projet>> getAllProjets(@PathVariable(value = "employe_id") Long employe_id) {
//	    if (!employeRepository.existsById(employe_id)) {
//	    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	    }
//	    List<Projet> projets = projetRepository.findProjetsByEmployeId(employe_id);
//	    return new ResponseEntity<>(projets, HttpStatus.OK);
//	  }
	
	@PostMapping("/employes/{employe_id}/projets")
	  public ResponseEntity<Projet> addProjet(@PathVariable(value = "employe_id") Long employe_id, @RequestBody Projet projetRequest) {
		Projet projet = employeRepository.findById(employe_id).map(employe -> {
	      long projetId = projetRequest.getProjetId();
	      
	      // projet existed
	      if (projetId != 0L) {
	    	  Projet _projet = projetRepository.findById(projetId)
	            .orElseThrow();
	        employe.addProjet(_projet);
	        employeRepository.save(employe);
	        return _projet;
	      }
	      
	      // add and create new projet
	      employe.addProjet(projetRequest);
	      return projetRepository.save(projetRequest);
	    }).orElseThrow();
	    return new ResponseEntity<>(projet, HttpStatus.CREATED);
	  }
	
}
