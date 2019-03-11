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
import p.kirke.testapp.app.data.StringCache;
import p.kirke.testapp.app.viewmodel.ViewModel;
import p.kirke.testapp.databinding.FragmentSelectorBinding;

public class SelectorFragment extends Fragment {

    private StringListAdapter adapter;
    private StringCache cache;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSelectorBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_selector,
                container, false);
        cache = StringCache.getInstance();
        initView(binding);
        return binding.getRoot();
    }

    private void initView(FragmentSelectorBinding binding) {
        setViewModelToLayout(binding);
        setUpRecyclerView(binding);
        setDataObserver();
        setStateObserver(binding);
    }

    private void setViewModelToLayout(FragmentSelectorBinding binding) {
        MainActivity activity = (MainActivity) getActivity();
        ViewModel viewModel = activity == null ? new ViewModel() : activity.getViewModel();
        binding.setViewModel(viewModel);
    }

    private void setUpRecyclerView(FragmentSelectorBinding binding) {
        RecyclerView recyclerView = binding.listView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new StringListAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void setDataObserver() {
        cache.getData().observe(this, stringList -> adapter.updateData(stringList));
    }

    public void setStateObserver(FragmentSelectorBinding binding) {
        cache.getState().observe(this, state -> {
            binding.error.setVisibility(state == StringCache.State.ERROR ? View.VISIBLE : View.GONE);
        });
    }
}
