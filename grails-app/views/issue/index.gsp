<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
</head>
    <body>
    <div class="container">
        <g:each var="issue" in="${issues}">
            <div class="panel panel-default">
                <div class="panel-heading">${issue.title}</div>
                <div class="panel-body">
                    ${issue.body}
                </div>
            </div>
        </g:each>
    </div>
    </body>
</html>