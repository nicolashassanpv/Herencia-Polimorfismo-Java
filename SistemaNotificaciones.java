abstract class CanalNotificacion {
    protected String usuario;
    protected String mensaje;

    public CanalNotificacion(String usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public abstract void enviarNotificacion();
}

interface Personalizable {
    void personalizarMensaje(String nuevoMensaje);
}

class CorreoElectronico extends CanalNotificacion implements Personalizable {
    private String direccionCorreo;

    public CorreoElectronico(String usuario, String mensaje, String direccionCorreo) {
        super(usuario, mensaje);
        this.direccionCorreo = direccionCorreo;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Enviando correo a " + direccionCorreo + ": " + mensaje);
    }

    @Override
    public void personalizarMensaje(String nuevoMensaje) {
        this.mensaje = nuevoMensaje;
    }
}

class MensajeTexto extends CanalNotificacion implements Personalizable {
    private String numeroTelefono;

    public MensajeTexto(String usuario, String mensaje, String numeroTelefono) {
        super(usuario, mensaje);
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Enviando mensaje de texto a " + numeroTelefono + ": " + mensaje);
    }

    @Override
    public void personalizarMensaje(String nuevoMensaje) {
        this.mensaje = nuevoMensaje;
    }
}

class Notificaciones {
    private CanalNotificacion[] canales;
    private int cantidad;

    public Notificaciones() {
        canales = new CanalNotificacion[10];
        cantidad = 0;
    }

    public void agregarCanal(CanalNotificacion canal) {
        if (cantidad < canales.length) {
            canales[cantidad] = canal;
            cantidad++;
        } else {
            System.out.println("No se pueden agregar más canales de notificación.");
        }
    }

    public void enviarNotificaciones() {
        for (int i = 0; i < cantidad; i++) {
            canales[i].enviarNotificacion();
        }
    }

    public void personalizarMensajes(String nuevoMensaje) {
        for (int i = 0; i < cantidad; i++) {
            if (canales[i] instanceof Personalizable) {
                ((Personalizable) canales[i]).personalizarMensaje(nuevoMensaje);
            }
        }
    }

    public void mostrarCanales() {
        for (int i = 0; i < cantidad; i++) {
            System.out.println(canales[i].getClass().getSimpleName() + " - Usuario: " + canales[i].usuario);
        }
    }
}

public class SistemaNotificaciones {
    public static void main(String[] args) {
        Notificaciones notificaciones = new Notificaciones();

        CanalNotificacion correo = new CorreoElectronico("Juan Perez", "Tu pedido ha sido enviado", "juan.perez@example.com");
        CanalNotificacion mensaje = new MensajeTexto("Maria Garcia", "Tu pedido ha sido entregado", "123456789");

        notificaciones.agregarCanal(correo);
        notificaciones.agregarCanal(mensaje);

        notificaciones.mostrarCanales();
        notificaciones.personalizarMensajes("Tu pedido está en camino");
        notificaciones.enviarNotificaciones();
    }
}

