import SwiftUI
import VisionKit

struct CameraView: View {
    var onCodeScanned: (String) -> Void
    @State var isShowingScanner = true
    @State private var scannedText = ""
    
    var body: some View {
        if DataScannerViewController.isSupported && DataScannerViewController.isAvailable {
            ZStack(alignment: .bottom) {
                DataScannerRepresentable(
                    shouldStartScanning: $isShowingScanner,
                    scannedText: $scannedText,
                    dataToScanFor: [.barcode(symbologies: [.qr])]
                )
            }
            .onChange(of: scannedText) {
                onCodeScanned(scannedText)
            }
        } else if !DataScannerViewController.isSupported {
            Text("It looks like this device doesn't support the DataScannerViewController")
        } else {
            Text("It appears your camera may not be available")
        }
    }
}
