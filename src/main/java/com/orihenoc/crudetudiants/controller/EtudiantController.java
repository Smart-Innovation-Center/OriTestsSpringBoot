package com.orihenoc.crudetudiants.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.orihenoc.crudetudiants.entity.Etudiant;
import com.orihenoc.crudetudiants.service.EtudiantService;

@Controller
public class EtudiantController {

	private EtudiantService etudiantService;

	public EtudiantController(EtudiantService etudiantService) {
		super();
		this.etudiantService = etudiantService;
	}
	
	@GetMapping("/etudiants")
	public String listeEtudiants(Model model) {
		model.addAttribute("etudiants", etudiantService.getAllEtudiants());
		return "etudiants";
	}
	
	@GetMapping("/etudiants/nouveau")
	public String nouveauEtudiant(Model model) {
		Etudiant etudiant = new Etudiant();
		model.addAttribute("etudiant", etudiant);
		return "nouveauEtudiant";
	}
}
