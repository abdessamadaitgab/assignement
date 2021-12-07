package ma.octo.assignement.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "VERSEMENT")
public class Versement {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(precision = 16, scale = 2, nullable = false)
  private BigDecimal montantVersement;

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateExecution;

  @Column
  private String nomPrenomEmetteur;

  @ManyToOne
  private Compte compteBeneficiaire;

  @Column(length = 200)
  private String motifVersement;

  public BigDecimal getMontantVersement() {
    return montantVersement;
  }

  public void setMontantVersement(BigDecimal montantVersement) {
    this.montantVersement = montantVersement;
  }

  public Date getDateExecution() {
    return dateExecution;
  }

  public void setDateExecution(Date dateExecution) {
    this.dateExecution = dateExecution;
  }

  public Compte getCompteBeneficiaire() {
    return compteBeneficiaire;
  }

  public void setCompteBeneficiaire(Compte compteBeneficiaire) {
    this.compteBeneficiaire = compteBeneficiaire;
  }

  public String getMotifVersement() {
    return motifVersement;
  }

  public void setMotifVersement(String motifVirement) {
    this.motifVersement = motifVirement;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNomPrenomEmetteur() {
    return nomPrenomEmetteur;
  }

  public void setNomPrenomEmetteur(String nomPrenomEmetteur) {
    this.nomPrenomEmetteur = nomPrenomEmetteur;
  }
}
