package ma.octo.assignement.dto;

import java.math.BigDecimal;
import java.util.Date;

public class VirementDto {
  private String numeroCompteEmetteur;
  private String numeroCompteBeneficiaire;
  private String motif;
  private BigDecimal montantVirement;
  private Date date;
  

public String getNrCompteEmetteur() {
    return numeroCompteEmetteur;
  }

  public void setNrCompteEmetteur(String numeroCompteEmetteur) {
    this.numeroCompteEmetteur = numeroCompteEmetteur;
  }

  public String getNrCompteBeneficiaire() {
    return numeroCompteBeneficiaire;
  }

  public void setNrCompteBeneficiaire(String numeroCompteBeneficiaire) {
    this.numeroCompteBeneficiaire = numeroCompteBeneficiaire;
  }

  public BigDecimal getMontantVirement() {
    return montantVirement;
  }

  public void setMontantVirement(BigDecimal montantVirement) {
    this.montantVirement = montantVirement;
  }

  public String getMotif() {
    return motif;
  }

  public void setMotif(String motif) {
    this.motif = motif;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
