package ch.nadlo.oss.capacitor.pdf_viewer;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "PDFViewer")
public class PDFViewerPlugin extends Plugin {

    private final PDFViewer implementation = new PDFViewer();

    @Override
    public void load() {
        super.load();

        implementation.setBridge(this.getBridge());
    }

    @PluginMethod
    public void open(PluginCall call) {
        String url = call.getString("url");
        String title = call.getString("title", "");

        if (url != null && !url.isEmpty()) {
            implementation.openViewer(url, title);
        }

        call.resolve();
    }

    @PluginMethod
    public void close(PluginCall call) {
        // implementation.close();

        call.resolve();
    }
}
