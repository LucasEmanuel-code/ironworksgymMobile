package com.example.ironworksgym;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ironworksgym.AgendamentoApp.Inicio; // Certifique-se de que o destino esteja correto
import com.example.ironworksgym.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao extends AppCompatActivity {

    // Método para conectar ao SQL Server
    public static Connection conectar() {
        Connection conn = null;
        String url = "jdbc:sqlserver://<IP_DO_SERVIDOR>:1433;databaseName=IronWorksGym";
        String user = "sa";
        String password = "@ITB123456";
        
        try {
            // Carrega o driver JDBC do SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Estabelece a conexão
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return conn;
    }
}

}

