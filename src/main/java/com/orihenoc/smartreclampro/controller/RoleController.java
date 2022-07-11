package com.orihenoc.smartreclampro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.orihenoc.smartreclampro.entity.RoleEntity;
import com.orihenoc.smartreclampro.repository.RoleRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class RoleController {
	@Autowired
	RoleRepository roleRepository;
	
	/*** LISTE DE TOUS LES RÔLES ***/
	
	@GetMapping("/roles")
	public ResponseEntity<List<RoleEntity>> getAllRoles(@RequestParam(required = false) String libelle) {
		try {
			//Recupères la liste des rôles et les met dans un tableau
			List<RoleEntity> roles = new ArrayList<RoleEntity>();
			if(libelle == null)
				//Recupère la liste de tous les rôles si le champ de recherche libellé est vide
				roleRepository.findAll().forEach(roles::add);
			else
				//Recupère la liste des rôles avec pour mot clé le libellé entré en recherche
				roleRepository.findByLibelle(libelle).forEach(roles::add);
			if (roles.isEmpty()) {
				//Affiche vide s'il n'y a pas de rôle
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			//Affiche la liste des rôles après les conditions sus-mentionnées
			return new ResponseEntity<>(roles, HttpStatus.OK);
		} catch (Exception e) {
			//En cas d'exception...
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*** LISTE DES RÔLES ACTIFS ***/
	
	@GetMapping("/roles/actifs")
	  public ResponseEntity<List<RoleEntity>> findActifs() {
	    try {
	    	//Récupère la liste des roles dont le champ actif est true
	      List<RoleEntity> roles = roleRepository.findByActive(true);
	      if (roles.isEmpty()) {
	    	  //Si aucun rôle n'est actif retourne vide
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      //Retourne la liste des roles actifs
	      return new ResponseEntity<>(roles, HttpStatus.OK);
	    } catch (Exception e) {
	    	//En cas d'exception...
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
/*** LISTE DES RÔLES INACTIFS ***/
	
	@GetMapping("/roles/inactifs")
	  public ResponseEntity<List<RoleEntity>> findInactifs() {
	    try {
	    	//Récupère la liste des roles dont le champ actif est false
	      List<RoleEntity> roles = roleRepository.findByActive(false);
	      if (roles.isEmpty()) {
	    	//Si aucun rôle n'est actif retourne vide
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      //Retourne la liste des roles actifs
	      return new ResponseEntity<>(roles, HttpStatus.OK);
	    } catch (Exception e) {
	    	//En cas d'exception...
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	/*** AFFICHER LES DETAILS D'UN RÔLE ***/
	
	@GetMapping("/roles/{id}")
	  public ResponseEntity<RoleEntity> getRoleById(@PathVariable("id") long id) {
		//Recherche le rôle par le paramètre id
	    Optional<RoleEntity> roleData = roleRepository.findById(id);
	    if (roleData.isPresent()) {
	    	//Retourne le rôle s'il existe
	      return new ResponseEntity<>(roleData.get(), HttpStatus.OK);
	    } else {
	    	//Retourne vide si le rôle n'existe pas
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	/*** CREER UN RÔLE ***/
	
	@PostMapping("/roles")
	  public ResponseEntity<RoleEntity> createRole(@RequestBody RoleEntity role) {
	    try {
	    	//Création du rôle selon le constructeur
	    	RoleEntity _role = roleRepository
	          .save(new RoleEntity(role.getLibelle(), role.getDescription(), true));
	      //Rôle créé
	      return new ResponseEntity<>(_role, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	//En cas d'exception...
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	/*** MODIFIER UN RÔLE ***/
	
	@PutMapping("/roles/{id}")
	  public ResponseEntity<RoleEntity> updateRole(@PathVariable("id") long id, @RequestBody RoleEntity role) {
		//Recherche le rôle par le paramètre id
		Optional<RoleEntity> roleData = roleRepository.findById(id);
	    if (roleData.isPresent()) {
	    	//On effectue la modification si le rôle existe
	    	RoleEntity _role = roleData.get();
	      _role.setLibelle(role.getLibelle());
	      _role.setDescription(role.getDescription());
	      //On sauvegarde la modification
	      return new ResponseEntity<>(roleRepository.save(_role), HttpStatus.OK);
	    } else {
	    	//Retourne vide si le rôle n'existe pas
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	/*** ACTIVER UN RÔLE ***/
	
	@PutMapping("/roles/activate/{id}")
	  public ResponseEntity<RoleEntity> activateRole(@PathVariable("id") long id, @RequestBody RoleEntity role) {
		//Recherche le rôle par le paramètre id
		Optional<RoleEntity> roleData = roleRepository.findById(id);
	    if (roleData.isPresent()) {
	    	//On active si le rôle existe
	    	RoleEntity _role = roleData.get();
	      _role.setActive(true);
	      //On sauvegarde la modification
	      return new ResponseEntity<>(roleRepository.save(_role), HttpStatus.OK);
	    } else {
	    	//Retourne vide si le rôle n'existe pas
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	/*** DESACTIVER UN RÔLE ***/
	
	@PutMapping("/roles/deactivate/{id}")
	  public ResponseEntity<RoleEntity> deactivateRole(@PathVariable("id") long id, @RequestBody RoleEntity role) {
		//Recherche le rôle par le paramètre id
		Optional<RoleEntity> roleData = roleRepository.findById(id);
	    if (roleData.isPresent()) {
	    	//On désactive si le rôle existe
	    	RoleEntity _role = roleData.get();
	      _role.setActive(false);
	      //On sauvegarde la modification
	      return new ResponseEntity<>(roleRepository.save(_role), HttpStatus.OK);
	    } else {
	    	//Retourne vide si le rôle n'existe pas
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	/*** SUPPRIMER UN RÔLE ***/
	
	@DeleteMapping("/roles/{id}")
	  public ResponseEntity<HttpStatus> deleteRole(@PathVariable("id") long id) {
	    try {
	      roleRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	/*** SUPPRIMER TOUS LES RÔLES ***/
	
	@DeleteMapping("/roles")
	  public ResponseEntity<HttpStatus> deleteAllRoles() {
	    try {
	    	//Supprimer tous les roles
	      roleRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	    	//En cas d'exception...
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
