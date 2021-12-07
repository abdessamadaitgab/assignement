package ma.octo.assignement.dto;

import java.math.BigDecimal;
import java.util.Date;

public class VersementDto {
	private String nomPrenomEmetteur;
	  private String rib;
	  private String motif;
	  private BigDecimal montantVirement;
	  private Date date;
	  

	public String getNomPrenomEmmetteur() {
	    return nomPrenomEmetteur;
	  }

	  public void setNomPrenomEmmetteur(String nomPrenomEmetteur) {
	    this.nomPrenomEmetteur = nomPrenomEmetteur;
	  }

	  public String getRib() {
	    return rib;
	  }

	  public void setRib(String rib) {
	    this.rib = rib;
	  }

	  public BigDecimal getMontantVersement() {
	    return montantVirement;
	  }

	  public void setMontantVersement(BigDecimal montantVirement) {
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
