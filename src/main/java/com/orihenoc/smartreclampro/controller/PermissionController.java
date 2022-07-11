package com.orihenoc.smartreclampro.controller;

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
import com.orihenoc.smartreclampro.entity.PermissionEntity;
import com.orihenoc.smartreclampro.repository.PermissionRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PermissionController {
	@Autowired
	PermissionRepository permissionRepository;
	
	/*** LISTE DE TOUTES LES PERMISSIONS ***/
	
	@GetMapping("/permissions")
	public ResponseEntity<List<PermissionEntity>> getAllPermissions(@RequestParam(required = false) String libelle) {
		try {
			//Recupères la liste des permissions et les met dans un tableau
			List<PermissionEntity> permissions = new ArrayList<PermissionEntity>();
			if(libelle == null)
				//Recupère la liste de toutes les permissions si le champ de recherche libellé est vide
				permissionRepository.findAll().forEach(permissions::add);
			else
				//Recupère la liste des permissions avec pour mot clé le libellé entré en recherche
				permissionRepository.findByLibelle(libelle).forEach(permissions::add);
			if (permissions.isEmpty()) {
				//Affiche vide s'il n'y a pas de permission
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			//Affiche la liste des permissions après les conditions sus-mentionnées
			return new ResponseEntity<>(permissions, HttpStatus.OK);
		} catch (Exception e) {
			//En cas d'exception...
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*** LISTE DES PERMISSIONS ACTIVES ***/
	
	@GetMapping("/permissions/actifs")
	  public ResponseEntity<List<PermissionEntity>> findActifs() {
	    try {
	    	//Récupère la liste des permissions dont le champ actif est true
	      List<PermissionEntity> permissions = permissionRepository.findByActive(true);
	      if (permissions.isEmpty()) {
	    	  //Si aucune permission n'est active retourne vide
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      //Retourne la liste des roles actifs
	      return new ResponseEntity<>(permissions, HttpStatus.OK);
	    } catch (Exception e) {
	    	//En cas d'exception...
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	/*** LISTE DES PERMISSIONS INACTIVES ***/
	
	@GetMapping("/permissions/inactifs")
	  public ResponseEntity<List<PermissionEntity>> findInactifs() {
	    try {
	    	//Récupère la liste des permissions dont le champ actif est false
	      List<PermissionEntity> permissions = permissionRepository.findByActive(false);
	      if (permissions.isEmpty()) {
	    	//Si aucune permission n'est active retourne vide
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      //Retourne la liste des permissions actives
	      return new ResponseEntity<>(permissions, HttpStatus.OK);
	    } catch (Exception e) {
	    	//En cas d'exception...
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	/*** AFFICHER LES DETAILS D'UNE PERMISSION ***/
	
	@GetMapping("/permissions/{id}")
	  public ResponseEntity<PermissionEntity> getRoleById(@PathVariable("id") long id) {
		//Recherche la permission par le paramètre id
	    Optional<PermissionEntity> permissionData = permissionRepository.findById(id);
	    if (permissionData.isPresent()) {
	    	//Retourne la permission si elle existe
	      return new ResponseEntity<>(permissionData.get(), HttpStatus.OK);
	    } else {
	    	//Retourne vide si la permission n'existe pas
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	/*** CREER UNE PERMISSION ***/
	
	@PostMapping("/permissions")
	  public ResponseEntity<PermissionEntity> createPermission(@RequestBody PermissionEntity permission) {
	    try {
	    	//Création de la permission selon le constructeur
	    	PermissionEntity _permission = permissionRepository
	          .save(new PermissionEntity(permission.getLibelle(), permission.getDescription(), true));
	      //Permission créée
	      return new ResponseEntity<>(_permission, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	//En cas d'exception...
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	/*** MODIFIER UNE PERMISSION ***/
	
	@PutMapping("/permissions/{id}")
	  public ResponseEntity<PermissionEntity> updatePermission(@PathVariable("id") long id, @RequestBody PermissionEntity permission) {
		//Recherche la permission par le paramètre id
		Optional<PermissionEntity> permissionData = permissionRepository.findById(id);
	    if (permissionData.isPresent()) {
	    	//On effectue la modification si la permission existe
	    	PermissionEntity _permission = permissionData.get();
	      _permission.setLibelle(permission.getLibelle());
	      _permission.setDescription(permission.getDescription());
	      //On sauvegarde la modification
	      return new ResponseEntity<>(permissionRepository.save(_permission), HttpStatus.OK);
	    } else {
	    	//Retourne vide si la permission n'existe pas
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	/*** ACTIVER UNE PERMISSION ***/
	
	@PutMapping("/permissions/activate/{id}")
	  public ResponseEntity<PermissionEntity> activatePermission(@PathVariable("id") long id, @RequestBody PermissionEntity permission) {
		//Recherche la permission par le paramètre id
		Optional<PermissionEntity> permissionData = permissionRepository.findById(id);
	    if (permissionData.isPresent()) {
	    	//On active si la permission existe
	    	PermissionEntity _permission = permissionData.get();
	      _permission.setActive(true);
	      //On sauvegarde la modification
	      return new ResponseEntity<>(permissionRepository.save(_permission), HttpStatus.OK);
	    } else {
	    	//Retourne vide si la permission n'existe pas
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	/*** DESACTIVER UNE PERMISSION ***/
	
	@PutMapping("/permissions/deactivate/{id}")
	  public ResponseEntity<PermissionEntity> deactivatePermission(@PathVariable("id") long id, @RequestBody PermissionEntity permission) {
			//Recherche la permission par le paramètre id
			Optional<PermissionEntity> permissionData = permissionRepository.findById(id);
		    if (permissionData.isPresent()) {
		    	//On désactive si la permission existe
		    	PermissionEntity _permission = permissionData.get();
		      _permission.setActive(false);
		      //On sauvegarde la modification
		      return new ResponseEntity<>(permissionRepository.save(_permission), HttpStatus.OK);
		    } else {
		    	//Retourne vide si la permission n'existe pas
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		  }
	
	/*** SUPPRIMER UNE PERMISSION ***/
	
	@DeleteMapping("/permissions/{id}")
	  public ResponseEntity<HttpStatus> deletePermission(@PathVariable("id") long id) {
	    try {
	      permissionRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	/*** SUPPRIMER TOUTES LES PERMISSIONS ***/
	
	@DeleteMapping("/permissions")
	  public ResponseEntity<HttpStatus> deleteAllPermissions() {
	    try {
	    	//Supprimer toutes les permissions
	      permissionRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	    	//En cas d'exception...
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
