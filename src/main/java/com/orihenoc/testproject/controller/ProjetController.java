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
import com.orihenoc.testproject.entity.Projet;
import com.orihenoc.testproject.repository.ProjetRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ProjetController {
	
	@Autowired
	ProjetRepository projetRepository;
	
	/*** LISTE DE TOUS LES RÔLES ***/
	
	@GetMapping("/projets")
	public ResponseEntity<List<Projet>> getAllProjets(@RequestParam(required = false) String title) {
		try {
			//Recupères la liste des rôles et les met dans un tableau
			List<Projet> projets = new ArrayList<Projet>();
			if(title == null)
				//Recupère la liste de tous les rôles si le champ de recherche libellé est vide
				projetRepository.findAll().forEach(projets::add);
			else
				//Recupère la liste des rôles avec pour mot clé le libellé entré en recherche
				projetRepository.findByTitle(title).forEach(projets::add);
			if (projets.isEmpty()) {
				//Affiche vide s'il n'y a pas de rôle
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			//Affiche la liste des rôles après les conditions sus-mentionnées
			return new ResponseEntity<>(projets, HttpStatus.OK);
		} catch (Exception e) {
			//En cas d'exception...
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	/*** AFFICHER LES DETAILS D'UN RÔLE ***/
	
	@GetMapping("/projets/{projet_id}")
	  public ResponseEntity<Projet> getProjetById(@PathVariable("projet_id") long projet_id) {
		//Recherche le rôle par le paramètre id
	    Optional<Projet> projet = projetRepository.findById(projet_id);
	    if (projet.isPresent()) {
	    	//Retourne le rôle s'il existe
	      return new ResponseEntity<>(projet.get(), HttpStatus.OK);
	    } else {
	    	//Retourne vide si le rôle n'existe pas
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	/*** CREER UN RÔLE ***/
	
	@PostMapping("/projets")
	  public ResponseEntity<Projet> createProjet(@RequestBody Projet projet) {
	    try {
	    	//Création du rôle selon le constructeur
	    	Projet _projet = projetRepository
	          .save(new Projet(projet.getTitle()));
	      //Rôle créé
	      return new ResponseEntity<>(_projet, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	//En cas d'exception...
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	/*** MODIFIER UN RÔLE ***/
	
	@PutMapping("/projets/{projet_id}")
	  public ResponseEntity<Projet> updateRole(@PathVariable("projet_id") long projet_id, @RequestBody Projet projet) {
		//Recherche le rôle par le paramètre id
		Optional<Projet> projetData = projetRepository.findById(projet_id);
	    if (projetData.isPresent()) {
	    	//On effectue la modification si le rôle existe
	    	Projet _projet = projetData.get();
	    	_projet.setTitle(projet.getTitle());
	      //On sauvegarde la modification
	      return new ResponseEntity<>(projetRepository.save(_projet), HttpStatus.OK);
	    } else {
	    	//Retourne vide si le rôle n'existe pas
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	
	/*** SUPPRIMER UN RÔLE ***/
	
	@DeleteMapping("/projets/{projet_id}")
	  public ResponseEntity<HttpStatus> deleteRole(@PathVariable("projet_id") long projet_id) {
	    try {
	      projetRepository.deleteById(projet_id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	/*** SUPPRIMER TOUS LES RÔLES ***/
	
	@DeleteMapping("/projets")
	  public ResponseEntity<HttpStatus> deleteAllRoles() {
	    try {
	    	//Supprimer tous les roles
	      projetRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	    	//En cas d'exception...
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

}
