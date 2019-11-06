/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


/**
 *
 * @author Alejandro
 */
public class Controlador {
    private static final Tablero t = Tablero.saberEstado();//Crea una instancia de tipo singleton para crearlo sino esta creado
    
    //Llama a la funcion insertar con la columna que eligio el usuario
    public void moverFichaUsuario(String id){
        
        t.insertar(id,t.jugador);  
        t.jugador="2";
        t.jugadorAnterior="1";
    }
    
    //Aqui se debe llamar al minimax para saber la columna donde se pone la ficha 
    public void moverFichaCompu(){
        Minimax m = Minimax.saberEstado();
        
        
        
        t.jugador="2";
        t.jugadorAnterior="1";
        if(t.bandera==true){
            t.insertar("3",t.jugador);
            t.jugador="1";
            t.jugadorAnterior="2";
            t.bandera=false;
        }
        else{
            int numero =  m.Inicio();
            t.posCompu = numero;
            String st = Integer.toString(numero);
            System.out.println("Minimax: "+st);
            t.insertar(st,t.jugador);//llama a insertar con la pos que devuelve ek minimax
            t.jugador="1";
            t.jugadorAnterior="2";
            
        } 
        
        t.verMatriz();//Imprime la matriz para ver que la compu inserto
    }
}
