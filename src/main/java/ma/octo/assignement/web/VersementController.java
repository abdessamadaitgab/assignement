package ma.octo.assignement.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.service.AutiService;

@RequestMapping("/versements")
@RestController
public class VersementController {
	  public static final int MONTANT_MAXIMAL = 10000;

	    Logger LOGGER = LoggerFactory.getLogger(VersementController.class);

	    @Autowired
	    private CompteRepository compterepository;
	    @Autowired
	    private VersementRepository versementrepository;
	    @Autowired
	    private AutiService autiservice;
	    
	    
	    //retourner la liste des versements 
	    @GetMapping("lister_versements")
	    List<Versement> loadAll() {
	        List<Versement> listversements = versementrepository.findAll();

	        if (CollectionUtils.isEmpty(listversements)) {
	            return null;
	        } else {
	            return listversements;
	        }
	    }
	    //afficher la liste des versements envoyées à un rib donné
	    @GetMapping("/lister_versements/{rib}")
	    public List<Versement> getListVersementByRib(@PathVariable String rib) {
	    	Compte c=new Compte();
	    	c = compterepository.findByRib(rib);
	    	return versementrepository.findBycompteBeneficiaire(c);
	    }
	    
	    // faire un versement

	    @PostMapping("/executerVersement")
	    @ResponseStatus(HttpStatus.CREATED)
	    public void createTransaction(@RequestBody VersementDto versementDto)
	            throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {
	        Compte compteBenificiaire = compterepository
	                .findByRib(versementDto.getRib());
	        
	        if (compteBenificiaire == null) {
	            System.out.println("Compte Beneficiaire Non existant");
	            throw new CompteNonExistantException("Compte Beneficiaire Non existant");
	        }
	        if(versementDto.getNomPrenomEmmetteur().length()<0) {
	        	System.out.println("nom vide");
	            throw new TransactionException("nom vide");
	        	
	        }

	        if (versementDto.getMontantVersement().equals(null)) {
	            System.out.println("Montant versement vide");
	            throw new TransactionException("Montant versement vide");
	        } else if (versementDto.getMontantVersement().intValue() == 0) {
	            System.out.println("Montant versement vide");
	            throw new TransactionException("Montant versement vide");
	        } else if (versementDto.getMontantVersement().intValue() < 10) {
	            System.out.println("Montant minimal de versement non atteint");
	            throw new TransactionException("Montant minimal de versement non atteint");
	        } else if (versementDto.getMontantVersement().intValue() > MONTANT_MAXIMAL) {
	            System.out.println("Montant maximal de versement dépassé");
	            throw new TransactionException("Montant maximal de versement dépassé");
	        }

	        if (versementDto.getMotif().length() < 0) {
	            System.out.println("Motif vide");
	            throw new TransactionException("Motif vide");
	        }


	        compteBenificiaire.setSolde(new BigDecimal(compteBenificiaire.getSolde().intValue() + versementDto.getMontantVersement().intValue()));
	        compterepository.save(compteBenificiaire);

	        Versement versement = new Versement();
	        versement.setDateExecution(versementDto.getDate());
	        versement.setCompteBeneficiaire(compteBenificiaire);
	        versement.setNomPrenomEmetteur(versementDto.getNomPrenomEmmetteur());
	        versement.setMontantVersement(versementDto.getMontantVersement());
	        // motif manquant
	        versement.setMotifVersement(versementDto.getMotif());

	        versementrepository.save(versement);

	        autiservice.auditVersement("Versement du monsieur " +versementDto.getNomPrenomEmmetteur()  + " vers le rib " + versementDto
	                        .getRib() + " d'un montant de " + versementDto.getMontantVersement()
	                        .toString());
	    }
	    
	    // supprimer un versement (probablement dans le cas d'un erreur)
		@DeleteMapping("/deleteVersement/{id}")
	 	public ResponseEntity<Map<String, Boolean>> deleteVersement(@PathVariable Long id){
	 		Versement v = versementrepository.getById(id);
	 		
	 		versementrepository.deleteById(v.getId());
	 		Map<String, Boolean> response = new HashMap<>();
	 		response.put("deleted", Boolean.TRUE);
	 		return ResponseEntity.ok(response);
	 		
	 	}
	    

}
