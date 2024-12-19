import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Test_Main {
    public static void main(String[] args){
        //Question 1.3 
        String filename ="exemple_de_base.txt";
        Patricia_Trie var= new Patricia_Trie();
        Hybridetries hip = new Hybridetries();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                String[] mots = line.split("\\s+");
                for (String mot : mots) {
                    mot = mot.replaceAll("[^a-zA-Z0-9]", "");
                    var.patInsertion(mot);
                    hip.insertKey(mot);
                }
            }
            br.close();
            System.out.println(var.toString());
            //Patricia_Trie.patTojson(var,"base.json");
            //System.out.println("prefixe de = "+var.prefixe("dac"));
            //System.out.println("Nb nil = "+var.comptageNil()+" Nb mots = "+var.comptageMots());
            //System.out.println("hauteur = "+var.hauteur());
            System.out.println("liste = "+var.listeMots()+" nb_comparaison = "+Patricia_Trie.comparaisonCpt);

        }catch(IOException e){
            System.err.println("Erreur"+e.getMessage());
        }

        //Question 6.10
        Patricia_Trie pat = new Patricia_Trie();
        File directory = new File("Shakespeare");
        File[] files = directory.listFiles();
        long startTime = System.currentTimeMillis();
        for (File file : files) {
            if (file.isFile()) { 
                System.out.println("Lecture du fichier : " + file.getName());
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] mots = line.split("\\s+");
                        for (String mot : mots) {
                            mot = mot.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
                            if (!mot.isEmpty()) {
                                pat.patInsertion(mot);
                            }
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Erreur"+e.getMessage());
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        Hybridetries tree = new Hybridetries();

         List<String> ls_files = Arrays.asList("1henryiv.txt", "1henryvi.txt", "2henryiv.txt","2henryvi.txt","3henryvi.txt","allswell.txt","asyoulikeit.txt","cleopatra copy.txt",
        "cleopatra.txt","comedy_errors.txt","coriolanus.txt","cymbeline.txt","hamlet.txt","henryv.txt","henryviii.txt","john copy.txt","john.txt","julius_caesar.txt","lear.txt","lear.txt","lll.txt", "macbeth.txt","measure.txt","merchant.txt","merry_wives.txt", "midsummer.txt","much_ado.txt","othello.txt","pericles.txt","richardii.txt","richardiii.txt","romeo_juliet copy.txt","romeo_juliet.txt","taming_shrew.txt","tempest.txt","timon.txt","titus.txt","troilus_cressida.txt","twelfth_night.txt","two_gentlemen.txt","winters_tale.txt");
        
        //mesuerer le temps
        long startTime_hyb = System.currentTimeMillis();
        for(String filepath : ls_files){
            tree.read(tree,filepath);
        }

        long endTime_hyb = System.currentTimeMillis();

        //Question 6.11 
        // Insertion 
        String long_word = "pneumonoultramicroscopicsilicovolcanoconiosis";
        String middle_word ="government";
        String short_word="dodu";
        List<String> sz_word = Arrays.asList("pneumonoultramicroscopicsilicovolcanoconiosis","government","dodu");
        List<String> supp_word = Arrays.asList("hello","your","is","as","but","or");
        
        System.out.println("Nombre de comparaison de l'initialisation de la patricia trie = "+Patricia_Trie.comparaisonCpt);

        Patricia_Trie.comparaisonCpt=0;
        long PatinsertStartTime = System.currentTimeMillis();
        for(String val: sz_word)
            pat.patInsertion(val);
        System.out.println("Nombre de comparaison de l'insertion = "+Patricia_Trie.comparaisonCpt);
        long PatinsertEndTime = System.currentTimeMillis();

        long HyBinsertStartTime = System.currentTimeMillis();
        for(String val: sz_word)
            tree.insertKey(val);
        long HyBinsertEndTime = System.currentTimeMillis();

        //recherche 
        ArrayList<Boolean> pat_res= new ArrayList<Boolean>();
        ArrayList<Boolean> hyb_res= new ArrayList<Boolean>();

        Patricia_Trie.comparaisonCpt=0;
        Long PatrechStartTime = System.currentTimeMillis();
        for(String val: sz_word)
            pat_res.add(pat.rechercher_mot(val));
        System.out.println("Nombre de comparaison de la recherche = "+Patricia_Trie.comparaisonCpt);
        Long PatrechEndTime = System.currentTimeMillis();

        Long HyBrechStartTime = System.currentTimeMillis();
        for(String val: sz_word)
            hyb_res.add(tree.Recherche(tree, val));
        Long HyBrechEndTime = System.currentTimeMillis();

        for(Boolean val : pat_res)
            System.out.print("valeur : "+val+" ");
        System.out.println();

        for(Boolean val : hyb_res)
            System.out.print("valeur : "+val+" ");
        System.out.println();

        // hauteur
        Patricia_Trie.comparaisonCpt=0;
        Long PathautStartTime = System.currentTimeMillis();
        System.out.println("hauteur de la patricia trie = "+pat.hauteur());
        System.out.println("Nombre de liste parcouru = "+Patricia_Trie.comparaisonCpt);
        Long PathautEndTime = System.currentTimeMillis();

        Long HyBhautStartTime = System.currentTimeMillis();
        System.out.println("hauteur de la trie hybride = "+tree.Hauteur(tree));
        Long HyBhautEndTime = System.currentTimeMillis();

        //Suppression 
        Long PatsuppStartTime = System.currentTimeMillis();
        for(String val : supp_word)
            pat.suppression(val);
        Long PatsuppEndTime = System.currentTimeMillis();

        

        //profondeur 
        Long PatproStartTime = System.currentTimeMillis();
        System.out.println("profondeur moyenne de la patricia trie = "+ pat.profondeurMoyenne());
        Long PatproEndTime = System.currentTimeMillis();

        Long HybproStartTime = System.currentTimeMillis();
        System.out.println("profondeur moyenne de la trie hybride = "+tree.ProfondeurMoyenne(tree));
        Long HybproEndTime = System.currentTimeMillis();
 
        System.out.println("****Temps écoulé construction de l'arbre pat trie*********" + elapsedTime + " millisecondes soit "+ (endTime - startTime)/1000.0+ " secondes");
        System.out.println("****Temps écoulé construction de l'arbre trie Hyb********* "+ (endTime_hyb - startTime_hyb) + "milisecondes soit "+ (endTime_hyb - startTime_hyb)/1000.0+ " secondes");
        System.out.println("****Temps écoulé insertion dans l'arbre patricia********* "+ (PatinsertEndTime - PatinsertStartTime) + "milisecondes soit "+ (PatinsertEndTime - PatinsertStartTime)/1000.0+ " secondes");
        System.out.println("****Temps écoulé insertion dans la trie hybride********* "+ (HyBinsertEndTime - HyBinsertStartTime) + "milisecondes soit "+ (HyBinsertEndTime - HyBinsertStartTime)/1000.0+ " secondes");
        System.out.println("****Temps écoulé recherche dans l'arbre patricia********* "+ (PatrechEndTime - PatrechStartTime) + "milisecondes soit "+ (PatrechEndTime - PatrechStartTime)/1000.0+ " secondes");
        System.out.println("****Temps écoulé recherche dans la trie hybride********* "+ (HyBrechEndTime - HyBrechStartTime) + "milisecondes soit "+ (HyBrechEndTime - HyBrechStartTime)/1000.0+ " secondes");
        
        System.out.println("****Temps écoulé hauteur de l'arbre patricia********* "+ (PathautEndTime - PathautStartTime) + "milisecondes soit "+ (PathautEndTime - PathautStartTime)/1000.0+ " secondes");
        System.out.println("****Temps écoulé hauteur de la trie hybride********* "+ (HyBhautEndTime - HyBhautStartTime) + "milisecondes soit "+ (HyBhautEndTime - HyBhautStartTime)/1000.0+ " secondes");
        System.out.println("****Temps écoulé profondeurMoyenne de l'arbre patricia********* "+ (PatproEndTime - PatproStartTime) + "milisecondes soit "+ (PatproEndTime - PatproStartTime)/1000.0+ " secondes");
        System.out.println("****Temps écoulé profondeurMoyenne de la trie hybride********* "+ (HybproEndTime - HybproStartTime) + "milisecondes soit "+ (HybproEndTime - HybproStartTime)/1000.0+ " secondes");
        System.out.println("****Temps écoulé suppression de l'arbre patricia********* "+ (PatsuppEndTime - PatsuppStartTime) + "milisecondes soit "+ (PatsuppEndTime - PatsuppStartTime)/1000.0+ " secondes");
        

    }
    
}