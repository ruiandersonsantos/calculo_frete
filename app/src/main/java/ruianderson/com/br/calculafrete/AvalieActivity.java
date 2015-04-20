package ruianderson.com.br.calculafrete;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class AvalieActivity extends ActionBarActivity {

    WebView wv = null;

    // inicial--

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avalie);

        wv = (WebView)findViewById(R.id.wv_avalie);

        WebSettings ws =  wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);
        wv.loadUrl("https://play.google.com/store/apps/developer?id=Rui+Anderson+Paim+Santos");

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
    }


}
