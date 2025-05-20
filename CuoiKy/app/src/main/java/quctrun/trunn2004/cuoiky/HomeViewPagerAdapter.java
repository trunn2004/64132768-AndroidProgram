package quctrun.trunn2004.cuoiky;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HomeViewPagerAdapter extends FragmentStateAdapter {

    public HomeViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new KhoanThuFragment();
        } else {
            return new KhoanChiFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

