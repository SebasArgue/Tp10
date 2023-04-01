import java.util.ArrayList;
import java.util.List;

public class Materia {
    String nombre;
    List<Materia> correlativas;

    public Materia(String nombre){
        this.nombre=nombre;
        correlativas=new ArrayList<>();
    }

    public boolean puedeCursar(Alumno alumno){
        boolean correlativasAprobadas= true;
        for (int i = 0; i < correlativas.size(); i++) {
            Materia materia= correlativas.get(i);
            int posicionMateria = alumno.materiasAprobadas.indexOf(materia);
            if (posicionMateria == -1){
                correlativasAprobadas=false;
            }
        }
        return correlativasAprobadas;

    }
}
