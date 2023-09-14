package med.voll.api.medico;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DatosDireccion;

public record DatosRegistroMedico(
		// dependency validation: 
		// @NOtBlank: comprueba que el valor no llegue nulo ni esta vacion
		// @NotNull: comprueba que el valor no llegue nulo
		// @email: comprueba que sea un email
		// @Pattern(regexp = "//d{4,6}"): permite solo numeros, de 4 a 6 digitos
		// @valid: siempre debe ir para que valide los datos del objeto
		@NotBlank
		String nombre,
		@NotBlank
		@Email 
		String email, 
		@NotBlank
		String telefono,
		@NotBlank
		@Pattern(regexp = "\\d{4,10}")
		String documento,
		@NotNull
		Especialidad especialidad,
		@NotNull
		@Valid
		DatosDireccion direccion) {

}
