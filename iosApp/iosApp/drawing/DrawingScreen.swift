import SwiftUI

import Foundation
import SwiftUI
import shared

extension DrawingScreen {
    @MainActor
    class DrawingViewModelWrapper: ObservableObject {
        let drawingViewModel: DrawingViewModel

        @Published var drawingState: DrawingUiState
        @Published var participants: [ParticipantUiState]

        init() {
            drawingViewModel = DrawingInjector().drawingViewModel
            drawingState = drawingViewModel.drawingUiState.value
            participants = drawingViewModel.drawingUiState.value.participants
        }

        func startObserving() {
            Task {
                for await drawingS in drawingViewModel.drawingUiState {
                    self.drawingState = drawingS
                    self.participants = drawingS.participants
                }
            }
        }

        func addParticipant(id: String) {
            drawingViewModel.addParticipant(id: id)
        }
    }
}

struct DrawingScreen: View {
    @ObservedObject private(set) var viewModel: DrawingViewModelWrapper = DrawingViewModelWrapper()
    private(set) var scannerRouter: ScannerRouter

    var body: some View {
        VStack(spacing: 16) {
            ParticipantsList(participants: viewModel.participants)

            Button(action: {
                scannerRouter.startScanner { id in
                    viewModel.addParticipant(id: id)
                }
            }) {
                Text("Add Participant")
            }
            .padding()
        }
        .padding(16)
    }
}

struct ParticipantsList: View {
    var participants: [ParticipantUiState]

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text("Participants")
                .font(.headline)
//            List(participants) { participant in
//                Text("\(participant.name) \(participant.surname)")
//            }
        }
    }
}

//struct DrawingScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        let scannerNav = ScannerNavDumb()
//        let viewModel = DrawingViewModel() // Replace with actual shared view model
//        DrawingScreen(viewModel: viewModel, scannerNav: scannerNav)
//    }
//}
