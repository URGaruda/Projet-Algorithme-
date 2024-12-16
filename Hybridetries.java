
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



public class Hybridetries  {

    /*classe racine = couple char int pour marquer la fin d'un mot */
    public class Racine{
        public Racine(char ra,int cpt,boolean visited){
            this.ra=ra;
            this.cpt=cpt; //pour marquer la fin d'un mot
            this.visited=visited; //pour e pas compter les mots deux fois
        }
        private char ra;
        private int cpt;
        private boolean visited;

        public char getRa(){
            return ra;
        }

        public void setRa(char ra ){
            this.ra=ra;
        }

        public int getcpt(){
            return cpt;
        }

        public void setcpt(int cpt ){
            this.cpt=cpt;
        }

        public boolean getVisited(){
            return visited;
        }

        public void setVisited(){
            visited= true;
        }


    }

    /*Fin classe racine  */


    //constructeur abr vide
    public Hybridetries(){
        this.racine= new Racine('/',0,false);
        this.inf = null;
        this.eq = null;
        this.sup = null;

    }

    
    private Hybridetries eq;
    private Hybridetries inf;
    private Hybridetries sup;
    private  Racine racine ;
    private static int end_word=0;

    // charAt(), substring(), isEmpty() dans la bibli String

    //primitive sur le clé 

    /*recuperer la première lettre de la clé */
    public static char firstchar(String key) {
        
        if (key != null && !key.isEmpty()) {
            return key.charAt(0);  
        } 
        System.out.println("la clé est vide");
        return ' ';

    }

    public Hybridetries getinf(){
        return this.inf;
    }
     
/*récuperer la cle sans la première lettre */
    public static String remaining(String key){

        if (key != null && !key.isEmpty()) {
            return key.substring(1);  
        } 
        System.out.println("la clé est vide");
        return "0";
     }




    /*longeur de la clé */
    public static int length(String key) {

        return key.length();
    }

    //Primitive abr

    /*arbre vide ou pas */
    public  boolean  isEmpty(){
       return this.racine.getRa()=='/'&&this.racine.getcpt()==0;
       
    }
    /*recuper racine  */
    public Racine getrac(){
        return this.racine;
    }
    
     public void setRac(char k){
        this.racine.setRa(k);
    }

    public int getCpt(){
        return end_word;
    }

    public void setCpt(){
        end_word++;
}
    public void set_end_of_word(){
        this.racine.setcpt(end_word);
    }
    public void resetCpt(){
        end_word=0;
    }
     


   
public Hybridetries insertKey(String key) {
    
    if (this.isEmpty()) {
       
        if (key.length() == 1) {
          
            this.setRac(firstchar(key.toLowerCase()));
            this.setCpt();
            this.set_end_of_word();
            System.out.println(" FIN " + this.getrac().getRa()+ " "+ this.getrac().getcpt());

            this.inf = new Hybridetries();  
            this.eq = new Hybridetries();
            this.sup = new Hybridetries();
            return this;
            
        } else { 
            
            this.setRac(firstchar(key.toLowerCase()));
            System.out.println("EQ " + this.getrac().getRa()+ " "+ this.getrac().getcpt());  
            this.inf = new Hybridetries();  
            this.eq = new Hybridetries();
            this.sup = new Hybridetries();  
            this.eq.insertKey(remaining(key)); 
        }
    } else {
        char p = firstchar(key); 
       
      
        if (p < this.getrac().getRa()) {
            System.out.println("INF " +this.firstchar(key.toLowerCase()));
           
            this.inf = this.inf.insertKey(key);  
        }
      
        else if (p > this.getrac().getRa()) {
            System.out.println("SUP " +this.firstchar(key.toLowerCase()));
            
            this.sup = this.sup.insertKey(key.toLowerCase());
           

        }
       
        else {
            System.out.println("eqBIS " + this.firstchar(key));
            if(key.length()==1&&this.getrac().getcpt()==0){ //afin de marquer la fin d'un nouveau mot préfixe d'un autre mot qui était dja dans le dico
                this.setCpt();
                this.set_end_of_word();
                return this;
            }
            else if(key.length()==1&&this.getrac().getcpt()!=0){
                System.out.println(" FIN mot deja dans le dico " + this.getrac().getcpt());
                this.setCpt();
                return this;//on ne fait rien car mot deja ds le dico
            }

            this.eq = this.eq.insertKey(remaining(key.toLowerCase()));
            
        }
    }
    return this;
}


    public boolean  Recherche(Hybridetries arbre,String mot){

     if(mot==" "){System.out.println("false " + this.getrac().getcpt());return false;}

     char p = firstchar(mot.toLowerCase());
     
       
     if(this.isEmpty()){System.out.println("false " + this.getrac().getcpt());return false;}

     if (p < this.getrac().getRa()) {
           return inf.Recherche(this.inf, mot.toLowerCase());  
        }
      
     else if (p > this.getrac().getRa()) {
            return sup.Recherche(this.sup,mot.toLowerCase());
        }
       
     else {
            if(mot.length()==1&&this.getrac().getcpt()!=0){System.out.println("true " + this.getrac().getcpt());return true;}
            else if (mot.length()==1){System.out.println("false " + this.getrac().getcpt()); return false;}

           return  eq.Recherche(this.eq,remaining(mot.toLowerCase()));  
        }
        
    }

    
    public int ComptageMots(Hybridetries arbre){

     if(arbre.isEmpty()) return 0; 
     
       
     if(arbre.getrac().getcpt()!=0&&arbre.getrac().getVisited()==false){//si pas visité et c'est un mots on rajoute 1
        arbre.getrac().setVisited();
        return 1+ ComptageMots(arbre.eq)+ComptageMots(arbre.sup)+ ComptageMots(arbre.inf);
     }else{
        return ComptageMots(arbre.eq)+ComptageMots(arbre.sup)+ ComptageMots(arbre.inf);
     }

    }

   

   /// fonction utilisé pour ListeMots()
    public  Set<String> ajout_mots(Hybridetries abr_courant , String mot_courrant, Set<String> words){
                
        if (abr_courant.isEmpty()) {
            return words;
        }

        if (!abr_courant.inf.isEmpty()){
            ajout_mots(abr_courant.inf, mot_courrant, words);

        }

        if (!abr_courant.sup.isEmpty()){
             ajout_mots(abr_courant.sup, mot_courrant, words);
        }
         // Ajouter le caractère actuel au mot en construction
        
        if(abr_courant.getrac().getcpt()==0){ 
          mot_courrant += abr_courant.getrac().getRa();
          return ajout_mots(abr_courant.eq, mot_courrant, words);

        }
        else if(abr_courant.getrac().getcpt()!=0 && abr_courant.getrac().getVisited()==true){
                return ajout_mots(abr_courant.eq, mot_courrant, words);

        }else if(abr_courant.getrac().getcpt()!=0 && abr_courant.getrac().getVisited()==false){
            mot_courrant += abr_courant.getrac().getRa();
            abr_courant.getrac().setVisited();
            words.add(mot_courrant);
            return ajout_mots(abr_courant.eq, mot_courrant, words);

        }
        return words;
    }

    public  ArrayList<String> ListeMots(Hybridetries arbre){
        Set<String> mots= new HashSet<>();
        Set<String> temp = ajout_mots(arbre , "", mots);
        ArrayList<String> res= new ArrayList<>();

        for(String words : temp){
            res.add(words);
        }
        Collections.sort(res);
        
        for(String words : res){
            System.out.println(words);
        }
        return res;
    }

    public int ComptageNil(Hybridetries arbre){

        if(!arbre.isEmpty()) {return 0 + ComptageNil(arbre.eq) + ComptageNil(arbre.inf) +ComptageNil(arbre.sup); }

        return 1;
    }
   
   
       


 //regler pb flag visited


 public  Set<Integer> hauteur_bis(Hybridetries abr_courant , int cpt_courant, Set<Integer> profondeurs){
                
    if (abr_courant.eq.isEmpty()&&abr_courant.inf.isEmpty()&&abr_courant.sup.isEmpty()) {
        profondeurs.add(cpt_courant);
        
        return profondeurs;
    }else{


    if (!abr_courant.inf.isEmpty()){
        cpt_courant++;

        hauteur_bis(abr_courant.inf, cpt_courant--, profondeurs);
        

     }

    if (!abr_courant.sup.isEmpty()){
        cpt_courant++;

        hauteur_bis(abr_courant.sup, cpt_courant--, profondeurs);
    }

    if (!abr_courant.eq.isEmpty()){
        cpt_courant++;
        hauteur_bis(abr_courant.eq, cpt_courant--, profondeurs);
        }

        
    }
    return profondeurs;
 }

  public int Hauteur(Hybridetries arbre){

    Set<Integer> profondeurs= new HashSet<>();
    Set<Integer> temp= new HashSet<>();
    temp= hauteur_bis(arbre , 0, profondeurs);
    int max = 0;
    for(Integer i : temp){
        if(i>max) max=i;
    }
    System.out.println(max);
    return max;
 }

 public int ProfondeurMoyenne(Hybridetries arbre){

    Set<Integer> profondeurs= new HashSet<>();
    Set<Integer> temp= new HashSet<>();
    temp= hauteur_bis(arbre , 0, profondeurs);
    int cpt = 0;
    for(Integer i : temp){
        cpt=+i;
    }
    System.out.println("somme " +cpt);
    System.out.println("taile  " +temp.size());
    System.out.println("Moy=  " +cpt/temp.size());

    return cpt/temp.size();
 }


 public int  Prefixe(Hybridetries arbre,String mot){

    

    char p = firstchar(mot.toLowerCase());
    
      
    if(this.isEmpty()){return 0;}

    if(p==' '){
        if(this.getrac().getcpt()!=0){
           return 1+ sup.Prefixe(this.sup,mot.toLowerCase())+inf.Prefixe(this.inf,mot.toLowerCase())+eq.Prefixe(this.eq,mot.toLowerCase());
            }
        else{
            return sup.Prefixe(this.sup,mot.toLowerCase())+inf.Prefixe(this.inf,mot.toLowerCase())+eq.Prefixe(this.eq,mot.toLowerCase());
        }
    } 
    

    if(p < this.getrac().getRa()) {
          return inf.Prefixe(this.inf, mot.toLowerCase());  
       }
     
    else if (p > this.getrac().getRa()) {
          return sup.Prefixe(this.sup,mot.toLowerCase());
       }
      
    else {
           if(mot.length()==1&&this.getrac().getcpt()!=0){
            
             return 1+ eq.Prefixe(this.eq,remaining(mot.toLowerCase()));
            }else if(mot.length()==1){
                return 0 + eq.Prefixe(this.eq,remaining(mot.toLowerCase()));
            }
           

           return eq.Prefixe(this.eq,remaining(mot.toLowerCase()));  
       }
       
   }

   public  Set<String[]> ajout_mots_bis(Hybridetries abr_courant , String mot_courrant, Set<String[]> words){ //pour manipuler des couples mots end_of_word
    
    String[] couple=new String[2];

    if (abr_courant.isEmpty()) {
        return words;
    }

    if (!abr_courant.inf.isEmpty()){
        ajout_mots_bis(abr_courant.inf, mot_courrant, words);

    }

    if (!abr_courant.sup.isEmpty()){
         ajout_mots_bis(abr_courant.sup, mot_courrant, words);
    }
     // Ajouter le caractère actuel au mot en construction
    
    if(abr_courant.getrac().getcpt()==0){ 
      mot_courrant += abr_courant.getrac().getRa();
      return ajout_mots_bis(abr_courant.eq, mot_courrant, words);

    }
    else if(abr_courant.getrac().getcpt()!=0 && abr_courant.getrac().getVisited()==true){
            return ajout_mots_bis(abr_courant.eq, mot_courrant,words);

    }else if(abr_courant.getrac().getcpt()!=0 && abr_courant.getrac().getVisited()==false){
        mot_courrant += abr_courant.getrac().getRa();
        abr_courant.getrac().setVisited();
        couple[0]= mot_courrant;
        couple[1]= abr_courant.getrac().getcpt() + "";


        words.add(couple);
        return ajout_mots_bis(abr_courant.eq, mot_courrant, words);

    }
    return words;
}


void reset(){// remet CPT à 0 et tous les noeuds à nul, peut etre pb ptrs null plus que ce qu'il en faut
    this.resetCpt();

    if (!this.inf.isEmpty()){
        this.getrac().setRa('/');
        this.getrac().setcpt(0);
        
        this.inf.reset();

    }

    if (!this.sup.isEmpty()){
        this.getrac().setRa('/');
        this.getrac().setcpt(0);
        this.sup.reset();
    }
     
    
    if(!this.eq.isEmpty()){ 
        this.getrac().setRa('/');
        this.getrac().setcpt(0);
        this.eq.reset();

    }
   
    
}

public static void bubbleSort(String[][] arr) {
    int n = arr.length;
    // Parcours du tableau
    for (int i = 0; i < n - 1; i++) {
        // Derniers i éléments sont déjà triés
        for (int j = 0; j < n - 1 - i; j++) {
            // Comparer le deuxième élément de chaque sous-tableau
            int integer1=Integer.parseInt(arr[j][1]);
            int integer2=Integer.parseInt(arr[j + 1][1]);
            if (integer1-integer2 > 0) {
                // Échanger les sous-tableaux
                String[] temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

public Hybridetries Suppression(Hybridetries arbre, String mot){
    

    Set<String[]> temp= new HashSet<>();
    Set<String[]> temp2= new HashSet<>();
   
   
    int i=0;
    temp= ajout_mots_bis(arbre,"",temp2);
    
    String[][] a_trier= new String[temp.size()][2];


    for(String[] couple : temp){
        a_trier[i++]=couple;
    }

    //trier l'arbre
    bubbleSort(a_trier);

    for (String[] pair : a_trier) {
    System.out.println(pair[0] +  " " + pair[1]);
    }
    String[] finaly=new String[a_trier.length];
   
    i=0;
    for(int p=0;p<a_trier.length;p++){
    if(!(a_trier[p][0].equals(mot))){ finaly[i++]=a_trier[p][0]; }

    
   }
   for (String s : finaly) {
    System.out.println(s);
}
   arbre.reset();
   for(String w : finaly){
    arbre.insertKey(w);
    System.out.println("****");
   }
   return arbre;
}

/*******************Pour exprerimentation******************************* */
 public void read(Hybridetries arbre,String filePath) {
        File file = new File(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
       String line;
       while ((line = reader.readLine()) != null) {
           // Appliquer une fonction à chaque ligne lue
           arbre.insertKey(line);
       }
       } catch (IOException e) {
        e.printStackTrace();
    }

 }

}