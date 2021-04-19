package org.tlhInganHol.android.klingonanalysisdemo;

import android.app.SearchManager;
import android.content.Intent;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class KlingonAnalysisDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.text);
        String phrase = textView.getText().toString();
        SpannableString spannableString = new SpannableString(phrase);
        ClickableSpan clickableSpan = new LookupClickableSpan(phrase);
        spannableString.setSpan(clickableSpan, 0, phrase.length(),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private class LookupClickableSpan extends ClickableSpan {
        private String mQuery;

        LookupClickableSpan(String query) {
            mQuery = query;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEARCH);
            intent.setComponent(new ComponentName(
                "org.tlhInganHol.android.klingonassistant",
                "org.tlhInganHol.android.klingonassistant.KlingonAssistant"));
            intent.putExtra(SearchManager.QUERY, mQuery);
            startActivity(intent);
        }
    }
}
