<%-- 
    Document   : Jugadas
    Created on : 28-sep-2018, 22:14:38
    Author     : Alejandro
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Logica.Tablero" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cuatro en linea Web</title>
    </head>
    <body background="madera.jpg">  
        <h1 style="color: chartreuse">Iniciando el juego cuatro en linea web</h1>
          
        <%
            
            Tablero t1 = Tablero.saberEstado(); //Instancia de la clase tablero
            
        %>
        <table>
            <%if(t1.getNombre()!=""){//Valida si el nombre no es nulo%>
            <h2 style="color: chartreuse">Turno de: <%=t1.getNombre()%>, con el color rojo </h2><br/><br/>
        <%} else{%>
            <h2 style="color: chartreuse">Turno de: Usuario no digito nombre, con el color rojo</h2><br/><br/>
        <%}%>

            <%//Estos for recorren la matriz para imprimir 
                for(int j=0; j<t1.getNumFil(); j++){%>
                <%for(int i=0; i<t1.getNumCol(); i++){
                    if(t1.getMatriz()[j][i]=="0"){//Valida si es 0 imprime circulo blanco%>

                        <img src="img1.jpg" style="width:80px; heigth:65px">
                    <%} else if(t1.getMatriz()[j][i]=="1"){//Valida si es 1 imprime circulo rojo%>
                        <img src="rojo.png" style="width:80px; heigth:65px">

                    <%}else{//Sino imprime amarillo%>
                        <img src="amarillo.png" style="width:80px; heigth:65px">
                    <%}%>

              <%}%><br/>
              <%}%>


            <form name="btns" method="post" action="/InformacionMovimientos">   
          </table>
              <%//Botones que dirigen al servlet y de ahi se obtiene la opcion elegida por el usuario%>
              <input type="hidden" name="bt" /> 
              <input type="button" name="0" value="Insertar" style="width:77px; background-color: sienna; color: chartreuse" onclick="{document.btns.bt.value=this.name;document.btns.submit();}"/> 
              <input type="button" name="1" value="Insertar" style="width:77px; background-color: sienna; color: chartreuse" onclick="{document.btns.bt.value=this.name;document.btns.submit();}" /> 
              <input type="button" name="2" value="Insertar" style="width:77px; background-color: sienna; color: chartreuse" onclick="{document.btns.bt.value=this.name;document.btns.submit();}" /> 
              <input type="button" name="3" value="Insertar" style="width:77px; background-color: sienna; color: chartreuse" onclick="{document.btns.bt.value=this.name;document.btns.submit();}" /> 
              <input type="button" name="4" value="Insertar" style="width:77px; background-color: sienna; color: chartreuse" onclick="{document.btns.bt.value=this.name;document.btns.submit();}" /> 
              <input type="button" name="5" value="Insertar" style="width:77px; background-color: sienna; color: chartreuse" onclick="{document.btns.bt.value=this.name;document.btns.submit();}" /> 
              <input type="button" name="6" value="Insertar" style="width:77px; background-color: sienna; color: chartreuse" onclick="{document.btns.bt.value=this.name;document.btns.submit();}" /> 
              
              
          </form>
    </body>
</html>
