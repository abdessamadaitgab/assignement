package ma.octo.assignement;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.repository.VirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class AssignementApplication implements CommandLineRunner {
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private VirementRepository virementRepository;
	@Autowired
	private VersementRepository versementRepository;

	public static void main(String[] args) {
		SpringApplication.run(AssignementApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		//ajout utilisateur 1
		Utilisateur utilisateur1 = new Utilisateur();
		utilisateur1.setUsername("user1");
		utilisateur1.setLastname("last1");
		utilisateur1.setFirstname("first1");
		utilisateur1.setGender("Male");
		//ajouter date du birthday
		Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1998");
        utilisateur1.setBirthdate(d1);
		utilisateurRepository.save(utilisateur1);

		//ajout utilisateur 2
		Utilisateur utilisateur2 = new Utilisateur();
		utilisateur2.setUsername("user2");
		utilisateur2.setLastname("last2");
		utilisateur2.setFirstname("first2");
		utilisateur2.setGender("Female");
		//ajouter date du birthday
		Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse("22/10/2000");
        utilisateur2.setBirthdate(d2);
        utilisateurRepository.save(utilisateur2);
        
        //créer compte 1 pour l'utilisateur 1
		Compte compte1 = new Compte();
		compte1.setNumeroCompte("010000A000001000");
		compte1.setRib("RIB1");
		compte1.setSolde(BigDecimal.valueOf(200000L));
		compte1.setUtilisateur(utilisateur1);

		compteRepository.save(compte1);

		//créer compte 2 pour l'utilisateur 2
		Compte compte2 = new Compte();
		compte2.setNumeroCompte("010000B025001000");
		compte2.setRib("RIB2");
		compte2.setSolde(BigDecimal.valueOf(140000L));
		compte2.setUtilisateur(utilisateur2);

		compteRepository.save(compte2);

		//Virement pour le test
		Virement v = new Virement();
		v.setMontantVirement(BigDecimal.TEN);
		v.setCompteBeneficiaire(compte2);
		v.setCompteEmetteur(compte1);
		v.setDateExecution(new Date());
		v.setMotifVirement("Assignment 2021");

		virementRepository.save(v);
		
		//Versement pour le test
		Versement versement = new Versement();
		versement.setNomPrenomEmetteur("abdessamad aitgab");
		versement.setCompteBeneficiaire(compte2);
		versement.setDateExecution(new Date());
		versement.setMontantVersement(BigDecimal.valueOf(99999));
		versement.setMotifVersement("versement familial");
		versementRepository.save(versement);
		
		
	}
}
