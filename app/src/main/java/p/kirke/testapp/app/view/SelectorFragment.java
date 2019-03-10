package p.kirke.testapp.app.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import p.kirke.testapp.R;
import p.kirke.testapp.app.ViewModel;
import p.kirke.testapp.app.data.StringCache;
import p.kirke.testapp.databinding.FragmentSelectorBinding;

public class SelectorFragment extends Fragment {

    private StringListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentSelectorBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_selector,
                container, false);
        initView(binding);
        return binding.getRoot();
    }

    private void initView(FragmentSelectorBinding binding) {
        setViewModelToLayout(binding);
        setUpRecyclerView(binding);
        setDataObserver();
    }

    private void setViewModelToLayout(FragmentSelectorBinding binding) {
        ViewModel viewModel = new ViewModel();
        binding.setViewModel(viewModel);
    }

    private void setUpRecyclerView(FragmentSelectorBinding binding) {
        RecyclerView recyclerView = binding.listView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new StringListAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void setDataObserver() {
        StringCache cache = StringCache.getInstance();
        cache.getData().observe(this, stringList -> adapter.updateData(stringList));
    }
}
