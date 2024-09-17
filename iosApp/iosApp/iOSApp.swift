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
                            .navigationBarBackButtonHidden(true)
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
//
//	var body: some Scene {
//		WindowGroup {
//			ContentView()
//		}
//	}
}
