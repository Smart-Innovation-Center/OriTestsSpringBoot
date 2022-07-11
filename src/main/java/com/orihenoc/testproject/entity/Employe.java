package com.orihenoc.testproject.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table(name = "employes")
public class Employe extends AuditModel {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "employe_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @JsonIgnore
    @ManyToMany(cascade = {
        CascadeType.ALL
    })
    @JoinTable(
        name = "employes_projets",
        joinColumns = {
            @JoinColumn(name = "employe_id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "project_id")
        }
    )
    Set<Projet> projets = new HashSet<Projet> ();


    public Employe() {
        super();
    }

    public Employe(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employe(String firstName, String lastName, Set <Projet> projets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.projets = projets;
    }


    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set <Projet> getProjects() {
        return projets;
    }

    public void setProjects(Set <Projet> projets) {
        this.projets = projets;
    }
    
    public void addProjet(Projet projet) {
        this.projets.add(projet);
        projet.getEmployes().add(this);
      }
      
}
