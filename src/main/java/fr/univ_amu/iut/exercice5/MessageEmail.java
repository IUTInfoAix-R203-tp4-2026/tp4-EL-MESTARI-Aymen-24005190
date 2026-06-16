package fr.univ_amu.iut.exercice5;

public record MessageEmail(
    String destinataire,
    String expediteur,
    String sujet,
    String corps,
    boolean important,
    int priorite,
    String[] piecesJointes) {}
