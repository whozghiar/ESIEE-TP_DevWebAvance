package fr.unilasalle.tp_garage_auto.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString (exclude = {"client", "rendezVous"})
@Data
@Builder
@Valid
@EqualsAndHashCode(exclude = {"client", "rendezVous"})
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
    @OneToMany(mappedBy = "vehicule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference("vehicule-rendezVous")
    private Set<RendezVous> rendezVous;

    // Relation avec Client
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference("client-vehicule")
    private Client client;


}
