<%-- 
    Document   : pantallaGano
    Created on : 30-sep-2018, 6:12:50
    Author     : Alejandro
--%>

<%@page import="Logica.Tablero"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ganador</title>
    </head>
    <body background="madera.jpg">
        <%
            Tablero t1 = Tablero.saberEstado();
            String nombre = t1.getNombre();
            System.out.println("Entro pantalla gano");
            
            if(t1.jugadorAnterior=="1"){%>
              <h1 style="color: chartreuse">Felicidades jugador 1!</h1>
              <h2 style="color: chartreuse">Nombre: <%=nombre%>!</h2><br/>
            
        <%} else{%>
            <h1 style="color: chartreuse">Has perdido tu juego contra la maquina!</h1><br/>
            <%}%>
        <%for(int j=0; j<t1.getNumFil(); j++){%>
                <%for(int i=0; i<t1.getNumCol(); i++){
                    if(t1.getMatriz()[j][i]=="0"){%>

                    <img src="img1.jpg" style="width:80px; heigth:65px">
                    <%} else if(t1.getMatriz()[j][i]=="1"){%>
                    <img src="rojo.png" style="width:80px; heigth:65px">
                    
                    <%} else if(t1.getMatriz()[j][i]=="3"){%>
                        <%if(t1.jugadorAnterior=="1"){%>
                        <img src="rojoGano.png" style="width:80px; heigth:65px">
                        <%} else{%>
                        <img src="amarilloGano.png" style="width:80px; heigth:65px">
                        <%}%>

                    <%}else{%>
                    <img src="amarillo.png" style="width:80px; heigth:65px">
                    <%}%>

                <%//System.out.println(" ");%>

              <%}%><br/>
              <%}%>
                <form name="btns" method="post" action="/InformacionMovimientos">   
              <input type="hidden" name="gane" /> 
              <input type="button" name="0" value="Reiniciar" style="width:77px; background-color: sienna; color: chartreuse" onclick="{document.btns.gane.value=this.name;document.btns.submit();}"/> 
              </form>
              
    </body>
</html>
