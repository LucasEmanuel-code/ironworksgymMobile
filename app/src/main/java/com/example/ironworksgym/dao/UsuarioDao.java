package com.example.ironworksgym.dao;

import com.example.ironworksgym.conexao.Conexao;
import com.example.ironworksgym.model.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.transform.Result;

public class UsuarioDao {

    public Usuario selecionaUsuario(String email, String senha){
        try {
            Connection conn = Conexao.conectar();
            if(conn != null){
                String sql  = "select * from Usuario where Nome = '"+email+"' and senha = '"+senha+"'";
                Statement st = null;
                st = conn.createStatement();

                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    Usuario usu = new Usuario();
                    usu.setCodigo(rs.getInt(1));
                    usu.setEmail(rs.getString(2));
                    usu.setSenha(rs.getString(3));

                    conn.close();
                    return usu;
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
