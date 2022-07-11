package com.orihenoc.smartreclampro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orihenoc.smartreclampro.entity.PermissionEntity;
import com.orihenoc.smartreclampro.repository.PermissionRepository;
import com.orihenoc.smartreclampro.repository.RoleRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AutorisationController {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PermissionRepository permissionRepository;

	/*** LISTE DES PERMISSIONS PAR ROLE ***/
	
//	@GetMapping("/roles/{id}/permissions")
//	  public ResponseEntity<List<PermissionEntity>> getAllAutorisations(@PathVariable(value = "roleId") Long roleId) {
//	    if (!roleRepository.existsById(roleId)) {
//	    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	    }
//	    List<PermissionEntity> permissions = permissionRepository.findPermissionsByRoleId(roleId);
//	    return new ResponseEntity<>(permissions, HttpStatus.OK);
//	  }
}
