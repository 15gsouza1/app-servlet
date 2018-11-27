/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import agenda.Contato;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author paulo.silva
 */
public class Mysql {
    public Connection conexao;

    public Connection conecta ()
    {
        /**
         * Detalhes para conectar ao banco dedados
         * host, banco, usuario e senha
         */
        String host = "localhost";
        String banco = "agenda_contatos";
        String usuario = "root";
        String senha = "1234";
       
        try {
            /**
             * Neste momento, estamos registrando o driver do MYSQL
             */
            Class.forName("com.mysql.jdbc.Driver");
            
            String url_conexao = "jdbc:mysql://" + host + "/" + banco + "?useTimezone=true&serverTimezone=America/Fortaleza";
            
            conexao = DriverManager.getConnection(url_conexao, usuario, senha);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("-------");
            System.out.println(e.getMessage());
            System.out.println("-------");
            System.exit(1);
        }

        return conexao;
    }
    
    public Contato selectPeloId(Integer id)
    {
        Contato contato = new Contato();

        try {
            String sql = "select * from contatos where id = ?";

            PreparedStatement instrucao = conexao.prepareStatement(sql);
            
            instrucao.setInt(1, id);
            ResultSet resultado = instrucao.executeQuery();
        
            if(resultado.next()) {
                contato.setId(resultado.getInt("id"));
                contato.setEmail(resultado.getString("email"));
                contato.setNome(resultado.getString("nome"));
                contato.setEndereco(resultado.getString("endereco"));
                contato.setData_nascimento(resultado.getDate("data_nascimento"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return contato;
    }
    
    public Boolean salvaContato(Contato contato)
    {
        Boolean resultado = false;

        try {
           String sql = "insert into contatos (nome, endereco, email, data_nascimento) values (?, ?, ?, ?);";
            PreparedStatement instrucao = conexao.prepareStatement(sql);
            
            instrucao.setString(1, contato.getNome());
            instrucao.setString(2, contato.getEndereco());
            instrucao.setString(3, contato.getEndereco());
            instrucao.setDate(4, new Date(contato.getDate().getTime()));
            
            resultado = instrucao.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return resultado;
    }

    public Boolean editaContato(Contato contato)
    {
        Boolean resultado = false;

        try {
           String sql = "update contatos set nome=?, endereco=?, email=?, data_nascimento=? where id=?";
            PreparedStatement instrucao = conexao.prepareStatement(sql);
            
            instrucao.setString(1, contato.getNome());
            instrucao.setString(2, contato.getEndereco());
            instrucao.setString(3, contato.getEmail());
            instrucao.setDate(4, new Date(contato.getDate().getTime()));
            instrucao.setInt(5, contato.getId());
            
            resultado = instrucao.execute();
        } catch (SQLException e) {
            System.out.println("Falha ao executar o update");
            System.out.println(e.getMessage());
        }
        
        return resultado;
    }

    public Boolean insereContato(Contato contato)
    {
        Boolean resultado = false;

        try {
            String sql = "insert into contatos (nome, endereco, email, data_nascimento) values (?, ?, ?, ?);";
            PreparedStatement instrucao = conexao.prepareStatement(sql);
            
            instrucao.setString(1, contato.getNome());
            instrucao.setString(2, contato.getEndereco());
            instrucao.setString(3, contato.getEmail());
            instrucao.setDate(4, new Date(contato.getDate().getTime()));
            
            resultado = instrucao.execute();
        } catch (SQLException e) {
            System.out.println("Falha ao executar o insert");
            System.out.println(e.getMessage());
        }
        
        return resultado;
    }

    public ArrayList selectContatos(String sql)
    {
        ArrayList<Contato> contatos = new ArrayList<>();
        try {
            PreparedStatement instrucao = conexao.prepareStatement(sql);
            
            ResultSet resultado = instrucao.executeQuery();
            
            while(resultado.next()) {
                Contato contato = new Contato();
                
                contato.setId(resultado.getInt("id"));
                contato.setEmail(resultado.getString("email"));
                contato.setNome(resultado.getString("nome"));
                contato.setEndereco(resultado.getString("endereco"));
                contato.setData_nascimento(resultado.getDate("data_nascimento"));
                
                contatos.add(contato);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        
        return contatos;
    }
}
