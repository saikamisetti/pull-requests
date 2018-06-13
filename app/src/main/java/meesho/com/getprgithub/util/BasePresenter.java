package meesho.com.getprgithub.util;

/**
 * Created by Sai on 12/06/18.
 */
public interface BasePresenter<T extends BaseView> {
    /**
     * Make this presenter attach to this view. This makes presenter ready to work with this
     * view.
     * To actually start data loading, refer to {@linkplain #load()}
     *
     * @param view View for the presenter to present data.
     */
    void attach(T view);

    /**
     * Detach. This will make presenter unsubscribe from all the resources held.
     * Only {@linkplain #attach(BaseView)} will kick start the presenter again.
     */
    void detach();

    /**
     * Start loading data into view.
     */
    void load();
}

