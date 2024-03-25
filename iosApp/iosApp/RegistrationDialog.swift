import SwiftUI
import shared

struct RegistrationDialog: View {
    @Binding var registrationDialogState: RegistrationDialogState
    var onDismiss: () -> Void

    var body: some View {
        EmptyView()
            .alert(isPresented: .constant(isDialogVisible())) {
                dismissKeyboard()
                return createAlert()
            }
    }

    private func isDialogVisible() -> Bool {
        return !(registrationDialogState is RegistrationDialogStateHidden)
    }

    private func createAlert() -> Alert {
        return Alert(
            title: Text(alertTitle()),
            message: Text(alertMessage()),
            dismissButton: .default(Text(alertButtonTitle()), action: {
                onDismiss()
            })
        )
    }

    private func alertTitle() -> String {
        if registrationDialogState is RegistrationDialogStateConfirmation {
            return "Success"
        } else if registrationDialogState is RegistrationDialogStateError {
            return "Error"
        } else {
            return ""
        }
    }

    private func alertMessage() -> String {
        if let errorState = registrationDialogState as? RegistrationDialogStateError {
            return mapErrorToMessage(errorState.error)
        } else if registrationDialogState is RegistrationDialogStateConfirmation {
            return "Registration successful!"
        } else {
            return ""
        }
    }

    private func alertButtonTitle() -> String {
        return "OK"
    }

    private func mapErrorToMessage(_ error: RegistrationError) -> String {
        if error is RegistrationErrorUnknown {
            return "An unknown error occurred. Please try again."
        } else {
            return "An unexpected error occurred."
        }
    }

    private func dismissKeyboard() {
        UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
    }
}
