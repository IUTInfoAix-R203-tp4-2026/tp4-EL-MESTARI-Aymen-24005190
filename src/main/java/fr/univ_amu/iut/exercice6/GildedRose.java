package fr.univ_amu.iut.exercice6;

/// Exercice 6 - Gilded Rose (capstone).
///
/// Ceci est le code existant de gestion de l'inventaire de la Gilded Rose. La
/// direction veut ajouter les articles *Conjured* (qui se dégradent deux fois
/// plus vite que les articles normaux). Mais avant de le faire, elle demande
/// qu'on nettoie le code - parce qu'actuellement, personne n'ose y toucher.
///
/// Règles en vigueur :
///
/// - À la fin de chaque journée, `sellIn` et `quality` de chaque article sont
///   mis à jour
/// - Une fois `sellIn` passé (négatif), la qualité se dégrade **deux fois plus
///   vite**
/// - La qualité n'est jamais négative
/// - La qualité d'un article n'est jamais au-dessus de 50... sauf pour
///   "Sulfuras" qui est toujours à 80
/// - "Aged Brie" *augmente* en qualité avec le temps
/// - "Sulfuras, Hand of Ragnaros" ne doit jamais être vendu (sellIn ne change
///   pas) et jamais se dégrader (quality ne change pas)
/// - "Backstage passes to a TAFKAL80ETC concert" augmente en qualité :
///   - de 2 quand il reste 10 jours ou moins
///   - de 3 quand il reste 5 jours ou moins
///   - tombe à 0 après le concert (sellIn < 0)
///
/// Votre mission :
///
/// 1. Écrire des tests de caractérisation couvrant **toutes** les règles (déjà
///    fournis)
/// 2. Refactorer `updateQuality()` en gardant les tests verts - par exemple en
///    extrayant une classe par type d'article (polymorphisme)
/// 3. Ajouter le support des articles "Conjured" qui se dégradent deux fois plus
///    vite (test dédié à activer une fois votre refactoring prêt)
///
/// Contrainte : la classe [Item] ne doit pas changer (signature figée par
/// la direction).
public class GildedRose {

  Item[] items;

  public GildedRose(Item[] items) {
    this.items = items;
  }

  public void updateQuality() {
    for (Item item : items) {
      if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
        continue; // Sulfuras ne change jamais
      }

      if (item.name.equals("Aged Brie")) {
        updateAgedBrie(item);
      } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
        updateBackstagePass(item);
      } else if (item.name.startsWith("Conjured")) {
        updateConjured(item);
      } else {
        updateNormal(item);
      }
    }
  }

  private void updateNormal(Item item) {
    decreaseQuality(item, 1);
    item.sellIn--;
    if (item.sellIn < 0) {
      decreaseQuality(item, 1);
    }
  }

  private void updateAgedBrie(Item item) {
    increaseQuality(item, 1);
    item.sellIn--;
    if (item.sellIn < 0) {
      increaseQuality(item, 1);
    }
  }

  private void updateBackstagePass(Item item) {
    increaseQuality(item, 1);
    if (item.sellIn < 11) {
      increaseQuality(item, 1);
    }
    if (item.sellIn < 6) {
      increaseQuality(item, 1);
    }
    item.sellIn--;
    if (item.sellIn < 0) {
      item.quality = 0;
    }
  }

  private void updateConjured(Item item) {
    decreaseQuality(item, 2);
    item.sellIn--;
    if (item.sellIn < 0) {
      decreaseQuality(item, 2);
    }
  }

  private void increaseQuality(Item item, int amount) {
    item.quality = Math.min(50, item.quality + amount);
  }

  private void decreaseQuality(Item item, int amount) {
    item.quality = Math.max(0, item.quality - amount);
  }
}
