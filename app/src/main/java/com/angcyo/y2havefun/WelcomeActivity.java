package com.angcyo.y2havefun;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.angcyo.y2havefun.components.RDataPullMgr;
import com.angcyo.y2havefun.mode.event.LoadedEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by angcyo on 15-09-07-007.
 */
public class WelcomeActivity extends BaseActivity {
    public static long ANIM_TIME = 300;//动画持续时间
    @Bind(R.id.logo_l_1)
    ImageView logoL1;
    @Bind(R.id.logo_l_2)
    ImageView logoL2;
    @Bind(R.id.logo_l_3)
    ImageView logoL3;
    @Bind(R.id.logo_r_1)
    ImageView logoR1;
    @Bind(R.id.logo_r_2)
    ImageView logoR2;
    @Bind(R.id.logo_r_3)
    ImageView logoR3;

    boolean animEnd = false;//动画是否结束
    boolean loadEnd = false;//本地数据是否装载完成

    Animation anims[];
    @Bind(R.id.launch)
    Button launch;
    @Bind(R.id.load_layout)
    LinearLayout loadLayout;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initWindow(android.support.design.R.color.accent_material_light);

        createAnim();
    }

    private void createAnim() {
        anims = new Animation[6];

        for (int i = 0; i < anims.length; i++) {
            anims[i] = i > 2 ? AnimationUtils.loadAnimation(this, R.anim.tran_rtol_logo) : AnimationUtils.loadAnimation(this, R.anim.tran_ltor_logo);
            anims[i].setStartOffset((i + 1) * ANIM_TIME);
            anims[i].setDuration(ANIM_TIME);
        }

        anims[5].setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animEnd = true;
                jumpMain();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void cancelAnim() {
    }

    private void startAnim() {
        showView(logoL1);
        logoL1.startAnimation(anims[0]);
        showView(logoL2);
        logoL2.startAnimation(anims[1]);
        showView(logoL3);
        logoL3.startAnimation(anims[2]);
        showView(logoR1);
        logoR1.startAnimation(anims[3]);
        showView(logoR2);
        logoR2.startAnimation(anims[4]);
        showView(logoR3);
        logoR3.startAnimation(anims[5]);
    }

    private void showView(View view) {
        view.setVisibility(View.VISIBLE);
    }

    private void jumpMain() {
        if (animEnd && loadEnd) {
            loadLayout.setVisibility(View.GONE);
            launch.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.launch)
    public void launchClick() {
        launchActivity(MainActivity.class);
        finish();
    }

    @Override
    protected void initAfter() {
        startAnim();
        loadData();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEvent(LoadedEvent event) {
        if (event.code == LoadedEvent.CODE_OK) {
            loadEnd = true;
            jumpMain();
        }
    }

    private void loadData() {
        RDataPullMgr.initDataAsync(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

}
