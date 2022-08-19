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

        implementation.setActivity(getActivity());
    }

    @PluginMethod
    public void open(PluginCall call) {
        String url = call.getString("url");
        Integer top = call.getInt("top", 0);

        if (url != null && !url.isEmpty()) {
            implementation.openViewer(url, top);
        }

        call.resolve();
    }

    @PluginMethod
    public void close(PluginCall call) {
        call.resolve();
    }
}
