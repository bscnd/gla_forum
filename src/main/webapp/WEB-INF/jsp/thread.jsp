<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" xml:lang="en-gb"
      lang="en-gb">
<head>

    <title>THREAD</title>
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
                        <c:choose>
                        <c:when test="${!empty sessionScope.sessionUtilisateur}">
                            <%-- Si l'utilisateur existe en session, alors on affiche son username. --%>
                        <p class="succes">Vous êtes connecté(e) en tant que : ${sessionScope.sessionUtilisateur.username}</p>
                        </c:when>

                        <c:otherwise>
                        <%-- TODO : Bouton vers "se connecter" --%>
                            <a href="http://127.0.0.1:8082/forum-0.0.1-SNAPSHOT/login">Se connecter</a>
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
                <td valign="middle" align="left" colspan="4" nowrap="nowrap">
                    <a href="http://127.0.0.1:8082/forum-0.0.1-SNAPSHOT/createtopic"><img
                        src="fichiers/button_topic_new.gif" alt="Post new topic" title="Post new topic"/></a>&nbsp;
                    <a
                        href="http://127.0.0.1:8082/forum-0.0.1-SNAPSHOT/answer?topicid=<c:out value="${concernedTopic.id}"/>"><img src="fichiers/button_topic_reply.gif" alt="Reply to topic"
                                      title="Reply to topic"/></a>
                </td>
            </tr>
            </tbody>
        </table>

        <br clear="all"/>

        <c:forEach items="${topicMessages}" var="message">

            <table class="tablebg" cellspacing="1" width="100%">
                <tbody>

                <tr class="row2">

                    <td valign="middle" align="center"><b class="postauthor"><c:out value="${message.auteur}"/></b>
                    </td>
                    <td width="100%" height="25">
                        <table cellspacing="0" width="100%">
                            <tbody>
                            <tr>

                                <td class="gensmall" width="100%">
                                    <div style="float: left;">&nbsp;
                                        <b>Fil de discussion :</b> <c:out value="${concernedTopic.topicname}"/>
                                    </div>
                                    <div style="float: right;">
                                        <b>Posted:</b> NOT SETUP&nbsp;
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>

                <tr class="row2">

                    <td class="profile" valign="top">
                        <table cellspacing="4" align="center" width="150">

                        </table>
                        <span class="postdetails"> <b>Posts:</b> NOT SETUP
						</span>

                    </td>
                    <td valign="top">
                        <table cellspacing="5" width="100%">
                            <tbody>
                            <tr>
                                <td>
                                    <div class="postbody"><c:out value="${message.contenu}"/></div>
                                    <br clear="all"/><br/>
                                    <table cellspacing="0" width="100%">
                                        <tbody>
                                        <tr valign="middle">
                                            <td class="gensmall" align="right"></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td class="spacer" colspan="2" height="1"><img src="fichiers/spacer.gif" alt="" width="1" height="1"/>
                    </td>
                </tr>
            </table>


        </c:forEach>


    </div>

    <br clear="all"/>
    <table class="tablebg" style="margin-top: 5px;" cellspacing="1"
           cellpadding="0" width="100%">
        <tbody>
        <tr>
            <td class="row1">
                <a href="http://127.0.0.1:8082/forum-0.0.1-SNAPSHOT/accueil">Index du forum</a>
            </td>
        </tr>
        </tbody>
    </table>

</div>
</body>
</html>
