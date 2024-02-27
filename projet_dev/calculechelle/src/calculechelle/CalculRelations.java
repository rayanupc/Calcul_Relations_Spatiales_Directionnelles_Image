package calculechelle;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;
/**
 * Classe main.
 *
 * @see Couleurs
 * @see Objet
 * @see Pixel
 *
 * @version 1.0.0
 *
 */
public class CalculRelations {
    /**
     * Fonction main
     *
     * @param args
     * On peut avoir 3 arguments. <br>
     * Le chemin, puis le choix couleur et enfin le choix de la méthode. <br>
     * Ils ne sont utiles que pour le lancement en ligne de commande.
     */
    public static void main(String[] args)
    {
        //Tests pour le lancement en ligne de commande.
        //On vérifie si la méthode est appelée avec trois arguments.
        if (args.length==3)
        {
            try {
                //On vérifie si le premier argument est un .png.
                if (!args[0].endsWith(".png"))
                {
                    throw new Exception("Format de fichier incompatible.");
                }
                //On vérifie si le deuxième argument vaut 0 ou 1.
                if (Integer.parseInt(args[1]) != 0 && Integer.parseInt(args[1]) != 1)
                {
                    throw new Exception("Le choix doit être 0 ou 1.");
                }
                //On vérifie si le troisième argument est compris entre 0 et 360
                if (Integer.parseInt(args[2]) < 0 || Integer.parseInt(args[2]) > 360)
                {
                    throw new Exception("L'angle doit être compris entre 0 et 360 inclus.");
                }

            } catch (Exception e) {
                //On vérifie si une exception est levée. Si oui, affiche un message d'erreur et quitte l'application.
                System.out.println("Erreur : " + e.getMessage());
                System.exit(0);
            }
        }

        try {
            //Lecture de la photo via la sélection de l'utilisateur.
            BufferedImage image = null;
            String nomImage = null;

            //Version ligne de commande.
            if (args.length != 0)
            {
                File file = new File(args[0]);

                if (!file.exists() || file.isDirectory())
                {
                    System.out.println("Le fichier spécifié n'existe pas.");
                    System.exit(0);
                }

                //Lecture du fichier.
                try {
                    image = ImageIO.read(file);
                    String fileName = file.getName();
                    int dotIndex = fileName.lastIndexOf('.');
                    nomImage = (dotIndex > 0) ? fileName.substring(0, dotIndex) : fileName;
                } catch (IOException e) {
                    System.out.println("Erreur lors de la lecture du fichier.");
                    System.exit(0);
                }
            }
            else {
                //Version GUI.
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    //Lecture du fichier.(De type .png.)
                    boolean fichierInvalide = true;
                    while (fichierInvalide) {
                        try {                       	
                            image = ImageIO.read(file);
                            String fileName = file.getName();
                            if (fileName.endsWith(".png")) {  
                                int dotIndex = fileName.lastIndexOf('.');
                                nomImage = (dotIndex > 0) ? fileName.substring(0, dotIndex) : fileName; 
                                fichierInvalide = false;
                                // Vérification des couleurs des pixels de l'image
                                for (int x = 0; x < image.getWidth(); x++) {
                                    for (int y = 0; y < image.getHeight(); y++) {
                                        int couleur = image.getRGB(x, y);
                                        if (couleur != Couleurs.JAUNE && couleur != Couleurs.VERT && couleur != Couleurs.VIOLET) {
                                            System.out.println("Erreur : le fichier doit contenir uniquement des pixels de couleur jaune, vert ou violet.");
                                            System.exit(0);
                                        }
                                    }

                                }
                            } else {
                                System.out.println("Le fichier doit être un fichier PNG.");
                                returnValue = fileChooser.showOpenDialog(null);
                                if (returnValue == JFileChooser.APPROVE_OPTION) {
                                    file = fileChooser.getSelectedFile();
                                } else {
                                    System.out.println("Fichier inexistant.");
                                    System.exit(0);
                                }
                            }
                            
                        } catch (IOException e) {
                            System.out.println("Erreur lors de la lecture du fichier.");
                            System.exit(0);
                        }
                    }
                } else {
                    System.out.println("Fichier inexistant.");
                    System.exit(0);
                }
            }

            //Récupération des dimensions de la photo.
            int width = image.getWidth();
            int height = image.getHeight();
            Pixel[][] espaceV = new Pixel[width][height];//Enregistrement de la photo sous forme d'une matrice de points.
            int[] mainColors = new int[3];//Tableau des 3 différentes couleurs dans la photo.
            int colorIndex = 0;

            //Parcours toute l'image et stocker l'image dans une classe pixel qu'on sauvegarde dans notre matrice espaceV.
            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    int color = image.getRGB(x, y);//Sauvegarde la couleur(en int) du pixel.
                    espaceV[x][y] = new Pixel(x, y, color);//Sauvegarde le pixel dans le tableau.

                    //Création du tableau exhaustif de toutes les couleurs
                    //Vérifie si la couleur est dans mainColors.
                    boolean isInMainColors = false;
                    for (int i = 0; i < mainColors.length; i++)
                    {
                        if (mainColors[i] == color)//On arrête la boucle si la couleur est déjà présente.
                        {
                            isInMainColors = true;
                            break;
                        }
                    }
                    //On ajoute la couleur dans mainColors si la couleur n'est pas dedans.
                    if (!isInMainColors && colorIndex < mainColors.length) {mainColors[colorIndex++] = color;}

                }
            }

            //Creation des 2 tableaux pour l'objet A et B.
            Objet objetA = new Objet();
            objetA.addColor(Couleurs.JAUNE);

            Objet objetB = new Objet();
            objetB.addColor(Couleurs.VERT);

            for (int x = 0; x < espaceV.length; x++)
            {
                for (int y = 0; y < espaceV[0].length; y++)
                {

                    int couleur = espaceV[x][y].getColor();
                    Pixel pixel = espaceV[x][y];

                    if (couleur == Couleurs.JAUNE)
                    {
                        objetA.addPoint(pixel);
                    } else if (couleur == Couleurs.VERT)
                    {
                        objetB.addPoint(pixel);
                    }
                }
            }

            //Calcul de tous les angles de l'objet A et B.
            objetA.calculAngles(objetB);
            objetB.calculAngles(objetA);

            //Calcul des centres de gravité.
            objetA.calculCentreGrav();
            objetB.calculCentreGrav();

            //Affiche les 2 objets d'affilée.
            objetA.afficherObjet(width, height);
            objetB.afficherObjet(width, height);
            //Affiche les 2 couleurs
            System.out.println("1. Objet jaune");
            System.out.println("2. Objet vert");

            //Demande à l'utilisateur le choix entre l'objet jaune ou l'objet vert.
            boolean continuer = true;
            while(continuer) {
	            int choixCouleur;
	            if (args.length != 0) //Version ligne de commande.
	            {
	                choixCouleur = Integer.parseInt(args[1]);
	            }
	            else //Version GUI.
	            {
	                System.out.println("Choisissez un objet (1 ou 2) :");
	                //Le choix doit obligatoirement être 1 ou 2 pour la couleur : 1 = jaune, 2 = vert.
	                while (true) {
	                    Scanner sc1 = new Scanner(System.in);
	                    if (sc1.hasNextInt()) {
	                        choixCouleur = sc1.nextInt();
	                        if (choixCouleur == 1 || choixCouleur == 2) {
	                            break;
	                        }
	                    } else {
	                        sc1.next();
	                    }
	                    System.out.println("\n");
	                    System.out.println("Veuillez entrer un entier valide!\n");
	                    System.out.println("Choisissez un objet (1 ou 2) :");
	                }
	            }						
	            if(choixCouleur==1) {
	                System.out.println("Vous avez choisi l'Objet Jaune.");
	                System.out.println();
	            }
	            else{
	                System.out.println("Vous avez choisi l'Objet Vert.");
	                System.out.println();
	            }
	            					
	            //Demande à l'utilisateur le choix de méthode.
	            //L'entier entré doit obligatoirement être entre 0 et 360 pour le choix de la méthode : 0 = barycentre, [1,360] = paires de points.
	            int choixMethode;
	            if (args.length != 0) //Version ligne de commande.
	            {
	                choixMethode = Integer.parseInt(args[2]);
	            }
	            else //Version GUI.
	            {
	                Scanner sc2 = new Scanner(System.in);
	                System.out.println("Entrez 0 pour la méthode du barycentre, ou choisissez le Pas de l'histogramme pour la méthode des paires de points(Entre 1 et 360):");
	                //Le choix doit obligatoirement être 1 ou 2 pour la couleur : 1 = jaune, 2 = vert.
	                while (true) {
	                    if (sc2.hasNextInt()) {
	                    	choixMethode = sc2.nextInt();
	                        if (choixMethode == 0 || ((choixMethode < 361) && (choixMethode > 0))) {
	                            break;
	                        }
	                    } else {
	                        sc2.next();
	                    }
	                    System.out.println("\n");
	                    System.out.println("Veuillez entrer un entier valide! (0 ou un nombre entre 1 et 360.)\n");
	                }   
	            }
	
	            //Traitement du choix de l'utilisateur.
	            if (choixCouleur == 1) //Objet jaune.
	            {
	                //Création et sauvegarde de l'histogramme.
	                objetA.creationHistogramme();
	                //Traitement du barycentre.
	                objetA.addAngleBarycentre(objetA.retourCentreGravite().getAnglesBarycentre(objetB));
	                
	                if(choixMethode == 0) //Choix de la méthode du barycentre.
	                {
	                    String ref = new String("Objet Vert");
	                    String arg = new String("Objet Jaune");
	                    //Affichage du résultat dans le dossier Results.
	                    objetA.affichageRelatifBarycentre(ref,arg);                
	                }
	                else //Choix de la méthode des paires de points.
	                {
	                    String ref = new String("Objet Vert");
	                    String arg = new String("Objet Jaune");
	                    //Affichage du résultat dans le dossier Results.
	                    objetA.affichageRelatifHistogramme(ref,arg);
	                }
	                objetA.sauvegarde(nomImage,choixMethode);
	                System.out.println("Les résultats pour l'objet Jaune ont été sauvegardées dans Results/.");
	            } else //Objet vert.
	            {
	                //Création et sauvegarde de l'histogramme.
	                objetB.creationHistogramme();
	                //Traitement du barycentre.
	                objetB.addAngleBarycentre(objetB.retourCentreGravite().getAnglesBarycentre(objetA));
	
	                if(choixMethode == 0) //Choix de la méthode du barycentre.
	                {
	                    String ref = new String("Objet Jaune");
	                    String arg = new String("Objet Vert");
	                    //Affichage du résultat dans le dossier Results.
	                    objetB.affichageRelatifBarycentre(ref, arg);                
	                }
	                else //Choix de la méthode des paires de points.
	                {
	                    String ref = new String("Objet Jaune");
	                    String arg = new String("Objet Vert");
	                    //Affichage du résultat dans le dossier Results.
	                    objetB.affichageRelatifHistogramme(ref,arg);
	                }
	                
	                objetB.sauvegarde(nomImage,choixMethode);
	                System.out.println("Les résultats pour l'objet vert ont été sauvegardées dans Results/.");
	            }
	            //Demande à l'utilisateur s'il souhaite continuer ou quitter.
	            System.out.println();
	            System.out.println("Souhaitez-vous continuer ? Oui(O) ou Non(N) ?");
	            char choixContinuer;
	            while (true) {
	                Scanner sc3 = new Scanner(System.in);
	                String input = sc3.nextLine().trim();
	                if (input.length() != 1 || (input.charAt(0) != 'o' && input.charAt(0) != 'O' && input.charAt(0) != 'n' && input.charAt(0) != 'N')) {
	                    System.out.println("Veuillez entrer un caractère valide !");
	                    System.out.println("Souhaitez-vous continuer ? Oui(O) ou Non(N) ?");
	                    continue;
	                }
	                choixContinuer = input.charAt(0);
	                break;
	            }
	            if ((choixContinuer == 'n' || choixContinuer == 'N')) {
	                continuer = false;
	                System.out.println("\nMerci d'avoir utilisé notre application !");
	            }
            }
        } catch (Exception e) {e.printStackTrace();}
        System.exit(0);
    }
}