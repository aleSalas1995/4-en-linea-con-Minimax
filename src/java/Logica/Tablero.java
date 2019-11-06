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
public class Tablero {
    //Variables privadas
    private static Tablero tab;//instancia privada
    private String[][] matriz;  // [fila][columna]
    private int numFil, numCol;
    private String nombre;
    
    //Variables publicas
    public int filaActual;
    public int columnaActual;
    public String jugador;
    public String jugadorAnterior;
    public int posCompu;
    public String inserto;
    public boolean bandera=true;
    
    //Get and set**************************************************************************************
    public String[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int x, int y, String pos) {
        this.matriz[x][y]=pos;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(this.nombre==null){
            this.nombre = nombre;
        }
    }

    public int getNumFil() {
        return numFil;
    }

    public void setNumFil(int numFil) {
        this.numFil = numFil;
    }

    public int getNumCol() {
        return numCol;
    }

    public void setNumCol(int numCol) {
        this.numCol = numCol;
    }
//*******************************************************************************************************
    //Instancia de tipo tablero que recibe filas y columnas
    //Crea la matriz de 0
    private Tablero(int numCol, int numFil) {
        //Asigna las variables
        this.numFil = numFil;
        this.numCol = numCol;
        this.filaActual=0;
        this.jugador="1";
        this.jugadorAnterior ="";
        
        
        //Crea la matriz con su tamano
        matriz = new String[numFil][numCol];
        // crea las celdas en 0
        for (int f = 0; f < numFil; f++){
            for (int c = 0; c < numCol; c++){
                matriz[f][c] = "0";
            }
        }
    }
    
    //Se aplica metodo singleton para no crear una instancia nueva y sino esta creado lo crea
    public static Tablero saberEstado(){
        if(tab == null){
            tab = new Tablero(7,6);
        }
        return tab;
    }
    
    //Funcion de insertar que recibe la columna a la que inserta
    public boolean insertar(String pos, String jugador){
        int x = Integer.parseInt(pos);//Pasa la columna a int
        for(int i = this.numFil-1; i>=0; i--){//Recorre la columna pafra colocar donde hay espacio vacio
            if(matriz[i][x]=="0"){
                if(this.jugador.equals("1")){//Verifica si es jugador 1
                    this.filaActual=i;//guarda la fila actual 
                    this.columnaActual=x;
                    this.matriz[i][x]=jugador;//Le asigna un 1 en esa posicion de la matriz
                    this.inserto="1";//Marca que ya el jugador 1 inserto
                    //verMatriz();
                    return true;
                }else{
                    this.filaActual=i;//guarda la fila actual
                    this.columnaActual=x;
                    this.matriz[i][x]=jugador;//Le asigna un 2 en esa posicion de la matriz 
                    this.inserto="0";//pone en 0 para que el jugador 1 inserte
                    //verMatriz();
                    return true;
                }
                
            }
        }
        return false;
    }
    
    //Funcion que pone la matriz de nuevo en 0 y reinicia las variables como si fueran creadas de nuevo 
    public void limpiar(){
        for (int f = 0; f < numFil; f++){
            for (int c = 0; c < numCol; c++){
                matriz[f][c] = "0";
            }
        }
        this.nombre="";
        this.filaActual=0;
        this.jugador="1";
        this.jugadorAnterior ="";
        
        
    }
    
    //Imprime la matriz en consolo para ver como esta formada
    public void verMatriz(){
        for (int f = 0; f < numFil; f++){
            for (int c = 0; c < numCol; c++){
                System.out.print(this.matriz[f][c]);
            }
            System.out.println("");
        }
    }
}
