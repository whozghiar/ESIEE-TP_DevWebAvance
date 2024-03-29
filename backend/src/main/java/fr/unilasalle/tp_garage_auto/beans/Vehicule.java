package fr.unilasalle.tp_garage_auto.beans;

import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
@Valid
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La marque est obligatoire")
    private String marque;
    @NotBlank(message = "Le modèle est obligatoire")
    private String modele;
    @NotBlank(message = "L'immatriculation est obligatoire")
    private String immatriculation;
    @NotBlank(message = "L'année est obligatoire")
    private int annee;

    // Relation avec RendezVous : un véhicule peut avoir plusieurs rendez-vous
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RendezVous> rendezVous;

    // Relation avec Client : un véhicule appartient à un client
    @ManyToOne
    private Client client;

}
