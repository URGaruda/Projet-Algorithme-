import java.io.*;
import java.util.Arrays;
import java.util.List;
public class Test_Pat_Main {
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
            System.out.println("liste = "+var.listeMots()+" nb_comparaison = "+var.comparaisonCpt);

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
        String long_word = "pneumonoultramicroscopicsilicovolcanoconiosis";
        List<String> suppr_word = Arrays.asList("and","our","for");

        long PatinsertStartTime = System.currentTimeMillis();
        pat.patInsertion(long_word);
        long PatinsertEndTime = System.currentTimeMillis();

        long HyBinsertStartTime = System.currentTimeMillis();
        tree.insertKey(long_word);
        long HyBinsertEndTime = System.currentTimeMillis();

        Long PatprofStartTime = System.currentTimeMillis();
        pat.profondeurMoyenne();
        Long PatprofEndTime = System.currentTimeMillis(); 

        Long HyBprofStartTime = System.currentTimeMillis();
        tree.ProfondeurMoyenne(tree);
        Long HyBprofEndTime = System.currentTimeMillis();

        Long PatrechStartTime = System.currentTimeMillis();
        for(String val: suppr_word)
            pat.rechercher_mot(val);
        Long PatrechEndTime = System.currentTimeMillis();

        Long HyBrechStartTime = System.currentTimeMillis();
        for(String val: suppr_word)
            tree.Recherche(tree, val);
        Long HyBrechEndTime = System.currentTimeMillis();
        /*
        Long PatsuppStartTime = System.currentTimeMillis();
        for(String val: suppr_word)
            pat.suppression(val);
        Long PatsuppEndTime = System.currentTimeMillis(); 

        Long HyBsuppStartTime = System.currentTimeMillis();
        for(String val: suppr_word)
            tree.Suppression(tree,val);
        Long HyBsuppEndTime = System.currentTimeMillis();
        */
        System.out.println("****Temps écoulé construction de l'arbre pat trie*********" + elapsedTime + " millisecondes soit "+ (endTime - startTime)/1000.0+ " secondes");
        System.out.println("****Temps écoulé construction de l'arbre trie Hyb********* "+ (endTime_hyb - startTime_hyb) + "milisecondes soit "+ (endTime_hyb - startTime_hyb)/1000.0+ " secondes");
        System.out.println("****Temps écoulé insertion dans l'arbre patricia********* "+ (PatinsertEndTime - PatinsertStartTime) + "milisecondes soit "+ (PatinsertEndTime - PatinsertStartTime)/1000.0+ " secondes");
        System.out.println("****Temps écoulé insertion dans la trie hybride********* "+ (HyBinsertEndTime - HyBinsertStartTime) + "milisecondes soit "+ (HyBinsertEndTime - HyBinsertStartTime)/1000.0+ " secondes");
        System.out.println("****Temps écoulé profondeur de l'arbre patricia********* "+ (PatprofEndTime - PatprofStartTime) + "milisecondes soit "+ (PatprofEndTime - PatprofStartTime)/1000.0+ " secondes");
        System.out.println("****Temps écoulé profondeur de la trie hybride********* "+ (HyBprofEndTime - HyBprofStartTime) + "milisecondes soit "+ (HyBprofEndTime - HyBprofStartTime)/1000.0+ " secondes");
        System.out.println("****Temps écoulé recherche dans l'arbre patricia********* "+ (PatrechEndTime - PatrechStartTime) + "milisecondes soit "+ (PatrechEndTime - PatrechStartTime)/1000.0+ " secondes");
        System.out.println("****Temps écoulé recherche dans la trie hybride********* "+ (HyBrechEndTime - HyBrechStartTime) + "milisecondes soit "+ (HyBrechEndTime - HyBrechStartTime)/1000.0+ " secondes");

    }
    
}