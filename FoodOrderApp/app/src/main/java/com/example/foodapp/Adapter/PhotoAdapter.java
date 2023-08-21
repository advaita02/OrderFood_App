package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;

//public class PhotoAdapter extends PagerAdapter {
//
//
//
//    private Context mContext;
//    private List<Photo> mListPhoto;
//
//    public PhotoAdapter(Context mContext, List<Photo> mListPhoto) {
//        this.mContext = mContext;
//        this.mListPhoto = mListPhoto;
//    }
//
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo, container, false);
//        ImageView imgPhoto = view.findViewById(R.id.imagePhoto);
//        Photo photo =  mListPhoto.get(position);
//        if (photo != null) {
//            Glide.with(mContext).load(photo.getResourceId()).into(imgPhoto);
//        }
//
//        // Add view to viewGroup
//        container.addView(view);
//        return view;
//    }
//
//    @Override
//    public int getCount() {
//        if (mListPhoto != null)
//            return  mListPhoto.size();
//        return 0;
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        // Remove View
//        container.removeView((View) object);
//    }

//}



// ======================
public class PhotoAdapter  extends PagerAdapter {
    private final Context mContext;
    private final int[] mImageIds;

    public PhotoAdapter(Context context, int[] imageIds) {
        mContext = context;
        mImageIds = imageIds;
    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Glide.with(mContext).load(mImageIds[position]).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
