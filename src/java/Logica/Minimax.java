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
public class Minimax {
    private int columna;//columna que va retornar el algoritmo 
    private int maxProfundidad;//Es definida en la pagina principal con facil=3; medio=5; dificil=9
    private static Minimax min;//Instancia del singleton 
    private static final int maximo = 1000;
    private static final int minimo = -1000;
    
    Tablero t = Tablero.saberEstado();//Instacia tablero
    Validaciones v = new Validaciones();//Instancia Validaciones 
    
    //Set de maxProfundidad
    public void setMaxProfundidad(int maxProfundidad) {
        
        this.maxProfundidad = maxProfundidad;
    }
    
    //Instancia con el singleton
    public static Minimax saberEstado(){
        if(min == null){
            min = new Minimax();
        }
        return min;
    }
    
    int calcularResultado (int contadorMaquina, int camposColumna){   
        int puntajeMovimiento = 4 - camposColumna;
        if(contadorMaquina==0)return 0;
        else if(contadorMaquina==1)return 1*puntajeMovimiento;
        else if(contadorMaquina==2)return 10*puntajeMovimiento;
        else if(contadorMaquina==3)return 100*puntajeMovimiento;
        else return 1000;
    }
    
    //Funcion que recibe el tablero y lo evalua
    public int evaluar (Tablero t){
      
        int contadorMaquina=1;//contador de la computadora 
        int puntaje=0;//contador del puntaje 
        int camposFila = 0;//contador de espacios vacios 
        int k=0, camposVacio =0;//numero de movimientos 
        for(int i=5;i>=0;--i){//for para recorrrer las filas 
            for(int j=0;j<=6;++j){//for para recorrer todas las columnas
                
                if(t.getMatriz()[i][j].equals("0") || t.getMatriz()[i][j].equals("1")) continue; //Si es uno o es 0 no me interesa esa casilla
                
                //Evalue el tablero moviendose hacia la derecha en cada una de las filas
                if(j<=3){ 
                    for(k=1;k<4;++k){//Hace un for durante 3 veces
                        if(t.getMatriz()[i][j+k].equals("2"))contadorMaquina++;//Si la casilla es 2 le suma al contador de la maquina
                        else if(t.getMatriz()[i][j+k].equals("1")){contadorMaquina=0;camposFila = 0;break;}//Si la casilla es 1 reinicia el contador de espacios vacios y de la maquina
                        else camposFila++;//Sino es ni 1 ni 2 le suma al contador de espacios vacios 
                    }                     
                    
                    camposVacio = 0; 
                    if(camposFila>0) //Si el numero de espacios vacios es mayor que 0 hace un for 
                        //Lo que hace es recorrer las posiciones de la columna  y ver el numero de 0 que hay 
                        for(int c=1;c<4;++c){
                            for(int m=i; m<= 5;m++){
                             if(t.getMatriz()[m][j+c].equals("0"))camposVacio++;
                             else break;
                            } 
                        } 
                    
                    if(camposVacio!=0) {
                        puntaje += calcularResultado(contadorMaquina, camposVacio);
                    }
                    contadorMaquina=1;   
                    camposFila = 0;
                } 
                
                //Evalua el tablero moviendose hacia arriba
                if(i>=3){
                    for(k=1;k<4;++k){
                        if(t.getMatriz()[i-k][j].equals("2"))contadorMaquina++;
                        else if(t.getMatriz()[i-k][j].equals("1")){contadorMaquina=0;break;} 
                    } 
                    camposVacio = 0; 
                    
                    if(contadorMaquina>0){
                        for(int m=i-k+1; m<=i-1;m++){
                         if(t.getMatriz()[m][j].equals("0"))camposVacio++;
                            else break;
                        }  
                    }
                    if(camposVacio!=0) puntaje += calcularResultado(contadorMaquina, camposVacio);
                    contadorMaquina=1;  
                    camposFila = 0;
                }
                 
                //Evalua el tablero moviendose hacia la izquierda 
                if(j>=3){
                    for(k=1;k<4;++k){
                        if(t.getMatriz()[i][j-k].equals("2"))contadorMaquina++;
                        else if(t.getMatriz()[i][j-k].equals("1")){contadorMaquina=0; camposFila=0;break;}
                        else camposFila++;
                    }
                    camposVacio=0;
                    if(camposFila>0) 
                        for(int c=1;c<4;++c){
                            int column = j- c;
                            for(int m=i; m<= 5;m++){
                             if(t.getMatriz()[m][column].equals("0"))camposVacio++;
                                else break;
                            } 
                        } 
                    
                    if(camposVacio!=0) puntaje += calcularResultado(contadorMaquina, camposVacio);
                    contadorMaquina=1; //reinicia el contador en cada iteracion 
                    camposFila = 0;
                }
                 
                //Evalue el tablero moviendose hacia la  diagonal arriba derecha 
                if(j<=3 && i>=3){
                    for(k=1;k<4;++k){
                        if(t.getMatriz()[i-k][j+k].equals("2"))contadorMaquina++;
                        else if(t.getMatriz()[i-k][j+k].equals("1")){contadorMaquina=0;camposFila=0;break;}
                        else camposFila++;                        
                    }
                    camposVacio=0;
                    if(camposFila>0){
                        for(int c=1;c<4;++c){
                            int column = j+c, row = i-c;
                            for(int m=row;m<=5;++m){
                                if(t.getMatriz()[m][column].equals("0"))camposVacio++;
                                else if(t.getMatriz()[m][column].equals("2"));
                                else break;
                            }
                        } 
                        if(camposVacio!=0) puntaje += calcularResultado(contadorMaquina, camposVacio);
                        contadorMaquina=1;
                        camposFila = 0;
                    }
                }
                 
                //Evalua el tablero moviendose hacia la diagonal arriba izquierda 
                if(i>=3 && j>=3){
                    for(k=1;k<4;++k){
                        if(t.getMatriz()[i-k][j-k].equals("2"))contadorMaquina++;
                        else if(t.getMatriz()[i-k][j-k].equals("1")){contadorMaquina=0;camposFila=0;break;}
                        else camposFila++;                        
                    }
                    camposVacio=0;
                    if(camposFila>0){
                        for(int c=1;c<4;++c){
                            int column = j-c, row = i-c;
                            for(int m=row;m<=5;++m){
                                if(t.getMatriz()[m][column].equals("0"))camposVacio++;
                                else if(t.getMatriz()[m][column].equals("2"));
                                else break;
                            }
                        } 
                        if(camposVacio!=0) puntaje += calcularResultado(contadorMaquina, camposVacio);
                        contadorMaquina=1;
                        camposFila = 0;
                    }
                } 
            }
        }
        return puntaje;//Devuelve el puntaje de los movimientos 
    }
    
    //Pone la posicion en 0
    public void remover (int column){
        for(int i=0;i<=5;++i){
            if(t.getMatriz()[i][column] != "0") {
                t.setMatriz(i, column, "0");
                break;
            }
        }        
    }
    
    //Funcion de minimax que lo que hace es ir recorriendo hasta la maxima profundidad 
    public int minimax(int profundidadInicial , int turno, int alpha, int beta){
        //Forma en donde se aplica la poda alpha beta
        if(beta<=alpha){
            if(turno == 1) 
                return maximo ; 
            else 
                return minimo; }
        
        int ganar = v.hayGanador(t);//llama a la funcion que verifica si hay ganador 
        if(ganar==2){
            return maximo/2;
        }
        else if(ganar==1){
            return minimo/2;
        }
        else if(ganar==0){
            return 0;
        } 
        
        //Si llego al maximo de profundidad llama a la funcion de evaluar
        if(profundidadInicial==maxProfundidad){
            return evaluar(t);
        }
        
        int resultadoMax=minimo, resultadoMin = maximo;
                
        for(int j=0;j<=6;++j){
            
            int puntuacion = 0;
            
            if(t.getMatriz()[0][j]!=("0")) continue; //Valida si la pasicion no es 0
            
            if(turno==1){
                
                    t.insertar(String.valueOf(j), "2");//Funcion que se encarga de insertar en la matriz
                    puntuacion = minimax(profundidadInicial+1, 2, alpha, beta);
                    
                    if(profundidadInicial==0){
                        System.out.println("Resultado de fila "+j+" = "+puntuacion);
                        //para cada iteracion se hace esto para obtener en la mayor puntuacion el j a insertar 
                        if(puntuacion > resultadoMax){
                            columna = j;//asigna la mejor columna 
                        } 
                        if(puntuacion == maximo/2){
                            remover(j);
                            break;
                        }
                    }
                    
                    resultadoMax = Math.max(puntuacion, resultadoMax);//asigna el maximo de los dos a la variable resultadoMax
                    
                    alpha = Math.max(puntuacion, alpha);  //asigna el maximo entre alpha y puntuacion para aplicar la poda 
            } 
            else if(turno==2){
                    t.insertar(String.valueOf(j), "1");
                    puntuacion = minimax(profundidadInicial+1, 1, alpha, beta);
                    resultadoMin = Math.min(puntuacion, resultadoMin);
                    
                    beta = Math.min(puntuacion, beta); //asigna el minimo entre puntuacion y beta 
            }  
            remover(j);//Revierte la matriz en la columna j en 0´s 
            if(puntuacion == maximo || puntuacion == minimo) break; //detiene la funcion si ya llego a ser maximo o minimo
        }  
        return turno==1?resultadoMax:resultadoMin;//si el turno es 1 retorna resultadoMax sino retorna resultadoMin
    }
    
    //Aca es donde inicia la funcion del minimax 
    public int Inicio (){
        columna = -1;
        minimax(0, 1, minimo, maximo);
        return columna;
    }
    
}
