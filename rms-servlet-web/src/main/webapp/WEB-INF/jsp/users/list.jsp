<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "tx" tagdir = "/WEB-INF/tags/" %>
<tx:wrapper>
<main class="mdl-layout__content">
    <div class="mdl-grid">
        <div class="mdl-cell mdl-cell--12-col">
            <table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
                <thead>
                    <tr>
                        <th class="mdl-data-table__cell--non-numeric">User Name</th>
                        <th class="mdl-data-table__cell--non-numeric">Password</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items = "${users}" var="user">
                    <tr>
                        <td class="mdl-data-table__cell--non-numeric"><c:out value = "${user.userName}"/></td>
                    <td class="mdl-data-table__cell--non-numeric"><c:out value = "${user.password}"/></td>
                    <td>
                        <a href="form?action=edit&id=${user.id}">
                            <button class="mdl-button mdl-js-button mdl-button--icon">
                                <i class="material-icons">edit</i>
                            </button>
                        </a>
                    </td>
                    <td>
                        <a href="${(loggedUser.id == user.id)?'#':"form?action=delete&id="}${(loggedUser.id == user.id)?'':user.id}" >
                            <button class="mdl-button mdl-js-button mdl-button--icon" ${(loggedUser.id == user.id)?'disabled':''} >
                                <i class="material-icons">delete</i>
                            </button>
                        </a>
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>  
        </div>
        <div class="mdl-cell mdl-cell--12-col">
            ${wmx}
        </div>
    </div>
</main>
</tx:wrapper>
