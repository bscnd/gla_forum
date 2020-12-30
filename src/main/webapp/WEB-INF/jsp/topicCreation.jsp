<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>Inscription</title>
    <link rel="stylesheet" href="fichiers/oc_stylesheet.css" type="text/css" />
</head>
<body>
<!-- TODO : action -->
<form method="post" action="createtopic">
    <fieldset>
        <legend>Nouveau topic</legend>
        <p>Créez un nouveau sujet de discussion.</p>

        <label for="topicname">Nom de la discussion <span class="requis">*</span></label>
        <!-- TODO : value -->
        <input type="text" id="topicname" name="topicname" value="" size="20" maxlength="60" />
        <span class="erreur">${form.erreurs['topicname']}</span>
        <br />

        <input type="submit" value="Créer" class="sansLabel" />
        <br/>

        <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
    </fieldset>
</form>
</body>
</html>