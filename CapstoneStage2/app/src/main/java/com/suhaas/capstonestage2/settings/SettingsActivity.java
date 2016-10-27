package com.suhaas.capstonestage2.settings;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.novoda.notils.caster.Views;
import com.suhaas.capstonestage2.BuildConfig;
import com.suhaas.capstonestage2.HNewsActivity;
import com.suhaas.capstonestage2.R;
import com.suhaas.capstonestage2.injection.Inject;
import com.suhaas.capstonestage2.preferences.LoginSharedPreferences;

public class SettingsActivity extends HNewsActivity implements SettingsFragment.Listener, LogoutConfirmDialogFragment.Listener, GoogleApiClient.OnConnectionFailedListener {

    private static final int REQUEST_INVITE = 0;
    private static final String LOGOUT_FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ".LOGOUT_FRAGMENT_TAG";

    private LoginSharedPreferences loginSharedPreferences;
    private GoogleApiClient googleApiClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupSubActivityWithTitle();

        loginSharedPreferences = LoginSharedPreferences.newInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Inject.usageAnalytics().trackPage(getString(R.string.analytics_page_settings));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                Inject.usageAnalytics().trackEvent(getString(R.string.analytics_event_app_invite_settings_complete));
            }
        }
    }

    @Override
    public void onShowLogoutDialog() {
        showLogoutConfirmationDialog();
    }

    @Override
    public void onAppInviteRequested() {
        setupGoogleClient();
        showAppInviteMessage();
    }

    private void setupGoogleClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(AppInvite.API)
                .enableAutoManage(this, this)
                .build();
    }

    private void showAppInviteMessage() {
        Snackbar.make(Views.findById(this, R.id.content_root), R.string.app_invite, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.app_invite_action, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onInviteClicked();
                    }
                }).show();
    }

    private void onInviteClicked() {
        Inject.usageAnalytics().trackEvent(getString(R.string.analytics_event_app_invite_started));
        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(getString(R.string.invitation_message))
                .setCustomImage(Uri.parse(getString(R.string.invitation_custom_image)))
                .setCallToActionText(getString(R.string.invitation_cta))
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }

    public void showLogoutConfirmationDialog() {
        Fragment fragment = getFragmentManager().findFragmentByTag(LOGOUT_FRAGMENT_TAG);
        if (fragment == null) {
            LogoutConfirmDialogFragment.newInstance().show(getFragmentManager(), LOGOUT_FRAGMENT_TAG);
        }
    }

    @Override
    public void onLogoutConfirmed() {
        loginSharedPreferences.logout();
        navigate().toNews();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Snackbar.make(Views.findById(this, R.id.content_root), R.string.google_play_services_error, Snackbar.LENGTH_SHORT).show();
    }
}
