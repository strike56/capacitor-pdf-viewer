package ch.nadlo.oss.capacitor.pdf_viewer;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.getcapacitor.Bridge;
import com.rajat.pdfviewer.PdfViewerActivity;

import java.util.ArrayList;
import java.util.List;

public class PDFViewer {
    private Bridge bridge = null;

    private static final List<Activity> activeActivities = new ArrayList<>();

    public void setBridge(Bridge bridge) {
        this.bridge = bridge;

        // Register global activity lifecycle callbacks if not already registered
        if (activeActivities.isEmpty()) {
            Application app = (Application) bridge.getContext().getApplicationContext();
            app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
                    if (activity.toString().startsWith("com.rajat.pdfviewer.PdfViewerActivity")) {
                        activeActivities.add(activity);
                    }
                }

                @Override
                public void onActivityDestroyed(@NonNull Activity activity) {
                    if (activity.toString().startsWith("com.rajat.pdfviewer.PdfViewerActivity")) {
                        activeActivities.remove(activity);
                    }
                }

                // Other lifecycle methods can remain empty
                public void onActivityStarted(@NonNull Activity activity) {
                }

                public void onActivityStopped(@NonNull Activity activity) {
                }

                public void onActivityResumed(@NonNull Activity activity) {
                }

                public void onActivityPaused(@NonNull Activity activity) {
                }

                public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                }
            });
        }
    }

    public void openViewer(String url, String title) {
        this.close();

        // Create an intent with unique extras or identifiers
        Intent activeIntent = PdfViewerActivity.Companion.launchPdfFromUrl(
                this.bridge.getContext(),
                url,
                title,
                "",
                false
        );

        this.bridge.getActivity().startActivity(activeIntent);
    }

    public void close() {
        for (Activity activity : activeActivities) {
            activity.finish();
        }
    }
}
