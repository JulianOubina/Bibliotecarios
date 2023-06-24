package Controller;
import model.Libro;
import model.Socio;
import java.util.*;

public class SocioCRUD {
    private static List<Socio> socios;

    public SocioCRUD() {
        socios = new ArrayList<>();
    }

    public void agregarSocio(Socio socio) {
        socios.add(socio);
        System.out.println("Socio ingresado con exito");
    }

    public void eliminarSocio(int id) {
        boolean entro = false;
        Iterator<Socio> iter = socios.iterator();
        while(iter.hasNext()) {
            Socio socio = iter.next();
            if (socio.getId() == id) {
                iter.remove();
                entro = true;
                break;
            }
        }
        if (entro){
            System.out.println("El socio se elimino con exito");
        }
        else {
            System.out.println("No se encontro un socio con ese id");
        }
    }

    public void actualizarSocio(int id){
        boolean entro = false;
        Scanner scanner = new Scanner(System.in);

        for(Socio socio : socios){
            if(socio.getId() == id){
                entro = true;
                System.out.println("Nombre: ");
                String nombreActualizado = scanner.nextLine();

                System.out.println("Apellido: ");
                String apellidoActualizado = scanner.nextLine();

                System.out.println("Dni: ");
                int dniActualizado = scanner.nextInt();

                scanner.nextLine();

                System.out.println("Email: ");
                String emailActualizado = scanner.nextLine();

                System.out.println("Telefono: ");
                int telefonoActualizado = scanner.nextInt();

                scanner.nextLine();

                System.out.println("Medio de comunicación: ");
                String medioComunicacionActualizado = scanner.nextLine();

                socio.setNombre(nombreActualizado);
                socio.setApellido(apellidoActualizado);
                socio.setDni(dniActualizado);
                socio.setEmail(emailActualizado);
                socio.setTelefono(telefonoActualizado);
                socio.setMedioComunicacion(medioComunicacionActualizado);
                break;
            }
        }
        if(entro){
            System.out.println("Los datos fueron actualizados");
        }
        else{
            System.out.println("No se encontraron socios con ese ID");
        }
    }
    public void mostrarSocios() {
        for (Socio socio : socios) {
            System.out.println("Id: " + socio.getId());
            System.out.println("Nombre: " + socio.getNombre());
            System.out.println("Apellido: " + socio.getApellido());
            System.out.println("Dni: " + socio.getDni());
            System.out.println("Email: " + socio.getEmail());
            System.out.println("Telefono: " + socio.getTelefono());
            System.out.println("Medio de comunicación: " + socio.getMedioComunicacion());
            System.out.println("----------------------------------");
        }
    }

    public boolean existeSocio(int id){
        for (Socio socio : socios){
            if(socio.getId() == id){
                return true;
            }
        }
        return false;
    }

    public static Socio buscarSocio(int id){
        for (Socio socio : socios){
            if(socio.getId() == id){
                return socio;
            }
        }
        return null;
    }

}