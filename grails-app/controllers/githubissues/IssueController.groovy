package githubissues

import groovy.util.logging.Slf4j

import static org.springframework.http.HttpStatus.*

@Slf4j
class IssueController {

    static allowedMethods = [save: "POST"]

    GithubService githubService

    def index() {
        def issues = githubService.findAllIssues()
        [issues: issues, issueCount: issues.size()]
    }

    def create() {
        def issue = new GithubIssue(title: params.title, body: params.body)
        [issue: issue]
    }

    def save(GithubIssue issue) {
        if (issue == null) {
            notFound()
            return
        }

        if (issue.hasErrors()) {
            log.info "there is an error"
            render model: [issue: issue], view:'create'
            return
        }

        githubService.createIssue(issue)

        redirect(action: 'index')
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'issue.label', default: 'Issue'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
