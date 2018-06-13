package meesho.com.getprgithub.feature.prlist;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import meesho.com.getprgithub.model.PullRequest;
import meesho.com.getprgithub.network.BaseApi;
import meesho.com.getprgithub.network.NetworkAdapter;
import timber.log.Timber;

/**
 * Created by Sai on 13/06/18.
 */
public class PRListPresenter implements PRListContract.PRListPresenter {

    PRListContract.PRListView view;

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
    public void fetchPR(String ownerName, String repoName) {
        BaseApi baseApi = NetworkAdapter.getClient().create(BaseApi.class);

        baseApi.fetchPullRequests(ownerName, repoName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<PullRequest>>() {
            @Override
            public void onSubscribe(Disposable d) {
                view.showProgress(true);
            }

            @Override
            public void onNext(List<PullRequest> pullRequests) {
                view.showProgress(false);
                view.onFetchPR(pullRequests);
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
