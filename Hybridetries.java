
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



public class Hybridetries  {

    /*classe racine = triplé char int boolean  pour marquer la fin d'un mot */
    public class Racine{
        public Racine(char ra,int cpt,boolean visited){
            this.ra=ra;
            this.cpt=cpt; //pour marquer la fin d'un mot
            this.visited=visited; //pour e pas compter les mots deux fois
        }
        private char ra;
        private int cpt;
        private boolean visited;


        /****Getter et Setter*****/
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
        public void resetflag(){
            visited=false;
        }


    }

    /*Fin classe racine  */


    //Constructeur abr vide
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

  

   

    public Hybridetries getinf(){
        return this.inf;
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

     /*****Primitive sur le clé ***/

    /*recuperer la première lettre de la clé */
    public static char firstchar(String key) {
        
        if (key != null && !key.isEmpty()) {
            return key.charAt(0);  
        } 
       
        return ' ';

    }
     
   /*récuperer la cle sans la première lettre */
    public static String remaining(String key){

        if (key != null && !key.isEmpty()) {
            return key.substring(1);  
        } 
        
        return " ";
     }




    /*longeur de la clé */
    public static int length(String key) {

        return key.length();
    }

    

    /*arbre vide ou pas */
    public  boolean  isEmpty(){
       return this.racine.getRa()=='/'&&this.racine.getcpt()==0;
       
    }
   
     

    private static int cpt_ins=0;

    public void setcpt_ins(){
        cpt_ins=0;
    }
    public int getcpt_ins(){
        System.out.println("complexité insertion : nb d'arbre cree" + cpt_ins );
        return cpt_ins;
    }

    public int  incr_cpt_ins(){
        System.out.println("complexité insertion : nb d'arbre cree " + cpt_ins );
       return cpt_ins+=3;

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
            incr_cpt_ins();
            return this;
            
        } else { 
            
            this.setRac(firstchar(key.toLowerCase()));
            System.out.println("EQ " + this.getrac().getRa()+ " "+ this.getrac().getcpt());  
            this.inf = new Hybridetries();  
            this.eq = new Hybridetries();
            this.sup = new Hybridetries(); 
            incr_cpt_ins(); 
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
               // System.out.println(" FIN mot deja dans le dico " + this.getrac().getcpt());
                this.setCpt();
                return this;//on ne fait rien car mot deja ds le dico
            }

            this.eq = this.eq.insertKey(remaining(key.toLowerCase()));
            
        }
    }
    return this;
}

    private static int cpt_re=0;
    public void setcpt_re(){
        cpt_re=0;
    }

    public int getcpt_re(){
        System.out.println("complexité recherche : nb de d'apel récursifs " + cpt_re );
        return cpt_re;
    }

    public boolean  Recherche(Hybridetries arbre,String mot){

     if(mot==" "){System.out.println("false " + this.getrac().getcpt());return false;}

     char p = firstchar(mot.toLowerCase());
     
       
     if(this.isEmpty()){System.out.println("false " + this.getrac().getcpt());return false;}
       
     if (p < this.getrac().getRa()) {
         cpt_re+=1;
           return inf.Recherche(this.inf, mot.toLowerCase());  
        }
        
     if (p > this.getrac().getRa()) {
         cpt_re+=1;
            return sup.Recherche(this.sup,mot.toLowerCase());
        }
        
     else {
        
            if(mot.length()==1&&this.getrac().getcpt()!=0){System.out.println("true " + this.getrac().getcpt());return true;}
            else if (mot.length()==1){System.out.println("false " + this.getrac().getcpt()); return false;}
            cpt_re+=1;
           return  eq.Recherche(this.eq,remaining(mot.toLowerCase()));  
        }
        
    }

    public int compter_noeuds=0;// pour compter tous les neouds de l'arbre

    void reset_flag(){// remet CPT à 0 et tous les noeuds à nul, peut etre pb ptrs null plus que ce qu'il en faut

        
        if (this.inf!=null&&!this.inf.isEmpty()){
        
        this.getrac().resetflag();
        this.inf.reset_flag();

        }

        if(this.sup!=null&&!this.sup.isEmpty()){
        
        this.getrac().resetflag();
        this.sup.reset_flag();
        }
     
    
        if(this.eq!=null&&!this.eq.isEmpty()){ 
       
        this.getrac().resetflag();
        this.eq.reset_flag();

        }   
     
     
    }

    public int Compter_bis(Hybridetries arbre){

        if(arbre.isEmpty()) return 0; 
       
     if(arbre.getrac().getcpt()!=0&&arbre.getrac().getVisited()==false){//si pas visité et c'est un mots on rajoute 1
        compter_noeuds++;
         arbre.getrac().setVisited();
        return 1+ Compter_bis(arbre.eq)+Compter_bis(arbre.sup)+ Compter_bis(arbre.inf);
     }else{
        compter_noeuds++;
        return Compter_bis(arbre.eq)+Compter_bis(arbre.sup)+ Compter_bis(arbre.inf);
     }
        
    }

    public int ComptageMots(Hybridetries arbre){
     arbre.reset_flag();
     return Compter_bis(arbre);

    }

   

   /// fonction utilisé pour ListeMots()
    public  Set<String> ajout_mots(Hybridetries abr_courant , String mot_courrant, Set<String> words){
    
       
        if(abr_courant!=null&&!abr_courant.isEmpty()){
        if (abr_courant.inf!=null&&!abr_courant.inf.isEmpty()){
             ajout_mots(abr_courant.inf, mot_courrant, words);

        }

        if (abr_courant.sup!=null&&!abr_courant.sup.isEmpty()){
             ajout_mots(abr_courant.sup, mot_courrant, words);
        }
         // Ajouter le caractère actuel au mot en construction
        
        if(abr_courant.getrac().getcpt()==0){ 
          mot_courrant += abr_courant.getrac().getRa();
           ajout_mots(abr_courant.eq, mot_courrant, words);

        }
       
        else if(abr_courant.getrac().getcpt()!=0 && abr_courant.getrac().getVisited()==true){
                 ajout_mots(abr_courant.eq, mot_courrant, words);

            
        } else if(abr_courant.getrac().getcpt()!=0 && abr_courant.getrac().getVisited()==false){
            mot_courrant += abr_courant.getrac().getRa();
            abr_courant.getrac().setVisited();
            words.add(mot_courrant);
             ajout_mots(abr_courant.eq, mot_courrant, words);

        }
               
       
        }
        return words;
       
    }

    public  ArrayList<String> ListeMots(Hybridetries arbre){
        arbre.reset_flag();
       
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
    Set<Integer> temp;
    temp= hauteur_bis(arbre , 0, profondeurs);
    int max = 0;
    for(Integer i : temp){
        if(i>max) max=i;
    }
    System.out.println("l'hauteur de l'arbre est   " +max);
    return max;
 }

 public int ProfondeurMoyenne(Hybridetries arbre){

    Set<Integer> profondeurs= new HashSet<>();
    Set<Integer> temp;
    temp= hauteur_bis(arbre , 0, profondeurs);
    int cpt = 0;
    for(Integer i : temp){
        cpt+=i;
    }
    System.out.println("somme des profondeurs de l'arbre " +cpt);
    System.out.println("nombre de profondeur dans l'arbre " +temp.size());
    System.out.println("Moy des profondeurs=  " +cpt/temp.size());

    return cpt/temp.size();
 }

public int cpt_pref=0;
 public int  Prefixe(Hybridetries arbre,String mot){

    char p = firstchar(mot.toLowerCase());
    
      
    if(this.isEmpty()){return 0;}

    if(p==' '){
        if(this.getrac().getcpt()!=0){
            cpt_pref++;
           return 1+ sup.Prefixe(this.sup,mot.toLowerCase())+inf.Prefixe(this.inf,mot.toLowerCase())+eq.Prefixe(this.eq,mot.toLowerCase());
            }
        else{
            cpt_pref++;
            return sup.Prefixe(this.sup,mot.toLowerCase())+inf.Prefixe(this.inf,mot.toLowerCase())+eq.Prefixe(this.eq,mot.toLowerCase());
        }
    } 
    

    if(p < this.getrac().getRa()) {
        cpt_pref++;
          return inf.Prefixe(this.inf, mot.toLowerCase());  
       }
     
    else if (p > this.getrac().getRa()) {
         cpt_pref++;
          return sup.Prefixe(this.sup,mot.toLowerCase());
       }
      
    else {
           if(mot.length()==1&&this.getrac().getcpt()!=0){
             cpt_pref++;
             return 1+ eq.Prefixe(this.eq,remaining(mot.toLowerCase()));
            }else if(mot.length()==1){
                cpt_pref++;
                return 0 + eq.Prefixe(this.eq,remaining(mot.toLowerCase()));
            }
            cpt_pref++;

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

public static int cpt_bulle=0;

public static void bubbleSort(String[][] arr) {
    int n = arr.length;
  
    for (int i = 0; i < n - 1; i++) {
      
        for (int j = 0; j < n - 1 - i; j++) {
            
            int integer1=Integer.parseInt(arr[j][1]);
            int integer2=Integer.parseInt(arr[j + 1][1]);
            if (integer1-integer2 > 0) {
                cpt_bulle++;
                
                String[] temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

public Hybridetries Suppression(Hybridetries arbre, String mot){
    

    Set<String[]> temp;
    Set<String[]> temp2= new HashSet<>();
   
    arbre.reset_flag();
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
    String[] finaly=new String[a_trier.length-1];
   
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

   }
   return arbre;
}

/*******************Pour exprerimentation avec les oeuvres de Shakespeare******************************* */
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
