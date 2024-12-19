import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patricia_Trie{
    protected char fin_chaine='\0';
    protected Elements[] liste = new Elements[127] ;
    protected boolean mot_vide = false ;
    protected int nb_nil;
    public static int comparaisonCpt = 0 ;
    public class Elements{
        private String val;
        private Patricia_Trie next ;

        public Elements(String val,Patricia_Trie next){
            this.val=val;
            this.next=next;
        }

        public String getVal(){
            return this.val;
        }

        public Patricia_Trie getNext(){
            return this.next;
        }

        public void setVal(String val){
            this.val=val;
        }

        public void setNext(Patricia_Trie next){
            this.next=next;
        }
    }

    public Patricia_Trie(){
        for(int i=0;i<127;i++){
            liste[i]=null;
        }
        nb_nil=127;
    }


    public static String plusGrandPrefixe(String mot1, String mot2) {
        int minLength = Math.min(mot1.length(), mot2.length());
        int i = 0;
        while (i < minLength && mot1.charAt(i) == mot2.charAt(i)) {
            i++;
        }
        return mot1.substring(0, i);
    }

    public Elements getElm_At(String m){
        char conv = m.charAt(0);
        int ascii = (int) conv ;
        ascii-=1;
        return liste[ascii];
    } 

    public void setElm_At(String at,String val,Patricia_Trie next){
        if(val==null || val.isEmpty()){return ;}
        char conv = at.charAt(0);
        int ascii = (int) conv ;
        ascii-=1;
        nb_nil--;
        liste[ascii]=new Elements(val, next);
    }

    public boolean isMotVide(){
        return mot_vide;
    }

    public int comptageNil(){
        int res=0;
        for(int i=0;i<127;i++){
            if(liste[i]!=null){
                if(liste[i].getNext()!=null)
                    res+=liste[i].getNext().comptageNil();
            }
        }
        return res+nb_nil;
    }

    public List<String> listeMots(){
        List<String> l = listeMots_sub();
        Collections.sort(l);
        return l;
    }
    private ArrayList<String> listeMots_sub(){ 
        ArrayList<String> res = new ArrayList<String>() ;
        for(int i=0;i<127;i++){
            if(liste[i]!=null){
                Elements cle = liste[i] ;
                String val_pure=cle.getVal();
                if(cle.getVal().endsWith(String.valueOf(fin_chaine))){
                    val_pure=cle.getVal().substring(0,cle.getVal().length()-1);
                    res.add(cle.getVal());
                }
                if(cle.getNext()!=null){
                    ArrayList<String> tmp = cle.getNext().listeMots_sub();
                    for(String var : tmp){
                        res.add(val_pure+var);
                    }
                }
            }
        }
        return res;
    }

    public Patricia_Trie fusion(Patricia_Trie p2){
        if(p2==null){
            return this;
        }else{
            List<String> l=p2.listeMots();
            for(String val : l){
                if(val.endsWith(String.valueOf(fin_chaine))){
                    this.patInsertion(val.substring(0,val.length()-1));
                }else{
                    this.patInsertion(val);
                }
            }
            return this;
        }
    }

    public static Patricia_Trie fusion(Patricia_Trie p1,Patricia_Trie p2){
        if(p1==null){
            if(p2==null)
                return null;
            return p2;
        }
        if(p2==null)
            return p1;
        
        Patricia_Trie res = new Patricia_Trie();
        List<String> l1 = p1.listeMots();
        List<String> l2 = p2.listeMots();
        for(String val : l1){
            if(val.endsWith(String.valueOf(res.fin_chaine))){
                res.patInsertion(val.substring(0,val.length()-1));
            }else{
                res.patInsertion(val);
            }
        }
        for(String val : l2){
            if(val.endsWith(String.valueOf(res.fin_chaine))){
                res.patInsertion(val.substring(0,val.length()-1));
            }else{
                res.patInsertion(val);
            }
        }
        return res;
            
    }

    public int prefixe(String mot){
        if(mot==null){return -1;}
        if(mot.isEmpty()){
            if(mot_vide){
                return 1;
            }    
            return 0;
        }
        Elements cle=getElm_At(mot);
        if(cle!=null){
            int len1=mot.length();
            String val_pure;
            if(cle.val.endsWith(String.valueOf(fin_chaine))){
                val_pure=cle.getVal().substring(0,cle.getVal().length()-1);
            }else{
                val_pure=cle.getVal().substring(0,cle.getVal().length());
            }
            int len2=val_pure.length();
            if(len1>len2){ // on regarde si le mot commence par clé : cas ou le mot est + grand que la clé 
                if(mot.startsWith(val_pure)){ 
                    if(cle.getNext()!=null){
                        return cle.getNext().prefixe(mot.substring(val_pure.length())); 
                    }else{
                        return 0;
                    }
                }else{
                    return 0;
                }
            }else{
                // on regarde si la clé commence avec mot  : cas ou clé est + grande ou égal au mot 
                if(val_pure.startsWith(mot)){
                    if(cle.getNext()!=null){
                        if(cle.getVal().endsWith(String.valueOf(fin_chaine))){
                            return 1+cle.getNext().comptageMots();
                        }
                        return cle.getNext().comptageMots();
                    }else{
                        if(cle.getVal().endsWith(String.valueOf(fin_chaine))){
                            return 1;
                        }else{
                            return 0; 
                        }
                    }
                }else{
                    return 0;
                }
                
            }
            
        }
        return 0;
    }

    public int comptageMots(){
        int res=0;
        for(int i=0;i<127;i++){
            Elements cle=liste[i];
            if(cle!=null){
                if(cle.getVal().endsWith(String.valueOf(fin_chaine))){
                    //System.out.print(" "+cle.getVal()+" ");
                    res++;
                    Patricia_Trie next = cle.getNext();
                    if(next!=null){
                        res+=next.comptageMots();
                    }
                }else{
                    Patricia_Trie next = cle.getNext();
                    if(next!=null){
                        res+=next.comptageMots();
                    }
                } 
            }
        }
        return res;
    }

    public double profondeurMoyenne(){
        ArrayList<Integer> l = new ArrayList<Integer>();
        profondeurList(l, 1);
        int sum=0;
        for(int i : l){
            sum+=i;
        }
        return sum*1.0/l.size();
    }

    public int hauteur(){
        ArrayList<Integer> l = new ArrayList<Integer>();
        profondeurList(l, 1);
        int max=0;
        for(int val : l){
            if(max<val){
                max=val;
            }
        }
        return max;
    }

    public void profondeurList(ArrayList<Integer> l,int deep){
        for(int i=0;i<127;i++){
            if(liste[i]!=null){
                Elements cle=liste[i];
                if(cle.val.endsWith(String.valueOf(fin_chaine))){
                    if(cle.getNext()==null)
                        l.add(deep);
                }
                if(cle.getNext()!=null){
                    Patricia_Trie.comparaisonCpt++;
                    cle.getNext().profondeurList(l, deep+1);
                }
            }
        }
    }

    public void patInsertion(String m){
        patInsertionBis(m, this.liste);
    }
    private void patInsertionBis(String m,Elements[] first){
        if(m==null){return ;}
        if(m.isEmpty()){
            if(this.liste==first){
                mot_vide=true;
            }    
            return ;
        }

        Elements cle=getElm_At(m);
        
        if(cle==null){
            setElm_At(m,m+fin_chaine, null);
            return;
        }else{
            String val_pure;
            if(cle.val.endsWith(String.valueOf(fin_chaine))){
                val_pure=cle.getVal().substring(0,cle.getVal().length()-1);
            }else{
                val_pure=cle.getVal().substring(0,cle.getVal().length());
            }
            Patricia_Trie.comparaisonCpt++;
            if(m.startsWith(val_pure)){ // cas où on insert un mot égal ou suffixe d'un mot déjà insérer 
                if(cle.getNext()==null){
                    cle.setNext(new Patricia_Trie());
                }
                if(cle.getVal().endsWith(String.valueOf(fin_chaine))){
                    cle.getNext().patInsertionBis(m.substring(cle.getVal().length()-1),first); // insert le suffixe du mot dans le noeud interne 
                }else{
                    cle.getNext().patInsertionBis(m.substring(cle.getVal().length()),first);
                }
            }else{
                // cas ou on insert un mot qui a un préfixe commun avec un mot déjà insérer dans le noeud 
                String petit = Patricia_Trie.plusGrandPrefixe(m, cle.getVal()); // obtenir le + grand prefixe commun 
                //puis ajouter le suffixe de la clé actuelle dans toutes les clés du sous-noeuds et ensuite 
                Patricia_Trie tmp = cle.getNext(); // temporaire de la liste suivante 
                Patricia_Trie next = new Patricia_Trie() ; // prochaine liste
                int len = petit.length();
                if(!petit.equals(m)){
                    String suf1 = m.substring(len);
                    String suf2 = cle.val.substring(len);
                    next.setElm_At(suf1,suf1+fin_chaine ,null );
                    next.setElm_At(suf2, suf2, tmp);
                    cle.setVal(petit);
                    cle.setNext(next);
                }else{// si le plus grand prefixe commun est = à m il faut pas essayé de l'ajouter à un sous noeud car on ne représente pas le caractère vide dans la Patricia trie
                    // petite note : Si on ajoute un mot et que la clé n'est pas un mot du dico alors on va quand même faire un sous-noeux en plus
                    // Solution : parcourir le noeux enfant et mettre en prefixe le suffixe de petit sur toutes le clés 
                    if(!(cle.val.endsWith(String.valueOf(fin_chaine)))){ // condition pour éviter de devoir rajouter une hauteur à l'arbre alors que le cle n'est plus un mot du dico 
                        if(cle.getNext()!=null){
                            String suf2 = cle.val.substring(len);
                            //System.out.println("suffix = "+suf2);
                            cle.getNext().put_prefix_liste(suf2);
                        }
                        cle.setVal(petit+fin_chaine);;
                    }else{
                    String suf2 = cle.val.substring(len);
                    next.setElm_At(suf2, suf2, tmp);
                    cle.setVal(petit+fin_chaine);
                    cle.setNext(next);
                    }
                }      
                
            }
        }
    }

    public void put_prefix_liste(String mot){ // on suppose que le mot ne finit pas par le caractère de fin de chaîne 
        for(int i=0;i<127;i++){
            if(liste[i]!=null){
                Elements cle=liste[i];
                cle.setVal(mot+cle.getVal());
                
            }
        }
    }
    public boolean rechercher_mot(String mot){
        if(mot==null){return false;}
        if(mot.length()==0){
            //System.out.println("vide");
            return mot_vide;
        }
        if(nb_nil==127){
            return false;
        }
        Elements cle = getElm_At(mot);
        if(cle!=null){
            Patricia_Trie.comparaisonCpt++;
            String petit;
            if(cle.val.endsWith(String.valueOf(fin_chaine))){
                petit=cle.getVal().substring(0,cle.getVal().length()-1);
            }else{
                petit=cle.getVal().substring(0,cle.getVal().length());
            }
            if(petit.equals(mot)){ // cas ou le plus grand prefixe commun vaut le mot recherché 2 possibilités 1. la cle se termine par '\0' et le mot y est 2. non
                if(cle.val.endsWith(String.valueOf(fin_chaine))){
                    return true;
                }
                return false;
            }
            int len1= petit.length();
            int len2= mot.length();
            if(len1>=len2 || !(mot.startsWith(petit))){// on verifie que le mot qu'on cherche est bien plus long que la clé qu'on possède sinon c'est qu'il n'y est pas 
                System.out.println("Plus petit");
                return false;
            }
            //on est dans le cas où notre mot n'est pas dans la clé de notre noeud mais qu'il a encore un suffixe qu'on pourra verifier l'appartenance dans les sous-noeuds            
            Patricia_Trie next = cle.getNext();
            if(next==null){ return false ;}
            String suf1 = mot.substring(len1);
            return next.rechercher_mot(suf1);
            
        }

        return false;
    }

    public boolean suppression(String mot){// fonction qui va supprimer le mot s'il se trouve dans la patricia trie et renvoyer vrai si la suppression a été opéré, faux sinon
        if(mot==null){ return false;}
        if(mot.length()==0){
            if(isMotVide()){
                mot_vide=false;
                return true;
            }else{
                return false;
            }
        }
        //System.out.println(" mot à supprimer = "+mot+" et sa taille = "+mot.length()+" vaut t'il mot nul ?"+String.valueOf(fin_chaine).equals(mot));
        Elements cle = getElm_At(mot);
        if(cle!=null){
            String petit;
            if(cle.val.endsWith(String.valueOf(fin_chaine))){
                petit=cle.getVal().substring(0,cle.getVal().length()-1);
            }else{
                petit=cle.getVal().substring(0,cle.getVal().length());
            }
            if(petit.equals(mot)){ // cas ou le plus grand prefixe commun vaut le mot recherché 2 possibilités 1. la cle se termine par '\0' et le mot y est 2. non
                if(cle.getVal().endsWith(String.valueOf(fin_chaine))){
                    if(cle.getNext()==null){
                        char conv = cle.getVal().charAt(0);
                        int ascii = (int) conv ;
                        ascii-=1;
                        nb_nil++;
                        liste[ascii]=null;
                    }else{
                        cle.setVal(cle.getVal().substring(0, cle.getVal().length()-1));
                    }
                    
                    return true;
                }
                return false;
            }
            int len1= petit.length();
            int len2= mot.length();
            if(len1>=len2 || !(mot.startsWith(petit))){
                return false;
            }
            Patricia_Trie next = cle.getNext();
            if(next==null){ return false ;}
            String suf1 = mot.substring(len1);
            boolean supp= next.suppression(suf1);
            if(supp && (127-next.nb_nil)==0){
                cle.setNext(null);
            }
            return supp ;
        }

        return false;
    }

    
    public static void patTojson(Patricia_Trie pat, String filename) {
        try {
            if (pat == null) {
                System.out.println("L'arbre est vide");
                return;
            }
    
            StringBuilder json = new StringBuilder();
            json.append("{\n");
            json.append("   \"label\": \"\",\n");
            json.append("   \"is_end_of_word\": ").append(pat.isMotVide()).append(",\n");
            json.append("   \"children\": {\n");
    
            Patricia_Trie.patTojsonRec(pat, json, 1);
    
            json.append("   }\n");
            json.append("}");
    
            FileWriter file = new FileWriter(filename);
            file.write(json.toString());
            file.close();
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
    
    private static void patTojsonRec(Patricia_Trie pat, StringBuilder json, int depth) {
        String indent = "   ".repeat(depth);
        int count = 0;
        int totalChildren = 127 - pat.nb_nil; 
    
        for (int i = 0; i < 127; i++) {
            if (pat.liste[i] != null) {
                Elements cle = pat.liste[i];
                count++;
                String val_pure = cle.getVal();
                if (val_pure.endsWith(String.valueOf(pat.fin_chaine))) {
                    val_pure = val_pure.substring(0, val_pure.length() - 1); 
                }
            
                json.append(indent).append("\"").append(cle.getVal().charAt(0)).append("\": {\n");
                json.append(indent).append("   \"label\": \"").append(val_pure).append("\",\n");
                json.append(indent).append("   \"is_end_of_word\": ").append(cle.getVal().endsWith(String.valueOf(pat.fin_chaine))).append(",\n");
                json.append(indent).append("   \"children\": ");
    
                if (cle.getNext() != null) {
                    json.append("{\n");
                    patTojsonRec(cle.getNext(), json, depth + 2);
                    json.append(indent).append("   }\n");
                } else {
                    json.append("{}\n");
                }
    
                
                if (count < totalChildren) {
                    json.append(indent).append("},\n");
                } else {
                    json.append(indent).append("}\n");
                }
            }
        }
    }

    public static Patricia_Trie json_to_pat(String file) {
        Patricia_Trie res = new Patricia_Trie();
        try {
            boolean est_il_vide = false;
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Pattern labelPattern = Pattern.compile("\"label\": \"(.*?)\"");
            Pattern ieowPattern = Pattern.compile("\"is_end_of_word\": (true|false)");
            //Pattern childPattern = Pattern.compile("\"children\": (\\{\\}|\\{)");
            while ((line = br.readLine()) != null) {
                //System.out.println("line = " + line);
    
                Matcher labelMatcher = labelPattern.matcher(line);
                Matcher ieowMatcher = ieowPattern.matcher(line);
                //Matcher childMatcher = childPattern.matcher(line);
                if (labelMatcher.find()) {
                    //System.out.println("Label trouvé : " + labelMatcher.group(1));
                    if (!labelMatcher.group(1).equals("")) {
                        br.close();
                        return null;
                    }
                    continue;
                }
    
                if (ieowMatcher.find()) {
                    //System.out.println("is_end_of_word trouvé : " + ieowMatcher.group(1));
                    est_il_vide = ieowMatcher.group(1).equals("true");
                    continue;
                }
    
                if (line.contains("\"children\"")) {
                    //System.out.println("coucou + "+line);
                    res = jsonToPatRec(br);
                    if(res==null){
                        System.out.println(" soupir");
                    }
                    res.mot_vide = est_il_vide;
                    br.close();
                    return res;
                }
            }
            //System.out.println("Résultat final : " + res.toString());
            br.close();
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
        System.out.println(" arrives tu");
        return res;
    }
    


    private static Patricia_Trie jsonToPatRec(BufferedReader br){
        Pattern posPattern = Pattern.compile("\"(.*?)\": \\{");
        Pattern labelPattern = Pattern.compile("\"label\": \"(.*?)\"");
        Pattern ieowPattern = Pattern.compile("\"is_end_of_word\": (true|false)");
        Pattern childPattern = Pattern.compile("\"children\": (\\{\\}|\\{)");
        Patricia_Trie res = new Patricia_Trie();
        try{
            String line;
            String pos=null ;
            String val=null ;
            Boolean is_mot;
            while ((line = br.readLine()) != null) {
                //System.out.println("ligne = "+line);
                Matcher posMatcher = posPattern.matcher(line);
                Matcher labelMatcher = labelPattern.matcher(line);
                Matcher ieowMatcher = ieowPattern.matcher(line);
                Matcher childMatcher = childPattern.matcher(line);
                
                if((posMatcher.find())){
                    if(!line.contains("\"children\":")){
                        pos=posMatcher.group(1);
                        System.out.println("pos = "+pos);
                        continue;
                    }
                }
                if(labelMatcher.find()){
                    val=labelMatcher.group(1);
                    System.out.println("val ="+val);
                    continue;
                }
                if(ieowMatcher.find()){
                    is_mot = ieowMatcher.group(1).equals("true");
                    res.patInsertion(val);
                    if(!is_mot){
                        Elements cle = res.getElm_At(val);
                        System.out.println("cle = "+cle.getVal()+" pos = "+val);
                        if(cle!=null){
                            cle.setVal(val); // on retire le caractère de fin de chaine 
                        }    
                    }
                    Elements cle = res.getElm_At(val);
                    System.out.println("cle = "+cle.getVal()+" pos = "+val);
                    //System.out.println("après insertion");
                    //System.out.println(res.toString());
                    continue;
                }
                if(childMatcher.find()){
                    System.out.println("est tu là ? "+childMatcher.group(1));
                    if(childMatcher.group(1).equals("{")){
                        Patricia_Trie tmp = jsonToPatRec(br);
                        System.out.println("attention");
                        //System.out.println(tmp.toString());
                        Elements cle = res.getElm_At(val);
                        //System.out.println("att cle : "+cle.getVal());
                        //System.out.println("fini");
                        cle.setNext(tmp);
                        line = br.readLine();
                    }
                    continue;   
                }

                if(line!=null){
                    String fin_elm = line.trim();
                    System.out.println("line : "+line.trim());
                    if(fin_elm.equals("}")){
                        return res;
                    }
                }
            }

        }catch(IOException e){
            System.err.println("Erreur : " + e.getMessage());
        }
        return res;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(sb, 0); 
        return sb.toString();
    }
    
    private void toStringRec(StringBuilder sb, int depth) {
        String indent = "  ".repeat(depth);
        for (int i = 0; i < 127; i++) {
            Elements element = liste[i];
            if (element != null ) {
                sb.append(indent)
                  .append("- ")
                  .append("Clé: ")
                  .append(element.getVal())
                  .append(" , FinDeMot: ")
                  .append(element.getVal().endsWith(String.valueOf(fin_chaine)) ? "Oui" : "Non")
                  .append("\n");
    
                Patricia_Trie nextTrie = element.getNext();
                if (nextTrie != null) {
                    nextTrie.toStringRec(sb, depth + 1);
                }
            }
        }
    
        if (depth == 0 && mot_vide) {
            sb.append(indent).append("- [Mot vide présent]\n");
        }
    }
}