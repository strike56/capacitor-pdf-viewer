export interface PDFViewerPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
