package com.orihenoc.crudetudiants.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "etudiants")
public class Etudiant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nom", nullable = false)
	private String nom;
	
	//@Column(name = "prenoms")
	private String prenoms;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	public Etudiant(String nom, String prenoms, String email) {
		super();
		this.nom = nom;
		this.prenoms = prenoms;
		this.email = email;
	}
	public Etudiant() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenoms() {
		return prenoms;
	}
	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
