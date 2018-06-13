package meesho.com.getprgithub.network;


import java.util.List;

import io.reactivex.Observable;
import meesho.com.getprgithub.model.PullRequest;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Sai on 12/06/18.
 */
public interface BaseApi {
//    @Headers({
//            "Accept: application/vnd.yourapi.v1.full+json"})
    @GET("repos/{owner}/{repo_name}/pulls")
    Observable<List<PullRequest>> fetchPullRequests(@Path(value = "owner") String owner, @Path(value = "repo_name") String repoName);
}
