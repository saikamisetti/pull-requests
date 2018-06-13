package meesho.com.getprgithub.network;


import java.util.List;

import io.reactivex.Observable;
import meesho.com.getprgithub.model.PullRequest;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Sai on 12/06/18.
 */
public interface BaseApi {

    @GET("repos/{owner}/{repo_name}/pulls?")
    Observable<Response<List<PullRequest>>> fetchInitialPullRequests(@Path(value = "owner") String owner, @Path(value = "repo_name") String repoName, @Query(value = "state") String state, @Query(value = "per_page") int perPage, @Query(value = "page") int page);

    @GET
    Observable<Response<List<PullRequest>>> fetchPullRequests(@Url String pageUrl);
}
