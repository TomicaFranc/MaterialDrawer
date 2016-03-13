package com.atomic.materialdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.ToggleDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {

    private Drawer navigationDrawerLeft;
    private Drawer navigationDrawerRight;
    private AccountHeader headerNavigationLeft;

    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            Toast.makeText(MainActivity.this, "Change click: "+(isChecked ? "true": "false"), Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //header
        headerNavigationLeft = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(false)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Person 1").withEmail("mail1@mail.com").withIcon(R.mipmap.ic_launcher),
                        new ProfileDrawerItem().withName("Person 2").withEmail("mail2@mail.com").withIcon(R.mipmap.ic_launcher),
                        new ProfileDrawerItem().withName("Person 2").withEmail("mail3@mail.com").withIcon(R.mipmap.ic_launcher)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        Toast.makeText(MainActivity.this, "Profile clicked "+profile.getName(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();

        //navigation drawer (LEFT-RIGHT)
        navigationDrawerLeft = new DrawerBuilder()
                .withActivity(this)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withActionBarDrawerToggle(false)
                .withAccountHeader(headerNavigationLeft)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int position, IDrawerItem drawerItem) {
                        Toast.makeText(MainActivity.this, "Long click: "+position, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();

        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("First item").withIcon(R.mipmap.ic_launcher));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Second item").withIcon(R.mipmap.ic_launcher));
        navigationDrawerLeft.addItem(new DividerDrawerItem());
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Third item").withIcon(R.mipmap.ic_launcher));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Fourth item").withIcon(R.mipmap.ic_launcher));

        navigationDrawerLeft.addItem(new SectionDrawerItem().withName("Section 1"));
        navigationDrawerLeft.addItem(new SwitchDrawerItem().withName("Switch").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener));
        navigationDrawerLeft.addItem(new ToggleDrawerItem().withName("Toggle").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener));

        //navigation drawer (RIGHT-LEFT)
        navigationDrawerRight = new DrawerBuilder()
                .withActivity(this)
                .withDisplayBelowStatusBar(false)
                .addDrawerItems(
                        new SecondaryDrawerItem().withName("First item").withIcon(R.mipmap.ic_launcher),
                        new SecondaryDrawerItem().withName("Second item").withIcon(R.mipmap.ic_launcher)
                )
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.END)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(-1)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Toast.makeText(MainActivity.this, "Click: "+position, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int position, IDrawerItem drawerItem) {
                        Toast.makeText(MainActivity.this, "Long click: "+position, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();

    }
}
