package githubissues

import spock.lang.Specification
import spock.lang.Unroll

class GithubIssueSpec extends Specification {

    @Unroll
    void "test title: #title validates: #expected"(String title, boolean expected) {

        expect:
        new GithubIssue(title: title).validate(['title']) == expected

        where:
        title                ||expected
        null                 || false
        ''                   || false
        'There is a problem' || true
    }

    @Unroll
    void "test body: #body validates: #expected"(String body, boolean expected) {

        expect:
        new GithubIssue(body: body).validate(['body']) == expected

        where:
        body                 ||expected
        null                 || true
        ''                   || true
        'steps to reproduce' || true
    }


    @Unroll
    void "test assignee: #assignee validates: #expected"(String assignee, boolean expected) {

        expect:
        new GithubIssue(assignee: assignee).validate(['assignee']) == expected

        where:
        assignee             ||expected
        null                 || true
        ''                   || true
        'sdelamo'            || true
    }

    @Unroll
    void "test milestone: #milestone validates: #milestone"(Integer milestone, boolean expected) {

        expect:
        new GithubIssue(milestone: milestone).validate(['milestone']) == expected

        where:
        milestone || expected
        null      || true
        2         || true
    }

    @Unroll
    void "test milestone: #labels validates: #labels"(List<String> labels, boolean expected) {

        expect:
        new GithubIssue(labels: labels).validate(['labels']) == expected

        where:
        labels     || expected
        null       || true
        ['grails'] || true
        ['']       || true
    }

    @Unroll
    void "test milestone: #assignees validates: #assignees"(List<String> assignees, boolean expected) {

        expect:
        new GithubIssue(assignees: assignees).validate(['assignees']) == expected

        where:
        assignees            || expected
        null                 || true
        ['']                 || true
        ['sdelamo', 'peter'] || true
    }
}
