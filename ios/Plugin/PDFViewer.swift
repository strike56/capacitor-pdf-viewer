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
            self.pdfView.document = document
            
            if let rootViewController = UIApplication.shared.keyWindow?.rootViewController {
                self.pdfView.frame = rootViewController.view.bounds;
                self.pdfView.frame.origin.y = CGFloat(top);
                
                rootViewController.view.bringSubviewToFront(self.pdfView);
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
