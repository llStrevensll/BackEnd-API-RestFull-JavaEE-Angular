
package co.com.strevens.servicio;

import co.com.strevens.data.PersonaDao;
import co.com.strevens.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

@Stateless
@Path("/personas") // path a utilizar en este webservice
public class PersonaServiceRS {
    
    //Inyectar implementacion del objeto persona DAO
    @Inject
    private PersonaDao personaDao;
    
    @GET
    @Produces(value=MediaType.APPLICATION_JSON)
    public List<Persona> listarPersonas(){
        
        List<Persona> personas = personaDao.encontrarTodasPersonas();
        
        System.out.println("Personas Encontradas: " + personas);
        return personas;
    }
    
    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("{id}") // Hace referencia al path: /personas/(id) ej: /personas/1
    public Persona encontrarPersona(@PathParam("id") int id){
        
        Persona persona = personaDao.encontrarPersona(new Persona(id));
        
        System.out.println("Persona Encontrada:" + persona);
        return persona;
    }
    
    @POST
    @Consumes(value= MediaType.APPLICATION_JSON)
    @Produces(value= MediaType.APPLICATION_JSON)
    public Persona agregarPersona(Persona persona){
        
        personaDao.insertarPersona(persona);
        
        System.out.println("Persona Agregada: " + persona);//incluye la llave primaria
        return persona;
    }
    
    @PUT
    @Consumes(value= MediaType.APPLICATION_JSON)
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response modificarPersona(@PathParam("id") int id, Persona personaModificada){
        
        //recuperar la persona - id
        Persona persona = personaDao.encontrarPersona(new Persona(id));//Instancia con el id
        if(persona != null){
            
            personaDao.actualizarPersona(personaModificada);
            
            System.out.println("Persona Modificada");
            return Response.ok().entity(personaModificada).build();//responder con la persona modificada si todo esta correcto
                
        }else{
            return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    @DELETE
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response eliminarPersona(@PathParam("id") int id){
        
        personaDao.eliminarPersona(new Persona(id));
        
        System.out.println("Persona Eliminada con el id:" + id);
        return Response.ok().build();
    }
        
    
}
