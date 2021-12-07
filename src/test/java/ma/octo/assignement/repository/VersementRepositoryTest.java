package ma.octo.assignement.repository;

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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Versement;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class VersementRepositoryTest {
	@Autowired
	private VersementRepository versementRepository;
	@Test
	  public void findOne() throws ParseException {
		  //given
		  Versement v1 =new Versement();
		  Utilisateur utilisateur1 = new Utilisateur();
			utilisateur1.setUsername("user1");
			utilisateur1.setLastname("last1");
			utilisateur1.setFirstname("first1");
			utilisateur1.setGender("Male");
		    Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1998");
	        utilisateur1.setBirthdate(d1);

	      
			Compte compte1 = new Compte();
			compte1.setNumeroCompte("010000A000001000");
			compte1.setRib("RIB1");
			compte1.setSolde(BigDecimal.valueOf(200000L));

			v1.setMontantVersement(BigDecimal.TEN);
			v1.setCompteBeneficiaire(compte1);
			v1.setDateExecution(new Date());
			v1.setMotifVersement("Assignment 2021");
			v1.setNomPrenomEmetteur("testeur testeur");
			versementRepository.save(v1);
		
			// when 
			Versement found = versementRepository.getById(v1.getId());
			//then 
			assertThat(found.getId()).isEqualTo(v1.getId());

			
	  }
	 @Test
	  public void findAll() throws ParseException {
		
		 ArrayList<Versement> l =  (ArrayList<Versement>) versementRepository.findAll();
			assertNotNull(l);	 
	 }
	 @Test
	  public void delete() throws ParseException{
		 Versement v =new Versement();
		  Utilisateur utilisateur1 = new Utilisateur();
			utilisateur1.setUsername("user1");
			utilisateur1.setLastname("last1");
			utilisateur1.setFirstname("first1");
			utilisateur1.setGender("Male");
		    Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1998");
	        utilisateur1.setBirthdate(d1);

	      
			Compte compte1 = new Compte();
			compte1.setNumeroCompte("010000A000001000");
			compte1.setRib("RIB1");
			compte1.setSolde(BigDecimal.valueOf(200000L));

			v.setMontantVersement(BigDecimal.TEN);
			v.setCompteBeneficiaire(compte1);
			v.setDateExecution(new Date());
			v.setMotifVersement("Assignment 2021");
			v.setNomPrenomEmetteur("testeur testeur");
			versementRepository.save(v);
			// when
			versementRepository.deleteById(v.getId());
			ArrayList<Versement> list = (ArrayList<Versement>) versementRepository.findAll();
			assertFalse(list.contains(v)); 
	 }
	 @Test
	  public void save() throws ParseException {
		 //given
		 Versement v =new Versement();
		  Utilisateur utilisateur1 = new Utilisateur();
			utilisateur1.setUsername("user1");
			utilisateur1.setLastname("last1");
			utilisateur1.setFirstname("first1");
			utilisateur1.setGender("Male");
		    Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1998");
	        utilisateur1.setBirthdate(d1);

	      
			Compte compte1 = new Compte();
			compte1.setNumeroCompte("010000A000001000");
			compte1.setRib("RIB1");
			compte1.setSolde(BigDecimal.valueOf(200000L));

			v.setMontantVersement(BigDecimal.TEN);
			v.setCompteBeneficiaire(compte1);
			v.setDateExecution(new Date());
			v.setMotifVersement("Assignment 2021");
			v.setNomPrenomEmetteur("testeur testeur");
			versementRepository.save(v);
			//when
			assertTrue(versementRepository.save(v).getId()==v.getId());

		 
	 }

}
