package top.zcwfeng.sunflower_java.adapters;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import top.zcwfeng.sunflower_java.R;

public class PlantDetailBindingAdapters {
    @BindingAdapter("imageFromUrl")
    public static void bindImageFromUrl(ImageView view, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(view.getContext())
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view);
        }
    }

    @BindingAdapter("isGone")
    public static void bindIsGone(FloatingActionButton view, boolean isGone) {
        if (isGone) {
            view.hide();
        } else {
            view.show();
        }
    }

    @BindingAdapter("renderHtml")
    public static void bindReaderHtml(TextView view, String description) {
        if (description == null) {
            view.setText("");
        } else {
            view.setText(HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT));
            view.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    @BindingAdapter("wateringText")
    public static void bindWateringText(TextView textView, int wateringInterval) {
        Resources res = textView.getContext().getResources();
        String quantityString = res.getQuantityString(R.plurals.watering_needs_suffix, wateringInterval, wateringInterval);

        SpannableStringBuilder builder = new SpannableStringBuilder().append(res.getString(R.string.watering_needs_prefix));
        builder.setSpan(new StyleSpan(Typeface.BOLD), 0, builder.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        int start = builder.length();
        builder.append(" " + quantityString);
        builder.setSpan(new StyleSpan(Typeface.ITALIC), start + 1, builder.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }
}