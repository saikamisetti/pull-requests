package meesho.com.getprgithub.model;

import com.squareup.moshi.Json;


public class User {

    @Json(name = "gists_url")
    private String gistsUrl;

    @Json(name = "repos_url")
    private String reposUrl;

    @Json(name = "following_url")
    private String followingUrl;

    @Json(name = "starred_url")
    private String starredUrl;

    @Json(name = "login")
    private String login;

    @Json(name = "followers_url")
    private String followersUrl;

    @Json(name = "type")
    private String type;

    @Json(name = "url")
    private String url;

    @Json(name = "subscriptions_url")
    private String subscriptionsUrl;

    @Json(name = "received_events_url")
    private String receivedEventsUrl;

    @Json(name = "avatar_url")
    private String avatarUrl;

    @Json(name = "events_url")
    private String eventsUrl;

    @Json(name = "html_url")
    private String htmlUrl;

    @Json(name = "site_admin")
    private boolean siteAdmin;

    @Json(name = "id")
    private int id;

    @Json(name = "gravatar_id")
    private String gravatarId;

    @Json(name = "node_id")
    private String nodeId;

    @Json(name = "organizations_url")
    private String organizationsUrl;

    public String getGistsUrl() {
        return gistsUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    public void setSiteAdmin(boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    @Override
    public String toString() {
        return
                "User{" +
                        "gists_url = '" + gistsUrl + '\'' +
                        ",repos_url = '" + reposUrl + '\'' +
                        ",following_url = '" + followingUrl + '\'' +
                        ",starred_url = '" + starredUrl + '\'' +
                        ",login = '" + login + '\'' +
                        ",followers_url = '" + followersUrl + '\'' +
                        ",type = '" + type + '\'' +
                        ",url = '" + url + '\'' +
                        ",subscriptions_url = '" + subscriptionsUrl + '\'' +
                        ",received_events_url = '" + receivedEventsUrl + '\'' +
                        ",avatar_url = '" + avatarUrl + '\'' +
                        ",events_url = '" + eventsUrl + '\'' +
                        ",html_url = '" + htmlUrl + '\'' +
                        ",site_admin = '" + siteAdmin + '\'' +
                        ",id = '" + id + '\'' +
                        ",gravatar_id = '" + gravatarId + '\'' +
                        ",node_id = '" + nodeId + '\'' +
                        ",organizations_url = '" + organizationsUrl + '\'' +
                        "}";
    }
}