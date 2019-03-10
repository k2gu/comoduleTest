package p.kirke.testapp.app.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import p.kirke.testapp.R;
import p.kirke.testapp.app.ViewModel;
import p.kirke.testapp.app.data.StringCache;
import p.kirke.testapp.databinding.ContentMainBinding;

public class MainActivity extends AppCompatActivity {

    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContentMainBinding binding = DataBindingUtil.setContentView(this, R.layout.content_main);
        binding.setLifecycleOwner(this);
        RecyclerView recyclerView = binding.listView;

        viewModel = new ViewModel();
        viewModel.filterAndMergeServiceOutputs();

        StringCache cache = StringCache.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StringListAdapter(cache.getData()));
    }
}
