//
//  KMPObservableViewModel.swift
//  BetKing
//
//  Created by Daniel Carracedo on 10/3/25.
//
import KMPObservableViewModelCore

extension Kmp_observableviewmodel_coreViewModel: @retroactive ViewModel { }

extension Int32 {
    var asSwiftInt: Int {
        Int(self)
    }
}

extension Int {
    var asKotlinInt: Int32 {
        Int32(self)
    }
}
