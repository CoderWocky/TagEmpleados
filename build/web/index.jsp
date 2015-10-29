<%-- 
    Document   : index
    Created on : 29-oct-2015, 8:40:26
    Author     : alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="tags" prefix="custom"%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Empleados</title>
    <style>
      body {
        font-family: Calibri, sans-serif;
        font-size: 16px;
      }
      table, th, td {
        border: 1px solid darkblue;
        background-color: lightblue;
      }
      table {
        border-collapse: collapse;
      }
      th, td {
        padding: 5px;
      }
      td.number {
        text-align: right;
      }
    </style>
  </head>
  <body>
    <h1>Tabla de Empleados desde Custom Tags</h1>
    <custom:listaempleados/>
  </body>
</html>
