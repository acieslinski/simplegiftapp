import SwiftUI
import shared

extension RegistrationScreen {
    @MainActor
    class RegistrationViewModelWrapper: ObservableObject {
        let registrationViewModel: RegistrationViewModel

        init() {
            registrationViewModel = RegistrationInjector().registrationViewModel
            registrationState = registrationViewModel.registrationState.value
            registrationDialogState = registrationViewModel.registrationDialogState.value
        }

        @Published var registrationState: RegistrationState
        @Published var registrationDialogState: RegistrationDialogState

        func startObserving() {
            Task {
                for await registrationS in registrationViewModel.registrationState {
                    self.registrationState = registrationS
                }
            }
            Task {
                for await dialogState in registrationViewModel.registrationDialogState {
                    self.registrationDialogState = dialogState
                }
            }
        }

        func onRegisterAction(name: String, surname: String) {
            registrationViewModel.onRegisterAction(name: name, surname: surname)
        }

        func onNotificationAckAction() {
            registrationViewModel.onNotificationAckAction()
        }
    }
}

struct RegistrationScreen: View {
    @ObservedObject private(set) var viewModel: RegistrationViewModelWrapper = RegistrationViewModelWrapper()
    private(set) var onRegistrationDone: () -> Void
    @State private var name: String = ""
    @State private var surname: String = ""

    var body: some View {
        VStack {
            LoginForm(
                name: $name,
                surname: $surname,
                state: viewModel.registrationState,
                onRegistrationAction: {
                    viewModel.onRegisterAction(name: name, surname: surname)
                }
            )
        }
        .padding()
        .onAppear {
            viewModel.startObserving()
        }
        .overlay(
            RegistrationDialog(registrationDialogState: $viewModel.registrationDialogState) {
                viewModel.onNotificationAckAction()
            }
        )
        .onChange(of: viewModel.registrationState.isRegistrationAck) { _, isAck in
            if isAck {
                onRegistrationDone()
            }
        }
    }
}

struct LoginForm: View {
    @Binding var name: String
    @Binding var surname: String
    var state: RegistrationState
    var onRegistrationAction: () -> Void

    var body: some View {
        VStack(spacing: 16) {
            VStack(alignment: .leading) {
                Text(getIosString(resource: iosStringRes.account_name))
                    .font(.caption)
                TextField("Enter your name", text: $name)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .padding(.top, 8)
            }

            VStack(alignment: .leading) {
                Text(getIosString(resource: iosStringRes.account_surname))
                    .font(.caption)
                TextField("Enter your surname", text: $surname)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .padding(.top, 8)
            }

            Spacer().frame(height: 16)

            if state.isLoading {
                ProgressView()
            }

            Button(action: onRegistrationAction) {
                Text(getIosString(resource: iosStringRes.account_register))
                    .frame(maxWidth: .infinity)
            }
            .disabled(state.isLoading)
            .padding()
            .background(state.isLoading ? Color.gray : Color.blue)
            .foregroundColor(.white)
            .cornerRadius(8)
        }
        .padding()
    }
}

//#Preview {
//    RegistrationScreen()
//}
