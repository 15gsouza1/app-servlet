/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import agenda.Contato;
import conexao.Mysql;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author paulo.silva
 */
@WebServlet(urlPatterns = {"/servlet-editar-contato"})
public class ServletEditarContato extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {
        String id = requisicao.getParameter("id");
        
        Mysql banco = new Mysql();
        
        banco.conecta();
        
        String sql = "Select * from contatos";
        
        Contato contato = banco.selectPeloId(Integer.parseInt(id));
        
        /**
         * Especificando que a variavel contato será também utilizada no JSP
         */
        requisicao.setAttribute("contato", contato);

        requisicao.getRequestDispatcher("/WEB-INF/contatos/pagina-editar-contato.jsp")
                .forward(requisicao, resposta);

    }

    @Override
    protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(requisicao.getParameter("id"));
        
        String nome = requisicao.getParameter("nome");
        String email = requisicao.getParameter("email");
        String endereco = requisicao.getParameter("endereco");
        String data_nascimento = requisicao.getParameter("data_nascimento");
        Date data_nascimento_convertidade = null;

        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        
            data_nascimento_convertidade = (Date) formatador.parse(data_nascimento);
        } catch (ParseException e) {
            System.out.println("Falha ao ler a data de nascimento");
            System.out.println(e.getMessage());
            System.exit(1);
        }
        
        Mysql banco = new Mysql();
        
        banco.conecta();

        Contato contato = new Contato();
        
        contato.setNome(nome);
        contato.setEmail(email);
        contato.setEndereco(endereco);
        contato.setData_nascimento(data_nascimento_convertidade);
        contato.setId(id);

        Boolean resultado = banco.editaContato(contato);
        
        resposta.sendRedirect("/agenda-contatos/servlet-lista-contatos");
    }
}
