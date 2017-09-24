package camera.android.com.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import camera.android.com.myapplication.Fragment.ItemFragment;
import camera.android.com.myapplication.Fragment.ItemFragment.OnListFragmentInteractionListener;
import camera.android.com.myapplication.Objects.MyItem;
import camera.android.com.myapplication.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<MyItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context mContext;
    private ActionMode mActionMode;

    public MyItemRecyclerViewAdapter(Context context, List<MyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getContent());

        if (holder.mItem.isSelected())
            holder.mainView.setBackgroundResource(R.color.light_gray);
        else
            holder.mainView.setBackgroundResource(R.color.white);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mItem.isSelected()) {
                    toggleSelection(holder, false);
                    return;
                }

                if (isThereAnyItemsSelected()) {
                    toggleSelection(holder, true);
                    return;
                }

                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (holder.mItem.isSelected())
                    holder.mItem.setSelected(false);

                toggleSelection(holder, true);
                return true;
            }
        });
    }

    private void toggleSelection(ViewHolder holder, boolean selected) {
        holder.mItem.setSelected(selected);
        notifyDataSetChanged();

        if (isThereAnyItemsSelected())
            mActionMode= ((Activity) mContext).startActionMode(new ItemFragment.ActionBarCallback());
        else{
            mActionMode.finish();
        }

    }

    private boolean isThereAnyItemsSelected() {
        for (MyItem item :
                mValues) {
            if (item.isSelected())
                return true;

        }
        return false;
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public MyItem mItem;
        public LinearLayout mainView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);

            mainView = (LinearLayout) view.findViewById(R.id.mainView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }


    }
}
