package fr.unilasalle.tp_garage_auto.beans;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Valid
@EqualsAndHashCode(exclude = "rendezVous")
public class Technicien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @NotNull(message = "Le nom ne peut pas être nul")
    @NotEmpty(message = "Le nom ne peut pas être vide")
    @Size(min = 2, max = 50, message = "Le nom doit être compris entre 2 et 50 caractères")
    @Schema(description = "Nom du technicien", example = "AUBRY")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @NotNull(message = "Le prénom ne peut pas être nul")
    @NotEmpty(message = "Le prénom ne peut pas être vide")
    @Size(min = 2, max = 50, message = "Le prénom doit être compris entre 2 et 50 caractères")
    @Schema(description = "Prénom du technicien", example = "Martine")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @NotEmpty(message = "L'email ne peut pas être vide")
    @NotNull(message = "L'email ne peut pas être nul")
    @Email(message = "L'email doit être valide")
    @Schema(description = "Email du Technicien", example = "technicien@mail.com")
    @Column(unique = true)
    private String email;


}
