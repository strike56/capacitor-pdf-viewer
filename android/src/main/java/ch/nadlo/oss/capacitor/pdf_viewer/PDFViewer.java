package ch.nadlo.oss.capacitor.pdf_viewer;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.getcapacitor.Bridge;
import com.rajat.pdfviewer.PdfViewerActivity;

import java.util.ArrayList;
import java.util.List;

public class PDFViewer {
    private Bridge bridge = null;
    private Intent activeIntent = null;

    private static final List<Activity> activeActivities = new ArrayList<>();

    public void setBridge(Bridge bridge) {
        this.bridge = bridge;

        // Register global activity lifecycle callbacks if not already registered
        if (activeActivities.isEmpty()) {
            Application app = (Application) bridge.getContext().getApplicationContext();
            app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
                    activeActivities.add(activity);
                }

                @Override
                public void onActivityDestroyed(@NonNull Activity activity) {
                    activeActivities.remove(activity);
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
        // Create an intent with unique extras or identifiers
        activeIntent = PdfViewerActivity.Companion.launchPdfFromUrl(
                this.bridge.getContext(),
                url,
                title,
                "",
                false
        );

        activeIntent.putExtra("UNIQUE_ID", "PDF_VIEWER_1"); // Or some other unique identifier

        this.bridge.getActivity().startActivity(activeIntent);
    }

    public void close() {
        Log.v("TriAppPDFViewer", "Closing all PDF Viewer activities.");

        if (activeIntent != null) {
            Activity pdfViewerActivity = getActivityByIntent(activeIntent);
            if (pdfViewerActivity != null) {
                pdfViewerActivity.finish(); // Explicitly close the PDF viewer activity
            } else {
                Log.v("TriAppPDFViewer", "No PDF Viewer activity found in stack.");
            }

            activeIntent = null;
        }
    }

    private Activity getActivityByIntent(Intent intent) {
        for (Activity activity : activeActivities) {
            // Match activities by comparing specific extras or tags
            String activityId = activity.getIntent().getStringExtra("UNIQUE_ID");
            String intentId = intent.getStringExtra("UNIQUE_ID");

            if (activityId != null && activityId.equals(intentId)) {
                return activity; // Match found
            }
        }
        return null; // No match found
    }
}
