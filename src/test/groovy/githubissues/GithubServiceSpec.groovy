package githubissues

import grails.test.mixin.TestFor
import spock.lang.IgnoreIf
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(GithubService)
class GithubServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @IgnoreIf({ !System.getProperty('GITHUB_REPOSITORY')  })
    void "test findAllIssues"() {
        when:
        service.githubRepository = System.getProperty('GITHUB_REPOSITORY')
        def result = service.findAllIssues()

        then:
        result != null
    }

    @IgnoreIf({ !System.getProperty('GITHUB_USERNAME') || !System.getProperty('GITHUB_PASSWORD') || !System.getProperty('GITHUB_REPOSITORY')  })
    void "test createIssue"() {
        when:
        service.githubRepository = System.getProperty('GITHUB_REPOSITORY')
        def issues = service.findAllIssues()
        int numberOfIssues = issues.size()

        def issue = new GithubIssue(title: 'Problem', body: 'Steps to reproduce')
        service.username = System.getProperty('GITHUB_USERNAME')
        service.password = System.getProperty('GITHUB_PASSWORD')
        service.githubRepository = System.getProperty('GITHUB_REPOSITORY')
        def result = service.createIssue(issue)

        then:
        result
        result.title == issue.title
        result.body == issue.body

        when:
        def issuesAfterCreation = service.findAllIssues()
        int numberOfIssuesAfterCreation = issuesAfterCreation.size()

        then:
        (numberOfIssues + 1) == numberOfIssuesAfterCreation
    }
}
