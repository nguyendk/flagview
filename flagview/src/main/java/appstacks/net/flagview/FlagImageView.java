package appstacks.net.flagview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

import java.util.Locale;

import androidx.appcompat.widget.AppCompatImageView;


@SuppressLint("AppCompatCustomView")
public class FlagImageView extends AppCompatImageView {

    private static final String TAG = FlagImageView.class.getCanonicalName();

    private String countryCode;

    public FlagImageView(Context context) {
        super(context);
        init(null);
    }

    public FlagImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FlagImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        super.setScaleType(ScaleType.CENTER_CROP);  // Scale to max width or height
        super.setAdjustViewBounds(true); // Definitely the right ratio

        if (isInEditMode()) return;

        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.FlagImageView, 0, 0);
            try {
                String countryCode = typedArray.getString(R.styleable.FlagImageView_countryCode);
                if (countryCode != null && !countryCode.isEmpty())
                    setCountryCode(countryCode);
                else
                    defaultLocal();
            } finally {
                typedArray.recycle();
            }
        }
    }

    @Deprecated
    @Override
    public void setScaleType(ScaleType scaleType) {
        // Remove
    }

    @Deprecated
    @Override
    public void setAdjustViewBounds(boolean adjustViewBounds) {
        // Remove
    }

    public void defaultLocal() {
        setCountryCode(Locale.getDefault().getCountry());
        Log.d(TAG, " defaultLocal " + Locale.getDefault().getCountry());
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Locale locale) {
        setCountryCode(locale.getCountry());
    }

    public void setCountryCode(String countryCode) {
        countryCode = countryCode != null && !countryCode.isEmpty() ? countryCode.toLowerCase() : "";
        if (!countryCode.equals(this.countryCode)) {
            this.countryCode = countryCode;
            updateDrawableWithCountryCode();
        }
    }

    private void updateDrawableWithCountryCode() {
        if (this.countryCode.isEmpty()) {
            setImageResource(R.drawable.flag_unknown);
        } else {
            if (this.countryCode.equalsIgnoreCase("BEST")) {
                setImageResource(R.drawable.flag_ww);
            } else {
                Resources resources = getResources();
                final String resName = "flag_" + this.countryCode;
                final int resourceId = resources.getIdentifier(resName, "drawable",
                        getContext().getPackageName());
                if (resourceId == 0) {
                    Log.w(TAG, " CountryCode is Wrong ");
                }
                setImageResource(resourceId); // resourceId = 0 is not found
            }
        }
    }
}
