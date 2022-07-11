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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "libelle", nullable = false)
	private String libelle;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "active")
	private boolean active;
	
	@ManyToMany (fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      })
	@JoinTable(name = "autorisations",
		        joinColumns = { @JoinColumn(name = "role_id") },
		        inverseJoinColumns = { @JoinColumn(name = "permission_id") })
	
	private Set<PermissionEntity> permissions = new HashSet<>();
	
	public RoleEntity(String libelle, String description, boolean active) {
		super();
		this.libelle = libelle;
		this.description = description;
		this.active = active;
	}
	
	public RoleEntity() {
		
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
	
	public void addPermission(PermissionEntity permission) {
	    this.permissions.add(permission);
	    permission.getRoles().add(this);
	  }
//	  
//	  public void removePermission(long permissionId) {
//	    PermissionEntity permission = this.permissions.stream().filter(p -> p.getId() == permissionId).findFirst().orElse(null);
//	    if (permission != null) {
//	      this.permissions.remove(permission);
//	      permission.getRoles().remove(this);
//	    }
//	  }
	
}
