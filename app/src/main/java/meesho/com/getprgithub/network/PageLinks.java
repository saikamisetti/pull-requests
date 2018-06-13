package meesho.com.getprgithub.network;

/**
 * Created by Sai on 13/06/18.
 */

import android.os.Parcelable;
import android.text.TextUtils;
import java.util.HashMap;
import okhttp3.HttpUrl;
import timber.log.Timber;

public class PageLinks {

    private static final String DELIM_LINKS = ",";
    private static final String DELIM_LINK_PARAM = ";";
    private static final String META_REL = "rel";
    private static final String META_FIRST = "first";
    private static final String META_LAST = "last";
    private static final String META_CURRENT = "current";
    private static final String META_NEXT = "next";
    private static final String META_PREVIOUS = "prev";

    private String first;
    private String last;
    private String next;
    private String prev;

    /**
     * Parse links from executed method
     *
     * @param linkHeader
     */
    public PageLinks(String linkHeader) {

        if (linkHeader != null) {
            String[] links = linkHeader.split(DELIM_LINKS);
            for (String link : links) {
                String[] segments = link.split(DELIM_LINK_PARAM);
                if (segments.length < 2)
                    continue;

                String linkPart = segments[0].trim();
                if (!linkPart.startsWith("<") || !linkPart.endsWith(">")) //$NON-NLS-1$ //$NON-NLS-2$
                    continue;
                linkPart = linkPart.substring(1, linkPart.length() - 1);

                for (int i = 1; i < segments.length; i++) {
                    String[] rel = segments[i].trim().split("="); //$NON-NLS-1$
                    if (rel.length < 2 || !META_REL.equals(rel[0]))
                        continue;

                    String relValue = rel[1];
                    if (relValue.startsWith("\"") && relValue.endsWith("\"")) //$NON-NLS-1$ //$NON-NLS-2$
                        relValue = relValue.substring(1, relValue.length() - 1);

                    if (META_FIRST.equals(relValue))
                        first = linkPart;
                    else if (META_LAST.equals(relValue))
                        last = linkPart;
                    else if (META_NEXT.equals(relValue))
                        next = linkPart;
                    else if (META_PREVIOUS.equals(relValue))
                        prev = linkPart;
                }
            }
        }
//        else {
//            next = response.getHeader(HEADER_NEXT);
//            last = response.getHeader(HEADER_LAST);
//        }
    }

    /**
     * @return first
     */
    public String getFirst() {
        return first;
    }

    /**
     * @return last
     */
    public String getLast() {
        return last;
    }

    /**
     * @return next
     */
    public String getNext() {
        return next;
    }

    /**
     * @return prev
     */
    public String getPrev() {
        return prev;
    }
}

