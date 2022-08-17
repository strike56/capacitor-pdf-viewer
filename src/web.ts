import { WebPlugin } from '@capacitor/core';

import type { PDFViewerPlugin } from './definitions';

export class PDFViewerWeb extends WebPlugin implements PDFViewerPlugin {
  async open(): Promise<void> {
    throw new Error('[PDFViewerWeb] method not implemented');
  }

  async close(): Promise<void> {
    throw new Error('[PDFViewerWeb] method not implemented');
  }
}
