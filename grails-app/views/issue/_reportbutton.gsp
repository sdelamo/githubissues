<script type="text/javascript">
    function popupwindow(url, title, w, h) {
        var left = (screen.width/2)-(w/2);
        var top = (screen.height/2)-(h/2);
        return window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
    }

    function openIssuePopup() {
        var url = "/issue/create";
        var title = "report an issue";
        var width = 500;
        var height = 600;
        popupwindow(url, title, width, height);
    }
</script>
<!--
<button type="button" onclick="javascript:openIssuePopup();" class="btn btn-default" aria-label="Left Align">
        <span class="glyphicon glyphicon glyphicon-share aria-hidden="true"></span>
        &nbsp;<g:message code="issue.report" default="Report a Bug"/>
</button>
-->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    <g:message code="issue.report" default="Report a Bug"/>
</button>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel"><g:message code="issue.create" default="Create an Issue"/></h4>
            </div>
            <g:form name="issueForm" action="save" controller="issue">
            <div class="modal-body">
                    <div class="form-group">
                        <label for="title"><g:message code="issue.title" default="Title"/></label>
                        <g:textField name="title" type="title" class="form-control required" id="title" placeholder="Title" value="${issue?.title}"/>
                    </div>

                    <div class="form-group">
                        <label for="body"><g:message code="issue.body" default="Body"/></label>
                        <textarea class="form-control" rows="3" name="body" id="body">${issue?.body}</textarea>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-primary">Create</button>
            </div>
            </g:form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('#issueForm').on('submit', function(e) {
        var title = $('#title');

        // Check if there is an entered value
        if(!title.val()) {
            // Add errors highlight
            title.closest('.form-group').removeClass('has-success').addClass('has-error');

            // Stop submission of the form
            e.preventDefault();
        } else {
            // Remove the errors highlight
            title.closest('.form-group').removeClass('has-error').addClass('has-success');
        }
    });
</script>
