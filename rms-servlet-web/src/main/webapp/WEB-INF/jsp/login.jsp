<%@ taglib prefix = "tx" tagdir = "/WEB-INF/tags/" %>
<tx:wrapper>
<main class="mdl-layout__content">
    <div class="mdl-card mdl-shadow--6dp">
        <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
            <h2 class="mdl-card__title-text">Acme Co. - Login</h2>
        </div>
        <form action="login" method="POST" >
            <div class="mdl-card__supporting-text">
                <div class="mdl-textfield mdl-js-textfield">
                    <input class="mdl-textfield__input" type="text" id="username" name="userName" value="${userName}" required />
                    <label class="mdl-textfield__label" for="username">Username</label>
                </div>
                <div class="mdl-textfield mdl-js-textfield">
                    <input class="mdl-textfield__input" type="password" id="userpass" name="password" required />
                    <label class="mdl-textfield__label" for="userpass">Password</label>
                </div>
            </div>
            <div class="mdl-card__actions mdl-card--border">
                <button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Log in</button>
                ${wmx}
            </div>
        </form>
    </div>
</main>
</tx:wrapper>