package tests;

import junit.framework.TestCase;
import logica.Elemento;
import logica.fruta.*;
import logica.fruta.Limon;
import logica.fruta.Sandia;
import logica.nivel.Nivel;
import logica.nivel.Mapa;
import logica.nivel.Mapa1;
import logica.obstaculo.*;
import logica.personaje.*;
import org.junit.Test;

import java.util.ArrayList;

public class testsJuegoBadIceCream extends TestCase {
    private static final Elemento X = new BloqueEstatico();
    private static final Elemento o = null;
    private static final Elemento H = new Hielo();

    @Test
    public void testMoverPersonajeAlEnivarUnaDireccion() {
        Chocolate helado1 = new Chocolate();
        helado1.desplazarse(1); // Desplazarse una posición hacia la derecha
        int XPosition = helado1.getCoordenadaX();
        assertEquals(2, XPosition); //  El helado empieza en x = 1 y = 1,
        // al desplazarse hacia la derecha ahora x debe ser 2.
    }

    @Test
    public void testVerificarLaExistenciaDeUnBloque() {
        Personaje personaje = new Chocolate();
        Mapa mapa1 = new Mapa1();
        assertTrue(mapa1.existeUnBloque(0, 0));
        assertFalse(mapa1.existeUnBloque(1, 1));
        assertFalse(mapa1.existeUnBloque(3, 4));
    }

    @Test
    public void testColisionConEnemigoYMorir() {
        Chocolate helado1 = new Chocolate();
        Monstruo monstruo = new Vaca(1, 1);
        Mapa mapa1 = new Mapa1();

        // Cuando el monstruo y el helado se encuentran en la misma posición el helado muere.
        assertTrue(mapa1.monstruoMataAHelado(helado1, monstruo));
    }

    @Test
    public void testRecogerFrutaYSumarPuntaje() {
        Chocolate helado1 = new Chocolate();
        Mapa mapaTest = new Mapa(
                new Elemento[][]{
                        {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
                        {X, o, o, o, o, o, o, o, o, o, X, o, o, o, o, X},
                        {X, o, o, o, o, o, o, o, o, o, X, o, o, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, X, o, X, o, o, X},
                        {X, o, o, o, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, o, o, o, o, o, o, o, o, o, o, o, o, X},
                        {X, o, o, o, o, o, X, o, o, o, o, o, o, o, o, X},
                        {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X}
                }, new ArrayList<Monstruo>() {
        },
                new ArrayList<Fruta>() {{
                    add(new Sandia(2, 1));
                    add(new Sandia(7, 7));
                    add(new Sandia(3, 1));

                }});
        Nivel nivelTest = new Nivel("test", mapaTest, helado1);
        nivelTest.actualizarNivel();
        nivelTest.actualizarJugador(1);
        nivelTest.actualizarNivel();
        nivelTest.actualizarJugador(1);
        nivelTest.actualizarNivel();
        nivelTest.actualizarJugador(1);

        // Como el jugador ha recogido 2 sandias (pasando por encima de ellas) y el puntaje de cada sandía es 10,
        // el puntaje total del nivel hasta ese punto debe ser igual a 20.

        assertEquals(20, nivelTest.getPuntaje());
    }

    @Test
    public void testGanarJuegoAlRecogerTodasLasFrutas() {
        Chocolate helado1 = new Chocolate();
        Mapa mapaTest = new Mapa(
                new Elemento[][]{
                        {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
                        {X, o, o, o, o, o, o, o, o, o, X, o, o, o, o, X},
                        {X, o, o, o, o, o, o, o, o, o, X, o, o, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, X, o, X, o, o, X},
                        {X, o, o, o, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, o, o, o, o, o, o, o, o, o, o, o, o, X},
                        {X, o, o, o, o, o, X, o, o, o, o, o, o, o, o, X},
                        {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X}
                }, new ArrayList<Monstruo>() {
        },
                new ArrayList<Fruta>() {{
                    add(new Sandia(2, 1));
                    add(new Limon(3, 1));
                }});
        Nivel nivelTest = new Nivel("test", mapaTest, helado1);
        nivelTest.actualizarNivel();
        nivelTest.actualizarJugador(1);
        nivelTest.actualizarNivel();
        nivelTest.actualizarJugador(1);
        nivelTest.actualizarNivel();
        nivelTest.actualizarJugador(1);
        nivelTest.actualizarNivel();

        // Como el jugador ha pasado por todas las frutas y ya no quedan frutas en el mapa,
        // el juego debe finalizar.

        assertTrue(nivelTest.elJuegoSeGano());

    }

    @Test
    public void testTerminarJuegoCuandoMuere() {
        Chocolate helado1 = new Chocolate();
        Mapa mapaTest = new Mapa(
                new Elemento[][]{
                        {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
                        {X, o, o, o, o, o, o, o, o, o, X, o, o, o, o, X},
                        {X, o, o, o, o, o, o, o, o, o, X, o, o, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, X, o, X, o, o, X},
                        {X, o, o, o, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, o, o, o, o, o, o, o, o, o, o, o, o, X},
                        {X, o, o, o, o, o, X, o, o, o, o, o, o, o, o, X},
                        {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X}
                }, new ArrayList<Monstruo>() {{
            add(new Vaca(1, 1));
        }},
                new ArrayList<Fruta>() {{
                    add(new Limon(7, 7));
                }});

        Nivel nivelTest = new Nivel("test", mapaTest, helado1);

        nivelTest.actualizarNivel();
        nivelTest.actualizarJugador(1);
        nivelTest.actualizarNivel();

        // Como el jugador empieza en la posición (1,1) y a la vaca se le pone en la misma posición de inicio,
        // el juego debe terminarse.

        assertTrue(nivelTest.seHaFinalizadoElJuego());
    }


    @Test
    public void testPonerHielo() {
        Chocolate helado1 = new Chocolate();
        Mapa mapaTest = new Mapa(
                new Elemento[][]{
                        {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
                        {X, o, o, o, o, o, o, o, o, o, X, o, o, o, o, X},
                        {X, o, o, o, o, o, o, o, o, o, X, o, o, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, X, o, X, o, o, X},
                        {X, o, o, o, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, o, o, o, o, o, o, o, o, o, o, o, o, o, o, X},
                        {X, o, o, o, o, o, X, o, o, o, o, o, o, o, o, X},
                        {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X}
                }, new ArrayList<Monstruo>() {{
        }},
                new ArrayList<Fruta>() {{
                    add(new Limon(7, 7));
                }});

        Nivel nivelTest = new Nivel("test", mapaTest, helado1);

        nivelTest.actualizarNivel();
        nivelTest.actualizarJugador(4); // Con la acción 4 se pone o rompe hielo según las condiciones
        nivelTest.actualizarNivel();

        // La posición x = 1 y = 7 está vacía, al enviar la acción 4 debe
        // colocarse un hielo en su lugar.

        assertTrue(mapaTest.hayHielo(1,7));
    }

    @Test
    public void testRomperHielo() {
        Chocolate helado1 = new Chocolate();
        Mapa mapaTest = new Mapa(
                new Elemento[][]{
                        {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
                        {X, o, o, o, o, o, o, o, o, o, X, o, o, o, o, X},
                        {X, H, o, o, o, o, o, o, o, o, X, o, o, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, X, o, X, o, o, X},
                        {X, H, o, o, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, o, o, o, o, o, o, o, o, o, o, o, o, X},
                        {X, H, o, o, o, o, X, o, o, o, o, o, o, o, o, X},
                        {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X}
                }, new ArrayList<Monstruo>() {{
            add(new Vaca(1, 1));
        }},
                new ArrayList<Fruta>() {{
                    add(new Limon(7, 7));
                }});

        Nivel nivelTest = new Nivel("test", mapaTest, helado1);

        nivelTest.actualizarNivel();
        nivelTest.actualizarJugador(4);   // Con la acción 4 se pone o rompe hielo según las condiciones
        nivelTest.actualizarNivel();

        // En la posición x = 1 y = 7 hay un hielo, al enviar la acción 4 esta
        // debe vaciarse.

        assertTrue(mapaTest.laPosicionEstaVacia(1,7));
    }

    @Test
    public void testImpedirElMovimientoDelHeladoAPosiciónConBloque() {
        Chocolate helado1 = new Chocolate();
        Mapa mapaTest = new Mapa(
                new Elemento[][]{
                        {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
                        {X, o, X, o, o, o, o, o, o, o, X, o, o, o, o, X},
                        {X, H, o, o, o, o, o, o, o, o, X, o, o, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, X, o, X, o, o, X},
                        {X, H, o, o, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, o, o, o, o, o, o, o, o, o, o, o, o, X},
                        {X, H, o, o, o, o, X, o, o, o, o, o, o, o, o, X},
                        {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X}
                }, new ArrayList<Monstruo>() {{
        }},
                new ArrayList<Fruta>() {{
                    add(new Limon(7, 7));
                }});

        Nivel nivelTest = new Nivel("test", mapaTest, helado1);

        nivelTest.actualizarNivel();
        nivelTest.actualizarJugador(1);
        nivelTest.actualizarNivel();

        // A pesar de que se indique mover el helado una posición a la izquierda,
        // este debe quedarse en su misma posición debido a que hay un bloque.

        assertEquals(1,helado1.getCoordenadaX());
    }



    @Test
    public void testMonstruoCambiaDeDireccionCuandoSeTopaConUnBloque() {
        Chocolate helado1 = new Chocolate();
        Mapa mapaTest = new Mapa(
                new Elemento[][]{
                        {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X},
                        {X, o, X, o, o, o, o, o, o, o, X, o, o, o, o, X},
                        {X, H, o, o, o, o, o, o, o, o, X, o, o, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, X, o, X, o, o, X},
                        {X, H, o, o, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, X, o, o, o, o, o, o, o, o, X, o, o, X},
                        {X, H, o, o, o, o, o, o, o, o, o, o, o, o, o, X},
                        {X, H, o, o, o, o, X, o, o, o, o, o, o, o, o, X},
                        {X, X, X, X, X, X, X, X, X, X, X, X, X, X, X, X}
                }, new ArrayList<Monstruo>() {{
            add(new Vaca(14, 13));
        }},
                new ArrayList<Fruta>() {{
                    add(new Limon(7, 7));
                }});

        Nivel nivelTest = new Nivel("test", mapaTest, helado1);

        nivelTest.actualizarNivel();
        nivelTest.actualizarNivel();
        nivelTest.actualizarNivel();
        nivelTest.actualizarNivel();

        // El monstruo empieza moviendose hacia abajo,
        // cuando encuentra un bloque solo cambia de dirección (la dir. de la vaca cambia en sentido horario),
        // en cada actualización se mueve una posición. Como estaba iendo hacia abajo y encontró un bloque,
        // ahora se mueve hacia la izquierda.

        assertTrue(mapaTest.hayMonstruo(13,14));
    }
}
