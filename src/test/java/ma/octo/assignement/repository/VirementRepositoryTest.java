package ma.octo.assignement.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Virement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class VirementRepositoryTest {

  @Autowired
  private VirementRepository virementRepository;
  @Autowired
  private CompteRepository compteRepository;

  @Test
  public void findOne() throws ParseException {
	  //given
	  Virement v =new Virement();
	  Utilisateur utilisateur1 = new Utilisateur();
		utilisateur1.setUsername("user1");
		utilisateur1.setLastname("last1");
		utilisateur1.setFirstname("first1");
		utilisateur1.setGender("Male");
	    Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1998");
        utilisateur1.setBirthdate(d1);

		Utilisateur utilisateur2 = new Utilisateur();
		utilisateur2.setUsername("user2");
		utilisateur2.setLastname("last2");
		utilisateur2.setFirstname("first2");
		utilisateur2.setGender("Female");
		Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse("22/10/2000");
      utilisateur2.setBirthdate(d2);
      
		Compte compte1 = new Compte();
		compte1.setNumeroCompte("010000A000001000");
		compte1.setRib("RIB1");
		compte1.setSolde(BigDecimal.valueOf(200000L));


		Compte compte2 = new Compte();
		compte2.setNumeroCompte("010000B025001000");
		compte2.setRib("RIB2");
		compte2.setSolde(BigDecimal.valueOf(140000L));
		compte2.setUtilisateur(utilisateur2);

        v.setId((long) 1);
		v.setMontantVirement(BigDecimal.TEN);
		v.setCompteBeneficiaire(compte2);
		v.setCompteEmetteur(compte1);
		v.setDateExecution(new Date());
		v.setMotifVirement("Assignment 2021");
		virementRepository.save(v);
	
		// when 
		Virement found = virementRepository.getById(v.getId());
		//then 
		assertThat(found.getId()).isEqualTo(v.getId());

		
  }

  @Test
  public void findAll() throws ParseException {   	 
    ArrayList<Virement> l=  (ArrayList<Virement>) virementRepository.findAll();
	System.out.print(l.toString());
	assertNotNull(l);

  }

  @Test
  public void save() throws ParseException {
	  //given
	  Virement v =new Virement();
	  Utilisateur utilisateur1 = new Utilisateur();
		utilisateur1.setUsername("user1");
		utilisateur1.setLastname("last1");
		utilisateur1.setFirstname("first1");
		utilisateur1.setGender("Male");
	    Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1998");
        utilisateur1.setBirthdate(d1);


		Utilisateur utilisateur2 = new Utilisateur();
		utilisateur2.setUsername("user2");
		utilisateur2.setLastname("last2");
		utilisateur2.setFirstname("first2");
		utilisateur2.setGender("Female");
		Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse("22/10/2000");
      utilisateur2.setBirthdate(d2);

      
		Compte compte1 = new Compte();
		compte1.setNumeroCompte("010000A000001000");
		compte1.setRib("RIB10");
		compte1.setSolde(BigDecimal.valueOf(200000L));
		compteRepository.save(compte1);



		Compte compte2 = new Compte();
		compte2.setNumeroCompte("010000B025001000");
		compte2.setRib("RIB20");
		compte2.setSolde(BigDecimal.valueOf(140000L));
		compte2.setUtilisateur(utilisateur2);
		compteRepository.save(compte1);



		v.setMontantVirement(BigDecimal.TEN);
		v.setCompteBeneficiaire(compte2);
		v.setCompteEmetteur(compte1);
		v.setDateExecution(new Date());
		v.setMotifVirement("Assignment 2021");
		virementRepository.save(v);
	
		//when
		
		assertTrue(virementRepository.save(v).getId()==v.getId());
         		
		
	  
  }

  @Test
  public void delete() throws ParseException {
	  //given
	  Virement v =new Virement();
	  Utilisateur utilisateur1 = new Utilisateur();
		utilisateur1.setUsername("user1");
		utilisateur1.setLastname("last1");
		utilisateur1.setFirstname("first1");
		utilisateur1.setGender("Male");
	    Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1998");
        utilisateur1.setBirthdate(d1);

		Utilisateur utilisateur2 = new Utilisateur();
		utilisateur2.setUsername("user2");
		utilisateur2.setLastname("last2");
		utilisateur2.setFirstname("first2");
		utilisateur2.setGender("Female");
		Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse("22/10/2000");
      utilisateur2.setBirthdate(d2);
      
		Compte compte1 = new Compte();
		compte1.setNumeroCompte("010000A000001000");
		compte1.setRib("RIB1");
		compte1.setSolde(BigDecimal.valueOf(200000L));


		Compte compte2 = new Compte();
		compte2.setNumeroCompte("010000B025001000");
		compte2.setRib("RIB2");
		compte2.setSolde(BigDecimal.valueOf(140000L));
		compte2.setUtilisateur(utilisateur2);

		v.setMontantVirement(BigDecimal.TEN);
		v.setCompteBeneficiaire(compte2);
		v.setCompteEmetteur(compte1);
		v.setDateExecution(new Date());
		v.setMotifVirement("Assignment 2021");
		virementRepository.save(v);
		//when
		virementRepository.deleteById(v.getId());
		ArrayList<Virement> l=  (ArrayList<Virement>) virementRepository.findAll();
		assertFalse(l.contains(v));
	  
  }
}