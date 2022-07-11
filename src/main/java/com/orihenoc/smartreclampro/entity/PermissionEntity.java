package com.orihenoc.smartreclampro.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "permissions")
public class PermissionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "libelle", nullable = false)
	private String libelle;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "active")
	private boolean active;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      },
		      mappedBy = "permissions")
		  @JsonIgnore
		  private Set<RoleEntity> roles = new HashSet<>();

	public PermissionEntity(String libelle, String description, boolean active) {
		super();
		this.libelle = libelle;
		this.description = description;
		this.active = active;
	}
	
	public PermissionEntity() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	 public Set<RoleEntity> getRoles() {
		 return roles;
	}
	 public void setRoles(Set<RoleEntity> roles) {
	    this.roles = roles;
	}  
}
