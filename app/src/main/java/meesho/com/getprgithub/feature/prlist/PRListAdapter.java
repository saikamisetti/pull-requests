package meesho.com.getprgithub.feature.prlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import meesho.com.getprgithub.R;
import meesho.com.getprgithub.model.PullRequest;

/**
 * Created by Sai on 13/06/18.
 */
public class PRListAdapter extends Adapter<PRListAdapter.ItemViewHolder> {

    List<PullRequest> pullRequests;

    public PRListAdapter(List<PullRequest> pullRequests) {
        this.pullRequests = pullRequests;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(pullRequests.get(position));
    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }

    @Override
    public void onViewRecycled(@NonNull ItemViewHolder holder) {
        holder.unbind();
        super.onViewRecycled(holder);

    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        Unbinder unbinder;

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.user_name)
        TextView userName;
        @BindView(R.id.tv_num)
        TextView prNum;

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        public static ItemViewHolder create(ViewGroup parent) {
            View view =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pull_request, parent, false);
            return new ItemViewHolder(view);
        }

        public void bind(PullRequest pullRequest) {
            unbinder = ButterKnife.bind(this, itemView);
            title.setText(pullRequest.getTitle());
            userName.setText(pullRequest.getUser().getLogin());
            prNum.setText(String.valueOf(pullRequest.getNumber()));
        }

        public void unbind() {
            if (unbinder == null) {
                return;
            }
            unbinder.unbind();
            itemView.setOnClickListener(null);
        }
    }
}
