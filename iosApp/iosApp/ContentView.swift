import SwiftUI
import shared
import KMPObservableViewModelSwiftUI
import KMPObservableViewModelCore

extension Kmp_observableviewmodel_coreViewModel: @retroactive ViewModel { }


struct ContentView: View {
    @StateViewModel private var viewModel = ViewModels().getExampleViewModel()
    
    var body: some View {
        randomUserScreen
    }
}

extension ContentView {
    private var randomUserScreen: some View {
        
        ZStack {
            switch onEnum(of:viewModel.uiState) {
            case .loading:
                LoadingState()
            case .error(let state):
                ErrorState(message: state.message) {
                    viewModel.reloadRandomUser()
                }
            case .success(let state):
                SuccessState(user: state.user) {
                    viewModel.reloadRandomUser()
                }
            }
        }
        .padding(16)
    }
}


struct LoadingState: View {
    var body: some View {
        VStack {
            ProgressView()
                .scaleEffect(1.5)
            Spacer().frame(height: 16)
            Text("Cargando usuario...")
                .foregroundColor(.blue)
                .font(.body)
        }
    }
}

struct ErrorState: View {
    let message: String?
    let onRetry: () -> Void
    
    var body: some View {
        VStack(alignment: .center, spacing: 16) {
            Text("¡Ups! Algo salió mal")
                .font(.headline)
                .fontWeight(.bold)
                .foregroundColor(.red)
            
            Text(message ?? "Error desconocido, por favor inténtalo de nuevo")
                .font(.body)
                .multilineTextAlignment(.center)
            
            Spacer().frame(height: 8)
            
            Button(action: onRetry) {
                Text("Intentar de nuevo")
                    .padding()
                    .overlay(
                        RoundedRectangle(cornerRadius: 8)
                            .stroke(Color.blue, lineWidth: 1)
                    )
            }
        }
        .padding(24)
        .background(
            RoundedRectangle(cornerRadius: 16)
                .fill(Color.white)
                .shadow(radius: 4)
        )
        .padding(16)
    }
}

struct SuccessState: View {
    let user: RandomUser
    let onReload: () -> Void
    
    var body: some View {
        VStack(alignment: .center) {
            if !user.results.isEmpty {
                UserCard(user: user.results[0])
                
                Spacer().frame(height: 24)
                
                Button(action: onReload) {
                    Text("Generar otro usuario")
                        .padding()
                        .frame(width: UIScreen.main.bounds.width * 0.7)
                        .overlay(
                            RoundedRectangle(cornerRadius: 8)
                                .stroke(Color.blue, lineWidth: 1)
                        )
                }
            } else {
                Text("No se encontraron resultados")
            }
        }
    }
}

struct UserCard: View {
    let user: Result
    
    var body: some View {
        VStack(alignment: .center) {
            AsyncImage(url: URL(string: user.picture.large)) { image in
                image
                    .resizable()
                    .aspectRatio(contentMode: .fill)
            } placeholder: {
                Color.gray
            }
            .frame(width: 120, height: 120)
            .clipShape(Circle())
            
            Spacer().frame(height: 16)
            
            Text("\(user.name.title) \(user.name.first) \(user.name.last)")
                .font(.headline)
                .fontWeight(.bold)
            
            Spacer().frame(height: 8)
            
            Text("@\(user.login.username)")
                .font(.body)
                .foregroundColor(.gray)
            
            Spacer().frame(height: 16)
            
            ContactInfoRow(iconName: "envelope", text: user.email)
            
            Spacer().frame(height: 8)
            
            ContactInfoRow(iconName: "phone", text: user.phone)
            
            Spacer().frame(height: 8)
            
            ContactInfoRow(iconName: "location", text: "\(user.location.city), \(user.location.country)")
        }
        .padding(16)
        .background(
            RoundedRectangle(cornerRadius: 16)
                .fill(Color.white)
                .shadow(radius: 4)
        )
        .padding(8)
    }
}

struct ContactInfoRow: View {
    let iconName: String
    let text: String
    
    var body: some View {
        HStack(alignment: .center) {
            Image(systemName: iconName)
                .foregroundColor(.blue)
                .frame(width: 24, height: 24)
            
            Spacer().frame(width: 8)
            
            Text(text)
                .font(.body)
            
            Spacer()
        }
        .padding(.vertical, 4)
    }
}

