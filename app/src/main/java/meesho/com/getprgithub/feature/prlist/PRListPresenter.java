package meesho.com.getprgithub.feature.prlist;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import meesho.com.getprgithub.model.PullRequest;
import meesho.com.getprgithub.network.BaseApi;
import meesho.com.getprgithub.network.NetworkAdapter;
import meesho.com.getprgithub.network.PageLinks;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by Sai on 13/06/18.
 */
public class PRListPresenter implements PRListContract.PRListPresenter {

    PRListContract.PRListView view;
    BaseApi baseApi;

    PRListPresenter() {
        this.baseApi = NetworkAdapter.getClient().create(BaseApi.class);
    }

    @Override
    public void attach(PRListContract.PRListView view) {
        this.view = view;
    }

    @Override
    public void detach() {

    }

    @Override
    public void load() {

    }

    @Override
    public void fetchPR(String ownerName, String repoName, String state) {
        baseApi.fetchPullRequests(ownerName, repoName, state, view.pageSize(), view.pageNum()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<PullRequest>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                view.showProgress(true);
            }

            @Override
            public void onNext(Response<List<PullRequest>> response) {
                view.showProgress(false);
                if (response.isSuccessful()) {
                    if (response.headers().get("link") != null) {
                        PageLinks pageLinks = new PageLinks(response.headers().get("link"));
                        view.onPageLinks(pageLinks);
                    }
                    view.onFetchPR(response.body());
                } else {
                    view.showError(new Error(response.message()));
                }
            }

            @Override
            public void onError(Throwable e) {
                view.showProgress(false);
                Timber.d("onError");
                view.showError(new Error(e.getMessage()));
            }

            @Override
            public void onComplete() {
                Timber.d("completed");
            }
        });
    }

    @Override
    public void loadMore(String nextUrl) {
        baseApi.fetchNextPagePullRequests(nextUrl).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<PullRequest>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                view.showProgress(true);
            }

            @Override
            public void onNext(Response<List<PullRequest>> pullRequests) {
                view.showProgress(false);
                if (pullRequests.headers().get("link") != null) {
                    PageLinks pageLinks = new PageLinks(pullRequests.headers().get("link"));
                    view.onPageLinks(pageLinks);
                }
                view.onFetchPR(pullRequests.body());
            }

            @Override
            public void onError(Throwable e) {
                view.showProgress(false);
                Timber.d("onError");
                view.showError(new Error(e.getMessage()));
            }

            @Override
            public void onComplete() {
                Timber.d("completed");
            }
        });
    }
}
