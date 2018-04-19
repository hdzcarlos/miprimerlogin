package com.cice.servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ServicioLogin extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     /*
        System.out.println("Merda");
        System.out.println("Como verte");
        System.out.println("Agrrrrrgdsssssss");
        System.out.println("Estooo vaa a explotar.-");
        */

        System.out.println("llamando a doPost");
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        System.out.println("usuario " + user + " $ Password" +pass);
        PrintWriter writer = resp.getWriter();

       // String sql = "INSERT INTO login VALUES ( null, '"+user+"', '"+pass+"')";
        String sql = "SELECT * FROM login WHERE user = '"+user+"' AND pass = '" + pass + "'";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/login","root","root");
            Statement statement = connection.createStatement();
            //statement.executeUpdate(sql);
            ResultSet rs = statement.executeQuery(sql);
            if(rs.first()){
                //tenemos una coincidenia
                writer.print("WELCOME USUARIO: "+user);
            }else {
                //No hay coincidencias
                writer.print("403 - DENIED ACCESS");

            }
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
