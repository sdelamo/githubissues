package githubissues

import grails.compiler.GrailsCompileStatic
import grails.validation.Validateable
import groovy.transform.CompileStatic

@GrailsCompileStatic
class GithubIssue implements Validateable {

    String title // Required. The title of the issue.
    String body // The contents of the issue.
    String assignee // Login for the user that this issue should be assigned to.
                    // NOTE: Only users with push access can set the assignee for new issues.
                    // The assignee is silently dropped otherwise. This field is deprecated.
    Integer milestone // The number of the milestone to associate this issue with.
                      // NOTE: Only users with push access can set the milestone for new issues.
                      // The milestone is silently dropped otherwise.
    List<String> labels	 // Labels to associate with this issue.
                         // NOTE: Only users with push access can set labels for new issues.
                         // Labels are silently dropped otherwise.
    List<String> assignees	// Logins for Users to assign to this issue.
                            // NOTE: Only users with push access can set assignees for new issues.
                            // Assignees are silently dropped otherwis

    static constraints = {
        title nullable: false, blank: false
        body nullable: true
        assignee nullable: true
        milestone nullable: true
        labels nullable: true
        assignees nullable:  true
    }
}
