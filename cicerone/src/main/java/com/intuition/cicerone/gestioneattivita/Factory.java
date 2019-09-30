package com.intuition.cicerone.gestioneattivita;

public interface Factory<T, P> {
	T crea(P param);

}
