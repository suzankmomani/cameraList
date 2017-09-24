package camera.android.com.myapplication.Objects;

/**
 * Created by suzan on 24/03/17.
 */

import java.io.Serializable;

public class MyItem implements Serializable {
    private final String id;
    private final String content;
    private final String details;
    private boolean isSelected;

    public MyItem(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return content;
    }
}