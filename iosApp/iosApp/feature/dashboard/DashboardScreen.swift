//
//  DashboardScreen.swift
//  iosApp
//
//  Created by Arkadiusz Cieśliński on 03/01/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Combine
import shared

extension DrawingUiState: Identifiable {}

extension DashboardScreen {
    @MainActor
    class DashboardViewModelWrapper: ObservableObject {
        let dashboardViewModel: DashboardViewModel
        
        init() {
            dashboardViewModel = DashboardInjector().dashboardViewModel
            dashboardUiState = dashboardViewModel.dashboardUiState.value
        }
        
        @Published var dashboardUiState: DashboardUiState
        
        func startObserving() {
            Task {
                for await state in dashboardViewModel.dashboardUiState {
                    self.dashboardUiState = state
                }
            }
        }
        
        func onDrawingClickAction(drawingId: String) {
            dashboardViewModel.onSelectDrawingAction(drawingId: drawingId)
        }
    }
}


struct DashboardScreen: View {
    @ObservedObject private(set) var viewModel: DashboardViewModelWrapper = DashboardViewModelWrapper() // Dependency injection can be added here
    var onDrawingAddClick: () -> Void = {}
    var onDrawingClick: (String) -> Void = {_ in }
    
    var body: some View {
        VStack {
            Text("Welcome X, X")
                .font(.headline)
                .padding()
            
            Button(action: {
                // Handle participate in draw action
            }) {
                Text("Participate in a draw")
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(8)
            }
            .padding(.horizontal)
            
            Text("Your Drawings:")
                .font(.title2)
                .padding()
            
            if viewModel.dashboardUiState is DashboardUiState.Loading {
                ProgressView()
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
            } else if viewModel.dashboardUiState is DashboardUiState.Empty {
                Text("No drawings available.")
                    .foregroundColor(.gray)
                    .padding()
            } else if viewModel.dashboardUiState is DashboardUiState.Success {
                let successState = viewModel.dashboardUiState as! DashboardUiState.Success
                List(successState.drawings) { drawing in
                    DrawingItemView(drawing: drawing) {
                        onDrawingClick(drawing.id)
                    }
                }
            }
        }
        .overlay(
            FloatingActionButton {
                onDrawingAddClick()
            }
                .padding(),
            alignment: .bottomTrailing
        )
        .onAppear {
            viewModel.startObserving()
        }
        .navigationBarBackButtonHidden(true)
    }
}

struct DrawingItemView: View {
    let drawing: DrawingUiState
    let onClick: () -> Void
    
    var body: some View {
        HStack {
            Text("\(drawing.orderNumber).")
                .font(.body)
                .frame(width: 50, alignment: .leading)
            
            VStack(alignment: .leading) {
                Text(drawing.title)
                    .font(.body)
                
                Text(drawing.getFormattedDate())
                    .font(.caption)
                    .foregroundColor(.gray)
            }
            Spacer()
        }
        .contentShape(Rectangle()) // Makes the whole row tappable
        .onTapGesture {
            onClick()
        }
        .padding()
    }
}

struct FloatingActionButton: View {
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            Image(systemName: "plus")
                .foregroundColor(.white)
                .padding()
                .background(Circle().fill(Color.blue))
        }
    }
}

struct DashboardScreen_Previews: PreviewProvider {
    static var previews: some View {
        DashboardScreen(viewModel: .init())
    }
}
