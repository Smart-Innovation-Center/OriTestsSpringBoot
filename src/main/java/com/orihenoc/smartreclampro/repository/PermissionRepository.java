package com.orihenoc.smartreclampro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.orihenoc.smartreclampro.entity.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
	//Ceci pour retourner la liste des permissions en fonction du libellé
	List<PermissionEntity> findByLibelle(String libelle);
	//Ceci pour retourner la liste des permissions en fonction du statut actif ou incatif
	List<PermissionEntity> findByActive(Boolean active);
	//Ceci pour retrouver les permissions par l'id d'un rôle
	//List<PermissionEntity> findPermissionsByRoleId(Long roleId);
}
