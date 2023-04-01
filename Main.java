import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Materia> materias=new ArrayList<>();
        List<Alumno> alumnos=new ArrayList<>();


        //agregamos las materias
        Materia matematica1 = new Materia("Matematica I");
        materias.add(matematica1);

        Materia matematica2 = new Materia("Matematica II");
        matematica2.correlativas.add(matematica1);
        materias.add(matematica2);

        Materia matematica3 = new Materia("Matematica III");
        matematica3.correlativas.add(matematica2);
        materias.add(matematica3);

        Materia programacion1 = new Materia("Programacion I");
        materias.add(programacion1);

        Materia programacion2 = new Materia("Programacion II");
        programacion2.correlativas.add(programacion1);
        materias.add(programacion2);

        Materia programacion3 = new Materia("Programacion III");
        programacion3.correlativas.add(programacion2);
        materias.add(programacion3);

        Materia basesDeDatos1 = new Materia("Bases de datos I");
        basesDeDatos1.correlativas.add(matematica1);
        basesDeDatos1.correlativas.add(programacion1);
        materias.add(basesDeDatos1);

        Materia basesDeDatos2 = new Materia("Bases de datos II");
        basesDeDatos2.correlativas.add(matematica2);
        basesDeDatos2.correlativas.add(programacion2);
        basesDeDatos2.correlativas.add(basesDeDatos1);
        materias.add(basesDeDatos2);

        Materia basesDeDatos3 = new Materia("Bases de datos III");
        basesDeDatos3.correlativas.add(programacion3);
        basesDeDatos3.correlativas.add(basesDeDatos2);
        materias.add(basesDeDatos3);

        //Agrego los alumnos

        Alumno joseRodriguez = new Alumno("Jose Rodriguez", "001");
        joseRodriguez.materiasAprobadas.add(matematica1);
        joseRodriguez.materiasAprobadas.add(matematica2);
        joseRodriguez.materiasAprobadas.add(matematica3);
        alumnos.add(joseRodriguez);

        Alumno vanesaSosa = new Alumno("Vanesa Sosa", "002");
        alumnos.add(vanesaSosa);

        Alumno robertoGomez = new Alumno("Roberto Gomez", "004");
        robertoGomez.materiasAprobadas.add(matematica1);
        robertoGomez.materiasAprobadas.add(matematica2);
        robertoGomez.materiasAprobadas.add(programacion1);
        alumnos.add(robertoGomez);

        List<Inscripcion> inscripciones=cargarInscripciones(materias,alumnos);

    }
    public static List<Inscripcion> cargarInscripciones(List<Materia> materias, List<Alumno> alumnos){
        List<Inscripcion> inscripciones=new ArrayList<>();

        try {
            String archInscripciones = "inscripciones.csv";
            for (String linea: Files.readAllLines(Paths.get(archInscripciones))){
                String[] lineaSeparada = linea.split(";");


                int posicionAlumno= -1;
                for (int i = 0; i < alumnos.size(); i++) {
                    Alumno alumno=alumnos.get(i);
                    if (alumno.nombre.equals(lineaSeparada[0])){
                        posicionAlumno = i;
                    }

                }

                int posicionMateria= -1;
                for (int i = 0; i < materias.size(); i++) {
                    Materia materia=materias.get(i);
                    if (materia.nombre.equals(lineaSeparada[2])){
                        posicionMateria = i;
                    }

                }

                if (posicionAlumno==-1) {
                    System.out.println(lineaSeparada[0] + "\t" + lineaSeparada[2] + "\t"+ "El alumno no existe");
                } else if (posicionMateria == -1) {
                    System.out.println(lineaSeparada[0] + "\t" + lineaSeparada[2] + "\t"+ "La materia no existe");
                }else {
                    Materia materia=materias.get(posicionMateria);
                    Alumno alumno= alumnos.get(posicionAlumno);

                    boolean alumnoPuedeCursarMateria = materias.get(posicionMateria).puedeCursar(alumnos.get(posicionAlumno));
                    if (alumnoPuedeCursarMateria){
                        inscripciones.add(new Inscripcion(alumno,materia));
                        System.out.println(lineaSeparada[0] + "\t" + lineaSeparada[2] + "\t"+ "Aprobada");
                    } else {
                        System.out.println(lineaSeparada[0] + "\t" + lineaSeparada[2] + "\t"+ "Rechazada");
                    }
                }






            }

        }catch (IOException e){

        }
        return inscripciones;

    }
}
