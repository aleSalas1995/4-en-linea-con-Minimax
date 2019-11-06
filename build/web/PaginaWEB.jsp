<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cuatro en linea Web</title>
</head>   
<body background="madera.jpg">
    <h1 style="color: chartreuse">Bienvenido al juego cuatro en linea web creado por Alejandro Salas!</h1> 
          <form name="frm" method="post" action="/InformacionMovimientos"> 
              
              <h2 style="color: chartreuse">Digite el nombre del usuario: <input type="text" name="txtNombre"/></h2><br/><br/>
              <input type="hidden" name="hdnbt" /> 
              <input type="button" name="2" value="Facil" style="width:100px; background-color: burlywood" onclick="{document.frm.hdnbt.value=this.name;document.frm.submit();}"/>
              <input type="button" name="5" value="Medio" style="width:100px; background-color: burlywood" onclick="{document.frm.hdnbt.value=this.name;document.frm.submit();}"/>
              <input type="button" name="8" value="Dificil" style="width:100px; background-color: burlywood" onclick="{document.frm.hdnbt.value=this.name;document.frm.submit();}"/><br/><br/>
              <img src="fondo.PNG" style="width:600px; heigth:500px">
              
              
          </form>
                
</body>
</html>