package models;

import controllers.Conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Autor {
    private int Id;
    private String Name;
    private String Affiliations;
    private String Email;
    private String Citas;
    private String AutorID;

    public String getCitas() {
        return Citas;
    }

    public void setCitas(String citas) {
        Citas = citas;
    }


    public Autor(int id, String name, String affiliations, String email) {
        Id = id;
        Name = name;
        Affiliations = affiliations;
        Email = email;
    }

    public Autor() {
        this.Citas = "0";
        this.Name = "Nombre";
        this.Affiliations = "Afiliacion";
        this.Id = 0;
        this.AutorID = "0";
        this.Email = "Email-e";
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return this.Id;
    }

    public String getAutorID() {
        return AutorID;
    }

    public void setAutorID(String autorID) {
        AutorID = autorID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAffiliations() {
        return Affiliations;
    }

    public void setAffiliations(String affiliations) {
        Affiliations = affiliations;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    /**
     * Utiliza un mapa y navega dentro de el para inizializar los parametros del objeto
     * @map Mapa con atributos del objeto
     */
    public void fromJsonToAutor(Map map) {
        this.Name = (String) map.get("name");
        this.AutorID = (String) map.get("author_id");
        this.Affiliations = (String) map.get("affiliations");
        this.Email = (String) map.get("email");

        try {
            double a = (Double) map.get("cited_by");
            this.Citas = a + "";
        } catch (Exception e) {
            this.Citas = "0";
            System.out.println(e.getMessage());
        }
    }

    /**
     * PErmite insertar en la base de datos el objeto actual
     * @return boolean
     *
     */
    public boolean insertar() {
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement consulta = cn.prepareStatement("INSERT INTO autor( name, author_id, affiliations, email, cited_by) VALUES ( ?, ?, ?, ? , ? )");
            consulta.setString(1, this.Name);
            consulta.setString(2, this.AutorID);
            consulta.setString(3, this.Affiliations);
            consulta.setString(4, this.Email);
            consulta.setInt(5, (int) Double.parseDouble(this.Citas));
            int row = consulta.executeUpdate();
            cn.close();
            return row > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }


    }
    /**
     * Permite actualizar en la base de datos el objeto actual
     * @return boolean
     *
     */
    public boolean actualizar() {

        try {
            Connection cn = Conexion.conectar();
            CallableStatement consulta = cn.prepareCall("{CALL autor_actualizar(?,?,?,?,?)}");
            consulta.setInt(1, this.Id);
            consulta.setString(2, this.Name);
            consulta.setString(3, this.AutorID);
            consulta.setString(4, this.Affiliations);
            consulta.setString(5, this.Email);
            consulta.setInt(6, (int) Double.parseDouble(this.Citas));
            consulta.execute();
            cn.close();
            return true;
        } catch (Exception e) {
            return false;
        }


    }
    /**
     * Permite eliminar en la base de datos el objeto actual
     * @return boolean
     *
     */
    public boolean eliminar() {

        try {
            Connection cn = Conexion.conectar();
            CallableStatement consulta = cn.prepareCall("DELETE FROM `autor` WHERE - ?");
            consulta.setInt(1, this.Id);
            consulta.execute();
            cn.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    /**
     * Permite obtener en la base de datos el objeto actual por el AutorID
     * @return boolean
     *
     */
    public void obtener() {

        try {
            Connection cn = Conexion.conectar();
            CallableStatement consulta = cn.prepareCall("{CALL autor_obtener(?)}");
            consulta.setString(1, this.AutorID);
            consulta.execute();
            ResultSet rs = consulta.getResultSet();
            if (rs.next()) {
                this.Id = rs.getInt("Id");
                this.Name = rs.getString("name");
                this.AutorID = rs.getString("author_id");
                this.Affiliations = rs.getString("affiliations");
                this.Email = rs.getString("email");
                this.Citas = rs.getInt("cited_by") + "";
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

    }
    /**
     * Obtener todo los elementos de esta clase de la base de datos
     * @return List<Autor>
     *
     */
    public static List<Autor> obtenerTodos() {
        List<Autor> lista = new ArrayList<>();
        try {
            Connection cn = Conexion.conectar();
            CallableStatement consulta = cn.prepareCall("{CALL autor_todo()}");
            consulta.execute();
            ResultSet rs = consulta.getResultSet();

            while (rs.next()) {
                Autor autor = new Autor();

                autor.Id = rs.getInt("Id");
                autor.Name = rs.getString("name");
                autor.AutorID = rs.getString("author_id");
                autor.Affiliations = rs.getString("affiliations");
                autor.Email = rs.getString("email");
                autor.Citas = rs.getInt("cited_by") + "";
                lista.add(autor);

            }

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return lista;
    }
}
