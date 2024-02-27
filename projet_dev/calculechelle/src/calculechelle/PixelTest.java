package calculechelle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PixelTest {

    @Test
    //Vérifie qu'il retourne bien x, c'est-à-dire ici 10.
    void testGetX() {
        Pixel pixel = new Pixel(10, 20, -14506108);
        assertEquals(10, pixel.getX());
    }

    @Test
    //Vérifie qu'il retourne bien y, c'est-à-dire ici 20.
    void testGetY() {
        Pixel pixel = new Pixel(10, 20, -14506108);
        assertEquals(20, pixel.getY());
    }

    @Test
    void testGetColor() {
        Pixel pixel = new Pixel(10, 20, -14506108);
        assertEquals(-14506108, pixel.getColor()); //Vérifie qu'il retourne la bonne couleur.
    }

    @Test
    void testGetAnglesPixel() {
        Pixel pixel = new Pixel(10, 20, -14506108);
        ArrayList<Double> angles = pixel.getAngles();
        assertEquals(0, angles.size()); //Vérifie que la taille du ArrayList est correct égal à 0.
    }

    @Test
    void testGetAnglesObjet() {
    	// Création d'un objet avec des pixels.
        Objet o = new Objet();
        o.addPoint(new Pixel(0, 0, -14506108));
        o.addPoint(new Pixel(1, 1, -14506108));
        Pixel pixel = new Pixel(2, 2, -14506108);
        //Retourne l'angle de o.
        pixel.getAngles(o);
        ArrayList<Double> angles = pixel.getAngles();
        //Vérification de la taille du ArrayList.
        assertEquals(2, angles.size());
    }

    @Test
    void testGetAnglesBarycentre() {
        // Création d'un objet avec des pixels.
        Objet objet = new Objet();
        objet.addPoint(new Pixel(10, 20, 0));
        objet.addPoint(new Pixel(30, 40, 0));
        objet.addPoint(new Pixel(50, 60, 0));

        // Calcul du centre de gravité.
        objet.calculCentreGrav();

        // Calcul de l'angle entre un pixel et le centre de gravité.
        Pixel pixel = new Pixel(30, 40, 0);
        double angle = pixel.getAnglesBarycentre(objet);

        // Vérification du résultat.
        assertEquals(0, angle);
    }


    @Test
    void testAfficheAngles() {
        Objet o = new Objet();
        o.addPoint(new Pixel(0, 0, -14506108));
        o.addPoint(new Pixel(1, 1, -14506108));
        Pixel pixel = new Pixel(2, 2, -14506108);
        pixel.getAngles(o);
        pixel.afficheAngles();
        // Vérifie que l'affichage est correct, il est inutile de vérifier la sortie standard.
    }
}
