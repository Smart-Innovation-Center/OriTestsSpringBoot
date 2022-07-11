package com.orihenoc.crudetudiants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.orihenoc.crudetudiants.entity.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

}
