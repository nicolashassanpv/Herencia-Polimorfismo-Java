import java.util.ArrayList;
import java.util.List;

abstract class Vuelo {
    protected int numeroVuelo;
    protected String origen;
    protected String destino;
    protected String fecha;

    public Vuelo(int numeroVuelo, String origen, String destino, String fecha) {
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
    }

    public abstract double calcularPrecio();
}

class VueloRegular extends Vuelo implements Promocionable {
    private int numeroAsientos;
    private static final double PRECIO_POR_ASIENTO = 100.0; // Precio base por asiento

    public VueloRegular(int numeroVuelo, String origen, String destino, String fecha, int numeroAsientos) {
        super(numeroVuelo, origen, destino, fecha);
        this.numeroAsientos = numeroAsientos;
    }

    @Override
    public double calcularPrecio() {
        return numeroAsientos * PRECIO_POR_ASIENTO;
    }

    @Override
    public void aplicarPromocion() {
        double precio = calcularPrecio();
        precio *= 0.9;
        System.out.println("Precio con promoción aplicado: " + precio);
    }
}

class VueloCharter extends Vuelo implements Promocionable {
    private double precioTotal;

    public VueloCharter(int numeroVuelo, String origen, String destino, String fecha, double precioTotal) {
        super(numeroVuelo, origen, destino, fecha);
        this.precioTotal = precioTotal;
    }

    @Override
    public double calcularPrecio() {
        return precioTotal;
    }

    @Override
    public void aplicarPromocion() {
        precioTotal *= 0.85;
        System.out.println("Precio con promoción aplicado: " + precioTotal);
    }
}

interface Promocionable {
    void aplicarPromocion();
}

class Reservas {
    private List<Vuelo> vuelos;

    public Reservas() {
        this.vuelos = new ArrayList<>();
    }

    public void agregarVuelos(Vuelo vuelo) {
        vuelos.add(vuelo);
    }

    public double calcularTotalReservas() {
        double total = 0.0;
        for (Vuelo vuelo : vuelos) {
            total += vuelo.calcularPrecio();
        }
        return total;
    }

    public void aplicarPromociones() {
        for (Vuelo vuelo : vuelos) {
            if (vuelo instanceof Promocionable) {
                ((Promocionable) vuelo).aplicarPromocion();
            }
        }
    }

    public void mostrarVuelos() {
        for (Vuelo vuelo : vuelos) {
            System.out.println("Vuelo " + vuelo.numeroVuelo + ": " + vuelo.origen + " -> " + vuelo.destino + " el " + vuelo.fecha);
        }
    }
}

public class SistemaReservas {
    public static void main(String[] args) {
        Reservas reservas = new Reservas();

        Vuelo vuelo1 = new VueloRegular(101, "Mendoza", "Misiones", "2024-12-07", 150);
        Vuelo vuelo2 = new VueloCharter(202, "Tierra del Fuego", "Santa fe", "2024-12-10", 5000);

        reservas.agregarVuelos(vuelo1);
        reservas.agregarVuelos(vuelo2);

        reservas.mostrarVuelos();

        double total = reservas.calcularTotalReservas();
        System.out.println("Precio total de las reservas: " + total);

        reservas.aplicarPromociones();
    }
}


