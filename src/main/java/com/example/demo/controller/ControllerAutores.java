package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.AutorDTO;
import com.example.demo.model.Autor_Libro;
import com.example.demo.model.Cantidad;
import com.example.demo.model.LibroDTO;

@Controller
public class ControllerAutores {
	List<AutorDTO> autor = PostMController.autor;
	List<LibroDTO> listaLibros = PostMController.listaLibros;
	List<Cantidad> numlibros = PostMController.numlibros;
	List<Autor_Libro> autorlibros = PostMController.autorlibro;

	/*para agregar nuevos autores*/
	@GetMapping("/nuevo_autor")
	public String mostrarFormularioUsuario(Model model) {
		model.addAttribute("autor", new AutorDTO());
		return "NuevoAutor";
	}
	
	/*para guardar a los autores, en el caso de que ya exista ese nombre y apellido (las dos cosas sean iguales que otro)
	 * , no lo añade*/
	@PostMapping("/guardar_autor")
	public String guardarUsuario(@ModelAttribute AutorDTO nuevoautor, Model model) {
		/*Comprueba si el nombre ya existe*/
		/*Si no existe, deja crear el autor, si existe, no crea nada*/
		boolean existeapellido = autor.stream().anyMatch(u -> u.getApellidos().equalsIgnoreCase(nuevoautor.getApellidos()) && u.getNombre().equalsIgnoreCase(nuevoautor.getNombre()));
		    if (!existeapellido) {
		        autor.add(nuevoautor);
		    }
		    model.addAttribute("autores", autor);
		    return "mostrar_Autores";
	}
	
	/*para añadir los libros nuevos con su autor correspondiente*/
	@GetMapping("/nuevo_libroautor")
	public String mostrarFormularioLibro(Model model) {
		model.addAttribute("libro", new LibroDTO());
		model.addAttribute("numlibro", new Cantidad());
		return "nuevo_libroautor";
	}
	
	/*para guardar los libros nuevos*/
	@PostMapping("/guardar_libroautor")
	public String guardarLibro(@RequestParam("isbn") int ISBN , @RequestParam("Nombre") String Nombre, @RequestParam("apellidos") String Apellidos,@ModelAttribute LibroDTO libro,@ModelAttribute AutorDTO autorbuscado, Model model) {
		/*comprueba si el libro existe*/
		boolean existe = listaLibros.stream().anyMatch(u -> u.getIsbn() == libro.getIsbn());
		/*Comprueba si el autor ya existe*/
		boolean existenombreapellido = autor.stream().anyMatch(u -> u.getNombre().equalsIgnoreCase(autorbuscado.getNombre())&& u.getApellidos().equalsIgnoreCase(Apellidos));
		/*Si el libro no existe y el autor existe se crea*/
		if (!existe && existenombreapellido ) {
			listaLibros.add(libro);
			numlibros.add(new Cantidad(libro,1));
			for(AutorDTO autorescritor: autor) {
				if(autorescritor.getNombre().equalsIgnoreCase(Nombre) && autorescritor.getApellidos().equalsIgnoreCase(Apellidos)) {
					autorlibros.add(new Autor_Libro(autorescritor,libro));
				}
			}
		}else {
			/*para en el caso de que el libro exista incrementar su cantidad en 1*/
			listaLibros.add(libro);
			for(Cantidad num : numlibros) {
				if(num.getLibro().getIsbn() == ISBN) {
					num.setCantidad(num.getCantidad() + 1); 
				}
			}
		}
		model.addAttribute("autorlibros", autorlibros);
		return "mostrarautorLibros";
	}
	
	/*Para buscar los libros por el autor*/
	@GetMapping("/busca_libroautor")
	public String buscarLibro (Model model) {
		model.addAttribute("autor", new AutorDTO());
		return "buscalibroautor";
	}
	/*Mostra los libros que tiene el autor elegido*/
	@PostMapping("/mostrarlibroautor")
	public String buscarLibros (@RequestParam("nombre") String Nombre, @RequestParam("apellido") String Apellido,Model model) {
		List<Autor_Libro> autorlibrosautor = new ArrayList<>();
		for(Autor_Libro autorlibrosescritos: autorlibros) {
			if(autorlibrosescritos.getAutor().getNombre().equalsIgnoreCase(Nombre) && autorlibrosescritos.getAutor().getApellidos().equalsIgnoreCase(Apellido)) {
				autorlibrosautor.add(autorlibrosescritos);
			}
		}
		model.addAttribute("autorlibrosautor", autorlibrosautor);
		return "muestralibrobuscadoautor";
	}
}
