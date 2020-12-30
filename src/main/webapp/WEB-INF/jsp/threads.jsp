<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" xml:lang="en-gb"
      lang="en-gb">
<head>
    <meta charset="utf-8"/>
    <title>Connexion</title>
    <link rel="stylesheet" href="fichiers/style.css" type="text/css"/>
</head>

<body class="ltr">
<div id="wrapcentre">
    <div id="pagecontent">
        <table class="tablebg" style="margin-top: 5px;" cellspacing="1"
               cellpadding="0" width="100%">
            <tbody>
            <tr>
                <td class="row1">
                    <p class="breadcrumbs">
                        <%-- Vérification de la présence d'un objet utilisateur en session --%>
                        <c:choose>
                            <c:when test="${!empty sessionScope.sessionUtilisateur}">
                                <%-- Si l'utilisateur existe en session, alors on affiche son username. --%>
                                <p class="succes">Vous êtes connecté(e) en tant que : ${sessionScope.sessionUtilisateur.username}</p>
                            </c:when>

                            <c:otherwise>
                                <%-- TODO : Bouton vers "se connecter" --%>
                                <p class="succes">Vous n'êtes pas connecté</p>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </td>
            </tr>
            </tbody>
        </table>

        <br clear="all"/>

        <table cellspacing="1" width="100%">
            <tbody>
            <tr>
                <td valign="middle" align="left"><img src="fichiers/button_topic_new.gif" alt="Post new topic"
                                                      title="Post new topic"/></td>
            </tr>
            </tbody>
        </table>

        <br clear="all"/>

        <table class="tablebg" cellspacing="1" width="100%">
            <tbody>
            <tr>
                <td class="cat" colspan="4">
                    <table cellspacing="0" width="100%">
                        <tbody>
                        <tr class="nav">
                            <td valign="middle">&nbsp;</td>
                            <td valign="middle" align="right">&nbsp;</td>
                        </tr>
                        </tbody>
                    </table>
                </td>
            </tr>

            <tr>
                <th>&nbsp;Topics&nbsp;</th>
                <th>&nbsp;Auteur&nbsp;</th>
                <!-- TODO : Ajouter le nombre de réponses au topic -->
            </tr>

            <c:forEach items="${allTopics}" var="topic">

                <tr>
                    <td class="row1"><a class="topictitle" href="#"><c:out value="${topic.topicname}"/></a></td>
                    <td class="row2" align="center" width="130"><p class="topicauthor"><a class="username-coloured"
                                                                                          href="#"><c:out
                            value="${topic.createur}"/></a></p></td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
        <br clear="all"/>
    </div>

    <table class="tablebg" style="margin-top: 5px;" cellspacing="1"
           cellpadding="0" width="100%">
        <tbody>
        <tr>
            <td class="row1">
                <p class="breadcrumbs">Index du forum</p>
            </td>
        </tr>
        </tbody>
    </table>

</div>

</body>
</html>
