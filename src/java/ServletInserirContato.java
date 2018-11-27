/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import agenda.Contato;
import conexao.Mysql;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(urlPatterns = {"/servlet-inserir-contato"})
public class ServletInserirContato extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {
        requisicao.getRequestDispatcher("/WEB-INF/contatos/pagina-criar-contato.jsp").forward(requisicao, resposta);
    }
    
    @Override
    protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {

        Mysql banco = new Mysql();
        
        banco.conecta();
        
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

        Contato contato = new Contato();
        
        contato.setNome(nome);
        contato.setEmail(email);
        contato.setEndereco(endereco);
        contato.setData_nascimento(data_nascimento_convertidade);
        
        banco.insereContato(contato);
        
        resposta.sendRedirect("/agenda-contatos/servlet-lista-contatos");

    }
}
