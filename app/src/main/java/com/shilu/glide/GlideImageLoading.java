package com.shilu.glide;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class GlideImageLoading extends AppCompatActivity {

    ImageView ivGlide;
    ImageView ivGlideGif;
    ImageView ivGlideRounded;

    String urlDota = "https://s-media-cache-ak0.pinimg.com/originals/2d/cd/80/2dcd80c6f5a21a437313adde93b373d8.jpg";
    String urlGif = "https://benjaminbenben.com/img/example-trefoil.gif";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glide_activity);

        ivGlide = (ImageView) findViewById(R.id.iv_glide);
        ivGlideRounded = (ImageView) findViewById(R.id.iv_glide_rounded);
        ivGlideGif = (ImageView) findViewById(R.id.iv_glide_gif);

        loadImageWithPlaceHolder(urlDota);
        loadRoundedImage(urlGif);
        loadGifImages(urlGif);
    }

    private void loadRoundedImage(String urlDota) {
        Glide.with(this).load(urlDota).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivGlideRounded) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                ivGlideRounded.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    private void loadGifImages(String urlGif) {
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(ivGlideGif);
        Glide.with(this).load(urlGif).into(imageViewTarget);
    }

    /**
     * the image we are loading is of very high resolution 1800 X 1800
     */
    private void loadSimpleImage(String url) {
        Glide.with(this).load(url).into(ivGlide);
    }

    /**
     * place holder appears until the actual image is loaded
     * what override does is resizes the action image. now we can see that the image is of
     * lesser resolution
     */
    private void loadImageWithPlaceHolder(String url) {
        Glide.with(this).load(url).placeholder(R.mipmap.ic_launcher).override(200,200).into(ivGlide);
    }
}
