export interface PDFViewerPlugin {
  open(params: { url: string, top?: number }): Promise<void>;
  close(): Promise<void>;
}
