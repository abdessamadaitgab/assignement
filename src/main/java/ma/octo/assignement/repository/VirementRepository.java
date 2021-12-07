package ma.octo.assignement.repository;

import ma.octo.assignement.domain.Virement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VirementRepository extends JpaRepository<Virement, Long> {
	public List <Virement> findAll();
}
