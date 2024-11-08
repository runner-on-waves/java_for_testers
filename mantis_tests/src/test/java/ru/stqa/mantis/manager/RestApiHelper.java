package ru.stqa.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.*;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.UserData;

public class RestApiHelper extends HelperBase {
    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));
        defaultClient.setBasePath(manager.property("api.basePath"));
    }


    public void createIssue(IssueData issueData) {
        Issue issue = new Issue();
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());
        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);
        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setCategory(categoryId);
        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            new RuntimeException(e);
        }
    }

    public void createUser(UserData userData) {
        User user = new User(); // User | The user to add.
        user.setUsername(userData.username());
        user.setRealName(userData.username());
        user.setEmail(userData.email());
        var accessLevel = new AccessLevel();
        accessLevel.setName(userData.accessLevel());
        user.setAccessLevel(accessLevel);
        user.setProtected(userData._protected());
        user.setPassword(userData.password());
        user.setEnabled(userData.enabled());
        UserApi apiInstance = new UserApi();
        try {
            apiInstance.userAdd(user);
        } catch (ApiException e) {
            new RuntimeException(e);
        }
    }
}
