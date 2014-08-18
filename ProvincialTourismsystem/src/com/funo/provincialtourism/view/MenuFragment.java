package com.funo.provincialtourism.view;

import com.funo.antennatestsystem.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MenuFragment extends Fragment
{
    // Fragment对应的标签，当Fragment依附于Activity时得到
    private String tag;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        tag = getTag();
    }

    @SuppressLint("ResourceAsColor")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        TextView textView = new TextView(getActivity());
        textView.setText(tag);
        textView.setTextColor(R.color.titlebar_bg);
        return textView;
    }
}