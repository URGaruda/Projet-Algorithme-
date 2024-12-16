import java.util.Arrays;
import java.util.List;

public class Maintest {


   
    public static void main(String[] args) {

        Hybridetries tree = new Hybridetries();

         List<String> files = Arrays.asList("1henryiv.txt", "1henryvi.txt", "2henryiv.txt","2henryvi.txt","3henryvi.txt","allswell.txt","asyoulikeit.txt","cleopatra copy.txt",
        "cleopatra.txt","comedy_errors.txt","coriolanus.txt","cymbeline.txt","hamlet.txt","henryv.txt","henryviii.txt","john copy.txt","john.txt","julius_caesar.txt","lear.txt","lear.txt","lll.txt", "macbeth.txt","measure.txt","merchant.txt","merry_wives.txt", "midsummer.txt","much_ado.txt","othello.txt","pericles.txt","richardii.txt","richardiii.txt","romeo_juliet copy.txt","romeo_juliet.txt","taming_shrew.txt","tempest.txt","timon.txt","titus.txt","troilus_cressida.txt","twelfth_night.txt","two_gentlemen.txt","winters_tale.txt");
        
        //mesuerer le temps
        long startTime = System.currentTimeMillis();
        for(String filepath : files){
        tree.read(tree,filepath);
    }

    long endTime = System.currentTimeMillis();


        System.out.println("****Temps écoué costruction de l'arbre********* "+ (endTime - startTime) + "milisecondes soit "+ (endTime - startTime)/1000.0+ " secondes");
    }
    
}
