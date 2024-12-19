import java.util.Arrays;
import java.util.List;

public class Maintest {


   
    public static void main(String[] args) {

        /***************construction arbre******* */
        Hybridetries tree = new Hybridetries();

        List<String> files = Arrays.asList("1henryiv.txt", "1henryvi.txt", "2henryiv.txt","2henryvi.txt","3henryvi.txt","allswell.txt","asyoulikeit.txt","cleopatra copy.txt",
        "cleopatra.txt","comedy_errors.txt","coriolanus.txt","cymbeline.txt","hamlet.txt","henryv.txt","henryviii.txt","john copy.txt","john.txt","julius_caesar.txt","lear.txt","lear.txt","lll.txt", "macbeth.txt","measure.txt","merchant.txt","merry_wives.txt", "midsummer.txt","much_ado.txt","othello.txt","pericles.txt","richardii.txt","richardiii.txt","romeo_juliet copy.txt","romeo_juliet.txt","taming_shrew.txt","tempest.txt","timon.txt","titus.txt","troilus_cressida.txt","twelfth_night.txt","two_gentlemen.txt","winters_tale.txt");
        
        //mesuerer le temps
        long startTime = System.currentTimeMillis();

        for(String filepath : files){
        tree.read(tree,filepath);
            }

        long endTime = System.currentTimeMillis();
        System.out.println("****Temps écoulé costruction de l'arbre**** "+ (endTime - startTime) + " milisecondes soit "+ (endTime - startTime)/1000.0+ " secondes");
    
            /*******************recherche********** */
        startTime = System.currentTimeMillis();

        //tree.Recherche(tree,"floccinaucinihilipilification");
        endTime = System.currentTimeMillis();

        System.out.println("****Temps écoulé Recherche de l'arbre********* "+ (endTime - startTime) + " milisecondes soit "+ (endTime - startTime)/1000.0+ " secondes");
        
            /*************ajout******* */
            tree.setcpt_ins();
            startTime = System.currentTimeMillis();
            tree.insertKey("pneumonoultramicroscopicsilicovolcanoconiosis");
            endTime = System.currentTimeMillis();
            System.out.println("****Temps écoulé insertion dans l'arbre********* "+ (endTime - startTime) + " milisecondes soit "+ (endTime - startTime)/1000.0+ " secondes");
            tree.getcpt_ins();

            /************recherche************ */
            tree.setcpt_re();
            startTime = System.currentTimeMillis();
            tree.Recherche(tree, "pneumonoultramicroscopicsilicovolcanoconiosis");
            endTime = System.currentTimeMillis();
            tree.getcpt_re();
            System.out.println("****Temps écoulé Recherche de l'arbre********* "+ (endTime - startTime) + " milisecondes soit "+ (endTime - startTime)/1000.0+ " secondes");

            /************hauteur*********** */
            startTime = System.currentTimeMillis();
            //tree.Hauteur(tree);
            endTime = System.currentTimeMillis();
      
            System.out.println("****Temps écoulé hauteur de l'arbre********* "+ (endTime - startTime) + " milisecondes soit "+ (endTime - startTime)/1000.0+ " secondes");
           
            /***Test ComptageMots****** */
            startTime = System.currentTimeMillis();
            System.out.println("Nombre de mots dans l'arbre  " + tree.ComptageMots(tree));
            endTime = System.currentTimeMillis();
            System.out.println("****Temps écoulé ComptageMot() de l'arbre********* "+ (endTime - startTime) + " milisecondes soit "+ (endTime - startTime)/1000.0+ " secondes");
            System.out.println("Nombre de noeud dans l'arbre  "+ tree.compter_noeuds);

            /***Test ComptageNi() */
            startTime = System.currentTimeMillis();
            System.out.println("Nombre de pointeurs nuls (noeud vide) dans l'arbre  " + tree.ComptageNil(tree));
            endTime = System.currentTimeMillis();
            System.out.println("****Temps écoulé ComptageNil() de l'arbre********* "+ (endTime - startTime) + " milisecondes soit "+ (endTime - startTime)/1000.0+ " secondes");
          
            /*******Test Prefixe() **********/
            startTime = System.currentTimeMillis();
            System.out.println("le mot est prefixe de "+ tree.Prefixe(tree, "tempestuous") +" mots dans l'arbre");
            System.out.println("Nombre de neuds parcouru pour trouver les mots dont elle est prefixe " + tree.cpt_pref);
            endTime = System.currentTimeMillis();
            System.out.println("****Temps écoulé Prefixe() de l'arbre********* "+ (endTime - startTime) + " milisecondes soit "+ (endTime - startTime)/1000.0+ " secondes");

            /***Supression() *****/
            startTime = System.currentTimeMillis();
            tree.Suppression(tree, "tempest");
            System.out.println("Nombre de comparaison dans le tri à bulle " + tree.cpt_bulle);
            endTime = System.currentTimeMillis();
            System.out.println("****Temps écoulé Supression() de l'arbre********* "+ (endTime - startTime) + " milisecondes soit "+ (endTime - startTime)/1000.0+ " secondes");

}
}
