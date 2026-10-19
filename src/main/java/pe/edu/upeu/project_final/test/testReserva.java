package pe.edu.upeu.project_final.test;

import pe.edu.upeu.project_final.implementacion.ReservaDaoImpl;
import pe.edu.upeu.project_final.interfaces.IReservaDao;
import pe.edu.upeu.project_final.modelo.Cliente;
import pe.edu.upeu.project_final.modelo.Habitacion;
import pe.edu.upeu.project_final.modelo.Reserva;

import java.time.LocalDate;
import java.util.List;

public class testReserva {
    static IReservaDao dao = new ReservaDaoImpl();
    public void Insert(){
        Reserva r = new Reserva();
        Cliente c = new Cliente();
        Habitacion h = new Habitacion();

        r.setFechaReserva(LocalDate.now());
        r.setFechaIngreso(LocalDate.parse("2026-11-07"));
        r.setFechaSalida(LocalDate.parse("2026-11-10"));
        r.setEstado("Activa");
        r.setTotal(160.00);

        c.setIdcliente(3);
        r.setCliente(c);

        h.setIdhabitacion(1);
        r.setHabitacion(h);

        boolean result = dao.insert(r);
        if (result){
            System.out.println("reserva success");
        } else {
            System.out.println("error de reserva");
        }

    }
    public static void listar() {
        List<Reserva> list = dao.listAll();
        if (list != null && !list.isEmpty()) {
            for (Reserva res : list) {
                System.out.println("ID_RESERVA: "+res.getIdreserva());
                System.out.println("FECHA_RESERVA: "+res.getFechaReserva());
                System.out.println("FECHA_INGRESO: "+res.getFechaIngreso());
                System.out.println("FECHA_SALIDA: "+res.getFechaSalida());
                System.out.println("HAB_NUM: " + res.getHabitacion().getNumero());
                System.out.println("PRECIO: " + res.getHabitacion().getPrecio());
                System.out.println("NOMBRE: " + res.getCliente().getNombre());
                System.out.println("TOTAL: " + res.getTotal());

            }
        } else {
            System.out.println("no hay registros");
        }
    }
    public static void main(String[] args) {
testReserva t = new testReserva();
t.listar();

    }


}
