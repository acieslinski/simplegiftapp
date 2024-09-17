import Foundation

protocol ScannerRouter {
    func startScanner(callback: @escaping (String) -> Void)
    func stopScanner(result: String)
}

class ScannerNavDumb: ScannerRouter {
    func startScanner(callback: @escaping (String) -> Void) {
        // Empty implementation for preview or testing
    }
    func stopScanner(result: String) {
        // Empty implementation for preview or testing
    }
}
