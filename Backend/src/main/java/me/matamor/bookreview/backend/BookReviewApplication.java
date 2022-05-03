package me.matamor.bookreview.backend;

import me.matamor.bookreview.backend.configs.ConfiguracionServidor;
import me.matamor.bookreview.backend.modelos.*;
import me.matamor.bookreview.backend.servicios.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(ConfiguracionServidor.class)
public class BookReviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookReviewApplication.class, args);
    }

    private Date toDate(String fecha) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
        } catch (ParseException e) {
            return null;
        }
    }

    @Bean
    public CommandLineRunner initData(CategoriaService categoriaService, EditorialService editorialService, AutorService autorService, PuntuacionesService puntuacionesService,
                                      LibroService libroService, PuntuacionService puntuacionService, UsuarioService usuarioService, ReviewService reviewService) {
        return args -> {
            //Insertamos unas categorias
            Categoria romance = categoriaService.insertar(new Categoria("Romance", "Libros de amor y engaño", "https://i.imgur.com/k4AGaAY.png"));
            Categoria accion = categoriaService.insertar(new Categoria("Acción", "Historias muy guays", "https://i.imgur.com/1flE7cr.png"));
            Categoria novela = categoriaService.insertar(new Categoria("Novela", "Novelas muy guays", "https://i.imgur.com/fmNUfr6.png"));

            //Insertamos unas editoriales
            Editorial narcea = editorialService.insertar(new Editorial("Narcea", "https://i.imgur.com/oWwLnUH.png"));
            Editorial bubok = editorialService.insertar(new Editorial("Bubok", "https://i.imgur.com/FYL0ONU.png"));

            //Insertamos unos autores
            Autor emmaBallan = autorService.insertar(new Autor("Emma", "Ballan"));

            //Insertamos unos libros
            Libro libro = libroService.insertar(new Libro("La mano del arquero",
                    "Esta increíble historia cuenta, en primera persona y con un amplio toque de humor negro, las vivencias de una abogada que sufre acoso laboral en un bufete gallego. Un acoso que se extiende como un manto pegajoso al resto de la integridad de la plantilla, por causas y finalidades absurdas, desembocando en una guerra personal y jurídica- de todo un equipo de abogados contra el sistema. De forma paralela, un narrador desconocido desarrolla un pequeño diario vinculado a la maternidad, que se terminará entrelazando con la historia principal, dándole su completo sentido y magnitud.",
                    Libro.TipoLibro.INDEPENDIENTE,
                    toDate("29/01/2013"),
                    299,
                    "https://www.bubok.es/libro/portadaLibro/221676/1/La-mano-del-arquero.jpg",
                    emmaBallan,
                    bubok,
                    List.of(novela, accion)));

            //Creamos un usuario fake
            Usuario usuario = usuarioService.registrar(new Usuario("matamor", "Santiago", "Gonzalez Londoño", "test", "matamor98@hotmail.com", "1234"));

            Puntuaciones puntuaciones = puntuacionesService.insertar(new Puntuaciones(usuario.getId()));
            Puntuacion puntuacion = puntuacionService.insertar(new Puntuacion(puntuaciones.getId(), usuario.getId(), 5));

            puntuaciones.getEntries().add(puntuacion);

            puntuacionesService.insertar(puntuaciones);

            libro.setPuntuacion(puntuaciones);
            libroService.insertar(libro);

            Review review = reviewService.insertar(new Review(usuario, libro, "Demasiado Perfecto", "Ninguna vida puede considerarse perfecta, pero para Abbey, es como si estuviera pagando los karmas de todos sus antepasados y esto es debido a una serie de infortunios que parece que se convirtieran peor que el anterior. Nathan es la perfección andante. No solo es su físico que atrae las miradas de las mujeres y la envidia de los hombres, sino su éxito profesional. Todo lo que se propone lo logra, pero ¿hasta dónde estaría dispuesto a llegar para conseguir sus objetivos?  Ya es difícil intentar sobrellevar la situación de un corazón roto de Abbey pero como las desgracias nunca llegan solas, de todos los lugares de la ciudad de Nueva York como es posible que coincidiera con quienes la traicionaron y para ponerle más limón a la herida que se vean tal felices juntos, es como si ella nunca hubiera existido. En definitiva su día no puede ir para peor, pero bien dicen que en medio de la tormenta viene la calma y será Nathan quien le ayudará un poco a salir de tan penosa situación. Lo que ella nunca se imaginó es que el remedio fuera peor que la enfermedad y se encuentre con que sea él que le pida una cita en condiciones.  Obviamente después de semejante chasco amoroso su cuota de hombres ha llegado a su fin y lo que menos que desea es volver a sufrir por amor, pero como es posible que sin que lo esperara, tenerlo cerca hace que se forme un huracán en medio de su corazón y sus piernas, porque es imposible que alguien como él pueda estar interesado en alguien como ella. ¿Será posible que un playboy y una chica que ya no cree en el felices para siempre puedan estar juntos?  Una historia que demuestra que los sentimientos que se sienten con el corazón son perfectos, porque cuando tu corazón se enamora de verdad es cuando aprendes que todas esas imperfecciones se convierten en perfectas para ti.", "https://i.imgur.com/YUt5EVO.jpg", 5));
        };
    }
}
