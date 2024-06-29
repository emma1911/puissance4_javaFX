package com.example.puissance4_javafx;

import PuissanceController.ControllerPuissance;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class AppPuissance extends Application{

     public void start(Stage stage) {
        BorderPane border = new BorderPane();
        ControllerPuissance controller = new ControllerPuissance(border);
        controller.getStart();
        //controller.listeJoueur();

        Scene scene = new Scene(border, 640, 480);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {

        /*String serveurBD = "jdbc:mysql://127.0.0.1:3306/puissance4";//?autoReconnect=true&useSSL=false";
        String login="root";
        String motPasse="";
        Connection conn;
        Statement ps;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(serveurBD, login, motPasse);
            String requete = "select from joueur ";
            ps = conn.createStatement();
            rs = ps.executeQuery(requete);
            while (rs.next()) {
                System.out.print("Code Joueur: " + rs.getLong("id")+" ");
                System.out.print("  Nom: " + rs.getString("Nom"));
                System.out.println("  score: " + rs.getString("score"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Execption");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("fin programme");*/


         launch();
    }

}