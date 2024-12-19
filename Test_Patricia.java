import java.io.*;
public class Test_Patricia {
    public static void main(String[] args){
        /*
        Patricia_Trie t1 = new Patricia_Trie();
        t1.patInsertion("AA");
        t1.patInsertion("AC");
        t1.patInsertion("TC");
        t1.patInsertion("TCA");
        Patricia_Trie.patTojson(t1,"test_json.txt");
        System.out.println(t1.toString());
        System.out.println("profondeur moyenne = "+t1.profondeurMoyenne()+" nbr de mots = "+t1.comptageMots());
        System.out.println("Nb nil = "+t1.comptageNil());
        System.out.println("liste = "+t1.listeMots());
        */
        /*t1.patInsertion("TACG");
        t1.patInsertion("AAT");
        t1.patInsertion("AT");
        t1.patInsertion("AT");
        t1.patInsertion("CGGA");
        t1.patInsertion("TAC");
        System.out.println("je recherche "+t1.rechercher_mot("TACG"));
        t1.suppression("TACG");
        System.out.println("je recherche "+t1.rechercher_mot("TACG"));
        t1.patInsertion("TACG");
        System.out.println(t1.toString());*/
        /*
        //Question 1.3 
        String file ="exemple_de_base.txt";
        Patricia_Trie var= new Patricia_Trie();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] mots = line.split("\\s+");
                for (String mot : mots) {
                    mot = mot.replaceAll("[^a-zA-Z0-9]", "");
                    var.patInsertion(mot);
                }
            }
            br.close();
            System.out.println(var.toString());
            Patricia_Trie.patTojson(var,"base.json");
            System.out.println("prefixe de = "+var.prefixe("dac"));
            System.out.println("Nb nil = "+var.comptageNil()+" Nb mots = "+var.comptageMots());
            System.out.println("hauteur = "+var.hauteur());
            System.out.println("liste = "+var.listeMots()+" nb_comparaison = "+var.comparaisonCpt);

        }catch(IOException e){
            System.err.println("Erreur"+e.getMessage());
        }
        */        
        /*Patricia_Trie t1 = new Patricia_Trie();
        t1.patInsertion("TACG");
        t1.patInsertion("AAT");
        t1.patInsertion("AT");
        t1.patInsertion("AT");
        t1.patInsertion("CGGA");
        t1.patInsertion("TAC");
        Patricia_Trie t2 = new Patricia_Trie();
        t2.patInsertion("des");
        t2.patInsertion("dactylographie");
        t2.patInsertion("du");
        t2.patInsertion("de");
        t2.patInsertion("dactylo");
        System.out.println(t2.suppression("dactylo"));
        t2.patInsertion("da");
        t2.patInsertion("AA");
        t2.patInsertion("AC");
        t2.patInsertion("TC");
        t2.patInsertion("TCA");
        //System.out.println(t2.toString());
        //System.out.println(t2.hauteur());
        //System.out.println("liste = "+t2.listeMots());
        Patricia_Trie res = t1.fusion(t2);
        Patricia_Trie res2 = Patricia_Trie.fusion(t1, t2);
        //System.out.println(res.toString());
        System.out.println("Nb nil = "+res.comptageNil()+" Nb mots = "+res.comptageMots());
        System.out.println("hauteur = "+res.hauteur());
        System.out.println("liste = "+res.listeMots());
        System.out.println("liste2 = "+res2.listeMots());*/
        /*Patricia_Trie t3 = new Patricia_Trie();
        t3.patInsertion("dactylographie");
        t3.patInsertion("dactylo");
        t3.suppression("dactylo");
        t3.patInsertion("da");
        System.out.println(t3.toString());*/
        /*
        Patricia_Trie t4 = Patricia_Trie.json_to_pat("base.json");
        Patricia_Trie.patTojson(t4, "test_fct.json");
        System.out.println("main : ");
        //System.out.println(t4.toString());
        */
        /*
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
                    System.err.println("Erreur lors de la lecture du fichier " + file.getName() + ": " + e.getMessage());
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Contenu de la Patricia Trie :");
        System.out.println(pat.toString());
        System.out.println("Nombre total de mots insérés : " + pat.comptageMots());
        long elapsedTime = endTime - startTime;
        System.out.println("Temps total d'insertion : " + elapsedTime + " millisecondes soit "+ (endTime - startTime)/1000.0+ " secondes");
        */
        if(args[0].equals("inserer")){
            Patricia_Trie var= new Patricia_Trie();
            try{
                BufferedReader br = new BufferedReader(new FileReader(args[1]));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] mots = line.split("\\s+");
                    for (String mot : mots) {
                        mot = mot.replaceAll("[^a-zA-Z0-9]", "");
                        var.patInsertion(mot);
                    }
                }
                br.close();
                //System.out.println(var.toString());
                if(args[1].contains(".txt"))
                    Patricia_Trie.patTojson(var,args[1].substring(0,args[1].length()-3).concat("json"));
                else
                Patricia_Trie.patTojson(var,args[1].concat(".json"));
            }catch(IOException e){
                System.err.println("Erreur"+e.getMessage());
            }
        }
        System.out.println("taille argv : "+args.length+" name : "+args[0]+" fichier : "+args[1]);
    }
}