package ch.nadlo.oss.capacitor.pdf_viewer;

import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.rajat.pdfviewer.PdfViewerActivity;

public class PDFViewer {
    private AppCompatActivity activity = null;
    private Intent activeIntent = null;

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void openViewer(String url, Integer topOffset) {
        // load PDF
        activeIntent = PdfViewerActivity.Companion.launchPdfFromUrl(
                this.activity.getApplicationContext(),
                url,
                "pdf title",
                "",
                false
        );

        this.activity.startActivity(activeIntent);

        ActionBar actionBar = this.activity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }
    }

    public void close() {
        if (activeIntent != null) {
            this.activity.onBackPressed();

            activeIntent = null;
        }
    }
}
