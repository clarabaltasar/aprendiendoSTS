<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
<form action="tablon2">
Nombre: <input type="text" name="Nombre"><br>
Asunto: <input type="text" name="Asunto"><br>
Comentario: <input type="text" name="Comentario"><br>
<input type="submit" value="Enviar">
</form>

<p th:each="anuncio : ${anuncios}">
<span th:text="'Nombre: ' + ${anuncio.nombre}"></span><br/>
<span th:text="'Asunto: '+ ${anuncio.asunto}"></span><br/>
</p>
</body>
</html>