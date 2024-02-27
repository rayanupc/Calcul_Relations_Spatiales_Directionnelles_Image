package calculechelle;
import java.util.ArrayList;

/**
 * Classe Pixel représentant un point. Elle est utilisée pour les calculs d’angles et possède les informations concernant les angles qu'elle a avec un objet.
 * <p>
 *     Un Pixel est caractérisé par les informations suivantes :
 * <ul>
 *     <li>Les coordonnées x et y sont attribuées lors de leur création.</li>
 *     <li>La variable color sera attribuée lors de sa création.</li>
 *     <li>La variable angles est une liste d'angles destinées à être calculée avec l'ensemble de points d'un objet.</li>
 * </ul>
 *
 * <p>
 *     Cette classe sera en communication avec la classe Objet.
 * </p>
 *
 * @see Objet#addPoint(Pixel)
 * @see Objet#retourneEnsemblePoints()
 * @see Objet#retourCentreGraviteX()
 * @see Objet#retourCentreGraviteY()
 * @see Objet#retourAngleBarycentre()
 * @see Objet#setCentreGravite(Pixel)
 * @see Objet#affichageBarycentre()
 * @see Objet#toString()
 * @see Objet#calculAngles(Objet)
 * @see Objet#createObjetImage(int, int)
 * @see Objet#afficherObjet(int, int)
 * @see Objet#creationHistogramme()
 * @see Objet#calculCentreGrav()
 * @see Objet#afficherCentreGravite(int, int)
 *
 * @version 1.0
 *
 */
 class Pixel
{
    /**Les coordonnés x et y sont les coordonnées du pixel, qui nous sert de point.
     *
     * @see Pixel#getX()
     * @see Pixel#getY()
     * @see Objet#retourX(int)
     * @see Objet#retourY(int)
     * @see Objet#retourCentreGraviteX()
     * @see Objet#retourCentreGraviteY()
     * @see Objet#createObjetImage(int, int)
     * @see Objet#afficherObjet(int, int)
     * @see Objet#calculCentreGrav()
     * @see Objet#afficherCentreGravite(int, int)
     *
     */
  private int x, y;
    /**La variable color sert à initialiser la couleur qui désigne le point. Elle n'est pas destinée à être modifiée.
     *
     * @see Couleurs
     * @see Pixel#getColor()
     * @see Pixel#toString()
     * @see Objet#createObjetImage(int, int) 
     * @see Objet#afficherObjet(int, int) 
     * @see Objet#calculCentreGrav()
     * @see Objet#afficherCentreGravite(int, int)
     *
     */
  private int color;
    /**La liste des angles sert à sauvegarder les angles d'un point avec tous les points d'un objet.
     *
     * @see Pixel#getAngles()
     * @see Pixel#getAngles(Objet)
     * @see Pixel#toString()
     * @see Objet#calculAngles(Objet)
     * @see Objet#creationHistogramme()
     *
     */
  private ArrayList<Double> angles;

    /**
     * Constructeur de Point.
     * <p>
     *     Les paramètres sont forcément définis lors de leur création, le ArrayList angles est défini automatiquement.
     * </p>
     *
     * @param x
     * Une coordonnée x du point.
     * @param y
     * Une coordonnée y du point.
     * @param color
     * La couleur de l'Objet.
     *
     * @see Pixel#x
     * @see Pixel#y
     * @see Pixel#color
     * @see Pixel#angles
     */
  public Pixel(int x, int y, int color)
  {
      this.x = x;
      this.y = y;
      this.color = color;
      this.angles = new ArrayList<Double>();
  }

    /**
     * Pour retourner la valeur de x.
     *
     * @return La coordonnée x du point.
     * 
     * @see Objet#retourX(int) 
     * @see Objet#retourCentreGraviteX() 
     * @see Objet#createObjetImage(int, int) 
     * @see Objet#afficherObjet(int, int) 
     * @see Objet#calculCentreGrav()
     * @see Objet#afficherCentreGravite(int, int)
     * @see Pixel#x
     *
     */
  public int getX()
  {
      return x;
  }

    /**
     * Pour retourner la valeur de y.
     *
     * @return La coordonnée y du point.
     *
     * @see Objet#retourX(int)
     * @see Objet#retourCentreGraviteX()
     * @see Objet#createObjetImage(int, int)
     * @see Objet#afficherObjet(int, int)
     * @see Objet#calculCentreGrav()
     * @see Objet#afficherCentreGravite(int, int)
     * @see Pixel#y
     *
     */
  public int getY()
  {
      return y;
  }

    /**
     * Pour retourner les valeurs de la couleur.
     *
     * @return La coordonnée couleur du point.
     *
     * @see Objet#createObjetImage(int, int)
     * @see Objet#afficherObjet(int, int)
     * @see Pixel#color
     *
     */
  public int getColor()
  {
      return color;
  }

    /**
     * Retourne une liste des angles, où les angles sont comparés avec les angles d'un objet.
     *
     * @return La liste des angles.
     *
     * @see Objet#creationHistogramme()
     * @see Pixel#angles
     *
     */
  public ArrayList<Double> getAngles() {return angles;}

    /**
     * Calcul d'angle entre 1 point et tous les autres points de l'objet en argument. Ils seront par la suite ajoutés à la liste.
     *
     * @param o
     * Objet qui sera utilisé pour calculer les angles du point avec celui-ci.
     *
     * @see Objet#calculAngles(Objet)
     * @see Objet#retourX(int)
     * @see Objet#retourY(int)
     * @see Pixel#angles
     *
     */
  public void getAngles(Objet o)
  {
      for (int i = 0; i < o.tailleEnsemblePoints(); i++)
      {
              double xDiff = o.retourX(i) - this.x;
              double yDiff = (o.retourY(i)*(-1)) - (this.y*(-1)); //On rectifie les coordonnées.
              //Calcul de l'angle entre deux points et conversion du résultat en degrés.
              double angle = (Math.atan2(yDiff, xDiff))*(180/Math.PI); 
              if (angle < 0) {angle += 360;}
              angles.add(angle);
      }
  }

    /**
     * Permet d'avoir l'angle du barycentre du pixel avec un autre Objet via le calcul de leur centre de gravité.
     *
     * @param o
     * Objet qui sera utilisé pour calculer l'angle du barycentre via le centre de gravité des 2 Objets.
     *
     * @return un angle calculé par le calcul du barycentre.
     *
     * @see Objet#retourCentreGraviteX()
     * @see Objet#retourCentreGraviteY()
     * @see Pixel#angles
     *
     */
  public double getAnglesBarycentre(Objet o)
  {
      double xDiff = o.retourCentreGraviteX() - this.x;
      double yDiff = (o.retourCentreGraviteY()*(-1)) - (this.y*(-1)); //On rectifie les coordonnées.
      double angle = (Math.atan2(yDiff, xDiff))*(180/Math.PI);
      if (angle < 0) {angle += 360;}
      return Math.round(angle * 100.0) / 100.0; //Avoir 2 chiffres après la virgule grâce à *100.0/100.0.
      
  }

    /**
     * Affiche tous les angles du point.
     *
     * @see Pixel#angles
     *
     */
  public void afficheAngles()
  {
      for(int i=0;i<angles.size();i++)
      {
          System.out.println("L'angle "+(i+1)+" est "+ angles.get(i));
      }
  }

    /**
     * Retourne les informations du point.
     * @return Les coordonnées et la couleur du point.
     *
     * @see Pixel#x
     * @see Pixel#y
     * @see Pixel#color
     */
  public String toString(){return "Pixel ("+x+","+y+") "+color;}

}