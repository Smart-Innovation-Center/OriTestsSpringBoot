package com.orihenoc.testproject.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "projets")
public class Projet extends AuditModel {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "projet_id")
    @GeneratedValue
    private Long projetId;

    @Column(name = "title")
    private String title;

    @JsonIgnore
    @ManyToMany(mappedBy = "projets", cascade = { CascadeType.ALL })
    private Set<Employe> employes = new HashSet<Employe>();
    
    public Projet() {
        super();
    }

    public Projet(String title) {
        this.title = title;
    }

    public Long getProjetId() {
        return projetId;
    }

    public void setProjetId(Long projetId) {
        this.projetId = projetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Employe> getEmployes() {
        return employes;
    }

    public void setEmployees(Set<Employe> employes) {
        this.employes = employes;
    }
}
