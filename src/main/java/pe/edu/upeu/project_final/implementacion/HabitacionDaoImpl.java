package pe.edu.upeu.project_final.implementacion;

import pe.edu.upeu.project_final.interfaces.IHabitacionDao;

import pe.edu.upeu.project_final.modelo.Habitacion;
import pe.edu.upeu.project_final.utils.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDaoImpl implements IHabitacionDao {
    @Override
    public boolean insert(Habitacion h) {
        boolean flag = false;
        String query = "INSERT INTO habitacion(numero,numeroPiso,numeroHabitacion,tipo,precio,estado)VALUES(?,?,?,?,?,?);";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1,h.getNumero());
            st.setInt(2,h.getNumeroPiso());
            st.setInt(3,h.getIdHabitacion());
            st.setObject(4,h.getTipo());
            st.setDouble(5,h.getPrecio());
            st.setString(6,h.getEstado());
            st.executeUpdate();
            flag=true;
        } catch (SQLException e) {
            System.out.println("Error al insertar Habitacion:" + e.getMessage());
        }
        return flag;
    }


    @Override
    public boolean update(Habitacion h) {
        boolean flag = false;
        String query = " UPDATE habitacion SET numero=?, numeroPiso =?, numeroHabitacion=?,  " +
                "tipo=?, precio=?, estado=?" +
                " WHERE idhabitacion=?";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1,h.getNumero());
            st.setInt(2,h.getNumeroPiso());
            st.setInt(3,h.getNumeroHabitacion());
            st.setString(4,h.getTipo());
            st.setDouble(5,h.getPrecio());
            st.setString(6,h.getEstado());
            st.setInt(7,h.getIdHabitacion());
            st.executeUpdate();
            flag=true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar Habitacion:" + e.getMessage());
        }
        return flag;
    }

    @Override
    public Habitacion searchById(int id) {
        Habitacion hab = null;
        String query = "SELECT * FROM habitacion WHERE idhabitacion=?;";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1,id);
            try( ResultSet rs = st.executeQuery()) {
                if (rs.next()){
                    hab = new Habitacion();
                    hab.setIdHabitacion(rs.getInt("idhabitacion"));
                    hab.setNumero(rs.getInt("numero"));
                    hab.setNumeroPiso(rs.getInt("numeroPiso"));
                    hab.setNumeroHabitacion(rs.getInt("numeroHabitacion"));
                    hab.setTipo(rs.getString("tipo"));
                    hab.setPrecio(rs.getDouble("precio"));
                    hab.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar habitacion por Id:" + e.getMessage());
        }
        return hab;
    }
    @Override
    public boolean delete(Habitacion h) {
        boolean flag = false;
        String query = "DELETE FROM habitacion WHERE idhabitacion=?";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1,h.getIdHabitacion());
            st.executeUpdate();
            flag=true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar habitacion:" + e.getMessage());
        }
        return flag;
    }

    @Override
    public List<Habitacion> listAll() {
        List<Habitacion> lista = new ArrayList<>();
        String query = "SELECT * FROM habitacion;";
        try (
                Connection cn = ConexionDB.getConnection();
                PreparedStatement st = cn.prepareStatement(query);
                ResultSet rs = st.executeQuery();
        ) {
            while (rs.next()){
               Habitacion hab = new Habitacion();
                hab.setIdHabitacion(rs.getInt("idhabitacion"));
                hab.setNumero(rs.getInt("numero"));
                hab.setNumeroPiso(rs.getInt("numeroPiso"));
                hab.setNumeroHabitacion(rs.getInt("numeroHabitacion"));
                hab.setTipo(rs.getString("tipo"));
                hab.setPrecio(rs.getDouble("precio"));
                hab.setEstado(rs.getString("estado"));
                lista.add(hab);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar habitaciones:" + e.getMessage());
        }
        return lista;
    }

    @Override
    public Habitacion searchByDni(int Id) {
        Habitacion hab = null;
        String query = "SELECT * FROM habitacion WHERE id=?;";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, Id);
            try( ResultSet rs = st.executeQuery()) {
                if (rs.next()){
                    hab = new Habitacion();
                    hab.setIdHabitacion(rs.getInt("idhabitacion"));
                    hab.setNumero(rs.getInt("numero"));
                    hab.setNumeroPiso(rs.getInt("numeroPiso"));
                    hab.setNumeroHabitacion(rs.getInt("numeroHabitacion"));
                    hab.setTipo(rs.getString("tipo"));
                    hab.setPrecio(rs.getDouble("precio"));
                    hab.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar habitacion por Id:" + e.getMessage());
        }
        return hab;
    }
}

