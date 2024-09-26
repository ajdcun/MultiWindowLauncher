package com.example.multiwindowlauncher;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.reflect.Method;
import org.lsposed.hiddenapibypass.HiddenApiBypass;

import kotlin.NotImplementedError;

public class MainActivity extends AppCompatActivity {

    private Button standardButton;
    private Button splitScreenButton;
    private Button freeFormButton;
    private Button pinnedButton;
    private Button embeddedActivityButton;

    private String firstPackage;
    private String firstActivity;
    private String secondPackage;
    private String secondActivity;
    private Rect windowBounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        standardButton = findViewById(R.id.standardButton);
        standardButton.setOnClickListener(v -> launchOnStandardMode());

        splitScreenButton = findViewById(R.id.splitScreenButton);
        splitScreenButton.setOnClickListener(v -> launchOnSplitScreenMode());

        freeFormButton = findViewById(R.id.freeFormButton);
        freeFormButton.setOnClickListener(v -> launchOnFreeFormMode());

        pinnedButton = findViewById(R.id.pinnedButton);
        pinnedButton.setOnClickListener(v -> launchOnPictureInPictureMode());

        embeddedActivityButton = findViewById(R.id.embeddedActivityButton);
        embeddedActivityButton.setOnClickListener(v -> launchOnEmbeddedMode());

        firstPackage = "com.google.android.apps.maps";
        firstActivity = "com.google.android.maps.MapsActivity";

        secondPackage = "com.android.chrome";
        secondActivity = "com.google.android.apps.chrome.Main";
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void launchOnStandardMode() {
        // Standard mode
        String mode = "Standard";
        Toast.makeText(this, String.format("Starte %s-Modus.", mode), Toast.LENGTH_LONG).show();
        try {
            killBackgroundProcess(firstPackage);
            killBackgroundProcess(secondPackage);
            Intent intent = getMainIntentAndFlags();

            intent.setComponent(new ComponentName(firstPackage, firstActivity));
            startActivity(intent);

            intent.setComponent(new ComponentName(secondPackage, secondActivity));
            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(this, String.format("Fehler im %s-Modus: %s" , mode, e.getMessage()), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void launchOnSplitScreenMode() {
        // Split-screen mode: API 32 (Android 12L)
        String mode = "Splitscreen";
        int minApiVersion = Build.VERSION_CODES.S_V2;
        Toast.makeText(this, String.format("Starte %s-Modus.", mode), Toast.LENGTH_LONG).show();
        if (Build.VERSION.SDK_INT >= minApiVersion) {
            try {
                killBackgroundProcess(firstPackage);
                killBackgroundProcess(secondPackage);
                Intent intent = getMainIntentAndFlags();

                intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.setComponent(new ComponentName(firstPackage, firstActivity));
                startActivity(intent);

                intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT);
                intent.setComponent(new ComponentName(secondPackage, secondActivity));
                startActivity(intent);

            } catch (Exception e) {
                Toast.makeText(this, String.format("Fehler im %s-Modus: %s" , mode, e.getMessage()), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, String.format("%s-Modus ist nur ab Android API %s verfügbar.", mode, minApiVersion), Toast.LENGTH_LONG).show();
        }
    }

    private void launchOnFreeFormMode() {
        // Free-Form mode: API 26 (Android 8)
        String mode = "Freeform";
        int minApiVersion = Build.VERSION_CODES.O;
        Toast.makeText(this, String.format("Starte %s-Modus.", mode), Toast.LENGTH_LONG).show();
        if (Build.VERSION.SDK_INT >= minApiVersion) {
            try {
                killBackgroundProcess(firstPackage);
                killBackgroundProcess(secondPackage);
                Intent intent = getMainIntentAndFlags();
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                ActivityOptions options = getActivityOptions(WindowingModes.getFreeFormId(), WindowingModes.getMethodName());

                windowBounds = getSplitScreenBoundsPrimary();
                options.setLaunchBounds(windowBounds);
                intent.setComponent(new ComponentName(firstPackage, firstActivity));
                startActivity(intent, options.toBundle());

                windowBounds = getSplitScreenBoundsSecondary();
                options.setLaunchBounds(windowBounds);
                intent.setComponent(new ComponentName(secondPackage, secondActivity));
                startActivity(intent, options.toBundle());

            } catch (Exception e) {
                Toast.makeText(this, String.format("Fehler im %s-Modus: %s" , mode, e.getMessage()), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, String.format("%s-Modus ist nur ab Android API %s verfügbar.", mode, minApiVersion), Toast.LENGTH_LONG).show();
        }
    }

    private void launchOnPictureInPictureMode() {
        // Picture-In-Picture mode: API 26 (Android 8)
        String mode = "Pinned";
        int minApiVersion = Build.VERSION_CODES.O;
        Toast.makeText(this, String.format("Starte %s-Modus.", mode), Toast.LENGTH_LONG).show();
        if (Build.VERSION.SDK_INT >= minApiVersion) {
            try {
                killBackgroundProcess(firstPackage);
                killBackgroundProcess(secondPackage);
                Intent intent = getMainIntentAndFlags();
                ActivityOptions options = getActivityOptions(WindowingModes.getPinnedId(), WindowingModes.getMethodName());

                intent.setComponent(new ComponentName(firstPackage, firstActivity));
                startActivity(intent, options.toBundle());

                intent.setComponent(new ComponentName(secondPackage, secondActivity));
                startActivity(intent, options.toBundle());

            } catch (Exception e) {
                Toast.makeText(this, String.format("Fehler im %s-Modus: %s" , mode, e.getMessage()), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, String.format("%s-Modus ist nur ab Android API %s verfügbar.", mode, minApiVersion), Toast.LENGTH_LONG).show();
        }
    }

    private void launchOnEmbeddedMode() {
        // Embedded activity mode: API 33 (Android 13)
        String mode = "Embedded";
        Toast.makeText(this, String.format("Starte %s-Modus.", mode), Toast.LENGTH_LONG).show();
        int minApiVersion = Build.VERSION_CODES.S;
        if (Build.VERSION.SDK_INT >= minApiVersion) {
            try {
                //TODO: Embedded activity mode
                throw new NotImplementedError();
            } catch (Exception e) {
                Toast.makeText(this, String.format("%s-Modus ist auf diesem Gerät nicht verfügbar.", mode), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, String.format("%s-Modus ist nur ab Android API %s verfügbar.", mode, minApiVersion), Toast.LENGTH_LONG).show();
        }
    }


    private void killBackgroundProcess(String packageName) {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        am.killBackgroundProcesses(packageName);
    }

    private Intent getMainIntentAndFlags() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    private ActivityOptions getActivityOptions(int modeStackId, String methodName) {
        ActivityOptions options = ActivityOptions.makeBasic();
        if(modeStackId != -1) {
            allowReflection();
            try {
                Method method = ActivityOptions.class.getMethod(methodName, int.class);
                method.invoke(options, modeStackId);
            } catch (Exception e) {
                Toast.makeText(this, String.format(e.getMessage()), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        return options;
    }

    @TargetApi(Build.VERSION_CODES.P)
    public static void allowReflection() {
        try {
            Class.forName("org.junit.Test");
        } catch (ClassNotFoundException e) {
            HiddenApiBypass.addHiddenApiExemptions("");
        }
    }

    private Rect getSplitScreenBoundsPrimary() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int fullScreenWidth = displayMetrics.widthPixels;
        int fullScreenHeight = displayMetrics.heightPixels;
        int orientation = this.getApplicationContext().getResources().getConfiguration().orientation;
        boolean isPortrait = orientation == Configuration.ORIENTATION_PORTRAIT;
        boolean isLandscape = orientation == Configuration.ORIENTATION_LANDSCAPE;

        Rect windowBounds = null;
        if (isPortrait){
            windowBounds = new Rect(0, 0, fullScreenWidth, fullScreenHeight/2);
        }
        else if(isLandscape){
            windowBounds = new Rect(0, 0, fullScreenWidth/2, fullScreenHeight);
        }

        return windowBounds;
    }

    private Rect getSplitScreenBoundsSecondary() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int fullScreenWidth = displayMetrics.widthPixels;
        int fullScreenHeight = displayMetrics.heightPixels;
        int orientation = this.getApplicationContext().getResources().getConfiguration().orientation;
        boolean isPortrait = orientation == Configuration.ORIENTATION_PORTRAIT;
        boolean isLandscape = orientation == Configuration.ORIENTATION_LANDSCAPE;

        Rect windowBounds = null;
        if (isPortrait){
            windowBounds = new Rect(0, fullScreenHeight/2, fullScreenWidth, fullScreenHeight);
        }
        else if(isLandscape){
            windowBounds = new Rect(fullScreenWidth/2, 0, fullScreenWidth, fullScreenHeight);
        }

        return windowBounds;
    }


}
