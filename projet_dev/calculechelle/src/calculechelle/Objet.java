package calculechelle;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * Classe Objet permet de récolter les informations d’un objet et de réaliser un histogramme de celui-ci.
 * <p>
 * 		Un Objet est caractérisé par les informations suivantes : 
 * <ul>
 * 		<li> Une variable color qui est attribuée lors de la création. </li>
 * 		<li> Une liste chaînée de type Pixel qui sera rempli à l'aide des points de l'objet. </li>
 * 		<li> Une liste chaînée de type ArrayList<> de Double qui sera rempli via les angles de l'objet. </li>
 * 		<li> Une variable centreGravite de type Pixel, qui est attribuée lors de la création. </li>
 * 		<li> Une variable angleBarycentre de type double, qui est attribuée lors de la création. </li>
 * </ul>
 *
 * <p>
 * 		Cette classe sera en communication avec la classe Pixel.
 * </p>
 * 
 * @see Pixel
 * @see Pixel#getAngles(Objet)  
 * @see Pixel#getAnglesBarycentre(Objet)
 * 
 * @version 1.0
 * 
 */

/**
 * Classe Objet représentant un objet composé de pixels, de couleurs et de variables pour stocker les résultats du barycentre et de l'histogramme.<br>
 * On part toujours d'un Objet vide.
 *
 */
class Objet
{	
	/**
     * La variable color de la classe Objet. Cette couleur est modifiable.
     * 
     * @see Objet#getColor()
     * @see Objet#addColor(int)
     * @see Objet#calculCentreGrav()
     * @see Objet#afficherCentreGravite(int, int)
     * @see Objet#sauvegarde(String ,int) throws FileNotFoundException
     *
     */
    private int color;
    
    /**
     * Les ensembles de points d'Objet. Cet ensemblesPoints est modifiable.
     * 
     * @see Objet#addPoint(Pixel)
     * @see Objet#retourX(int)
     * @see Objet#retourY(int)
     * @see Objet#retourneEnsemblePoints()
     * @see Objet#tailleEnsemblePoints()
     * @see Objet#calculAngles(Objet)
     * @see Objet#createObjetImage(int , int)
     * @see Objet#afficherObjet(int , int)
     * @see Objet#creationHistogramme()
     * @see Objet#calculCentreGrav()
     * @see Pixel#getAngles(Objet)
     *
     */
    private ArrayList<Pixel> ensemblesPoints = new ArrayList<Pixel>();//
    
    /**
     * L'histogramme d'Objet. Cet histogramme est modifiable.
     * 
     * @see Objet#creationHistogramme()
     * @see Objet#affichageHistogramme(int)
     * @see Objet#getHistogramme()
     * @see Objet#getAngleBot()
     * @see Objet#getAngleTop()
     * @see Objet#getAngleLeft()
     * @see Objet#getAngleRight()
     *
     */
    private ArrayList<ArrayList<Double>> histogramme = new ArrayList<ArrayList<Double>>();
    /**
     * Le centre de gravité d'Objet. Ce centre de gravité est modifiable.
     * 
     * @see Objet#retourCentreGravite()
     * @see Objet#retourCentreGraviteX()
     * @see Objet#retourCentreGraviteY()
     * @see Objet#setCentreGravite(Pixel)
     * @see Objet#calculCentreGrav()
     * @see Objet#afficherCentreGravite(int , int)
     * @see Pixel#getAnglesBarycentre(Objet)
     *
     */
    private Pixel centreGravite;
    /**
     * L'angle du barycentre d'Objet. Cet angleBarycentre est modifiable.
     * 
     * @see Objet#addAngleBarycentre(double)
     * @see Objet#retourAngleBarycentre()
     * @see Objet#affichageBarycentre()
     * @see Objet#toString()
     * @see Objet#affichageRelatifBarycentre(String , String)
     * @see Pixel#getAnglesBarycentre(Objet)
     *
     */
    private double angleBarycentre;
    
    /**
     * Obtenir l'information de la variable color de l'objet.
     * 
     * @return la couleur de l'objet.
     * 
     */

    public int getColor() {
        return color;
    }

    /**
     * Initialise la couleur avec le paramètre.
     *
     * @param color
     * Correspond à la couleur à ajouter.
     *
     */
    public void addColor(int color)
    {
        this.color=color;
    }
    
    /**
     * Ajouter un point à la liste.
     * 
     * @param p
     * Point à ajouter à l'ensemble de points.
     *
     */
    public void addPoint(Pixel p) {ensemblesPoints.add(p);}

    /**
     * Retourne la valeur de x présente à une position qui est passée en argument.
     * 
     * @param position
     * Position dans l'ensemble de points.
     * 
     * @return la valeur de x de l'objet.
     *
     * @see Pixel#getX()
     * @see Pixel#getAngles(Objet)
     *
     */
    public int retourX(int position)
    {
        return ensemblesPoints.get(position).getX();
    }

    /**
     * Retourne la valeur de y présente à une position qui est passée en argument.
     * 
     * @param position
     * Position dans l'ensemble de points.
     * 
     * @return la valeur de y de l'objet.
     * @see Pixel#getY()
     * @see Pixel#getAngles(Objet)
     *
     */
    public int retourY(int position)
    {
        return ensemblesPoints.get(position).getY();
    }

    /**
     * Retourne la liste de l'ensemble de tous les points.
     * 
     * @return la liste d'ensemble de points.
     *
     */
    public ArrayList<Pixel> retourneEnsemblePoints() {return ensemblesPoints;}

    /**
     * Permet de connaître la taille de l'ArrayList de l'ensemble de points de l'objet.
     * 
     * @return la taille de l'ensemblesPoints.
     *
     * @see Pixel#getAngles(Objet)
     * 
     */
    public int tailleEnsemblePoints()
    {
        return ensemblesPoints.size();
    }

    //Ensemble des méthodes pour la méthode du barycentre
    /**
     * Donne le point du centre de Gravité de l'objet.
     *  
     * @return le point du centre de gravité.
     * 
     * @see Pixel#getAnglesBarycentre(Objet)
     *
     */
    public Pixel retourCentreGravite() {return centreGravite;}

    /**
     * Donne uniquement la valeur de x du centre de gravité.
     *  
     * @return la coordonnée x du centre de gravité.
     *
     * @see Pixel#getAnglesBarycentre(Objet)
     *
     */
    public int retourCentreGraviteX() {return centreGravite.getX();}

    /**
     * Donne uniquement la valeur de y du centre de gravité
     *  
     * @return la coordonnée y du centre de gravité.
     *
     * @see Pixel#getAnglesBarycentre(Objet)
     *
     */
    public int retourCentreGraviteY() {return centreGravite.getY();}

    /**
     * Initialise l'angle de barycentre avec la valeur qui est passée en argument.
     * 
     * @param angleBarycentre
     * Angle du barycentre à ajouter.
     *
     */
    public void addAngleBarycentre(double angleBarycentre) {this.angleBarycentre=angleBarycentre;}

    /**
     * Donne la valeur de l'angle du barycentre de l'objet.
     * 
     * @return angleBarycentre.
     */
    public double retourAngleBarycentre() {return angleBarycentre;}

    /**
     * Initialise le centre de gravité avec le point qui est passé en argument.
     * 
     * @param centreGraviteAttendu
     * Pixel/Point du centre de gravité à ajouter.
     *
     */
    public void setCentreGravite(Pixel centreGraviteAttendu){
        this.centreGravite = centreGraviteAttendu;
    }
    
    /**
     * Affiche la valeur de l'angle du barycentre.
     * 
     * @see Objet#sauvegarde(String, int)
     *
     */
    public void affichageBarycentre()
    {
        System.out.println("-------\nLa methode du barycentre nous donne: "+angleBarycentre+" degré.");
    }

    /**
     * Retourne en String la valeur du barycentre.
     *
     */
    public String toString(){
        return "-------\nLa methode du barycentre nous donne: "+angleBarycentre+" degré.";
    }

    /**
     * Calcul les angles de tous les points de l'objet par rapport à un autre objet passé en argument.
     * 
     * @param B
     * Un Objet à comparer pour le calcul.
     *
     * @see Pixel#getAngles(Objet)
     *
     */
    public void calculAngles(Objet B)
    {
        for(int i=0;i<ensemblesPoints.size();i++)
        {
            ensemblesPoints.get(i).getAngles(B);
        }
    }

    /**
     * Affiche l'objet seul à partir de ses points.
     * 
     * @param width
     * Largeur de l'image.
     *
     * @param height
     * Hauteur de l'image.
     * 
     * @return le resultImage.
     *
     * @see Pixel#getX()
     * @see Pixel#getY()
     * @see Pixel#getColor()
     */
    //JUnit test, Même code qu'afficherObjet mais de type BufferedImage.
    public BufferedImage createObjetImage(int width, int height)
    {
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (Pixel p : ensemblesPoints) {
            int x = p.getX();
            int y = p.getY();
            int color = p.getColor();
            resultImage.setRGB(x, y, color);
        }
        return resultImage;
    }

    /**
     * Affiche l'objet seul à partir de ses points.
     * 
     * @param width
     * Largeur de l'image.
     *
     * @param height
     * Hauteur de l'image.
     *
     * @see Pixel#getX()
     * @see Pixel#getY()
     * @see Pixel#getColor()
     *
     */
    public void afficherObjet(int width, int height)
    {
        //Création d'une nouvelle image.
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (Pixel p : ensemblesPoints)
        {
            int x = p.getX();
            int y = p.getY();
            int color = p.getColor();
            resultImage.setRGB(x, y, color);
        }

        //Affichage de l'image.
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel(new ImageIcon(resultImage)));
        frame.pack();//Re dimension.
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Création de l'histogramme de l'objet, qui est sauvegardé dans l'ArrayList histogramme.
     *
     * @see Pixel#getAngles()
     * @see Objet#histogramme
     */
    public void creationHistogramme()
    {
        for (int i=0; i<361; i++)
        {
            histogramme.add(new ArrayList<Double>());
        }

        for(int i=0;i<ensemblesPoints.size();i++)
        {
            for(int j=0;j<ensemblesPoints.get(i).getAngles().size();j++)
            {
                int index = (int) Math.floor(ensemblesPoints.get(i).getAngles().get(j)) % 360;
                histogramme.get(index).add(ensemblesPoints.get(i).getAngles().get(j));
            }
        }
    }

    /**
     * Affichage de l'histogramme avec un pas qui est choisi en argument (De 1 à 360).
     * 
     * @param pas
     * Pas pour l'histogramme.
     * 
     * @see Objet#sauvegarde(String ,int) throws FileNotFoundException
     */
    public void affichageHistogramme(int pas)
    {
        System.out.println("angles(en degré)\n-");
        for (int i=0; i<361; i+=pas)
        {
            int n=histogramme.get(i).size();
            String nombreOccurence = "";
            for(int j=0;j<(n/1000);j++) //La compression par défaut serait 1000.
            {
                nombreOccurence+="|";
            }
            System.out.println(i+":"+(n!=0 ? "("+n+")" : "")+nombreOccurence);
        }
        System.out.println("-\nangles(en degré)");
    }

    /**
     * Renvoi l'histogramme sous forme de ArrayList.
     * 
     * @return histogramme.
     * 
     */
    //JUnit test.
    public ArrayList<ArrayList<Double>> getHistogramme(){
        return histogramme;
    }

    /**
     * Calcul le centre de gravité.
     *
     * @see Pixel#getX()
     * @see Pixel#getY()
     *
     */
    public void calculCentreGrav()
    {   
        int sommeX=0,sommeY=0;
        for(Pixel p : ensemblesPoints)
        {
            sommeX+=p.getX();
            sommeY+=p.getY();
        }
        this.centreGravite = new Pixel(Math.round(sommeX/ensemblesPoints.size()),Math.round(sommeY/ensemblesPoints.size()),color);
    }

    /**
     * Affichage du centre de gravité dans une image.
     * 
     * @param width
     * Largeur de l'image.
     * @param height
     * Hauteur de l'image.
     *
     * @see Pixel#getX()
     * @see Pixel#getY()
     *
     */
    public void afficherCentreGravite(int width, int height)
    {
        //Création d'une nouvelle image.
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int x = centreGravite.getX();
        int y = centreGravite.getY();
        int color = this.color;
        resultImage.setRGB(x, y, color);

        //Affichage de l'image.
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel(new ImageIcon(resultImage)));
        frame.pack();//Re dimension.
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Sauvegarde de l'histogramme ou de l'angle du barycentre dans un fichier Results.
     * Le choix de la sauvegarde se fait via le pas.
     * Un pas de 0 va faire une sauvegarde de l'angle du barycentre.
     * Un pas de 1 jusqu'à 360 va faire une sauvegarde de l'histogramme.
     *
     * @param nomImage
     * Nom de l'image.
     *
     * @param pas
     * Pas pour l'histogramme, 0 désigne un choix de la méthode du barycentre.
     *
     * @throws FileNotFoundException
     * Arrêt de l'exécution.
     *
     * @see Objet#affichageBarycentre()
     * @see Objet#affichageHistogramme(int)
     * 
     */
    public void sauvegarde(String nomImage,int pas) throws FileNotFoundException
    {
        //Nom du fichier.
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy-HH-mm");
        String dateTimeString = dateTime.format(formatter);
        String nomFichier=null;
        if(color==Couleurs.JAUNE)
        {
            nomFichier = "Results/" + nomImage + "_" + "Jaune" + "_" + dateTimeString + ".txt";
        }
        else
        {
            nomFichier = "Results/" + nomImage + "_" + "Vert" + "_" + dateTimeString + ".txt";
        }

        //Création du dossier Results s'il n'existe pas déjà.
        File directory = new File("Results");
        if (!directory.exists()) {directory.mkdir();}

        //Écriture sur le fichier.
        FileOutputStream outputStream = new FileOutputStream(nomFichier);
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream oldOut = System.out;

        if(pas==0)
        {
            System.setOut(printStream);
            affichageBarycentre();
        }else
        {
            System.setOut(printStream);
            affichageHistogramme(pas);
        }

        System.setOut(oldOut);
    }

    /**
     * Obtenir tous les angles du haut de l'histogramme.
     * 
     * @return total.
     * 
     * @see Objet#affichageRelatifHistogramme(String, String)
     *
     */
    public double getAngleBot() {
        double total=0;
        for(int x=0;x<180;x++) {
            total += histogramme.get(x).size();         
        }
        return total;
    }
    /**
     * Obtenir tous les angles du bas de l'histogramme.
     * 
     * @return total.
     * 
     * @see Objet#affichageRelatifHistogramme(String, String)
     */
    public double getAngleTop() {
        double total=0;
        for(int x=180;x<360;x++) {
            total += histogramme.get(x).size();         
        }
        return total;
    }
    /**
     * Obtenir tous les angles présents à droite de l'histogramme.
     * 
     * @return total.
     * 
     * @see Objet#affichageRelatifHistogramme(String, String)
     */
    public double getAngleRight() {
        double total=0;
        for(int x=90;x<270;x++) {
            total += histogramme.get(x).size();         
        }
        return total;
    }
    /**
     * Obtenir tous les angles présents à gauche de l'histogramme.
     * 
     * @return total.
     * 
     * @see Objet#affichageRelatifHistogramme(String, String)
     */
    public double getAngleLeft() {
        double total=0;
        for(int x=0;x<90;x++) {
            total += histogramme.get(x).size();         
        }
        for(int y=270;y<360;y++) {
            total += histogramme.get(y).size();
        }
        return total;
    }

    /**
     * Affichage complet de l'interprétation des résultats via la méthode des paires de points.
     * 
     * @param ref
     * Le nom de l'Objet référant.
     *
     * @param arg
     * Le nom de l'Objet argument.
     *
     * @see Objet#getAngleTop()
     * @see Objet#getAngleBot()
     * @see Objet#getAngleRight()
     * @see Objet#getAngleLeft()
     *
     */
    public void affichageRelatifHistogramme(String ref, String arg){
        StringBuffer droite = new StringBuffer();
        StringBuffer haut = new StringBuffer();
        StringBuffer gauche = new StringBuffer();
        StringBuffer bas = new StringBuffer();
        double total = getAngleBot()+getAngleTop()+getAngleRight()+getAngleLeft();
        
        //Implementation de l'échelle.
        
        //"Droite" mis dans un StringBuffer.
        if((getAngleRight()/total == 0)) {
            droite.append("Nullement à droite");
        }
        else if((getAngleRight()/total <=0.2)) {
            droite.append("Très peu à droite");
        }
        else if((getAngleRight()/total <= 0.4)) {
            droite.append("Peu à droite");
        }
        else if((getAngleRight()/total <= 0.6)){
            droite.append("Moyennement à droite");
        }
        else if((getAngleRight()/total <= 0.8)){
            droite.append("Beaucoup à droite");
        }
        else {
            droite.append("Totalement à droite");
        }
        
        //"Gauche" mis dans un StringBuffer.
        if((getAngleLeft()/total == 0)) {
            gauche.append("Nullement à gauche");
        }
        else if((getAngleLeft()/total <= 0.2)) {
            gauche.append("Très peu à gauche");
        }
        else if((getAngleLeft()/total <=  0.4)) {
            gauche.append("Peu à gauche");
        }
        else if((getAngleLeft()/total <=  0.6)) {
            gauche.append("Moyennement à gauche");
        }
        else if((getAngleLeft()/total <=  0.8)) {
            gauche.append("Beaucoup à gauche");
        }
        else {
            gauche.append("Totalement à gauche");
        }
        
        
        //"haut" mis dans un StringBuffer.
        if((getAngleTop()/total == 0)) {
            haut.append("Nullement en haut");
        }
        else if((getAngleTop()/total <= 0.2)) {
            haut.append("Très peu en haut");
        }
        else if((getAngleTop()/total <= 0.4)) {
            haut.append("Peu en haut");
        }
        else if((getAngleTop()/total <= 0.6)) {
            haut.append("Moyennement en haut");
        }
        else if((getAngleTop()/total <= 0.8)) {
            haut.append("Beaucoup en haut");
        }
        else {
            haut.append("Totalement en haut");
        }
        
        
        //"bas" mis dans un StringBuffer.
        if((getAngleBot()/total == 0)) {
            bas.append("Nullement en bas");
        }
        else if((getAngleBot()/total <= 0.2)) {
            bas.append("Très peu en bas");
        }
        else if((getAngleBot()/total <= 0.4)) {
            bas.append("Peu en bas");
        }
        else if((getAngleBot()/total <= 0.6)) {
            bas.append("Moyennement en bas");
        }
        else if((getAngleBot()/total <= 0.8)) {
            bas.append("Beaucoup en bas");
        }
        else {
            bas.append("Totalement en bas");
        }

        //Affichage.
        System.out.println("L'"+arg+" est : ");
        System.out.println();
        System.out.println(" * " + droite.toString()+" par rapport à l'"+ref+".");
        System.out.println(" * " + gauche.toString()+" par rapport à l'"+ref+".");
        System.out.println(" * "+ haut.toString()+" par rapport à l'"+ref+".");
        System.out.println(" * "+ bas.toString()+" par rapport à l'"+ref+".");
        System.out.println();
        System.out.println("Échelle : Nullement < Très peu < Peu < Moyennement < Beaucoup < Totalement");
        System.out.println();
    }

    /**
     * Affichage complet de l'interprétation des résultats via la méthode du Barycentre.
     *
     * @param ref
     * Le nom de l'Objet référant.
     *
     * @param arg
     * Le nom de l'Objet argument.
     * 
     */
    public void affichageRelatifBarycentre(String ref, String arg)
    {
        double angleR=(angleBarycentre+180)%360;
        if (angleR < 0) {angleR += 360;}
        
        //Le StringBuffer va permettre de stocker la phrase pour l'interprétation du résultat.
        StringBuffer sb = new StringBuffer("L'"+arg+" est ");
        
        if((0<angleR && angleR<180) && (90<angleR && angleR<270))
        {
            sb.append("en haut à gauche de l'"+ref+".");
        }
        if((0<angleR && angleR<180) && ((0<angleR && angleR<90) || (270<angleR && angleR<360)))
        {
            sb.append("en haut à droite de l'"+ref+".");
        }
        if((180<angleR && angleR<360) && (90<angleR && angleR<270))
        {
            sb.append("en bas à gauche de l'"+ref+".");
        }
        if((180<angleR && angleR<360) && ((0<angleR && angleR<90) || (270<angleR && angleR<360)))
        {
            sb.append("en bas à droite de l'"+ref+".");
        }
        
        String interpretation = sb.toString();//Conversion du StringBuffer en String pour l'affichage.
        
        System.out.println(interpretation);//Affichage.
        System.out.println();

    }
}