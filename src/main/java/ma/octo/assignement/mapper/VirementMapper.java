package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;

public class VirementMapper {

    private static VirementDto virementDto;

    public static VirementDto map(Virement virement) {
        virementDto = new VirementDto();
        virementDto.setNrCompteEmetteur(virement.getCompteEmetteur().getNumeroCompte());
        virementDto.setNrCompteBeneficiaire(virement.getCompteBeneficiaire().getNumeroCompte());
        virementDto.setDate(virement.getDateExecution());
        virementDto.setMotif(virement.getMotifVirement());
        virementDto.setMontantVirement(virement.getMontantVirement());

        return virementDto;

    }
}
