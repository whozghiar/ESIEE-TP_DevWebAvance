package fr.unilasalle.tp_garage_auto.beans;

import jakarta.persistence.*;

import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Valid
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marque;
    private String modele;
    private String immatriculation;
    private int annee;

    // Relation avec Client
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    // Relation avec RendezVous : un véhicule peut avoir plusieurs rendez-vous
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RendezVous> rendezVous;

}
