/*
 * Copyright 2018-2020 Pranav Pandey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pranavpandey.android.dynamic.support.tutorial.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.pranavpandey.android.dynamic.support.R;
import com.pranavpandey.android.dynamic.support.theme.DynamicTheme;
import com.pranavpandey.android.dynamic.support.tutorial.DynamicSimpleTutorial;
import com.pranavpandey.android.dynamic.support.tutorial.DynamicTutorial;
import com.pranavpandey.android.dynamic.support.tutorial.activity.DynamicTutorialActivity;
import com.pranavpandey.android.dynamic.support.utils.DynamicResourceUtils;
import com.pranavpandey.android.dynamic.support.widget.Dynamic;
import com.pranavpandey.android.dynamic.utils.DynamicColorUtils;

/**
 * A {@link DynamicTutorialFragment} with an image, title, subtitle and description that
 * will be used with the {@link DynamicTutorialActivity}.
 */
public class DynamicTutorialFragment extends Fragment implements
        DynamicTutorial<DynamicSimpleTutorial, DynamicTutorialFragment> {

    /**
     * Fragment argument key to set the dynamic tutorial.
     */
    private static final String ADS_ARGS_TUTORIAL = "ads_args_tutorial";

    /**
     * Tutorial key to maintain its state.
     */
    private static final String ADS_STATE_TUTORIAL = "ads_state_tutorial";

    /**
     * Dynamic simple tutorial used by this fragment.
     */
    private DynamicSimpleTutorial mDynamicSimpleTutorial;

    /**
     * Root view of this fragment.
     */
    private ViewGroup mRootView;

    /**
     * Image view to show the tutorial image.
     */
    private ImageView mImageView;

    /**
     * Scroll view to show the scrolling content.
     */
    private NestedScrollView mScrollView;

    /**
     * Text view to show the tutorial title.
     */
    private TextView mTitleView;

    /**
     * Text view to show the tutorial subtitle.
     */
    private TextView mSubtitleView;

    /**
     * Text view to show the tutorial description.
     */
    private TextView mDescriptionView;

    /**
     * Function to initialize this fragment.
     *
     * @param dynamicSimpleTutorial The dynamic simple tutorial for this fragment.
     *
     * @return An instance of {@link DynamicTutorialFragment}.
     */
    public static DynamicTutorialFragment newInstance(
            @NonNull DynamicSimpleTutorial dynamicSimpleTutorial) {
        DynamicTutorialFragment fragment = new DynamicTutorialFragment();
        Bundle args = new Bundle();
        args.putParcelable(ADS_ARGS_TUTORIAL, dynamicSimpleTutorial);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            if (requireArguments().containsKey(ADS_ARGS_TUTORIAL)) {
                mDynamicSimpleTutorial = requireArguments().getParcelable(ADS_ARGS_TUTORIAL);
            }
        }

        if (savedInstanceState != null) {
            mDynamicSimpleTutorial = savedInstanceState.getParcelable(ADS_STATE_TUTORIAL);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ads_fragment_tutorial_simple, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRootView = view.findViewById(R.id.ads_tutorial_simple);
        mImageView = view.findViewById(R.id.ads_tutorial_simple_image);
        mScrollView = view.findViewById(R.id.ads_tutorial_simple_scroller);
        mTitleView = view.findViewById(R.id.ads_tutorial_simple_title);
        mSubtitleView = view.findViewById(R.id.ads_tutorial_simple_subtitle);
        mDescriptionView = view.findViewById(R.id.ads_tutorial_simple_description);

        if (mDynamicSimpleTutorial != null) {
            if (mImageView != null) {
                Dynamic.set(mImageView, DynamicResourceUtils.getDrawable(
                        requireContext(), mDynamicSimpleTutorial.getImageRes()));
            }

            Dynamic.set(mTitleView, mDynamicSimpleTutorial.getTitle());
            Dynamic.set(mSubtitleView, mDynamicSimpleTutorial.getSubtitle());
            Dynamic.set(mDescriptionView, mDynamicSimpleTutorial.getDescription());
        }

        tintWidgets(getBackgroundColor());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(ADS_STATE_TUTORIAL, mDynamicSimpleTutorial);
    }

    /**
     * Tint the widgets according to the supplied background color.
     *
     * @param color The color to generate the tint.
     */
    private void tintWidgets(@ColorInt int color) {
        final @ColorInt int tintColor = DynamicColorUtils.getTintColor(color);

        if (mDynamicSimpleTutorial != null && mDynamicSimpleTutorial.isTintImage()) {
            Dynamic.tint(mImageView, tintColor, color);
        }

        Dynamic.tint(mTitleView, tintColor, color);
        Dynamic.tint(mSubtitleView, tintColor, color);
        Dynamic.tint(mDescriptionView, tintColor, color);
        Dynamic.tint(mScrollView, tintColor, color);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

    @Override
    public void onPageSelected(int position) {
        onBackgroundColorChanged(getBackgroundColor());
    }

    @Override
    public void onPageScrollStateChanged(int state) { }

    @Override
    public @NonNull DynamicSimpleTutorial getTutorial() {
        return mDynamicSimpleTutorial;
    }

    @Override
    public @NonNull DynamicTutorialFragment createTutorial() {
        return this;
    }

    @Override
    public int getTutorialId() {
        return mDynamicSimpleTutorial.getId();
    }

    @Override
    public int getBackgroundColor() {
        return mDynamicSimpleTutorial != null ? mDynamicSimpleTutorial.getBackgroundColor()
                : DynamicTheme.getInstance().get().getPrimaryColor();
    }

    @Override
    public void onBackgroundColorChanged(int color) {
        tintWidgets(color);
    }

    @Override
    public void onSetPadding(int left, int top, int right, int bottom) {
        if (mRootView == null || bottom <= 0 || mRootView.getPaddingBottom() >= bottom) {
            return;
        }

        mRootView.setPadding(mRootView.getPaddingLeft() + left,
                mRootView.getPaddingTop() + top,
                mRootView.getPaddingRight() + right,
                mRootView.getPaddingBottom() + bottom);
    }
}
