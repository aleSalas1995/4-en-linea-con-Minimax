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
public class Validaciones {
    private static final Tablero t = Tablero.saberEstado();//Instancia de la clase tablero
    public String[] registro1 = new String[4];
    public String[] registro2 = new String[4];
    int jugador;
    
    
    //Fiuncion que valida si existe ganador 
    public int hayGanador (Tablero t){
        
        int contadorMaquina = 0, contadorUsuario = 0;//inicializa contadores de puntaje 
        for(int fila =5;fila>=0;--fila){
            for(int columna=0;columna<=6;++columna){
                if(t.getMatriz()[fila][columna].equals("0")) continue;
                
                //Recorre la matriz hacia la derecha para ir verificando si hay iguales 
                //Se va sumando a los contadores y agregando al registro para cuando alguno gano tener donde se formo la 4 en linea 
                //Devuelve 1 si gano el usuario y 2 si gano la compu
                if(columna<=3){//Esto se da porque si la columna es 4,5,6 no se puede formar las 4 
                    for(int k=0;k<4;++k){ 
                            if(t.getMatriz()[fila][columna+k].equals("2")) {
                                contadorMaquina++;
                                registro2[k]=(String.valueOf(fila)+","+String.valueOf(columna+k));
                            }
                            else if(t.getMatriz()[fila][columna+k].equals("1")) {
                                contadorUsuario++;
                                registro1[k]=(String.valueOf(fila)+","+String.valueOf(columna+k));
                            }
                            else break; 
                    }
                    //Comparaciones para saber si alguno de los dos gano 
                    if(contadorMaquina==4){
                        return 2;
                    } else if (contadorUsuario==4){
                        return 1;
                    }
                    contadorMaquina = 0; contadorUsuario = 0;
                } 
                
                //Recorre la matriz hacia arriba para ir verificando si hay iguales 
                //Se va sumando a los contadores y agregando al registro para cuando alguno gano tener donde se formo la 4 en linea 
                //Devuelve 1 si gano el usuario y 2 si gano la compu
                if(fila>=3){//Si es menor que 3 no se puede formar el 4 en linea, no vale la pena validar
                    for(int k=0;k<4;++k){
                        
                            if(t.getMatriz()[fila-k][columna].equals("2")) {
                                contadorMaquina++;
                                registro2[k]=(String.valueOf(fila-k)+","+String.valueOf(columna));//Va guardando en el registro 
                            }
                            else if(t.getMatriz()[fila-k][columna].equals("1")) {
                                contadorUsuario++;
                                registro1[k]=(String.valueOf(fila-k)+","+String.valueOf(columna));
                            }
                            else break;
                    }
                    if(contadorMaquina==4){
                        return 2;
                        
                    } else if (contadorUsuario==4){
                        return 1;
                    }
                    contadorMaquina = 0; contadorUsuario = 0;
                } 
                
                //Recorre la matriz hacia la diagonal arriba derecha para ir verificando si hay iguales 
                //Se va sumando a los contadores y agregando al registro para cuando alguno gano tener donde se formo la 4 en linea 
                //Devuelve 1 si gano el usuario y 2 si gano la compu
                if(columna<=3 && fila>= 3){
                    for(int k=0;k<4;++k){
                        if(t.getMatriz()[fila-k][columna+k].equals("2")) {
                            contadorMaquina++;
                            registro2[k]=(String.valueOf(fila-k)+","+String.valueOf(columna+k));
                        }
                        else if(t.getMatriz()[fila-k][columna+k].equals("1")) {
                            contadorUsuario++;
                            registro1[k]=(String.valueOf(fila-k)+","+String.valueOf(columna+k));
                        }
                        else break;
                    }
                    if(contadorMaquina==4){
                        return 2;
                    } else if (contadorUsuario==4){
                        return 1;
                    }
                    contadorMaquina = 0; contadorUsuario = 0;
                }
                
                //Recorre la matriz hacia la diagonal izquierda para ir verificando si hay iguales 
                //Se va sumando a los contadores y agregando al registro para cuando alguno gano tener donde se formo la 4 en linea 
                //Devuelve 1 si gano el usuario y 2 si gano la compu
                if(columna>=3 && fila>=3){
                    for(int k=0;k<4;++k){
                        if(t.getMatriz()[fila-k][columna-k].equals("2")) {
                            registro2[k]=(String.valueOf(fila-k)+","+String.valueOf(columna-k));
                            contadorMaquina++;
                        }
                        else if(t.getMatriz()[fila-k][columna-k].equals("1")) {
                            contadorUsuario++;
                            registro1[k]=(String.valueOf(fila-k)+","+String.valueOf(columna-k));
                        }
                        else break;
                    } 
                    if(contadorMaquina==4){
                        return 2; 
                    }
                        
                    else if (contadorUsuario==4){
                        return 1;
                    }
                    //Reiniciar contadores
                    contadorMaquina = 0; contadorUsuario = 0;
                }  
            }
        }
        
        //Valida si no hay empate
        for(int j=0;j<7;++j){
            if(t.getMatriz()[0][j].equals("0"))return -1;
        }
        //El juego se empato
        return 0;
    }
    
    //Verifica que la fila no sea mayor que la cantidad de filas 
    //ni que sea menor que 0 y valida si ficha no es 0, debe ser 1 o 2
    private boolean filaValida(int f, String ficha) {
        if(0 <= f && f < t.getNumFil()&&ficha!="0"){
            return true;
            
        }
        return false;
    }
    
    //Verifica que la columna no sea mayor que la cantidad de columnas 
    //ni que sea menor que 0 y valida si ficha no es 0, debe ser 1 o 2
    private boolean columnaValida(int c, String ficha) {
        if(0 <= c && c < t.getNumCol()&&ficha!="0"){
            return true;
            
        }
        return false;
    }
    
    //Esta funcion le pone 3 a las posiciones donde se formo el 4 en linea para luego ser impresas de distinto color
    public void llenarMatriz(String[] registro){
        for(int j=0; j<registro.length; j++){
            String posicion = registro[j];
            int x = Integer.parseInt("" + posicion.charAt(0));
            int y = Integer.parseInt("" + posicion.charAt(2));
            t.setMatriz(x,y,"3");
        }
        
        
    }
}
