/*
 * Copyright 2018 Pranav Pandey
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

package com.pranavpandey.android.dynamic.support.recyclerview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.pranavpandey.android.dynamic.support.R;
import com.pranavpandey.android.dynamic.support.utils.DynamicLayoutUtils;
import com.pranavpandey.android.dynamic.support.view.DynamicInfoView;

/**
 * A DynamicRecyclerViewFrame to display the DynamicInfoView links.
 *
 * @see DynamicRecyclerViewFrame
 * @see DynamicInfoView
 */
public class DynamicRecyclerViewNested extends DynamicRecyclerViewFrame {

    public DynamicRecyclerViewNested(@NonNull Context context) {
        super(context);
    }

    public DynamicRecyclerViewNested(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicRecyclerViewNested(@NonNull Context context,
                                     @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected @LayoutRes int getLayoutRes() {
        return R.layout.ads_recycler_view_nested;
    }

    @Override
    public @Nullable RecyclerView.LayoutManager getRecyclerViewLayoutManager() {
        return DynamicLayoutUtils.getLinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL);
    }
}
