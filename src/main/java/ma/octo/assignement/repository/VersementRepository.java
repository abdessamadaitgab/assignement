package ma.octo.assignement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Versement;

public interface VersementRepository extends JpaRepository<Versement, Long> {
	List<Versement> findBycompteBeneficiaire(Compte c);

}
