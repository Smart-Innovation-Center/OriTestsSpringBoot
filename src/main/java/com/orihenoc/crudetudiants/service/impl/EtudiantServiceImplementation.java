package com.orihenoc.crudetudiants.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orihenoc.crudetudiants.entity.Etudiant;
import com.orihenoc.crudetudiants.repository.EtudiantRepository;
import com.orihenoc.crudetudiants.service.EtudiantService;

@Service
public class EtudiantServiceImplementation implements EtudiantService {
	
	private EtudiantRepository etudiantRepository;
	
	public EtudiantServiceImplementation(EtudiantRepository etudiantRepository) {
		super();
		this.etudiantRepository = etudiantRepository;
	}


	@Override
	public List<Etudiant> getAllEtudiants(){
		return etudiantRepository.findAll();
	}
}
