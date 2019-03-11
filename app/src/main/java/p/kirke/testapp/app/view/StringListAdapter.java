package p.kirke.testapp.app.view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import p.kirke.testapp.R;
import p.kirke.testapp.databinding.ListItemStringBinding;

public class StringListAdapter extends RecyclerView.Adapter<StringListAdapter.ViewHolder> {

    private List<String> dataList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        ListItemStringBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_string,
                viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.binding.setDataString(dataList.get(position));
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    void updateData(List<String> updatedData) {
        dataList = updatedData;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ListItemStringBinding binding;

        ViewHolder(final ListItemStringBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}