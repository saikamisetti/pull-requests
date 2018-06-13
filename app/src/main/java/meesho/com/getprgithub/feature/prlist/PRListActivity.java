package meesho.com.getprgithub.feature.prlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import meesho.com.getprgithub.R;
import meesho.com.getprgithub.model.PullRequest;
import meesho.com.getprgithub.widget.PREditTextWidget;

/**
 * Created by Sai on 13/06/18.
 */
public class PRListActivity extends AppCompatActivity implements PRListContract.PRListView{

    List<PullRequest> pullRequests = new ArrayList<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.et_owner)
    PREditTextWidget etOwner;
    @BindView(R.id.et_repo)
    PREditTextWidget etRepoName;
    @BindView(R.id.btn_fetch_pr)
    Button btnFetchPR;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.root_view) View rootView;
    PRListAdapter adapter;
    PRListContract.PRListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pull_requests);
        ButterKnife.bind(this);
        setupRecyclerView();
        presenter = new PRListPresenter();
        presenter.attach(this);
        progressBar.setVisibility(View.GONE);
        btnFetchPR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pullRequests.clear();
                etOwner.hideKeyboard();
                if(entriesValid()) {
                    presenter.fetchPR(etOwner.getEditText(), etRepoName.getEditText());
                }
            }
        });
    }

    boolean entriesValid() {
        boolean isValid = !etOwner.getEditText().isEmpty() && !etRepoName.getEditText().isEmpty();
        if(!isValid) {
            if(etOwner.getEditText().isEmpty()){
                etOwner.setError("Owner name cannot be empty");
            } else {
                etRepoName.setError("Repo name cannot be empty");
            }
        }
        return isValid;
    }

    void setupRecyclerView() {
        adapter = new PRListAdapter(pullRequests);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFetchPR(List<PullRequest> pullRequests) {
        this.pullRequests.clear();
        this.pullRequests.addAll(pullRequests);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void presenter(PRListContract.PRListPresenter prListPresenter) {
        this.presenter = prListPresenter;
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show?View.VISIBLE:View.GONE);
    }

    @Override
    public void showError(Error error) {
        Snackbar.make(rootView, error.getMessage(), Snackbar.LENGTH_SHORT).show();
    }
}
