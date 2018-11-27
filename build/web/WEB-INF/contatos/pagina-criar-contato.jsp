<%-- 
    Document   : pagina-lista-contatos
    Created on : 27/11/2018, 15:42:24
    Author     : paulo.silva
--%>

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
        <form method="POST" action="/agenda-contatos/servlet-inserir-contato">
        <h1>
            Agenda <button href="#" class="btn btn-criar btn-azul">Salvar</button>
        </h1>
            <div class="campo">
                <label>Nome:</label> <input type="text" value="" name="nome"/>
            </div>
            <div class="campo">
                <label>Email:</label> <input type="text" value="" name="email"/>
            </div>
            <div class="campo">
                <label>Endereço:</label> <input type="text" value="" name="endereco"/>
            </div>
            <div class="campo">
                <label>Data Nascimento:</label> <input type="text" value="" name="data_nascimento"/>
            </div>
        </form>
    </body>
</html>
