
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main
{
    static void mostrar(List<Pelicula> peliculas, Consumer c)
    {
        for(Pelicula p: peliculas)
        {
            c.accept(p);
        }
    }
    public static void main(String[] args)
    {

        List<Pelicula> peliculas = new ArrayList<>();
        peliculas.add(new Pelicula("E.T.", "Steven Spielberg", 1982));
        peliculas.add(new Pelicula("Avatar", "James Cameron", 2009));
        peliculas.add(new Pelicula("Salvar al soldado Ryan", "Steven Spielberg", 1998));
        peliculas.add(new Pelicula("Origen", "Christopher Nolan", 2010));
        peliculas.add(new Pelicula("L.A. Confidential", "Curtis Hanson", 1997));

        System.out.println("--------------original-----------------");
        Consumer<Pelicula> imprimir = p-> System.out.println(p);

        mostrar(peliculas, imprimir);
/*

        Comparator<Pelicula> cEstreno = (p1, p2) -> p2.getEstreno() - p1.getEstreno();

        peliculas.sort(cEstreno);
        System.out.println("-----------------Peliculas ordenadas por estreno----------------");

        mostrar(peliculas, imprimir);


        Comparator<Pelicula> cDirector = (p1, p2) -> p1.getDirector().compareTo(p2.getDirector());

        Comparator<Pelicula> cTitulo = (p1, p2) ->  p1.getTitulo().compareTo(p2.getTitulo());

        peliculas.sort(cDirector.thenComparing(cTitulo));
        System.out.println("-----------------Peliculas ordenadas por segunda ves----------------");

        mostrar(peliculas, imprimir);*/


        System.out.println("-----------------TAREA STREAM----------------");
        System.out.println("----------------- 1 ----------------");
        List<Pelicula> sublistado = peliculas.stream().filter(p-> p.getEstreno()>=2000).collect(Collectors.toList());

        System.out.println("----------------- 2 ----------------");
        String titulosS = peliculas.stream()
                .filter(p -> p.getDirector().equals("Steven Spielberg"))
                .map(p -> p.getTitulo())
                .collect(Collectors.joining(", ",
                        "Peliculas de Steven Spielberg: ", ""));
        System.out.println(titulosS);

        System.out.println("----------------- 3 ----------------");

        Comparator<Pelicula> cNombre = (p1, p2) -> p1.getTitulo().compareTo(p2.getTitulo());

        List<String> ListaNombresAlfabetico =peliculas.stream()
                .sorted(cNombre)
                .map(p->p.getTitulo()).collect(Collectors.toList());

        ListaNombresAlfabetico.stream().forEach(System.out::println);

        System.out.println("----------------- 4 ----------------");

        Optional<Integer> anioMasReciente = peliculas.stream()
                .map(Pelicula::getEstreno)
                .max(Integer::compareTo);
        System.out.println("Año de estreno mas reciente:"+anioMasReciente.get());

    }
}
