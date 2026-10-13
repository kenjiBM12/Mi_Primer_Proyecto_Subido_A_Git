package pe.edu.upeu.project_final.test;


import pe.edu.upeu.project_final.implementacion.HabitacionDaoImpl;

import pe.edu.upeu.project_final.interfaces.IHabitacionDao;

import pe.edu.upeu.project_final.modelo.Habitacion;

import java.util.List;

public class testHabitacion {

        static IHabitacionDao hab = new HabitacionDaoImpl();

        public static void listar() {
            List<Habitacion> lista = hab.listAll();
            if (lista != null && !lista.isEmpty()) {
                for (Habitacion habitacion : lista) {
                    System.out.println("ID_HABITACION:" + habitacion.getIdHabitacion());
                    System.out.println("NUMERO:" + habitacion.getNumero());
                    System.out.println("NUMERO_PISO:" + habitacion.getNumeroPiso());
                    System.out.println("NUMERO_HABITACION:" + habitacion.getNumeroHabitacion());
                    System.out.println("TIPO:" + habitacion.getTipo());
                    System.out.println("PRECIO:" + habitacion.getPrecio());
                    System.out.println("ESTADO: " + habitacion.getEstado());
                }
            } else {
                System.out.println("no hay registros");
            }
        }
        public static void insertar(){
            Habitacion ha = new Habitacion();
            ha.setNumero(002);
            ha.setNumeroPiso(2);
            ha.setNumeroHabitacion(102);
            ha.setTipo("matrimonial");
            ha.setPrecio(120.0);
            ha.setEstado("disponible");
            boolean result = hab.insert(ha);
            if (result){
                System.out.println("insert success");
            }else{
                System.out.println("insert error");
            }
        }

        public static void eliminar(){
            Habitacion h = new Habitacion();
            h.setIdHabitacion(2);
            boolean result = hab.delete(h);
            if (result){
                System.out.println("delete success");
            }else{
                System.out.println("delete error");
            }
        }


        public static void main(String[] args) {
            System.out.println("************");
            listar();
            System.out.println("************");
            insertar();
            System.out.println("************");
            listar();

        }


    }


