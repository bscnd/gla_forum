<%-- TODO : Feuille de style --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>Inscription</title>
    <link rel="stylesheet" href="fichiers/oc_stylesheet.css" type="text/css" />
</head>
<body>
<form method="post" action="RegisterServlet">
    <fieldset>
        <legend>Inscription</legend>
        <p>Vous pouvez vous inscrire via ce formulaire.</p>

        <label for="username">Nom d'utilisateur <span class="requis">*</span></label>
        <input type="text" id="username" name="username" value="" size="20" maxlength="60" />
        <span class="erreur">${erreurs['username']}</span>
        <br />

        <label for="password">Mot de passe <span class="requis">*</span></label>
        <input type="password" id="password" name="password" value="" size="20" maxlength="20" />
        <span class="erreur">${erreurs['password']}</span>
        <br />

        <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
        <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
        <span class="erreur">${erreurs['confirmation']}</span>
        <br />

        <input type="submit" value="Inscription" class="sansLabel" />
        <br />
    </fieldset>
</form>
</body>
</html>