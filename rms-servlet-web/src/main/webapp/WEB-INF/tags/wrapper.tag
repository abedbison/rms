<%@tag description="RMS Wrapper" pageEncoding="UTF-8"%>
<%@attribute name="content"%>
<%@ taglib prefix = "rms" uri = "/WEB-INF/tags/link.tld"%>
<%@ taglib prefix = "tx" uri = "/WEB-INF/tags/template.tld" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">

        <title>RMS</title>
        <meta name="description" content="Index">
        <meta name="author" content="Mitrais">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
        <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
        <rms:link type="stylesheet" href="css/styles.css?v=1.0"/>

    <!--[if lt IE 9]>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
    <![endif]-->
</head>

<body>
    <div class="mdl-layout mdl-js-layout mdl-color--grey-100">
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
            <header class="mdl-layout__header">
                <div class="mdl-layout__header-row">
                    <!-- Title -->
                    <span class="mdl-layout-title">RMS</span>
                    <!-- Add spacer, to align navigation to the right -->
                    <div class="mdl-layout-spacer"></div>
                    <!-- Navigation Only Large Screen-->
                    <nav class="mdl-navigation mdl-layout--large-screen-only">
                        <tx:navigation/>
                    </nav>
                </div>
            </header>
            <div class="mdl-layout__drawer">
                <span class="mdl-layout-title">RMS</span>
                <nav class="mdl-navigation">
                    <tx:navigation/>
                </nav>
            </div>
            <jsp:doBody/>
        </div>
    </div>
    <rms:script type="application/javascript" src="js/scripts.js"/>
</body>
</html>
