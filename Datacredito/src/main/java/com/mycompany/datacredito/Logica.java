package com.mycompany.datacredito;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gtior
 */
public class Logica implements Serializable{
    
    List<Persona> persona = new ArrayList();
    Scanner sc = new Scanner(System.in);
    
    public Logica(){
    
      menu();
    }
    
    public void quemarDatos(Persona persona){
        
        try{
                File archivo2 = new File("Archivos/Registros.txt");
                ArrayList<Persona> lista2 = new ArrayList();
                if(archivo2.exists()){
                    ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(archivo2));
                    lista2 = (ArrayList<Persona>) objectInput.readObject();
                }
                eliminarRepetidos(lista2, persona);
                lista2.add(persona);
                ObjectOutputStream escritura = new ObjectOutputStream(new FileOutputStream(archivo2));
                escritura.writeObject(lista2);
                escritura.close();
        }catch(FileNotFoundException ex){
            System.out.println("No se encontro el archivo!!");    
        }catch(IOException ex){
            System.out.println("Error en el archivo!!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarRegistro()
    {
        int codigoEliminar = 0;
        int id = 0;
             try(FileInputStream fis=new FileInputStream("Archivos/Registros.txt")){
            try {
                 ObjectInputStream entrada = new ObjectInputStream(fis);
                Object actual = null;
                ArrayList<Persona> lista = null;
                int contador = 0;
                int cont = 0;
                Persona persona = null;
                System.out.println("Ingrese el codigo del usuario al que desea elmimar su record: ");
                Scanner sc = new Scanner(System.in);
                id = sc.nextInt();
                System.out.println("Ingrese el codigo del record a eliminar: ");
                codigoEliminar = sc.nextInt();
                try {
                    while((actual = entrada.readObject()) != null){
                    lista = (ArrayList<Persona>) actual;
                    if(lista.get(contador).getCodigo() == id){
                        
                        if(lista.get(contador).getRecord().get(cont).getCodigo() == codigoEliminar && lista.get(contador).getRecord().get(cont).getEstado() == "false"){
                        lista.get(contador).getRecord().remove(lista.get(contador).getRecord().get(cont));
                        quemarDatos(persona = (Persona)lista.get(contador));
                    }
                        cont ++;
                    }
                    contador++;
                }//while
                entrada.close();
                } catch (Exception e) {
                }
                
            } catch (Exception e) {
            }
          
        }catch(IOException ex){
            System.out.println("Error");
        }
   
    }
    
    private Persona eliminarRepetidos(ArrayList<Persona> lista,Persona persona){
        ArrayList<Persona> prueba = new ArrayList<>(lista);
        try {
            for (Persona lista1 : prueba) {
            if(lista1.getCodigo()== persona.getCodigo()){
                lista.remove(lista1);
            }
        }
        } catch (Exception e) {
        }
        
        return persona;
    }//eliminarRepetidos
    
    public void lectura() {
        try(FileInputStream fis=new FileInputStream("Archivos/Registros.txt")){
            try {
                 ObjectInputStream entrada = new ObjectInputStream(fis);
                Object actual = null;
                ArrayList<Persona> lista = null;
                while((actual = entrada.readObject()) != null){
                    lista = (ArrayList<Persona>) actual;
                    for(int i = 0;i<lista.size();++i){
                        System.out.println("Identificacion: "+lista.get(i).getCodigo());
                        System.out.println("Nombre: "+lista.get(i).getNombre());
                        System.out.println("Apellido: "+lista.get(i).getApellido());
                        for(int j = 0;j<lista.get(i).getRecord().size();++j){
                            System.out.println("Codigo Record: "+lista.get(i).getRecord().get(j).getCodigo());
                            System.out.println("Nombre Empresa: "+lista.get(i).getRecord().get(j).getEmpresa());
                            System.out.println("Estado: "+lista.get(i).getRecord().get(j).getEstado());
                            System.out.println("Valor: "+lista.get(i).getRecord().get(j).getValor());
                    }//for
                    }      
                }//while
                entrada.close();
            } catch (Exception e) {
            }
          
        }catch(IOException ex){
            System.out.println("Error");
        }
    }
    public void lectura2(Record record,int id) {
        try(FileInputStream fis=new FileInputStream("Archivos/Registros.txt")){
            try {
                 ObjectInputStream entrada = new ObjectInputStream(fis);
                Object actual = null;
                ArrayList<Persona> lista = null;
                int contador = 0;
                Persona persona = null;
                while((actual = entrada.readObject()) != null){
                    lista = (ArrayList<Persona>) actual;
                    if(lista.get(contador).getCodigo() == id){
                        lista.get(contador).getRecord().add(record);
                        quemarDatos(persona = (Persona)lista.get(contador));
                    }
                    contador++;
                }//while
                entrada.close();
            } catch (Exception e) {
            }
          
        }catch(IOException ex){
            System.out.println("Error");
        }
    }
    
    
    public  void guardar() {
        
        System.out.println("Ingrese la cedula: ");
        int ced = sc.nextInt();
        System.out.println("Ingrese el nombre: ");
        String nom = sc.next();
        System.out.println("Ingrese el apellido: ");
        String ap = sc.next();
        Persona per = new Persona(ced, nom, ap);
        quemarDatos(per);
     
    }
    
    public void guardarRecord(){
        System.out.println("Ingrese el codigo del Record");
        int cod = sc.nextInt();
        System.out.println("Ingrese el Nombre de la empresa");
        String nombre = sc.next();
        System.out.println("Ingrese el estado del Record");
        String estado = sc.next();
        System.out.println("Ingrese el valor del Record");
        float valor = sc.nextFloat();
        System.out.println("Ingrese el codigo de la persona para agregar el Record");
        int codP = sc.nextInt();
        Record recor = new Record(cod, nombre, estado, valor);
        lectura2(recor,codP);

        
    }
    
    
   public void menu(){
{
        int opcion = 0;
        do
        {
            System.out.println("\t\tBienvenido");
            System.out.println("1. Agregar Persona");
            System.out.println("2. Agregar Record de persona");
            System.out.println("3. Eliminar Record de persona");
            System.out.println("4. Listar todo");
            System.out.println("5. Salir.");   
            System.out.println("\nIngrese una opcion de las siguientes por favor: ");
            try
            {
                opcion = sc.nextInt();
                switch(opcion)
                {
                    case 1:
                       guardar(); 
                    break;
                    case 2:
                        guardarRecord();
                    break;
                    case 3:
                        eliminarRegistro();
                    break;
                    case 4:
                        lectura();
                    break;
                    case 5:
                        System.out.println("Gracias...vemos");
                        System.exit(0);
                    break;
                    default:
                    System.out.println("Opcion no valida...");
                    break;
                }
            }catch(InputMismatchException e)
            {
                System.out.println("Opcion no valida...\nIntentelo de nuevo..\n");
                opcion = 0;
                sc= new Scanner(System.in);
                menu();
            }
        }while(opcion != 5);         
    }
  }}
    
    

