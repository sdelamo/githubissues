<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
    </head>
<body>
    <div class="container">


        <g:if test="${issue?.hasErrors()}">
            <div class="alert alert-danger" role="alert">
                <g:renderErrors bean="${issue}" as="list" />
            </div>
        </g:if>

    <g:form action="save" controller="issue">
        <h2><g:message code="issue.create" default="Create an Issue"/></h2>
        <div class="form-group">
            <label for="title"><g:message code="issue.title" default="Title"/></label>
            <g:textField name="title" type="title" class="form-control" id="title" placeholder="Title" value="${issue?.title}"/>
        </div>

        <div class="form-group">
            <label for="body"><g:message code="issue.body" default="Body"/></label>
            <textarea class="form-control" rows="3" name="body" id="body">${issue?.body}</textarea>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </g:form>
    </div>
    </body>
</html>
