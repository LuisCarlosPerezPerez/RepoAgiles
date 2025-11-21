package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.model.Cantidad;
import com.example.demo.model.LibroDTO;
import com.example.demo.model.UsuarioDTO;
/*Todo Funciona*/
@Controller
public class ControllerReservas {
	List<LibroDTO> listaLibros = PostMController.listaLibros;
	List<UsuarioDTO> listaUsuarios = PostMController.listaUsuarios;
	List<Cantidad> numlibros = PostMController.numlibros;
	HashMap <UsuarioDTO,LibroDTO> usuariolibro = new HashMap<>();
	
	// formulario para buscar el libro y el usuario que quiere reservar
	@GetMapping("/nueva_reserva")
	public String mostrarFormularioReserva(Model model) {
		return "formReserva";
	}
	//Realizacion d ela reserva
	@PostMapping("/reservar")
	public String reservarLibro(@RequestParam("isbn") int ISBN ,@ModelAttribute LibroDTO Libros,@ModelAttribute UsuarioDTO usuario,Model model,
			@RequestParam("dni") String DNI) {

		//buscar si existe un usuario con es dni y se guarda 
		 usuario = listaUsuarios.stream().filter(u -> u.getDNI().equalsIgnoreCase(DNI)).findFirst().orElse(null);
		 //buscar si existe un usuario con ese isb y se guarda 
		 Libros = listaLibros.stream().filter(libro -> libro.getIsbn() == ISBN ).findFirst().orElse(null);
		/*Si  el usuario no existe,no hace nada*/
		if (usuario !=null) {
				//Comprueba que el usuario sea mayor de 18 años
					if(usuario.getEdad() >=18) {
						//recore el arraylist cantidad
						for(Cantidad num : numlibros) {
							//comprueba que el libro tenga ese mismo isbn y para restarle 1 a la cantidad 
							if(num.getLibro().getIsbn() == ISBN && num.getCantidad() > 0) {
								num.setCantidad(num.getCantidad() - 1); 
								//busca el libro con cierto isbn y que este su estado disponible
								for(LibroDTO libro : listaLibros) {
									if(libro.getEstado().equalsIgnoreCase("Disponible") && libro.getIsbn() == ISBN) {
										libro.setEstado("Reservado");
										usuariolibro.put(usuario, Libros);
										break;
									}
								}
							}
					}

			}
			
		}
		model.addAttribute("reservas", usuariolibro);
		return "mostrarReserva";
	}
	// muestra las reservas
	@GetMapping("/mostrar_Reserva")
	public String mostrarReservas(Model model) {
		model.addAttribute("reservas", usuariolibro);
		return "mostrarReserva";
	}

}
