
package co.com.strevens.data;

import co.com.strevens.domain.Persona;
import java.util.List;


public interface PersonaDao {
    
    //Metodos
    public List<Persona> encontrarTodasPersonas();
    
    public Persona encontrarPersona(Persona persona);
    
    public void insertarPersona(Persona persona);
    
    public void actualizarPersona(Persona persona);
    
    public void eliminarPersona(Persona persona);
    
    
}
