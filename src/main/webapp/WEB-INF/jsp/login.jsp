<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Connexion</title>
	<link rel="stylesheet" href="fichiers/oc_stylesheet.css" type="text/css" />
</head>
<body>
<form method="post" action="connexion">
	<fieldset>
		<legend>Connexion</legend>
		<p>Vous pouvez vous connecter via ce formulaire.</p>

		<label for="username">Nom d'utilisateur <span class="requis">*</span></label>
		<input type="email" id="email" name="email" value="<c:out value="${utilisateur.username}"/>" size="20" maxlength="60" />
		<span class="erreur">${form.erreurs['username']}</span>
		<br />

		<label for="password">Mot de passe <span class="requis">*</span></label>
		<input type="password" id="password" name="password" value="" size="20" maxlength="20" />
		<span class="erreur">${form.erreurs['password']}</span>
		<br />

		<input type="submit" value="Connexion" class="sansLabel" />
		<br />

		<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
	</fieldset>
</form>
</body>
</html>