package com.orihenoc.testproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orihenoc.testproject.entity.Employe;
import com.orihenoc.testproject.repository.EmployeRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class EmployeController {

	@Autowired
	EmployeRepository employeRepository;
	
	/*** LISTE DE TOUS LES RÔLES ***/
	
	@GetMapping("/employes")
	public ResponseEntity<List<Employe>> getAllEmployes(@RequestParam(required = false) String firstName, String lastName) {
		try {
			//Recupères la liste des rôles et les met dans un tableau
			List<Employe> employes = new ArrayList<Employe>();
			//if(firstName == null & lastName == null)
				//Recupère la liste de tous les rôles si le champ de recherche libellé est vide
				employeRepository.findAll().forEach(employes::add);
			//else
				//Recupère la liste des rôles avec pour mot clé le libellé entré en recherche
				//employeRepository.findByName(firstName, lastName).forEach(employes::add);
			if (employes.isEmpty()) {
				//Affiche vide s'il n'y a pas de rôle
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			//Affiche la liste des rôles après les conditions sus-mentionnées
			return new ResponseEntity<>(employes, HttpStatus.OK);
		} catch (Exception e) {
			//En cas d'exception...
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	/*** AFFICHER LES DETAILS D'UN RÔLE ***/
	
	@GetMapping("/employes/{employe_id}")
	  public ResponseEntity<Employe> getProjetById(@PathVariable("employe_id") long employe_id) {
		//Recherche le rôle par le paramètre id
	    Optional<Employe> employe = employeRepository.findById(employe_id);
	    if (employe.isPresent()) {
	    	//Retourne le rôle s'il existe
	      return new ResponseEntity<>(employe.get(), HttpStatus.OK);
	    } else {
	    	//Retourne vide si le rôle n'existe pas
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	/*** CREER UN RÔLE ***/
	
	@PostMapping("/employes")
	  public ResponseEntity<Employe> createEmploye(@RequestBody Employe employe) {
	    try {
	    	//Création du rôle selon le constructeur
	    	Employe _employe = employeRepository
	          .save(new Employe(employe.getFirstName(), employe.getLastName()));
	      //Rôle créé
	      return new ResponseEntity<>(_employe, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	//En cas d'exception...
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	/*** MODIFIER UN RÔLE ***/
	
	@PutMapping("/employes/{employe_id}")
	  public ResponseEntity<Employe> updateEmploye(@PathVariable("employe_id") long employe_id, @RequestBody Employe employe) {
		//Recherche le rôle par le paramètre id
		Optional<Employe> employeData = employeRepository.findById(employe_id);
	    if (employeData.isPresent()) {
	    	//On effectue la modification si le rôle existe
	    	Employe _employe = employeData.get();
	      _employe.setFirstName(employe.getFirstName());
	      _employe.setLastName(employe.getLastName());
	      //On sauvegarde la modification
	      return new ResponseEntity<>(employeRepository.save(_employe), HttpStatus.OK);
	    } else {
	    	//Retourne vide si le rôle n'existe pas
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	
	/*** SUPPRIMER UN RÔLE ***/
	
	@DeleteMapping("/employes/{employe_id}")
	  public ResponseEntity<HttpStatus> deleteEmploye(@PathVariable("employe_id") long employe_id) {
	    try {
	      employeRepository.deleteById(employe_id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	/*** SUPPRIMER TOUS LES RÔLES ***/
	
	@DeleteMapping("/employes")
	  public ResponseEntity<HttpStatus> deleteAllEmployes() {
	    try {
	    	//Supprimer tous les roles
	      employeRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	    	//En cas d'exception...
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
