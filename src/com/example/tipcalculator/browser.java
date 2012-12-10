package com.example.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.webkit.WebView;
// ...
import android.widget.Button;
import android.widget.EditText;

public class browser extends Activity {
   private EditText urltext;
   private Button gobutton;
   private WebView webview;
   
   @Override
   public void onCreate(Bundle savedInstanceState) {
      // ...
      super.onCreate(savedInstanceState);
      setContentView(R.layout.browser);

      // Get a handle to all user interface elements
      urltext = (EditText) findViewById(R.id.url_field);
      gobutton = (Button) findViewById(R.id.go_button);
      webview = (WebView) findViewById(R.id.web_view);

      // Setup event handlers
      gobutton.setOnClickListener(new OnClickListener() {
         public void onClick(View view) {
            openBrowser();
         }
      });
      urltext.setOnKeyListener(new OnKeyListener() {
         public boolean onKey(View view, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
               openBrowser();
               return true;
            }
            return false;
         }
      });
   }

   /** Open a browser on the URL specified in the text box */
   private void openBrowser() {
      webview.getSettings().setJavaScriptEnabled(true);
      webview.loadUrl(urltext.getText().toString());
   }
}