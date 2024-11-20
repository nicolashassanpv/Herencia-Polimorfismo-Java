abstract class MetodoPago {
    protected String titular;
    protected int numero;

    public MetodoPago(String titular, int numero) {
        this.titular = titular;
        this.numero = numero;
    }

    public abstract void realizarPago();
}

interface Cancelable {
    void cancelarPago();
}

class TarjetaCredito extends MetodoPago implements Cancelable {
    private String fechaExpiracion;
    private int codigoSeguridad;

    public TarjetaCredito(String titular, int numero, String fechaExpiracion, int codigoSeguridad) {
        super(titular, numero);
        this.fechaExpiracion = fechaExpiracion;
        this.codigoSeguridad = codigoSeguridad;
    }

    @Override
    public void realizarPago() {
        System.out.println("Pago realizado con tarjeta de crédito.");
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago con tarjeta de crédito cancelado.");
    }
}

class PayPal extends MetodoPago implements Cancelable {
    private String correoElectronico;

    public PayPal(String titular, int numero, String correoElectronico) {
        super(titular, numero);
        this.correoElectronico = correoElectronico;
    }

    @Override
    public void realizarPago() {
        System.out.println("Pago realizado con PayPal.");
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago con PayPal cancelado.");
    }
}

class Pagos {
    private MetodoPago[] metodosPago;
    private int cantidad;

    public Pagos() {
        metodosPago = new MetodoPago[10];
        cantidad = 0;
    }

    public void agregarMetodo(MetodoPago metodo) {
        if (cantidad < metodosPago.length) {
            metodosPago[cantidad] = metodo;
            cantidad++;
        } else {
            System.out.println("No se pueden agregar más métodos de pago.");
        }
    }

    public void realizarPagos() {
        for (int i = 0; i < cantidad; i++) {
            metodosPago[i].realizarPago();
        }
    }

    public void cancelarPagos() {
        for (int i = 0; i < cantidad; i++) {
            if (metodosPago[i] instanceof Cancelable) {
                ((Cancelable) metodosPago[i]).cancelarPago();
            }
        }
    }

    public void mostrarPagos() {
        for (int i = 0; i < cantidad; i++) {
            System.out.println(metodosPago[i].getClass().getSimpleName() + " - Titular: " + metodosPago[i].titular);
        }
    }
}

public class SistemadePagos {
    public static void main(String[] args) {
        Pagos pagos = new Pagos();

        MetodoPago tarjeta = new TarjetaCredito("Juan Perez", 123456789, "12/24", 123);
        MetodoPago paypal = new PayPal("Maria Garcia", 987654321, "maria@example.com");

        pagos.agregarMetodo(tarjeta);
        pagos.agregarMetodo(paypal);

        pagos.mostrarPagos();
        pagos.realizarPagos();
        pagos.cancelarPagos();
    }
}

