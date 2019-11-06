<%-- 
    Document   : pantallaEmpato
    Created on : 01-oct-2018, 7:12:49
    Author     : Alejandro
--%>
<%@page import="Logica.Tablero"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empate</title>
    </head>
    <body background="madera.jpg">
        <h1 style="color: chartreuse">Ha habido empate entreJugador 1 y la computadora!</h1><br/>
        <%Tablero t1 = Tablero.saberEstado();
        
            for(int j=0; j<t1.getNumFil(); j++){
                for(int i=0; i<t1.getNumCol(); i++){
                    System.out.println("Empate");
                    if(t1.getMatriz()[j][i]=="1"){%>
                    <img src="rojo.png" style="width:80px; heigth:65px">

                    <%}else{
                    System.out.println("Empate");%>
                    <img src="amarillo.png" style="width:80px; heigth:65px">
                    <%}%>

              <%}%><br/>
              <%}%>
              <form name="btns" method="post" action="/InformacionMovimientos">   
              <input type="hidden" name="empate" /> 
              <input type="button" name="0" value="Reiniciar" style="width:77px; background-color: sienna; color: chartreuse" onclick="{document.btns.empate.value=this.name;document.btns.submit();}"/> 
              </form>
              </body>
</html>
