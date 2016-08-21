package com.example.varsha.navtry;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.varsha.navtry.R;
import com.example.varsha.navtry.FatSecretUtils;
import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpParameters;
import oauth.signpost.signature.HmacSha1MessageSigner;
import oauth.signpost.signature.QueryStringSigningStrategy;


/**
 * Created by Varsha on 5/3/2016.
 */
public class FatSecretUtils extends ActionBarActivity{


    private static Context context;
    public static final String PREFERENCES_FILE = "FatSecret";

    public static final String OAUTH_ACCESS_TOKEN_KEY = "8bb11b48638b4bd79a9b43551b2a5f30";
    public static final String OAUTH_ACCESS_SECRET_KEY = "05265d74537847fcb922d3ce49c4724f";

    public static void setContext(Context cxt)
    {
        context = cxt;
    }

    public static String sign(String url) throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(context.getString(R.string.fatsecret_consumer_key), context.getString(R.string.fatsecret_consumer_secret));
        consumer.setMessageSigner(new HmacSha1MessageSigner());
        consumer.setSigningStrategy(new QueryStringSigningStrategy());
        consumer.setTokenWithSecret(prefs.getString(OAUTH_ACCESS_TOKEN_KEY, ""), prefs.getString(OAUTH_ACCESS_SECRET_KEY, ""));
        return consumer.sign(url);
    }
}
