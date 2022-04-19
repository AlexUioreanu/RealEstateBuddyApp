package com.example.realestatebuddy.ui.about;

import android.os.Bundle;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.realestatebuddy.R;


public class AboutFragment extends Fragment {

    private TextView linkTxt;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_about, container, false);

        linkTxt = v.findViewById(R.id.about_linkTxt);

        removeUnderline();

        return v;
    }

    private void removeUnderline() {
        Spannable sa = (Spannable) linkTxt.getText();
        for (URLSpan u : sa.getSpans(0, sa.length(), URLSpan.class)) {
            sa.setSpan(new UnderlineSpan() {
                public void updateDrawState(TextPaint tp) {
                    tp.setUnderlineText(false);
                }
            }, sa.getSpanStart(u), sa.getSpanEnd(u), 0);
        }
    }
}