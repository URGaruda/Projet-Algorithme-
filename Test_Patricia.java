import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Test_Patricia {
    public static void main(String[] args){
    
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
                Patricia_Trie.patTojson(var,"pat.json");
            }catch(IOException e){
                System.err.println("Erreur"+e.getMessage());
            }
        }
        if(args[0].equals("suppression")){
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
                Patricia_Trie supp = Patricia_Trie.json_to_pat("pat.json");
                List<String> ls = var.listeMots();
                String mot_pure;
                for(String mot : ls){
                    if(mot.endsWith(String.valueOf('\0'))){
                        mot_pure=mot.substring(0,mot.length()-1);
                    }else{
                        mot_pure=mot;
                    }
                    supp.suppression(mot_pure);
                }
                System.out.println(supp.toString());
                Patricia_Trie.patTojson(supp,"pat.json");

            }catch(IOException e){
                System.err.println("Erreur"+e.getMessage());
            }
        }

        if(args[0].equals("fusion")){
            Patricia_Trie p1 = Patricia_Trie.json_to_pat(args[1]);
            Patricia_Trie p2 = Patricia_Trie.json_to_pat(args[2]);
            Patricia_Trie fus = Patricia_Trie.fusion(p1, p2);
            Patricia_Trie.patTojson(fus,"pat.json");
        }

        if(args[0].equals("listeMots")){
            Patricia_Trie pat = Patricia_Trie.json_to_pat(args[1]);
            try{

                FileWriter file = new FileWriter("mot.txt");
                List<String> ls = pat.listeMots();
                int cpt=0;
                String mot_pure;
                for(String mot : ls){
                    if(mot.endsWith(String.valueOf('\0'))){
                        mot_pure=mot.substring(0,mot.length()-1);
                    }else{
                        mot_pure=mot;
                    }
                    if(cpt<ls.size()-1)
                        file.write(mot_pure.concat("\n"));
                    else
                        file.write(mot_pure);
                    cpt++;
                }
                file.close();
            }catch(IOException e){
                System.err.println("Erreur"+e.getMessage());
            }
        }

        if(args[0].equals("profondeurMoyenne")){
            Patricia_Trie pat = Patricia_Trie.json_to_pat(args[1]);
            try{
                FileWriter file = new FileWriter("profondeur.txt");
                String profondeur =  String.valueOf(pat.profondeurMoyenne());
                file.write(profondeur);
                file.close();
            }catch(IOException e){
                System.err.println("Erreur"+e.getMessage());
            }
        }

        if(args[0].equals("prefixe")){
            Patricia_Trie pat = Patricia_Trie.json_to_pat(args[1]);
            try{
                FileWriter file = new FileWriter("prefixe.txt");
                String prefixe = String.valueOf(pat.prefixe(args[2]));
                System.out.println("prefixe : "+prefixe);
                file.write(prefixe);
                file.close();
            }catch(IOException e){
                System.err.println("Erreur"+e.getMessage());
            }
        }

    }

}