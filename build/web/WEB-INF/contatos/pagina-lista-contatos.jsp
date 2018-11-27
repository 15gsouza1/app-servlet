<%-- 
    Document   : pagina-lista-contatos
    Created on : 27/11/2018, 15:42:24
    Author     : paulo.silva
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="agenda.Contato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda de Contatos</title>
        <!-- ${pageContext.request.contextPath} -->
        <link href="/agenda-contatos/css/estilo.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <h1>
            Agenda
            <a href="/agenda-contatos/servlet-inserir-contato" class="btn btn-criar btn-azul">Criar</a>
        </h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>NOME</th>
                    <th>ENDEREÇO</th>
                    <th>EMAIL</th>
                    <th>DATA NASCIMENTO</th>
                    <th>AÇÕES</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Contato> lista = (ArrayList <Contato>) request.getAttribute("contatos");
                    for(int i = 0; i < lista.size(); i++) {
                        Contato contato = lista.get(i);
                %>
                <tr>
                    <td> <%=contato.getId() %> </td>
                    <td> <%=contato.getNome() %> </td>
                    <td> <%=contato.getEndereco() %> </td>
                    <td> <%=contato.getEmail() %> </td>
                    <td> <%=contato.getData_nascimento() %> </td>
                    <td>
                        <a class="btn btn-azul" href="/agenda-contatos/servlet-editar-contato?id=<%=contato.getId()%>"> Editar </a>
                        <a class="btn btn-azul" href="/agenda-contatos/servlet-excluir-contato?id=<%=contato.getId()%>"> Excluir </a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
