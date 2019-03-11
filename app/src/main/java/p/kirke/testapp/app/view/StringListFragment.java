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

import java.util.Arrays;

import p.kirke.testapp.R;
import p.kirke.testapp.databinding.FragmentStringsListBinding;

public class StringListFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentStringsListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_strings_list,
                container, false);
        initView(binding);
        return binding.getRoot();
    }

    private void initView(FragmentStringsListBinding binding) {
        RecyclerView recyclerView = binding.stringsList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        StringListAdapter adapter = new StringListAdapter();
        String[] stringArray = new String[]{"String one", "String two", "String three"};
        adapter.updateData(Arrays.asList(stringArray));
        recyclerView.setAdapter(adapter);
    }
}
