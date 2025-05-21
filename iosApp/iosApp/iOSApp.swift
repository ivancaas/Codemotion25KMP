import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        doInitKoinIos(modules: [])
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
