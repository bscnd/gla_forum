<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>Composer</title>
    <link rel="stylesheet" href="fichiers/oc_stylesheet.css" type="text/css" />
</head>
<body>
<form method="post" action="answer?topicid=<c:out value="${topicId}"/>">
    <fieldset>
        <legend>Rédiger un message</legend>
        <p>Ecrire un message dans ce fil de discussion.</p>

        <label for="contenu">Nom de la discussion <span class="requis">*</span></label>
        <textarea id="contenu" name="contenu" rows="2" cols="40" tabindex="40" placeholder="Votre message..."></textarea>
        <span class="erreur">${form.erreurs['contenu']}</span>
        <br />

        <input type="submit" value="Créer" class="sansLabel" />
        <br/>

        <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
        <a href="http://127.0.0.1:8082/forum-0.0.1-SNAPSHOT/accueil">Allez à l'accueil</a>
    </fieldset>
</form>
</body>
</html>