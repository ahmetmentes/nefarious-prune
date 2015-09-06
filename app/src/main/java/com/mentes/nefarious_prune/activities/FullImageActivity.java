package com.mentes.nefarious_prune.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mentes.nefarious_prune.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ahmetmentes on 06/09/15.
 */
public class FullImageActivity extends AppCompatActivity {

    public static final String TAG = FullImageActivity.class.getSimpleName();

    public static final String IMAGE_URL = "imageUrl";

    public static Intent newIntent(AppCompatActivity activity, String imageUrl) {
        Intent intent = new Intent(activity, FullImageActivity.class);
        intent.putExtra(IMAGE_URL, imageUrl);
        return intent;
    }


    @Bind(R.id.background_layout)
    LinearLayout backgroundLayout;

    @Bind(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        ButterKnife.bind(this);

        ViewCompat.setTransitionName(image, IMAGE_URL);
        supportPostponeEnterTransition();

        Picasso.with(this).load(getIntent().getStringExtra(IMAGE_URL)).into(image, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        applyPalette(palette);
                    }
                });
            }

            @Override
            public void onError() {
                supportStartPostponedEnterTransition();
            }
        });

    }

    private void applyPalette(Palette palette) {
        int primary = getResources().getColor(R.color.primary);
        int color = palette.getMutedColor(primary);
        backgroundLayout.setBackgroundColor(color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        }
        supportStartPostponedEnterTransition();
    }

}
