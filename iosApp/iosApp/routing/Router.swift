import SwiftUI

final class Router: ObservableObject, ScannerRouter, RegistrationRouter {
    private var scannerCallback: ((String) -> Void)?

    public enum Destination: Codable, Hashable {
        case drawing
        case scanner
        case registration
    }

    @Published var navPath = NavigationPath()

    func navigate(to destination: Destination) {
        navPath.append(destination)
    }

    func navigateBack() {
        navPath.removeLast()
    }

    func navigateToRoot() {
        navPath.removeLast(navPath.count)
    }
    
    func clear() {
        navPath = NavigationPath()
    }
    
    func startScanner(callback: @escaping (String) -> Void) {
        scannerCallback = callback
        navigate(to: Router.Destination.scanner)
    }

    func stopScanner(result: String) {
        scannerCallback?(result)
        navigateBack()
    }

    func startDrawing() {
        navigate(to: Router.Destination.drawing)
    }
}
