
package co.com.strevens.data;

import co.com.strevens.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless // Convierte a EJB
public class PersonaDaoImpl implements PersonaDao{
    
    //Habilitar uso de JPA - nombre en : persistence.xml
    @PersistenceContext (unitName = "PersonaPU")
    EntityManager entityManger;
    
    @Override
    public List<Persona> encontrarTodasPersonas() {
        //Query especificado en la entidad - domain.Persona
        return entityManger.createNamedQuery("Persona.encontrarTodasPersonas").getResultList();
    }

    @Override
    public Persona encontrarPersona(Persona persona) {
        //Busca la persona por su ID
        return entityManger.find(Persona.class, persona.getIdPersona());
    }

    @Override
    public void insertarPersona(Persona persona) {
        entityManger.persist(persona);
        //recuperar llave primaria - se enviara al cliente esta llave primaria
        entityManger.flush();
    }

    @Override
    public void actualizarPersona(Persona persona) {
        entityManger.merge(persona);
    }

    @Override
    public void eliminarPersona(Persona persona) {
        //usar merge para actualizar primero el estado antes de eliminar
        entityManger.remove(entityManger.merge(persona));
    }
    
}
