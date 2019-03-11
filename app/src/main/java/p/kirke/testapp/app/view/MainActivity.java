package p.kirke.testapp.app.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import p.kirke.testapp.R;
import p.kirke.testapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        createFragmentViewPager(binding);
    }

    private void createFragmentViewPager(ActivityMainBinding binding) {
        ViewPager viewPager = binding.fragmentContainer;
        TabLayout tabLayout = binding.tabLayout;

        TabAdapter adapter = getTabAdapterWithFragments();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private TabAdapter getTabAdapterWithFragments() {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new StringListFragment(), getString(R.string.string_list_fragment_title));
        adapter.addFragment(new SelectorFragment(), getString(R.string.selector_fragment_title));
        return adapter;
    }
}
