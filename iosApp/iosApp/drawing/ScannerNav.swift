import Foundation

protocol ScannerNav {
    func startScanner(callback: @escaping (String, String, String) -> Void)
}

class ScannerNavDumb: ScannerNav {
    func startScanner(callback: @escaping (String, String, String) -> Void) {
        // Empty implementation for preview or testing
    }
}
