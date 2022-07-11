package com.orihenoc.smartreclampro.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.orihenoc.smartreclampro.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	
	//Ceci pour retourner la liste des rôles en fonction du libellé
	List<RoleEntity> findByLibelle(String libelle);
	//Ceci pour retourner la liste des rôles en fonction du statut actif ou incatif
	List<RoleEntity> findByActive(Boolean active);
	//Ceci pour retrouver les rôles par l'id d'une permission
	//List<RoleEntity> findRolesByPermissionId(Long permissionId);
}
