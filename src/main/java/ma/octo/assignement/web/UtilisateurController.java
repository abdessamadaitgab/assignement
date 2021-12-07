package ma.octo.assignement.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;

@RequestMapping("/utilisateurs")

@RestController
public class UtilisateurController {
	
	 @Autowired
	    private UtilisateurRepository utilisateurRepository;
	 @Autowired
	    private CompteRepository compteRepository;
	 
	 @GetMapping("lister_utilisateurs")
	    List<Utilisateur> loadAllUtilisateur() {
	        List<Utilisateur> all = utilisateurRepository.findAll();

	        if (CollectionUtils.isEmpty(all)) {
	            return null;
	        } else {
	            return all;
	      
	        }
	    }

	    @GetMapping("lister_comptes")
	    List<Compte> loadAllCompte() {
	        List<Compte> all = compteRepository.findAll();

	        if (CollectionUtils.isEmpty(all)) {
	            return null;
	        } else {
	            return all;
	        }
	    }

}
