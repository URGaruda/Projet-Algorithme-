
public class Main {

  

    public static void main(String[] args) {

        
        
      Hybridetries tree = new Hybridetries();

      String exple_de_base = "A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches du clavier de la machine a ecrire ?";

      String[] words=exple_de_base.split(" ");

     for(String word : words){
        tree.insertKey(word);
        System.out.println("****");

      }
      /***test recherche****/
      tree.Recherche(tree, "genial");
      tree.Suppression(tree, "genial");
      tree.Recherche(tree, "genial");

      /***test Comptage, doit afficher 34 apres la suppressio de "geial"****/

     System.out.println("nb de mot dans l'arbre " +tree.ComptageMots(tree));

      /** test ListMots*** */
      System.out.println("liste de mots dans l'odre alphabetique " );
      tree.ListeMots(tree);

      /*****teste hauteur*** */
      tree.Hauteur(tree);

      /***test Profondeurmoyenne()  */
      tree.ProfondeurMoyenne(tree);

      /*****Test Prefixe()**** */
      System.out.println("dactylo est prefixe de " + tree.Prefixe(tree, "dactylo")+ " mots dans l'arbre");

      /***Test Suppression()*** */
      tree.Recherche(tree, "redevables");
      tree.Suppression(tree, "redevables");
      tree.Recherche(tree, "redevables");

     
      int nb_mots= tree.ComptageMots(tree);
      System.out.println("NB mot "+ nb_mots);

    


    

      
        
  

        
        
        
}
}
