//
//  DashboardRouter.swift
//  iosApp
//
//  Created by Arkadiusz Cieśliński on 03/01/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation

protocol DashboardRouter {
    func startDrawing()
}

class DashboardRouterDumb: RegistrationRouter {
    func startDrawing() {
        // Empty implementation for preview or testing
    }
}
