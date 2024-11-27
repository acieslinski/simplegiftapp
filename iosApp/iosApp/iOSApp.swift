import SwiftUI
import shared

@main
struct iOSApp: App {
    @ObservedObject var router = Router()

    init() {
        KoinInitializerKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            NavigationStack(path: $router.navPath) {
                // TODO at least one element must be on stack, so empty screen should be created
                RegistrationScreen(viewModel: .init()) {
                    router.navigate(to: Router.Destination.drawing)
                }
                .navigationDestination(for: Router.Destination.self) { destination in
                    switch destination {
                    case .registration:
                        RegistrationScreen(viewModel: .init()) {
                            router.navigateBack()
                            router.navigate(to: Router.Destination.drawing)
                        }
                    case .drawing:
                        DrawingScreen(viewModel: .init(), scannerRouter: router)
                    case .scanner:
                        ScannerScreen { code in
                            router.stopScanner(result: code)
                        }
                    }
                }
            }
            .environmentObject(router)
        }
    }
}
