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
<form method="post" action="login">
	<fieldset>
		<legend>Connexion</legend>
		<p>Vous pouvez vous connecter via ce formulaire.</p>

		<label for="username">Nom d'utilisateur <span class="requis">*</span></label>
		<input type="text" id="username" name="username" value="<c:out value="${utilisateur.username}"/>" size="20" maxlength="60" />
		<span class="erreur">${form.erreurs['username']}</span>
		<br/>

		<label for="password">Mot de passe <span class="requis">*</span></label>
		<input type="password" id="password" name="password" value="" size="20" maxlength="20" />
		<span class="erreur">${form.erreurs['password']}</span>
		<br/>

		<input type="submit" value="Connexion" class="sansLabel" />
		<br/>

		<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

		<%-- Vérification de la présence d'un objet utilisateur en session --%>
		<c:if test="${!empty sessionScope.sessionUtilisateur}">
			<%-- Si l'utilisateur existe en session, alors on affiche son username. --%>
			<p class="succes">Vous êtes connecté(e) en tant que : ${sessionScope.sessionUtilisateur.username}</p>
			<a href="http://127.0.0.1:8082/forum-0.0.1-SNAPSHOT/accueil">Allez à l'accueil</a>
		</c:if>
	</fieldset>
</form>
</body>
</html>