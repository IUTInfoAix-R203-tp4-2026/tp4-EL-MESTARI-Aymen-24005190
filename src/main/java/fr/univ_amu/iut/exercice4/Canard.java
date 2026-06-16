package fr.univ_amu.iut.exercice4;

public class Canard extends Animal {
  public Canard(String nom) {
    super(nom);
  }

  @Override
  public String faireDuBruit() {
    return "Coin coin !";
  }
}
