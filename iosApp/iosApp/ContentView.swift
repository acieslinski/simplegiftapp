import SwiftUI
import VisionKit
import shared

struct ContentView: View {
    
    var body: some View {
    DrawingScreen(viewModel: .init(), scannerRouter: ScannerNavDumb())
//        ScannerScreen()
//        	RegistrationScreen(viewModel: .init())
    }
}

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//		ContentView()
//	}
//}
