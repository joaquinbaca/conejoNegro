package com.example.dgp.conejonegro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by alexr on 25/11/2017.
 */

public class verSalas extends AppCompatActivity {
    private ArrayList<Sala> salas;
    ConexionBD conexion = null;
    Sala s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listasalas);

        salas = new ArrayList<Sala>();
        try {
            conexion = new ConexionBD();
            ResultSet rs = conexion.hacerConsulta("SELECT * FROM SALA");
            String descripcion = rs.getString("texto");
            ArrayList<Elemento> elementos= new ArrayList<Elemento>();
            while(rs.next()) {
                int planta = rs.getInt("planta");
                String idZona = rs.getString("idZona");
                ResultSet rs2 = conexion.hacerConsulta("SELECT * FROM ZONA WHERE idZona='"+idZona+"'");
                rs2.next();
                String nombre = rs2.getString("nombre");
                ResultSet rs1 = conexion.hacerConsulta("SELECT * FROM ELEMENTO WHERE idZona='"+idZona+"'");
                while(rs1.next()){
                    Elemento e = new Elemento(rs1.getString("nombre"),rs1.getString("descripcion"));
                    elementos.add(e);
                }
                s = new Sala(elementos, planta, nombre);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        salas.add(s);
    }
}