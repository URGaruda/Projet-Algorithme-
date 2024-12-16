
public class Main {

  

    public static void main(String[] args) {

        
        
      Hybridetries tree = new Hybridetries();

      String exple_de_base = "A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches du clavier de la machine a ecrire ?";

      String[] words=exple_de_base.split(" ");

     for(String word : words){
        tree.insertKey(word);
        System.out.println("****");

      }
     tree.Suppression(tree, "genial");
     tree.Recherche(tree, "genial");
      //Set<String[]> mots= new HashSet<>();
     // Set<String[]> ya= new HashSet<>();

     // ya= tree.ajout_mots_bis(tree,"",mots);

      /*for(String[] cple : ya){
        
        System.out.println(cple[0]+ " "+ cple[1]);

      }*/

     //int nb=tree.Prefixe(tree,"de");

     //System.out.println("nb =  "+ nb);
      

     
      /*for(String word : words){
        
        tree.Recherche(tree,word);
        
        
      }

      
      int nb_mots= tree.ComptageMots(tree);
      System.out.println("NB mot "+ nb_mots);*/

     /*  ArrayList<String> hh= tree.ListeMots(tree);
      System.out.println(hh.size());*/



      /*tree.insertKey("ha");
      System.out.println("****");
      tree.insertKey("hao");
      System.out.println("****");
      tree.insertKey("za");
      System.out.println("****");
      tree.insertKey("hai");
      System.out.println("****");
      tree.insertKey("haa");
      System.out.println("****");
      tree.insertKey("bi");
      System.out.println("****");
      tree.insertKey("haotgf");
      System.out.println("****");
      tree.insertKey("haotgf");*/


     
      //tree.Hauteur(tree);
      //tree.ProfondeurMoyenne(tree);
     

     /* tree.Recherche(tree, "za");
      tree.Recherche(tree, "hai");
      tree.Recherche(tree, "haa");
      tree.Recherche(tree, "ha");
      tree.Recherche(tree, "hao"); 
      tree.Recherche(tree, "bi");
      tree.Recherche(tree, "haotgf");
      tree.Recherche(tree, "hiiiiiii");
      tree.Recherche(tree, "h");*/

      
        
  

        
        
        
}
}
/*            javac Main.java
              java Main            */