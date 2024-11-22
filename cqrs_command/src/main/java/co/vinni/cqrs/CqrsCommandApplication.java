package co.vinni.cqrs;

import co.vinni.cqrs.dto.PeticionEvent;
import co.vinni.cqrs.dto.QuejaEvent;
import co.vinni.cqrs.dto.RecursoEvent;
import co.vinni.cqrs.dto.SugerenciaEvent;
import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Queja;
import co.vinni.cqrs.persistence.entity.Recurso;
import co.vinni.cqrs.persistence.entity.Sugerencia;
import co.vinni.cqrs.service.PeticionCommandService;
import co.vinni.cqrs.service.QuejaCommandService;
import co.vinni.cqrs.service.RecursoCommandService;
import co.vinni.cqrs.service.SugerenciaCommandService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class CqrsCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(CqrsCommandApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(PeticionCommandService peticionCommandService,
							 QuejaCommandService quejaCommandService,
							 RecursoCommandService recursoCommandService,
							 SugerenciaCommandService sugerenciaCommandService) {
		return args -> {
			// Crear instancia de la aplicación PQRS con todos los servicios
			PqrsApp app = new PqrsApp(peticionCommandService, quejaCommandService, recursoCommandService, sugerenciaCommandService);
			app.iniciar();
		};
	}
}

class PqrsApp {
	private final PeticionCommandService peticionCommandService;
	private final QuejaCommandService quejaCommandService;
	private final RecursoCommandService recursoCommandService;
	private final SugerenciaCommandService sugerenciaCommandService;

	// Constructor que acepta todos los servicios
	public PqrsApp(PeticionCommandService peticionCommandService,
				   QuejaCommandService quejaCommandService,
				   RecursoCommandService recursoCommandService,
				   SugerenciaCommandService sugerenciaCommandService) {
		this.peticionCommandService = peticionCommandService;
		this.quejaCommandService = quejaCommandService;
		this.recursoCommandService = recursoCommandService;
		this.sugerenciaCommandService = sugerenciaCommandService;
	}

	public void iniciar() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\n*** Sistema PQRS ***");
			System.out.println("Seleccione una opción:");
			System.out.println("1. Registrar una Petición");
			System.out.println("2. Registrar una Queja");
			System.out.println("3. Registrar un Recurso");
			System.out.println("4. Registrar una Sugerencia");
			System.out.println("5. Salir");
			System.out.print("Opción: ");

			String opcion = scanner.nextLine().trim();

			if (opcion.equals("5")) {
				System.out.println("Saliendo del sistema...");
				break;
			}

			if (opcion.equals("1") || opcion.equals("2") || opcion.equals("3") || opcion.equals("4")) {
				System.out.print("Nombre: ");
				String nombre = scanner.nextLine();
				System.out.print("Apellido: ");
				String apellido = scanner.nextLine();
				System.out.print("Correo Electrónico: ");
				String email = scanner.nextLine();
				System.out.print("Mensaje: ");
				String mensaje = scanner.nextLine();

				if (opcion.equals("1")) {
					// Crear la entidad Peticion
					Peticion peticion = Peticion.builder()
							.nombre(nombre)
							.apellido(apellido)
							.email(email)
							.mensaje(mensaje)
							.build();

					// Crear y enviar el evento PeticionEvent
					PeticionEvent peticionEvent = PeticionEvent.builder()
							.eventType("CreatePeticion")
							.peticion(peticion)
							.build();

					peticionCommandService.create(peticionEvent);
					System.out.println("Petición registrada y evento enviado exitosamente.");
				} else if (opcion.equals("2")) {
					// Crear la entidad Queja
					Queja queja = Queja.builder()
							.nombre(nombre)
							.apellido(apellido)
							.email(email)
							.mensaje(mensaje)
							.build();

					// Crear y enviar el evento QuejaEvent
					QuejaEvent quejaEvent = QuejaEvent.builder()
							.eventType("CreateQueja")
							.queja(queja)
							.build();

					quejaCommandService.create(quejaEvent);
					System.out.println("Queja registrada y evento enviado exitosamente.");
				} else if (opcion.equals("3")) {
					// Crear la entidad Recurso
					Recurso recurso = Recurso.builder()
							.nombre(nombre)
							.apellido(apellido)
							.email(email)
							.mensaje(mensaje)
							.build();

					// Crear y enviar el evento RecursoEvent
					RecursoEvent recursoEvent = RecursoEvent.builder()
							.eventType("CreateRecurso")
							.recurso(recurso)
							.build();

					recursoCommandService.create(recursoEvent);
					System.out.println("Recurso registrado y evento enviado exitosamente.");
				} else if (opcion.equals("4")) {
					// Crear la entidad Sugerencia
					Sugerencia sugerencia = Sugerencia.builder()
							.nombre(nombre)
							.apellido(apellido)
							.email(email)
							.mensaje(mensaje)
							.build();

					// Crear y enviar el evento SugerenciaEvent
					SugerenciaEvent sugerenciaEvent = SugerenciaEvent.builder()
							.eventType("CreateSugerencia")
							.sugerencia(sugerencia)
							.build();

					sugerenciaCommandService.create(sugerenciaEvent);
					System.out.println("Sugerencia registrada y evento enviado exitosamente.");
				}
			} else {
				System.out.println("Opción inválida. Intente de nuevo.");
			}
		}
	}
}