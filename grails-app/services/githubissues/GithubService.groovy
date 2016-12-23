package githubissues

import com.budjb.httprequests.HttpClientFactory
import com.budjb.httprequests.HttpRequest
import com.budjb.httprequests.HttpResponse
import com.budjb.httprequests.filter.bundled.BasicAuthFilter
import com.budjb.httprequests.jersey1.JerseyHttpClientFactory
import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import groovy.util.logging.Slf4j

@CompileStatic
@Slf4j
class GithubService implements GrailsConfigurationAware {

    static final String ISSUES_PATH = '/issues'

    String githubRepository

    String username

    String password

    @Override
    void setConfiguration(Config co) {
        githubRepository = co.getRequiredProperty('github.repository', String)
        username = co.getRequiredProperty('github.username', String)
        password = co.getRequiredProperty('github.password', String)
    }

    static String githubApiUrlForRepositoryUrl(String githubRepositoryUrl) {
        if ( !githubRepositoryUrl ) {
            log.error 'parameter githubRepositoryUrl is required'
            return null
        }

        final String githubPreffix = 'https://github.com/'
        final String githubApiReposPreffix = 'https://api.github.com/repos/'
        if ( !githubRepositoryUrl.startsWith(githubPreffix)) {
            log.error "you must supply a url which is preffixed $githubPreffix"
        }

        githubRepositoryUrl.replaceAll(githubPreffix, githubApiReposPreffix)
    }

    HttpClientFactory httpClientFactory() {
        new JerseyHttpClientFactory()
    }

    GithubIssue createIssue(GithubIssue issue) {
        def client = httpClientFactory().createHttpClient()
        client.addFilter(new BasicAuthFilter(username, password))
        def request = new HttpRequest()
        request.setUri(issuesUrl())
        request.addHeader('Accept', 'application/json')
        request.addHeader('Content-Type', 'application/json')
        def response = client.post(request, issue.toMap())

        log.info response.toString()
        if (response.status != 201) {
            log.error 'issue could not be created'
            return null
        }
        githubIssueWithHttpResponse(response)
    }

    String issuesUrl() {
        String url = githubApiUrlForRepositoryUrl(githubRepository)
        "$url$ISSUES_PATH" as String
    }

    List<GithubIssue> findAllIssues(String state = 'open') {
        def client = httpClientFactory().createHttpClient()
        String url = "${issuesUrl()}?state=$state" as String
        def request = new HttpRequest()
        request.setUri(url)
        request.addHeader('Accept', 'application/json')
        request.addHeader('Content-Type', 'application/json')
        def response = client.get(request)

        if (response.status != 200) {
            log.error 'issues could not be fetched'
            return null
        }
        githubIssuesWithHttpResponse(response)
    }

    @CompileStatic(TypeCheckingMode.SKIP)
    List<GithubIssue> githubIssuesWithHttpResponse(HttpResponse response) {
        def l = response.getEntity(List)
        l.collect {
            new GithubIssue(title: it.title, body: it.body)
        }
    }

    @CompileStatic(TypeCheckingMode.SKIP)
    GithubIssue githubIssueWithHttpResponse(HttpResponse response) {
        Map json = response.getEntity(Map)
        new GithubIssue(title: json.title, body: json.body)
    }
}
