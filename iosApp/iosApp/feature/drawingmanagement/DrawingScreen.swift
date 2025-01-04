import Foundation
import SwiftUI
import shared

@MainActor
class DrawingViewModelWrapper: ObservableObject {
    let drawingViewModel: DrawingViewModel

    @Published var drawingState: DrawingUiState_
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

    func onDraw() {
        // Implement the "Draw" logic
    }

    func onClose() {
        // Implement the "Close" logic
    }
}

struct DrawingScreen: View {
    @ObservedObject private(set) var viewModel: DrawingViewModelWrapper = DrawingViewModelWrapper()
    private(set) var scannerRouter: ScannerRouter

    var body: some View {
        VStack(spacing: 16) {
            // Date
            Text(viewModel.drawingState.getFormattedDate())
                .font(.footnote)
                .padding(.bottom, 8)

            // Title
            Text(viewModel.drawingState.title)
                .font(.title2)
                .padding(.bottom, 8)

            // Description
            Text(viewModel.drawingState.details)
                .font(.body)
                .padding(.bottom, 16)

            // Participants List
            ParticipantsList(participants: viewModel.participants)

            // Add Participant Button
            Button(action: {
                scannerRouter.startScanner { id in
                    viewModel.addParticipant(id: id)
                }
            }) {
                Text("Add Participant")
                    .padding()
                    .frame(maxWidth: .infinity)
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(8)
            }

            // Draw Button
            Button(action: {
                viewModel.onDraw()
            }) {
                Text("Draw")
                    .padding()
                    .frame(maxWidth: .infinity)
                    .background(Color.green)
                    .foregroundColor(.white)
                    .cornerRadius(8)
            }

            // Close Button
            Button(action: {
                viewModel.onClose()
            }) {
                Text("Close")
                    .padding()
                    .frame(maxWidth: .infinity)
                    .background(Color.red)
                    .foregroundColor(.white)
                    .cornerRadius(8)
            }
        }
        .padding(16)
        .onAppear {
            viewModel.startObserving()
        }
    }
}

struct ParticipantsList: View {
    var participants: [ParticipantUiState]

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text("Participants")
                .font(.headline)

            ForEach(participants, id: \.self) { participant in
                Text("\(participant.name) \(participant.surname)")
                    .padding(16)
            }
        }
    }
}

struct DrawingScreen_Previews: PreviewProvider {
    static var previews: some View {
        DrawingScreen(
            viewModel: .init(),
            scannerRouter: ScannerNavDumb()
        )
    }
}
