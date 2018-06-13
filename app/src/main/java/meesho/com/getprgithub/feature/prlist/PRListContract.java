package meesho.com.getprgithub.feature.prlist;

import java.util.List;

import meesho.com.getprgithub.model.PullRequest;
import meesho.com.getprgithub.network.PageLinks;
import meesho.com.getprgithub.util.BasePresenter;
import meesho.com.getprgithub.util.BaseView;

/**
 * Created by Sai on 13/06/18.
 */
public class PRListContract {
    public interface PRListView extends BaseView<PRListPresenter> {
        void onFetchPR(List<PullRequest> pullRequests);

        void onPageLinks(PageLinks pageLinks);
    }

    public interface PRListPresenter extends BasePresenter<PRListView> {
        void fetchPR(String ownerName, String repoName, String state);

        void loadMore(String nextUrl);
    }
}
