package meesho.com.getprgithub.util;

/**
 * Created by Sai on 12/06/18.
 */
public interface BaseView<P extends BasePresenter> {
    /**
     * Set a presenter on the view. It is recommended not to invoke immediate presenter methods in
     * this call.
     * Views should just save a reference to this presenter.
     *
     * @param p presenter p.
     */
    void presenter(P p);

    /**
     * Show that data load is in progress.
     *
     * @param show true if data load is in progress, false otherwise.
     */
    void showProgress(boolean show);

    /**
     * Show that there is an error loading data.
     *
     * @param error Sync error.
     */
    void showError(Error error);
}
