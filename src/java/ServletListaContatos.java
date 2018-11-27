/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import conexao.Mysql;
import java.sql.Connection;
import java.util.ArrayList;
/**
 *
 * @author paulo.silva
 */
@WebServlet(urlPatterns = {"/servlet-lista-contatos"})
public class ServletListaContatos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {

        Mysql banco = new Mysql();
        
        banco.conecta();

        String sql = "Select * from contatos";
        
        ArrayList contatos = banco.selectContatos(sql);

        requisicao.setAttribute("contatos", contatos);

        requisicao.getRequestDispatcher("/WEB-INF/contatos/pagina-lista-contatos.jsp")
                .forward(requisicao, resposta);
        
    }
}
