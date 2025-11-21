package com.example.demo.controller;

import java.util.ArrayList;
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
/*Todo funciona*/
@Controller
public class PostMController {
	/*listas*/
	public static List<LibroDTO> listaLibros = new ArrayList<>();
	public static List<UsuarioDTO> listaUsuarios = new ArrayList<>();
	public static List<Cantidad> numlibros = new ArrayList<>();
	
	/*para listar los libros*/
	@GetMapping("/lista_libros")
	public String listarLibrs(Model model) {
		model.addAttribute("libros", listaLibros);
		return "mostrarLibros";
	}
	
	/*para listar los libros y su cantidad*/
	@GetMapping("/lista_cantidad")
	public String listarLibrosCantidad (Model model) {
		model.addAttribute("libros", numlibros);
		return "mostarCantidad";
	}
	
	
	/*para añadir los libros nuevos*/
	@GetMapping("/nuevo_libro")
	public String mostrarFormularioLibro(Model model) {
		model.addAttribute("libro", new LibroDTO());
		model.addAttribute("numlibro", new Cantidad());
		return "nuevo_libro";
	}
	
	/*para guardar los libros nuevos*/
	@PostMapping("/guardar_libro")
	public String guardarLibro(@RequestParam("isbn") int ISBN ,@ModelAttribute LibroDTO libro, Model model) {
		boolean existe = listaLibros.stream().anyMatch(u -> u.getIsbn() == libro.getIsbn());
		/*Si el libro no existe se crea*/
		if (!existe) {
			listaLibros.add(libro);
			numlibros.add(new Cantidad(libro,1));
		}else {
			/*para en el caso de que el libro exista incrementar su cantidad en 1*/
			listaLibros.add(libro);
			for(Cantidad num : numlibros) {
				if(num.getLibro().getIsbn() == ISBN) {
					num.setCantidad(num.getCantidad() + 1); 
				}
			}
		}
		model.addAttribute("libros", listaLibros);
		return "mostrarLibros";
	}
	/*Para eliminar un libro*/
	@GetMapping("/Elim_libro")
	public String mostrarformEliminar(Model model) {
		model.addAttribute("libro", new LibroDTO());
		return "Elim_libro";
	}
	
	@PostMapping("/eliminar_libro")
	public String eliminarLibro(@RequestParam("eliminacion") int ISBN , Model model) {
		boolean eliminar = false;
		int pos=0;
		for(Cantidad num : numlibros) {
			if(num.getLibro().getIsbn() == ISBN ) {
				for(LibroDTO libro : listaLibros) {
					if(libro.getIsbn() == ISBN && libro.getEstado().equalsIgnoreCase("Disponible")) {
						num.setCantidad(num.getCantidad() - 1);
						listaLibros.remove(pos);
						break;
					}
					pos ++;
				}
				if(num.getCantidad() == 0 && num.getLibro().getEstado().equalsIgnoreCase("Disponible")) {
					eliminar = true;
				}
			}
		}
		if (eliminar) {
			numlibros.removeIf(num -> num.getLibro().getIsbn() == ISBN);
		}
		
		model.addAttribute("libros", listaLibros);
		return "mostrarLibros";
	}

	/*para modifcar los libros*/
	@GetMapping("/modificar_libro")
	public String buscarlibro(Model model) {
		model.addAttribute("libro", new LibroDTO());
		return "Mod_libro";
	}
	/*Para saber que isbn se desea y mostrar los atributos de ese isbn despues*/
	@PostMapping("/modificacion")
	public String modificarLibro(@RequestParam("modificar") int ISBN , Model model) {
		for(LibroDTO libro : listaLibros) {
			if(libro.getIsbn() == ISBN) {
				model.addAttribute("libro", libro);
				return "modificacion";
			}
		}
		model.addAttribute("libro", ISBN);
		return "mostrarLibros";
	}
	/*para realizar la modificacion*/
	@PostMapping("/modificacionfinal")
	public String modLibros (@RequestParam("isbn") int ISBN ,@RequestParam("nuevotitulo") String titulo 
			, @RequestParam("nuevoautor") String autor, @RequestParam("nuevopublicadoen") String publicadoen, Model model) {
		/*bucle*/
		for(LibroDTO libro : listaLibros) {
			/*comprobar si ahi algun libro con el isbn introduccido*/
			if(libro.getIsbn() == ISBN) {
				libro.setAutor(autor);
				libro.setTitulo(titulo);
				libro.setPublicadoen(publicadoen);
				model.addAttribute("libros", listaLibros); //Muy importante, por esto no me funcionaba el programa
			}
		}
		return "mostrarLibros";
	}
	/*para listar los usuarios*/
	@GetMapping("/lista_usuarios")
	public String listarUsuarios(Model model) {
		model.addAttribute("usuarios", listaUsuarios);
		return "mostrarUsuarios";
	}
	/*para agregar nuevos usuarios*/
	@GetMapping("/nuevo_usuario")
	public String mostrarFormularioUsuario(Model model) {
		model.addAttribute("usuario", new UsuarioDTO());
		return "nuevo_Usuario";
	}
	
	/*para guardar usuarios, en el caso de que ya exista ese dni, no lo añade*/
	@PostMapping("/guardar_usuario")
	public String guardarUsuario(@ModelAttribute UsuarioDTO usuario, Model model) {
		/*Comprueba si el dni ya existe*/
		boolean existe = listaUsuarios.stream().anyMatch(u -> u.getDNI().equalsIgnoreCase(usuario.getDNI()));
		/*Si no existe deja crear el usuario, si existe, no crea nada*/
		    if (!existe) {
		        listaUsuarios.add(usuario);
		    }

		    model.addAttribute("usuarios", listaUsuarios);
		    return "mostrarUsuarios";
	}
	/*Para buscar el libro*/
	@GetMapping("/busca_libro")
	public String buscarLibro (Model model) {
			model.addAttribute("libro", new LibroDTO());
		return "buscalibro";
	}
	/*Mostra el libro si existe y si no existe mostrar un mensaje*/
	@PostMapping("/mostrarlibro")
	public String buscarLibros (@RequestParam("isbn") int ISBN,Model model) {
		for(LibroDTO libro : listaLibros) {
			if(libro.getIsbn() == ISBN) {
				model.addAttribute("libros", libro);
			}
		}
		model.addAttribute("libro", ISBN);
		return "muestralibrobuscado";
	}
	
}