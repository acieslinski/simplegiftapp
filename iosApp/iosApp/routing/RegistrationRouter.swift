import Foundation

protocol RegistrationRouter {
    func startDrawing()
}

class RegistrationRouterDumb: RegistrationRouter {
    func startDrawing() {
        // Empty implementation for preview or testing
    }
}
