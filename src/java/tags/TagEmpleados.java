/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tags;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author alumno
 */
public class TagEmpleados extends TagSupport {

  private ResultSet obtenerRegistros() throws SQLException {
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    Connection conexion = DriverManager.getConnection(
        "jdbc:oracle:thin:@localhost:1521:XE", "system", "javaoracle");

    String query = "SELECT APELLIDO, OFICIO, SALARIO, DEPT_NO FROM EMP";
    return conexion.createStatement().executeQuery(query);
  }
  
  private String htmlCabecera() {
    return "<table><thead><tr><th>APELLIDO</th><th>OFICIO</th><th>SALARIO</th>"
        + "<th>DEPARTAMENTO</th></tr></thead><tbody>";
  }
  
  private String htmlFila(ResultSet registro) throws SQLException {
    return String.format("<tr><td>%s</td><td>%s</td><td class='number'>%d</td>"
    + "<td class='number'>%d</td></tr>",
        registro.getString("APELLIDO"), registro.getString("OFICIO"), 
        registro.getInt("SALARIO"), registro.getInt("DEPT_NO"));
  }
  
  private String htmlCierre() {
    return "</tbody></table>";
  }

  @Override
  public int doStartTag() throws JspTagException {
    return SKIP_BODY;
  }

  @Override
  public int doEndTag() throws JspTagException {
    try {
      ResultSet datos = obtenerRegistros();
      JspWriter out = pageContext.getOut();
      out.write(htmlCabecera());
      while (datos.next()) {
        out.write(htmlFila(datos));
      }
      out.write(htmlCierre());
      return EVAL_PAGE;
    } catch (SQLException | IOException ex) {
      Logger.getLogger(TagEmpleados.class.getName()).log(Level.SEVERE, null, ex);
      return SKIP_PAGE;
    }
  }
}
