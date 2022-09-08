package ch.nadlo.oss.capacitor.pdf_viewer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.getcapacitor.Bridge;
import com.rajat.pdfviewer.PdfViewerActivity;

public class PDFViewer {
    private Bridge bridge = null;
    private Intent activeIntent = null;

    public void setBridge(Bridge bridge) {
        this.bridge = bridge;
    }

    public void openViewer(String url, String title) {
        // load PDF
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
        if (activeIntent != null) {
            this.bridge.getActivity().finish();

            activeIntent = null;
        }
    }
}
