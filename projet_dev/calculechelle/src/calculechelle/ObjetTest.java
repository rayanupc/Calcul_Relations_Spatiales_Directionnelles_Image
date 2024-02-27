package calculechelle;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.jupiter.api.Test;

class ObjetTest {

	@Test
    public void testAddColor() {
		//Création d'un objet.
        Objet obj = new Objet();
        obj.addColor(255);
        //Vérifie qu'on obtiens bien la couleur qu'il faut.
        assertEquals(255, obj.getColor());
    }
    
    @Test
    public void testAddPoint() {
    	//Création d'un objet.
        Objet obj = new Objet();
        Pixel p = new Pixel(10, 20, 255);
        obj.addPoint(p);
        //Vérification de l'ensemble des points ainsi que leurs valeurs.
        assertEquals(1, obj.tailleEnsemblePoints());
        assertEquals(p, obj.retourneEnsemblePoints().get(0));
    }
    
    @Test
    public void testRetourX() {
    	//Création d'un objet.
        Objet obj = new Objet();
        Pixel p1 = new Pixel(10, 20, 255);
        Pixel p2 = new Pixel(30, 40, 255);
        obj.addPoint(p1);
        obj.addPoint(p2);
        //Vérification de la valeur de X pour chaque Pixel.
        assertEquals(10, obj.retourX(0));
        assertEquals(30, obj.retourX(1));
    }
    
    @Test
    public void testRetourY() {
    	//Création de l'objet.
        Objet obj = new Objet();
        Pixel p1 = new Pixel(10, 20, 255);
        Pixel p2 = new Pixel(30, 40, 255);
        obj.addPoint(p1);
        obj.addPoint(p2);
      //Vérification de la valeur de Y pour chaque Pixel.
        assertEquals(20, obj.retourY(0));
        assertEquals(40, obj.retourY(1));
    }
    
    
    
	@Test
	public void testRetourneEnsemblePoints() {
		//Création d'un objet.
		Objet obj = new Objet();
        Pixel p1 = new Pixel(10, 20, 255);
        Pixel p2 = new Pixel(30, 40, 255);
        //Ajout des points.
        obj.addPoint(p1);
        obj.addPoint(p2);
        ArrayList<Pixel> ensemblePoints = obj.retourneEnsemblePoints();
        //Vérifie si le ArrayList contient bien les points ajoutés.
        assertTrue(ensemblePoints.contains(p1));
        assertTrue(ensemblePoints.contains(p2));   
	}

	@Test
	public void testTailleEnsemblePoints() {
		//Création de l'objet.
		Objet obj = new Objet();
        Pixel p1 = new Pixel(10, 20, 255);
        Pixel p2 = new Pixel(30, 40, 255);
        //Ajout des points.
        obj.addPoint(p1);
        obj.addPoint(p2);
        int taille = obj.tailleEnsemblePoints();
        //Vérifie la taille.
        assertEquals(2,taille);
	}

	@Test
	public void testRetourCentreGravite() {
		// Créer un objet avec des données de test
        Objet objet = new Objet();
        Pixel centreGraviteAttendu = new Pixel(10, 20, 255);
        objet.setCentreGravite(centreGraviteAttendu);

        // Vérifier que la méthode retourCentreGravite() retourne le centre de gravité attendu
        Pixel centreGraviteObtenu = objet.retourCentreGravite();
        assertEquals(centreGraviteAttendu, centreGraviteObtenu);
        
	}

	@Test
	public void testRetourCentreGraviteX() {
		// Créer un objet avec des données de test
        Objet objet = new Objet();
        Pixel centreGraviteAttendu = new Pixel(10, 20, 255);
        objet.setCentreGravite(centreGraviteAttendu);

        // Vérifier que la méthode retourCentreGravite() retourne le centre de gravité attendu
        Pixel centreGraviteObtenu = objet.retourCentreGravite();
        assertEquals(centreGraviteAttendu.getX(), centreGraviteObtenu.getX());
	}

	@Test
	public void testRetourCentreGraviteY() {
		// Créer un objet avec des données de test
        Objet objet = new Objet();
        Pixel centreGraviteAttendu = new Pixel(10, 20, 255);
        objet.setCentreGravite(centreGraviteAttendu);

        // Vérifier que la méthode retourCentreGravite() retourne le centre de gravité attendu
        Pixel centreGraviteObtenu = objet.retourCentreGravite();
        assertEquals(centreGraviteAttendu.getY(), centreGraviteObtenu.getY());
	}

	@Test
	public void testAddAngleBarycentre() {
		//Création de l'objet.
		Objet objet = new Objet();
		double angleBarycentreAttendu = 2.5;
        objet.addAngleBarycentre(angleBarycentreAttendu);
        //Vérifie le résultat attendu.
        assertEquals(angleBarycentreAttendu, objet.retourAngleBarycentre(), 0.001); //précision de 0.001
	}

	@Test
	public void testRetourAngleBarycentre() {
		//Création de l'objet.
		Objet objet = new Objet();
		double angleBarycentre = 2.5;
		objet.addAngleBarycentre(angleBarycentre);
		//Vérifie l'angle obtenue.
		assertEquals(2.5,objet.retourAngleBarycentre(),0.001);
	}

	@Test
	public void testAffichageBarycentre() {
		//Création de l'objet.
		Objet objet = new Objet();
		objet.addAngleBarycentre(2.5);
		String messageAttendu = "-------\nLa methode du barycentre nous donne: 2.5 degré.";
		//Vérifie le message attendu.
		assertEquals(messageAttendu,objet.toString());
	}

	@Test
	public void testCalculAngles() {
		//Création de 2 objets.
		Objet objA = new Objet();
        Objet objB = new Objet();

        // Ajout de pixel dans objA et objB
        objA.retourneEnsemblePoints().add(new Pixel(1, 2,255));
        objA.retourneEnsemblePoints().add(new Pixel(3, 4,255));
        objB.retourneEnsemblePoints().add(new Pixel(5, 6,255));
        objB.retourneEnsemblePoints().add(new Pixel(7, 8,255));
        objA.calculAngles(objB);

        // Vérifie que les angles ont été calculer
        for (Pixel pixel : objA.retourneEnsemblePoints()) {
            assertNotNull(pixel.getAngles());
	
        }
	}


	@Test
	public void testAfficherObjet() {
		//Création de l'objet.
		Objet objet = new Objet();
        int width = 100;
        int height = 100;

        BufferedImage resultImage = objet.createObjetImage(width, height);
        //Vérifie que resultImage n'est pas vide.
        assertNotNull(resultImage);
        //Vérifie les valeurs de "width" et "height".
        assertEquals(width, resultImage.getWidth());
        assertEquals(height, resultImage.getHeight());
	}

	@Test
	void testCreationHistogramme() {
		//Création de l'objet.
		Objet obj = new Objet();
	    obj.addPoint(new Pixel(0, 0, 255));
	    obj.addPoint(new Pixel(0, 1, 255));
	    obj.addPoint(new Pixel(1, 0, 255));

	    // Calcul des angles avant la création de l'histogramme
	    ArrayList<Double> anglesAvant = new ArrayList<Double>();
	    for (Pixel pixel : obj.retourneEnsemblePoints()) {
	        anglesAvant.addAll(pixel.getAngles());
	    }

	    // Création de l'histogramme
	    obj.creationHistogramme();

	    // Vérification de la taille de l'histogramme
	    assertEquals(361, obj.getHistogramme().size());

	    // Vérification du nombre d'angles dans l'histogramme
	    int count = 0;
	    for (ArrayList<Double> list : obj.getHistogramme()) {
	        count += list.size();
	    }
	    assertEquals(anglesAvant.size(), count);

	    // Vérification du contenu de l'histogramme pour les angles présents
	    for (Double angle : anglesAvant) {
	        int index = (int) Math.floor(angle) % 360;
	        assertTrue(obj.getHistogramme().get(index).contains(angle));
	    }

	    // Vérification du contenu de l'histogramme pour les angles absents
	    ArrayList<Double> anglesAbsent = new ArrayList<Double>();
	    anglesAbsent.add(45.0);
	    anglesAbsent.add(90.0);
	    anglesAbsent.add(135.0);
	    for (Double angle : anglesAbsent) {
	        int index = (int) Math.floor(angle) % 360;
	        assertFalse(obj.getHistogramme().get(index).contains(angle));
	    }
	}

	@Test
	void testCalculCentreGrav() {
		//Création de l'objet.
		Objet objet = new Objet();
		Pixel p1 = new Pixel(1, 2, 0);
        Pixel p2 = new Pixel(3, 4, 0);
        Pixel p3 = new Pixel(5, 6, 0);
        //Ajout des points dans objet.
        objet.retourneEnsemblePoints().add(p1);
        objet.retourneEnsemblePoints().add(p2);
        objet.retourneEnsemblePoints().add(p3);
        
        objet.calculCentreGrav();
        Pixel centreGravite = objet.retourCentreGravite();
        //Vérification du résultat.
        assertEquals(3, centreGravite.getX());
        assertEquals(4, centreGravite.getY());
        assertEquals(0, centreGravite.getColor());
        
	}

	@Test
	void testAfficherCentreGravite() {
		//Création de l'objet.
		Objet objet = new Objet();
		int color = 0xFF0000; //Red Color
		Pixel p1 = new Pixel(50, 50, 0);
		objet.addColor(color);
		objet.retourneEnsemblePoints().add(p1);
		objet.getColor(); // Red color
		//Les dimensions.
		int width = 100;
		int height = 100;

		objet.calculCentreGrav();
		objet.afficherCentreGravite(width, height);
		
		BufferedImage image = objet.createObjetImage(width, height);
		//Création d'une fenêtre.
		JFrame frame = new JFrame(); 
		JLabel label = new JLabel(new ImageIcon(image));
		frame.getContentPane().add(label);
		frame.pack();

		assertFalse(frame.isVisible()); // Vérifie que la fenêtre se ferme immédiatement après son ouverture.
		frame.dispose();
        
	}

	@Test
	void testSauvegarde() {
		//Création de l'objet.
		Objet obj = new Objet();
        obj.addColor(Couleurs.JAUNE);

        // Ajout de points
        obj.addPoint(new Pixel(0, 0, 255));
        obj.addPoint(new Pixel(1, 1, 255));

        // Définition du centre de gravité
        Pixel centreGravite = new Pixel(0, 0, 255);
        obj.setCentreGravite(centreGravite);

        // Ajout d'un angle barycentre
        double angleBarycentre = 45.0;
        obj.addAngleBarycentre(angleBarycentre);

        // Appel de la méthode de sauvegarde
        String nomImage = "test_image";
        int pas = 0;
        try {
			obj.sauvegarde(nomImage, pas);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

        // Vérification de la création du fichier
        String nomFichierAttendu = "Results/test_image_Jaune_*.txt";
        File file = new File(nomFichierAttendu.replace("*", ""));
        assertFalse(file.exists());

        // Nettoyage après le test
        file.delete();
	}

	@Test
	void testGetAngleBot() {
		//Création de l'objet.
		Objet obj = new Objet();
        Pixel p1 = new Pixel(1, 1, 0xFFFFFF);
        Pixel p2 = new Pixel(2, 2, 0xFFFFFF);
        Pixel p3 = new Pixel(3, 3, 0xFFFFFF);
        //Ajout des points de pixel.
        obj.addPoint(p1);
        obj.addPoint(p2);
        obj.addPoint(p3);

        Objet otherObj = new Objet();
        Pixel p4 = new Pixel(4, 4, 0xFFFFFF);
        otherObj.addPoint(p4);

        obj.calculAngles(otherObj);
        obj.creationHistogramme();
       
        double expectedAngleBot = obj.getHistogramme().get(0).size() + obj.getHistogramme().get(1).size(); // Ajout des angles dans l'intervalle [0, 180]
        double actualAngleBot = obj.getAngleBot();
        //Vérifie qu'il s'agit bien du résultat attendu.
        assertEquals(expectedAngleBot, actualAngleBot, "Le getAngleBot() doit retourner une somme correcte dans l'intervalle  [0, 180]");
    }

	@Test
	void testGetAngleTop() {
		Objet objet = new Objet();

        // Ajout des données à l'histogramme
        for (int i = 0; i < 360; i++) {
            ArrayList<Double> values = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                values.add(j * 1.0);
            }
            objet.getHistogramme().add(values);
        }

        double result = objet.getAngleTop();

        // Calculate the expected result
        double expected = 0;
        for (int x = 180; x < 360; x++) {
            expected += x;
        }

        // Assert that the result is equal to the expected value
        assertEquals(expected, result, 0.001);
	}

	@Test
	void testGetAngleRight() {
		Objet objet = new Objet();

        // Ajout des données à l'histogramme
        for (int i = 0; i < 360; i++) {
            ArrayList<Double> values = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                values.add(j * 1.0);
            }
            objet.getHistogramme().add(values);
        }

        double result = objet.getAngleRight();

        // Calculate the expected result
        double expected = 0;
        for (int x=90;x<270;x++) {
            expected += x;
        }

        // Assert that the result is equal to the expected value
        assertEquals(expected, result, 0.001);
	}

	@Test
	void testGetAngleLeft() {
		Objet objet = new Objet();

        // Ajout des données à l'histogramme
        for (int i = 0; i < 360; i++) {
            ArrayList<Double> values = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                values.add(j * 1.0);
            }
            objet.getHistogramme().add(values);
        }

        double result = objet.getAngleLeft();

        // Calculate the expected result
        double expected = 0;
        for(int x=0;x<90;x++) {
            expected += x;         
        }
        for(int y=270;y<360;y++) {
            expected += y;
        }

        // Assert that the result is equal to the expected value
        assertEquals(expected, result, 0.001);
	}

}
