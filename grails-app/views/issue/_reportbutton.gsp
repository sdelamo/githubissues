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
<button type="button" onclick="javascript:openIssuePopup();" class="btn btn-default" aria-label="Left Align">
        <span class="glyphicon glyphicon glyphicon-share aria-hidden="true"></span>
        &nbsp;<g:message code="issue.report" default="Report a Bug"/>
</button>