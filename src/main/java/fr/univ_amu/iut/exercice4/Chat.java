package fr.univ_amu.iut.exercice4;

public class Chat extends Animal {
  public Chat(String nom) {
    super(nom);
  }

  @Override
  public String faireDuBruit() {
    return "Miaou !";
  }
}
