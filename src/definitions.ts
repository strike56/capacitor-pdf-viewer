export interface PDFViewerPlugin {
  open(params: { url: string; title?: string, top?: number }): Promise<void>;
  close(): Promise<void>;
}
