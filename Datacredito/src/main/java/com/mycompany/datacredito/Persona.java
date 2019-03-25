package com.mycompany.datacredito;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author gtior
 */
public class Persona implements Serializable {
    
    private int codigo;
    private String nombre;
    private String apellido;
    private ArrayList<Record> record = new ArrayList();
   

    /**
     * Constructor para instanciar la clase
     * @param codigo
     * @param nombre
     * @param apellido 
     */
    public Persona(int codigo, String nombre, String apellido) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the record
     */
    public ArrayList<Record> getRecord() {
        return record;
    }

    /**
     * @param record the record to set
     */
    public void setRecord(ArrayList<Record> record) {
        this.record = record;
    }

  
    
    
    
}
