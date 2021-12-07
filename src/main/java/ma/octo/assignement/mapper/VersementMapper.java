package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;

public class VersementMapper {
	private static VersementDto versementDto;

    public static VersementDto map(Versement versement) {
    	versementDto = new VersementDto();
    	versementDto.setNomPrenomEmmetteur(versement.getNomPrenomEmetteur());
    	versementDto.setRib(versement.getCompteBeneficiaire().getRib());
    	versementDto.setDate(versement.getDateExecution());
    	versementDto.setMotif(versement.getMotifVersement());
    	versementDto.setMontantVersement(versement.getMontantVersement());

        return versementDto;

    }

}
