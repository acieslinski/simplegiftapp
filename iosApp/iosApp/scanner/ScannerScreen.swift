import SwiftUI
import AVFoundation
import shared

struct ScannerScreen: View {
    @State var isShowingScanner = true
    @State private var scannedCode = ""
    @State private var isPermissionGranted = false

    var onScannedCode: (String) -> Void

    var body: some View {
        VStack {
            if isPermissionGranted {
                Text(getIosString(resource: iosStringRes.scanner_hint_scan))
                    .font(.headline)
                    .padding()
                
                CameraView { code in
                    self.scannedCode = code
                    self.onScannedCode(code)
                 }
                .edgesIgnoringSafeArea(.all)
            } else {
                Text("Please grant camera permission to scan QR codes.")
                    .padding()
                    .onAppear(perform: checkCameraPermission)
            }

                Text("Scanned code: \(scannedCode)")
                    .padding()
        }
    }

    private func checkCameraPermission() {
        switch AVCaptureDevice.authorizationStatus(for: .video) {
        case .authorized:
            isPermissionGranted = true
        case .notDetermined:
            AVCaptureDevice.requestAccess(for: .video) { granted in
                DispatchQueue.main.async {
                    self.isPermissionGranted = granted
                }
            }
        default:
            isPermissionGranted = false
        }
    }
}
