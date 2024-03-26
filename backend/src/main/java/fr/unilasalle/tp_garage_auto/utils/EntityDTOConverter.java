package fr.unilasalle.tp_garage_auto.utils;

import fr.unilasalle.tp_garage_auto.DTO.VehiculeDTO;
import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.beans.Vehicule;

public class EntityDTOConverter {

    /**
     * Convertit un vehicule en VehiculeDTO
     * @param vehicule Le vehicule à convertir
     * @return
     */
    public static VehiculeDTO convertToVehiculeDTO(Vehicule vehicule) {
        VehiculeDTO vehiculeDTO = new VehiculeDTO();
        vehiculeDTO.setId(vehicule.getId());
        vehiculeDTO.setMarque(vehicule.getMarque());
        vehiculeDTO.setModele(vehicule.getModele());
        vehiculeDTO.setImmatriculation(vehicule.getImmatriculation());
        vehiculeDTO.setAnnee(vehicule.getAnnee());
        return vehiculeDTO;
    }

    /**
     * Convertit un VehiculeDTO en Vehicule
     * @param vDto Le VehiculeDTO à convertir
     * @param client Le client associé
     * @return
     */
    public static Vehicule convertToVehiculeEntity(VehiculeDTO vDto, Client client) {
        Vehicule vehicule = new Vehicule();
        vehicule.setId(vDto.getId());
        vehicule.setMarque(vDto.getMarque());
        vehicule.setModele(vDto.getModele());
        vehicule.setImmatriculation(vDto.getImmatriculation());
        vehicule.setAnnee(vDto.getAnnee());
        vehicule.setClient(client);
        return vehicule;
    }
}
