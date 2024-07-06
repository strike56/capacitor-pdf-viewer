import Foundation
import UIKit
import PDFKit

@objc public class PDFViewer: NSObject {
    private let pdfView = PDFView()
    
    @objc public func open(_ pdfURL: URL, top: Int = 0) {
        DispatchQueue.main.sync {
            if let rootViewController = UIApplication.shared.keyWindow?.rootViewController {
                if !rootViewController.view.contains(self.pdfView) {
                    rootViewController.view.addSubview(self.pdfView);
                }
            }
        }
        
        guard let document = PDFDocument(url: pdfURL) else {
            return
        }
        
        DispatchQueue.main.async {
            if let rootViewController = UIApplication.shared.keyWindow?.rootViewController {
                self.pdfView.frame = rootViewController.view.bounds;
                self.pdfView.frame.origin.y = CGFloat(top);
                // Adjust the height to account for the top padding
                self.pdfView.frame.size.height = rootViewController.view.bounds.height - CGFloat(top)

                rootViewController.view.bringSubviewToFront(self.pdfView);
                
                // make PDF fit full width
                self.pdfView.autoScales = true
                
                self.pdfView.document = document

            }
        }
    }
    
    @objc public func closeViewer() {
        DispatchQueue.main.async {
            if let rootViewController = UIApplication.shared.keyWindow?.rootViewController {
                // hide pdfView to make hidding effect faster
                rootViewController.view.sendSubviewToBack(self.pdfView);
                
                // clear document
                self.pdfView.document = nil;
                
                self.pdfView.frame = CGRect();
                self.pdfView.removeFromSuperview();
            }
        }
    }
}
