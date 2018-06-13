package meesho.com.getprgithub.feature.prlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import meesho.com.getprgithub.R;
import meesho.com.getprgithub.model.PullRequest;
import meesho.com.getprgithub.network.PageLinks;
import meesho.com.getprgithub.widget.PREditTextWidget;

/**
 * Created by Sai on 13/06/18.
 */
public class PRListActivity extends AppCompatActivity implements PRListContract.PRListView {

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
    @BindView(R.id.root_view)
    View rootView;
    @BindView(R.id.filter_spinner)
    Spinner filterSpinner;
    PRListAdapter adapter;
    PRListContract.PRListPresenter presenter;
    PageLinks pageLinks;
    boolean isLoading = false;
    private ArrayAdapter spinnerAdapter;
    private String state = "open";
    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pull_requests);
        unbinder = ButterKnife.bind(this);
        setupRecyclerView();
        setupSpinner();
        presenter = new PRListPresenter();
        presenter.attach(this);
        progressBar.setVisibility(View.GONE);
        btnFetchPR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pullRequests.clear();
                etOwner.hideKeyboard();
                if (entriesValid()) {
                    isLoading = true;
                    presenter.fetchPR(etOwner.getEditText(), etRepoName.getEditText(), state);
                }
            }
        });
    }

    private void setupSpinner() {
        spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.states_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(spinnerAdapter);
        filterSpinner.setSelection(0, false);
        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String state = (String) parent.getItemAtPosition(position);
                state = state.toLowerCase();
                PRListActivity.this.state = state;
                if (entriesValid()) {
                    pullRequests.clear();
                    presenter.fetchPR(etOwner.getEditText(), etRepoName.getEditText(), state);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    boolean entriesValid() {
        boolean isValid = !etOwner.getEditText().isEmpty() && !etRepoName.getEditText().isEmpty();
        if (!isValid) {
            if (etOwner.getEditText().isEmpty()) {
                etOwner.setError("Owner name cannot be empty");
            } else {
                etRepoName.setError("Repo name cannot be empty");
            }
        }
        return isValid;
    }

    void setupRecyclerView() {
        adapter = new PRListAdapter(pullRequests);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoading) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && pageLinks.getNext() != null && !pageLinks.getNext().isEmpty()) {
                        isLoading = true;
                        presenter.loadMore(pageLinks.getNext());
                    }
                }
            }
        });
    }

    @Override
    public void onFetchPR(List<PullRequest> pullRequests) {
        this.pullRequests.addAll(pullRequests);
        adapter.notifyDataSetChanged();
        isLoading = false;
    }

    @Override
    public void onPageLinks(PageLinks pageLinks) {
        this.pageLinks = pageLinks;
    }

    @Override
    public void presenter(PRListContract.PRListPresenter prListPresenter) {
        this.presenter = prListPresenter;
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(Error error) {
        Snackbar.make(rootView, error.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
