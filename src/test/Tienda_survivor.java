/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import static test.Assets.tienda_ninja;

/**
 *
 * @author Rodrigo
 */
public class Tienda_survivor extends Item {

    private Game game;
    private Player player;
    private Assets assets;
    private int piso;
    private boolean moverTiendaDerecha = false;
    private boolean moverTiendaIzquierda = false;
    private boolean moverTiendaArriba = false;
    private boolean moverTiendaAbajo = false;
    private int objetoSeleccionado = 0;
    private double xSelect;
    private double ySelect;
    private ArrayList<ObjetoTienda> productos;
    private boolean comprarObjeto = false;
    boolean empezarMensaje = false;
    private boolean confirmarCompra = true;
    private int contTicks = 0;
    private boolean validarCompra = true;
    private boolean estaTienda = false;
    private boolean menuTienda = true;
    private boolean validarB = true;
    private boolean validarA = false;
    private boolean fueraTienda = true;
    private int xSelectMensaje;
    private boolean comprarOmejorar = true;
    private boolean mensajeNivel = false;
    private boolean mensajeErrorCompra=false;
    private boolean noTienesOpciones=false;
    private boolean confirmarSwat=false;
    private boolean confirmarNinja=false;
    private DecimalFormat tv;
    private boolean posicionarObjeto=false;
    private boolean posicionSelect;
    private int swatOninja=-1;
    private boolean mensajeCongelado=false;
    private boolean desplegarSelectCongelado=false;
    private int xSelectCongelado;
    private int ySelectCongelado;
    private boolean noMejorar=false;
     SoundClipTest sound;
    

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param piso
     * @param game
     */
    public Tienda_survivor(int x, int y, int width, int height, int piso, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.piso = piso;
        init();
    }

    public void init() {
        productos = new ArrayList<>();

        productos.add(new ObjetoTienda((int) (game.getWidth() * .371), (int) (game.getHeight() * .228), (int) (game.getWidth() * .06), (int) (game.getHeight() * .075), 0, true, Assets.tienda_swat, 300, "Swat", 1, 50, 5, 5, 5, game));
        productos.add(new ObjetoTienda((int) (game.getWidth() * .491), (int) (game.getHeight() * .228), (int) (game.getWidth() * .06), (int) (game.getHeight() * .075), 1, true, Assets.tienda_ninja, 400, "Ninja", 1, 50, 5, 5, 5, game));
        productos.add(new ObjetoTienda((int) (game.getWidth() * .606), (int) (game.getHeight() * .228), (int) (game.getWidth() * .06), (int) (game.getHeight() * .075), 2, true, Assets.tienda_hada, 800, "Hada", 1, 50, 5, 5, 5, game));
        productos.add(new ObjetoTienda((int) (game.getWidth() * .725), (int) (game.getHeight() * .228), (int) (game.getWidth() * .06), (int) (game.getHeight() * .075), 3, true, Assets.tienda_dron, 100, "Drone", 1, 50, 5, 5, 5, game));
        

        productos.get(0).setRango(1);
        productos.get(0).setVelDisparo(100);
        productos.get(0).setVida(100);
        productos.get(0).setSubirRango(1);
        productos.get(0).setSubirVelDisparo(10);
        productos.get(0).setSubirVida(40);
        

        productos.get(1).setVelAtaque(150);
        productos.get(1).setVida(100);
        productos.get(1).setVelMover(1);
        productos.get(1).setSubirVelAtaque(20);
        productos.get(1).setSubirVida(40);
        productos.get(1).setSubirVelMover(1);
        
       productos.get(3).setCantBalas(200);
       productos.get(3).setRango(1);
       productos.get(3).setVelDisparo(100);
       productos.get(3).setSubirCantBalas(50);
       productos.get(3).setSubirRango(1);
       productos.get(3).setSubirVelDisparo(10);
       
       
    }

    /**
     *
     * @return
     */
    public Game getGame() {
        return game;
    }

    /**
     *
     * @param game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     *
     * @return
     */
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public int getSwatOninja() {
        return swatOninja;
    }

    public void setSwatOninja(int swatOninja) {
        this.swatOninja = swatOninja;
    }

    @Override
    public void tick() {

        contTicks++;
        entrarTienda();
        salirTienda();

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.store_survivor, getX(), getY(), getWidth(), getHeight(), null); // shop animation

        if (game.isPausaTienda() && estaTienda) {
            if(!posicionarObjeto){
                g.drawImage(Assets.tienda4, (int) (game.getWidth() * .1), (int) (game.getHeight() * .1), (int) (game.getWidth() * .75), (int) (game.getHeight() * .75), null);

                mostrarProductosDisponibles(g);
                desplegarInfoObjetos(g);

                g.drawImage(Assets.select, (int) (xSelect), (int) (ySelect), (int) (game.getWidth() * .06), (int) (game.getHeight() * .075), null);

                if (!comprarObjeto) {
                    moverSelect();
                }

                desplegarMensajeCompra(g);
            }else{
                definirPosicionHada(g);
                comprarOmejorar=true;
                realizarAccionObjetos();
            }
        }

    }

    public void entrarTienda() {
        if (game.getPlayer().intersects(this) && game.getKeyManager().entrar) {
            game.setPausaTienda(true);
            estaTienda = true;
            xSelect = (double) (game.getWidth() * .29);
            ySelect = (double) (game.getHeight() * .52);
        }

        for (int i = 0; i < game.getFoundControllers().size(); i++) {
            Controller controller = game.getFoundControllers().get(0);
            controller.poll();
            Component[] components = controller.getComponents();

            if (game.getPlayer().intersects(this) && components[5].getPollData() == 1 && fueraTienda) {
                game.setPausaTienda(true);
                estaTienda = true;
                xSelect = (int) (game.getWidth() * .371);
                ySelect = (int) (game.getHeight() * .228);
                fueraTienda = false;
            }
        }
    }

    public void salirTienda() {
        if (game.isPausaTienda() && game.getKeyManager().salir && estaTienda) {
            game.setPausaTienda(false);
            estaTienda = false;
        }

        for (int i = 0; i < game.getFoundControllers().size(); i++) {
            Controller controller = game.getFoundControllers().get(0);
            controller.poll();
            Component[] components = controller.getComponents();

            if (game.isPausaTienda() && components[6].getPollData() == 1 && estaTienda && menuTienda) {
                game.setPausaTienda(false);
                estaTienda = false;
                comprarObjeto = false;
                empezarMensaje = false;
                fueraTienda = true;
            }
        }
    }

    public void moverSelect() {

        for (int i = 0; i < game.getFoundControllers().size(); i++) {
            Controller controller = game.getFoundControllers().get(0);
            controller.poll();
            Component[] components = controller.getComponents();
            Component componentx = components[1];
            Component componenty = components[0];

            //mover a la derecha
            if (componentx.getPollData() >= .9) {
                moverTiendaAbajo = false;
                moverTiendaArriba = false;
                moverTiendaIzquierda = false;
                moverTiendaDerecha = true;
            }
            if (moverTiendaDerecha && componentx.getPollData() < .9) {
                xSelect = encontrarDireccionSelect(xSelect);
                moverTiendaDerecha = false;
            }

            //mover a la izquierda
            if (componentx.getPollData() <= -.9) {
                moverTiendaAbajo = false;
                moverTiendaArriba = false;
                moverTiendaIzquierda = true;
                moverTiendaDerecha = false;
            }
            if (moverTiendaIzquierda && componentx.getPollData() > -.9) {
                xSelect = encontrarDireccionSelect(xSelect);
                moverTiendaIzquierda = false;
            }

            //mover a arriba
            if (componenty.getPollData() <= -.9) {
                moverTiendaAbajo = false;
                moverTiendaArriba = true;
                moverTiendaIzquierda = false;
                moverTiendaDerecha = false;
            }
            if (moverTiendaArriba && componenty.getPollData() > -.9) {

                ySelect = encontrarDireccionSelect(ySelect);
                moverTiendaArriba = false;
            }

            //mover a abajo
            if (componenty.getPollData() >= .9) {
                moverTiendaAbajo = true;
                moverTiendaArriba = false;
                moverTiendaIzquierda = false;
                moverTiendaDerecha = false;
            }
            if (moverTiendaAbajo && componenty.getPollData() < .9) {
                ySelect = encontrarDireccionSelect(ySelect);
                moverTiendaAbajo = false;
            }

        }

    }

    public void mostrarProductosDisponibles(Graphics g) {

        Iterator itr = productos.iterator();
        while (itr.hasNext()) {
            ObjetoTienda obj = (ObjetoTienda) itr.next();
            Font leter = new Font("Arial", 1, 10);
            g.setFont(leter);
            g.setColor(Color.BLACK);
            g.drawString(obj.getNombre(), obj.getX() - ((int) (game.getWidth() * .005)), obj.getY() - ((int) (game.getHeight() * .015)));
            g.drawImage(obj.getObjeto(), obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight(), null);

            g.drawString("" + obj.getCosto() + "$", obj.getX() + ((int) (game.getWidth() * .018)), obj.getY() + ((int) (game.getHeight() * .095)));
        }

    }

    public double encontrarDireccionSelect(double select) {
        double x = -1;

        if (moverTiendaDerecha) {
            x = game.getWidth();
        };
        if (moverTiendaIzquierda) {
            x = 0;
        };
        if (moverTiendaArriba) {
            x = 0;
        };
        if (moverTiendaAbajo) {
            x = game.getHeight();
        };

        Iterator itr = productos.iterator();
        while (itr.hasNext()) {
            ObjetoTienda obj = (ObjetoTienda) itr.next();

            if (moverTiendaDerecha) {
                if (obj.getX() > select && obj.getX() < x && obj.getY() == ySelect) {
                    x = obj.getX();
                    objetoSeleccionado = obj.getNumObjeto();
                }
            }

            if (moverTiendaIzquierda) {
                if (obj.getX() < select && obj.getX() > x && obj.getY() == ySelect) {
                    x = obj.getX();
                    objetoSeleccionado = obj.getNumObjeto();
                }
            }

            if (moverTiendaArriba) {
                if (obj.getY() < select && obj.getY() > x && obj.getX() == xSelect) {
                    x = obj.getY();
                    objetoSeleccionado = obj.getNumObjeto();
                }
            }

            if (moverTiendaAbajo) {
                if (obj.getY() > select && obj.getY() < x && obj.getX() == xSelect) {
                    x = obj.getY();
                    objetoSeleccionado = obj.getNumObjeto();
                }
            }

        }
        if (x == 0 || x == game.getWidth() || x == game.getHeight()) {
            return select;
        }
        return x;
    }

    public void desplegarInfoObjetos(Graphics g) {

        Font leter = new Font("Arial", 1, 12);
        g.setFont(leter);
        g.setColor(Color.black);

        Iterator itr = productos.iterator();
        while (itr.hasNext()) {
            ObjetoTienda obj = (ObjetoTienda) itr.next();

            if (obj.getNumObjeto() == objetoSeleccionado) {
                g.drawString("Nivel " + obj.getLevel(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .24));

                if (objetoSeleccionado == 0) {
                    g.drawString("Soberviviente que cuenta", (int) (game.getWidth() * .142), (int) (game.getHeight() * .265));
                    g.drawString("con disparo. Dispara a", (int) (game.getWidth() * .142), (int) (game.getHeight() * .279));
                    g.drawString("los zombies que esten en", (int) (game.getWidth() * .142), (int) (game.getHeight() * .295));
                    g.drawString("un radio determinado", (int) (game.getWidth() * .142), (int) (game.getHeight() * .312));
                    g.drawString("Daño por bala: " + obj.getCantidadReal(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .337));
                    g.drawString("rango: " + obj.getRango(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .357));
                    g.drawString("Velocidad Disparo: " + obj.getVelDisparo(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .377));
                    g.drawString("Vida: " + obj.getVida(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .397));
                    g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.417));

                    
                    if (obj.getLevel() >= obj.getLevelMax()) {
                        g.drawString("Este artefacto no cuenta", (int) (game.getWidth() * .142), (int) (game.getHeight() * .61));
                        g.drawString("con mejoras disponibles", (int) (game.getWidth() * .142), (int) (game.getHeight() * .626));
                    } else {
                        g.drawString("Costo: " + obj.getPrecioMejora(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .61));
                        g.drawString("nivel " + (obj.getLevel() + 1), (int) (game.getWidth() * .142), (int) (game.getHeight() * .63));
                        g.drawString("Aumento de daño: +" + obj.getCantidadSubir(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .65));
                        g.drawString("Aumento de rango: +" + obj.getSubirRango(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .67));
                        g.drawString("Disminuir velocidad ", (int) (game.getWidth() * .142), (int) (game.getHeight() * .69));
                        g.drawString("de disparo: -" + obj.getSubirVelDisparo(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .705));
                        g.drawString("Aumento vida: +" + obj.getSubirVida(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .725));
                    }
                }

                if (objetoSeleccionado == 1) {
                    g.drawString("Sobreviviente que cuenta", (int) (game.getWidth() * .142), (int) (game.getHeight() * .265));
                    g.drawString("con espada. Ataca cuerpo", (int) (game.getWidth() * .142), (int) (game.getHeight() * .279));
                    g.drawString("a cuerpo a los zombies", (int) (game.getWidth() * .142), (int) (game.getHeight() * .295));
                    g.drawString("de su alrededor", (int) (game.getWidth() * .142), (int) (game.getHeight() * .312));
                    g.drawString("Daño por golpe: " + obj.getCantidadReal(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .337));
                    g.drawString("Velocidad ataque: " + obj.getVelAtaque(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .357));
                    g.drawString("Vida: " + obj.getVida(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .377));
                    g.drawString("Velocidad: " + obj.getVelMover(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .397));
                    g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.417));
                    
                    if (obj.getLevel() >= obj.getLevelMax()) {
                        g.drawString("Este artefacto no cuenta", (int) (game.getWidth() * .142), (int) (game.getHeight() * .61));
                        g.drawString("con mejoras disponibles", (int) (game.getWidth() * .142), (int) (game.getHeight() * .626));
                    } else {
                        g.drawString("Costo: " + obj.getPrecioMejora(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .61));
                        g.drawString("nivel " + (obj.getLevel() + 1), (int) (game.getWidth() * .142), (int) (game.getHeight() * .63));
                        g.drawString("Aumento de daño: +" + obj.getCantidadSubir(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .65));
                        g.drawString("Aumento velocidad ", (int) (game.getWidth() * .142), (int) (game.getHeight() * .67));
                        g.drawString("ataque: +" + obj.getSubirVelAtaque(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .685));
                        g.drawString("Aumento vida: +" + obj.getSubirVida(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .705));
                        g.drawString("Aumento velocidad ", (int) (game.getWidth() * .142), (int) (game.getHeight() * .725));
                        g.drawString("movimiento: +" + obj.getSubirVelMover(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .74));
                    }
                }

                if (objetoSeleccionado == 2) {
                    g.drawString("Se asigan a un ", (int) (game.getWidth() * .142), (int) (game.getHeight() * .265));
                    g.drawString("sobreviviente y lo", (int) (game.getWidth() * .142), (int) (game.getHeight() * .279));
                    g.drawString("cura cada cierto", (int) (game.getWidth() * .142), (int) (game.getHeight() * .295));
                    g.drawString("tiempo", (int) (game.getWidth() * .142), (int) (game.getHeight() * .312));
                    g.drawString("Tiempo de curacion: " + obj.getCantidadReal(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .337));
                    g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.357));

                    if (obj.getLevel() >= obj.getLevelMax()) {
                        g.drawString("Este artefacto no cuenta", (int) (game.getWidth() * .142), (int) (game.getHeight() * .61));
                        g.drawString("con mejoras disponibles", (int) (game.getWidth() * .142), (int) (game.getHeight() * .626));
                    } else {
                        g.drawString("Costo: " + obj.getPrecioMejora(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .61));
                        g.drawString("nivel " + (obj.getLevel() + 1), (int) (game.getWidth() * .142), (int) (game.getHeight() * .63));
                        g.drawString("Disminucion de tiempo: -" + obj.getCantidadSubir(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .65));
                    }
                }

                if (objetoSeleccionado == 3) {
                    g.drawString("Se coloca detras del", (int) (game.getWidth() * .142), (int) (game.getHeight() * .265));
                    g.drawString("jugador y dipara a ", (int) (game.getWidth() * .142), (int) (game.getHeight() * .279));
                    g.drawString("los zombies cercanos", (int) (game.getWidth() * .142), (int) (game.getHeight() * .295));
                    g.drawString("Daño por bala: " + obj.getCantidadReal(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .337));
                    g.drawString("Cantidad balas: " + obj.getCantBalas(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .357));
                    g.drawString("Rango: " + obj.getRango(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .377));
                    g.drawString("Velocidad disparo: " + obj.getVelDisparo(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .397));
                    g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.417));
                    
                    if (obj.getLevel() >= obj.getLevelMax()) {
                        g.drawString("Este artefacto no cuenta", (int) (game.getWidth() * .142), (int) (game.getHeight() * .61));
                        g.drawString("con mejoras disponibles", (int) (game.getWidth() * .142), (int) (game.getHeight() * .626));
                    } else {
                        g.drawString("Costo: " + obj.getPrecioMejora(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .61));
                        g.drawString("nivel " + (obj.getLevel() + 1), (int) (game.getWidth() * .142), (int) (game.getHeight() * .63));
                        g.drawString("Aumento de daño: +" + obj.getCantidadSubir(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .65));
                        g.drawString("Aumento de cantidad", (int) (game.getWidth() * .142), (int) (game.getHeight() * .67));
                        g.drawString("de balas: +" + obj.getSubirCantBalas(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .685));
                        g.drawString("Aumento de rango: +" + obj.getSubirRango(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .705));
                        g.drawString("Aumento de velocidad", (int) (game.getWidth() * .142), (int) (game.getHeight() * .725));
                        g.drawString("de disparo: +" + obj.getSubirVelDisparo(), (int) (game.getWidth() * .142), (int) (game.getHeight() * .74));
                    }
                }

            }

        }

    }

    public void desplegarMensajeCompra(Graphics g) {

        for (int i = 0; i < game.getFoundControllers().size(); i++) {
            Controller controller = game.getFoundControllers().get(0);
            controller.poll();
            Component[] components = controller.getComponents();
            Component componenta = components[5];
            Component componentm = components[1];

            if (componenta.getPollData() == 0 && !comprarObjeto) {
                empezarMensaje = true;
            }

            if (componenta.getPollData() == 1 && empezarMensaje && !comprarObjeto) {
                validarA = true;
                empezarMensaje = false;
            }

            if (componenta.getPollData() == 0 && validarA && !comprarObjeto) {
                comprarObjeto = true;
                validarA = false;
                xSelectMensaje = (int) (game.getWidth() * .35);
            }

            if (comprarObjeto) {
                menuTienda = false;

                Iterator itr = productos.iterator();
                while (itr.hasNext()) {
                    ObjetoTienda obj = (ObjetoTienda) itr.next();

                    if (obj.getNumObjeto() == objetoSeleccionado) {
                        if (obj.getCosto() <= game.getPlayer().getDinero()) {
                            if (objetoSeleccionado >= 0 && objetoSeleccionado < 4) {
                                g.drawImage(Assets.mensajeComprarMejorar, (int) (game.getWidth() * .1), (int) (game.getHeight() * .1), (int) (game.getWidth() * .75), (int) (game.getHeight() * .75), null);
                                salirMensajeCompraMejora(g);
                        } else {
                            g.drawImage(Assets.dineroInsuficiente, (int) (game.getWidth() * .1), (int) (game.getHeight() * .1), (int) (game.getWidth() * .75), (int) (game.getHeight() * .75), null);
                            salirLetreto();
                        }
                    }
                }
            }
        }
    }
    }

    public void salirMensajeCompra() {

        for (int i = 0; i < game.getFoundControllers().size(); i++) {
            Controller controller = game.getFoundControllers().get(0);
            controller.poll();
            Component[] components = controller.getComponents();
            Component componentA = components[5];
            Component componentB = components[6];

            if (componentA.getPollData() == 1 && !validarA) {
                validarA = true;
            }

            if (componentA.getPollData() == 0 && validarA) {
                validarCompra = true;
                comprarObjeto = false;
                validarA = false;
                menuTienda = true;
                realizarAccionObjetos();
            }

            if (componentB.getPollData() == 1 && empezarMensaje) {
                validarB = true;
            }

            if (componentB.getPollData() == 0 && validarB) {
                validarCompra = false;
                comprarObjeto = false;
                empezarMensaje = false;
                validarB = false;
                menuTienda = true;
            }

        }
    }

    public void salirMensajeCompraMejora(Graphics g) {
        for (int i = 0; i < game.getFoundControllers().size(); i++) {
            Controller controller = game.getFoundControllers().get(0);
            controller.poll();
            Component[] components = controller.getComponents();
            Component component = components[1];
            Component componentA = components[5];
            Component componentB = components[6];

            //mover a la derecha
            if (component.getPollData() >= .9) {
                moverTiendaIzquierda = false;
                moverTiendaDerecha = true;
                xSelectMensaje = (int) (game.getWidth() * .51);
                comprarOmejorar = false;
            }
            if (moverTiendaDerecha && component.getPollData() < .9) {
                moverTiendaDerecha = false;
            }

            //mover a la izquierda
            if (component.getPollData() <= -.9) {
                moverTiendaIzquierda = true;
                moverTiendaDerecha = false;
                xSelectMensaje = (int) (game.getWidth() * .35);
                comprarOmejorar = true;
            }
            if (moverTiendaIzquierda && component.getPollData() > -.9) {
                moverTiendaIzquierda = false;
            }
            g.drawImage(Assets.select, xSelectMensaje, (int) (game.getHeight() * .375), (int) (game.getWidth() * .09), (int) (game.getHeight() * .16), null);

            if (mensajeNivel || mensajeErrorCompra  || noTienesOpciones  || confirmarSwat || confirmarNinja || noMejorar) {
                if(noMejorar){
                   g.drawImage(Assets.mensaje_2, (int) (game.getWidth() * .1), (int) (game.getHeight() * .1), (int) (game.getWidth() * .75), (int) (game.getHeight() * .75), null); 
                   salirLetreroNivel(); 
                }
                if(confirmarNinja){
                    g.drawImage(Assets.mensaje_1, (int) (game.getWidth() * .1), (int) (game.getHeight() * .1), (int) (game.getWidth() * .75), (int) (game.getHeight() * .75), null);
                    salirLetreroHadaUnaOpcion();
                    swatOninja=1;
                    realizarAccionObjetos();
                }
                if(confirmarSwat){
                    g.drawImage(Assets.mensaje_3, (int) (game.getWidth() * .1), (int) (game.getHeight() * .1), (int) (game.getWidth() * .75), (int) (game.getHeight() * .75), null);
                    salirLetreroHadaUnaOpcion();
                    swatOninja=0;
                    realizarAccionObjetos();
                }
                if(noTienesOpciones){
                    g.drawImage(Assets.mensaje_4, (int) (game.getWidth() * .1), (int) (game.getHeight() * .1), (int) (game.getWidth() * .75), (int) (game.getHeight() * .75), null);
                    salirLetreroNivel();
                }
                if(mensajeErrorCompra){
                    g.drawImage(Assets.mensajeNoCompra, (int) (game.getWidth() * .1), (int) (game.getHeight() * .1), (int) (game.getWidth() * .75), (int) (game.getHeight() * .75), null);
                    salirLetreroNivel();
                }
                if(mensajeNivel){
                    g.drawImage(Assets.mensajeNivel, (int) (game.getWidth() * .1), (int) (game.getHeight() * .1), (int) (game.getWidth() * .75), (int) (game.getHeight() * .75), null);
                    salirLetreroNivel();
                }
            } else {
                //para el boton comprar
                if (componentA.getPollData() == 1 && !validarA && (!mensajeNivel || !mensajeErrorCompra || !noTienesOpciones || !confirmarSwat || !confirmarNinja || !noMejorar)) {
                    validarA = true;
                }

                if (componentA.getPollData() == 0 && validarA) {
                    if(comprarOmejorar && ((game.isSwatActivo() && objetoSeleccionado==0) || (game.isNinjaActivo() && objetoSeleccionado==1) || (game.isHadaActivo() && objetoSeleccionado==2) || (game.isDroneActivo() && objetoSeleccionado==3))){
                        noMejorar=true;
                    }else{
                        if(!comprarOmejorar && !game.isSwatActivo() && game.isNinjaActivo() && objetoSeleccionado==2){
                        confirmarNinja=true;
                        validarB=true;
                    }else{
                        if(!comprarOmejorar && game.isSwatActivo() && !game.isNinjaActivo() && objetoSeleccionado==2){
                        confirmarSwat=true;
                        validarB=true;
                            }else{
                                if(!comprarOmejorar && !game.isSwatActivo() && !game.isNinjaActivo() && objetoSeleccionado==2){
                                noTienesOpciones=true;
                                }else{
                                    if(!comprarOmejorar && ((objetoSeleccionado==0 && game.isSwatActivo()) || (objetoSeleccionado==1 && game.isNinjaActivo()) || (objetoSeleccionado==2 && game.isHadaActivo()) || (objetoSeleccionado==3 && game.isDroneActivo()))){
                                    mensajeErrorCompra = true;
                                    }else{
                                        if (comprarOmejorar && productos.get(objetoSeleccionado).getLevel() >= productos.get(objetoSeleccionado).getLevelMax()) {
                                        mensajeNivel = true;
                                        } else {
                                            validarA = false;
                                            menuTienda = true;
                                            comprarObjeto = false;
                                            realizarAccionObjetos();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (componentB.getPollData() == 1 && validarB) {
                    validarB = false;
                }

                if (componentB.getPollData() == 0 && !validarB) {
                    validarCompra = false;
                    comprarObjeto = false;
                    empezarMensaje = false;
                    validarB = true;
                    menuTienda = true;
                }
            }
        }
    }

    public void realizarAccionObjetos() {
        int cont = 0;
        Iterator itr = productos.iterator();
        while (itr.hasNext()) {
            ObjetoTienda obj = (ObjetoTienda) itr.next();
            if (obj.getNumObjeto() == objetoSeleccionado) {

                             
                    if(objetoSeleccionado==0 && comprarOmejorar){
                        if(!game.isSwatActivo()){
                            if(obj.getLevel()<obj.getLevelMax()){
                                obj.setLevel(obj.getLevel()+1);
                                obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                                obj.setRango(obj.getRango()+obj.getSubirRango());
                                obj.setVelDisparo(obj.getVelDisparo()+obj.getSubirVelDisparo());
                                obj.setVida(obj.getVida()+obj.getSubirVida());
                            }
                        }
                    }
                    
                    if(objetoSeleccionado==0 && !comprarOmejorar){
                        if(!game.isSwatActivo()){
                            game.setSwatActivo(true);
                            game.setRambo(new SobrevivienteRambo((int)(game.getWidth() * 0.155555),(int)(game.getHeight() * 0.489711),(int) (game.getWidth()*.025),(int)(game.getHeight()*.06),getProductos().get(0).getCantidadReal(),getProductos().get(0).getVelDisparo(),getProductos().get(0).getVida(),getProductos().get(0).getRango(),3, game));
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                        comprarOmejorar=true;
                    }
                    
                    if(objetoSeleccionado==1 && comprarOmejorar){
                        if(!game.isNinjaActivo()){
                            if(obj.getLevel()<obj.getLevelMax()){
                                obj.setLevel(obj.getLevel()+1);
                                obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                                obj.setVelAtaque(obj.getVelAtaque()+obj.getSubirVelAtaque());
                                obj.setVida(obj.getVida()+obj.getSubirVida());
                                obj.setVelMover(obj.getVelMover()+obj.getSubirVelMover());
                                game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getPrecioMejora());
                            }
                        }
                    }

                if(objetoSeleccionado==1 && !comprarOmejorar){
                    if(!game.isNinjaActivo()){
                        game.setNinjaActivo(true);
                        game.setRocky( new SobrevivienteRocky((int)(game.getWidth() * 0.155555),(int)(game.getHeight() * 0.489711),(int) (game.getWidth()*.025),(int)(game.getHeight()*.06),getProductos().get(1).getCantidadReal(),getProductos().get(1).getVida(),getProductos().get(1).getVelMover(),3,getProductos().get(1).getVelAtaque(),game ) );
                        game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                    }
                    comprarOmejorar=true;
                }
                    
                
                if(objetoSeleccionado==2 && comprarOmejorar){
                    if(!game.isHadaActivo()){
                      if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getPrecioMejora());
                        }
                    }
                }

                if(objetoSeleccionado==2 && !comprarOmejorar){
                            if(!game.isHadaActivo()){
                                if(game.isNinjaActivo() && game.isSwatActivo()){
                                    posicionarObjeto=true;
                                    mensajeCongelado=true;
                                    validarA=true;
                                    desplegarSelectCongelado=false;

                                }else{
                                   if(game.isNinjaActivo() || game.isSwatActivo()){
                                    System.out.println(objetoSeleccionado);
                                    System.out.println(comprarOmejorar);
                                    System.out.println(swatOninja);
                                    if(swatOninja==0){
                                        game.setHada( new Sobreviviente_Healer(0,game.getRambo().getX(),game.getRambo().getY(),20,20,obj.getCantidadReal(),game ));
                                    }
                                    if(swatOninja==1){
                                        game.setHada( new Sobreviviente_Healer(1,game.getRocky().getX(),game.getRocky().getY(),20,20,obj.getCantidadReal(),game ));
                                    } 

                                }
                                }
                                game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                            }
                            comprarOmejorar=true;
                    }
                
                if(objetoSeleccionado==3 && comprarOmejorar){
                    if(!game.isDroneActivo()){
                      if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            obj.setCantBalas(obj.getCantBalas()+obj.getSubirCantBalas());
                            obj.setRango(obj.getRango()+obj.getSubirRango());
                            obj.setVelDisparo(obj.getVelDisparo()+obj.getSubirVelDisparo());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getPrecioMejora());
                        }
                    }
                    }

                if(objetoSeleccionado==3 && !comprarOmejorar){
                            if(!game.isDroneActivo()){
                            game.setDroneActivo(true);
                            game.setDrone( new Drone( game.getPlayer().getX() , game.getPlayer().getY(),35,35,getProductos().get(3).getCantidadReal(),getProductos().get(3).getRango(),getProductos().get(3).getCantBalas(),getProductos().get(3).getVelDisparo(),game ) );
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                            comprarOmejorar=true;
                    }

               

                

            }
        }

    }

    public void salirLetreto() {
        for (int i = 0; i < game.getFoundControllers().size(); i++) {
            Controller controller = game.getFoundControllers().get(0);
            controller.poll();
            Component[] components = controller.getComponents();
            Component componentA = components[5];

            if (componentA.getPollData() == 1 && !validarA) {
                validarA = true;
            }

            if (componentA.getPollData() == 0 && validarA) {
                comprarObjeto = false;
                validarA = false;
                menuTienda = true;
            }
        }
    }

    public void salirLetreroNivel() {
        menuTienda = false;
        for (int i = 0; i < game.getFoundControllers().size(); i++) {
            Controller controller = game.getFoundControllers().get(0);
            controller.poll();
            Component[] components = controller.getComponents();
            Component componentA = components[5];

            if (componentA.getPollData() == 1 && validarA) {
                validarA = false;
            }

            if (componentA.getPollData() == 0 && !validarA) {
                validarCompra = false;
                comprarObjeto = false;
                menuTienda = true;
                validarA = false;
                mensajeNivel = false;
                mensajeErrorCompra = false;
                comprarOmejorar=true;
                noTienesOpciones=false;
                confirmarSwat=false;
                mensajeCongelado=false;
                desplegarSelectCongelado=true;
                noMejorar=false;
                if(posicionarObjeto){
                    validarA=true;
                }
            }
        }
        
        
    }
    
    public void salirLetreroHadaUnaOpcion(){
        menuTienda = false;
        for (int i = 0; i < game.getFoundControllers().size(); i++) {
            Controller controller = game.getFoundControllers().get(0);
            controller.poll();
            Component[] components = controller.getComponents();
            Component componentA = components[5];
            Component componentB = components[6];
            
            if (componentB.getPollData() == 1 && validarB) {
                validarB = false;
            }

            if (componentB.getPollData() == 0 && !validarB) {
                validarCompra = false;
                comprarObjeto = false;
                menuTienda = true;
                validarA = false;
                mensajeNivel = false;
                mensajeErrorCompra = false;
                comprarOmejorar=true;
                noTienesOpciones=false;
                confirmarSwat=false;
            }
            

            if (componentA.getPollData() == 1 && validarA) {
                validarA = false;
            }

            if (componentA.getPollData() == 0 && !validarA) {
                validarCompra = false;
                comprarObjeto = false;
                menuTienda = true;
                validarA = false;
                mensajeNivel = false;
                mensajeErrorCompra = false;
                comprarOmejorar=true;
                noTienesOpciones=false;
                noMejorar=false;
                if(confirmarSwat){
                   swatOninja=0;
                }
                if(confirmarNinja){
                    swatOninja=1;
                }
                confirmarSwat=false;
                confirmarNinja=false;
            }
        }
    }

    public void definirPosicionHada(Graphics g){
        
        for (int i = 0; i < game.getFoundControllers().size(); i++) {
            Controller controller = game.getFoundControllers().get(0);
            controller.poll();
            Component[] components = controller.getComponents();
            Component component = components[1];
            Component componentA = components[5];

            
            if(mensajeCongelado){
                g.drawImage(Assets.mensaje_5, (int) (game.getWidth() * .1), (int) (game.getHeight() * .1), (int) (game.getWidth() * .75), (int) (game.getHeight() * .75), null);
                salirLetreroNivel();
            }
        
        if(desplegarSelectCongelado){
            g.drawImage(Assets.select,xSelectCongelado, ySelectCongelado, (int)(game.getRambo().getWidth()), (int) (game.getRambo().getHeight()), null);
            
            if(game.getRambo().getX()<game.getRocky().getX()){
                if(component.getPollData()<-.1){
                   xSelectCongelado=game.getRambo().getX(); 
                   ySelectCongelado=game.getRambo().getY();
                    swatOninja=0;
                }
                if(component.getPollData()>.1){
                   xSelectCongelado=game.getRocky().getX(); 
                   ySelectCongelado=game.getRocky().getY();
                   swatOninja=1;
                }
            }else{
                if(component.getPollData()>.1){
                    xSelectCongelado=game.getRambo().getX();
                    ySelectCongelado=game.getRambo().getY();
                    swatOninja=0;
                }
                if(component.getPollData()<-.1){
                   xSelectCongelado=game.getRocky().getX(); 
                   ySelectCongelado=game.getRocky().getY();
                   swatOninja=1;
                }
            }
            
            if (componentA.getPollData() == 1 && validarA) {
                validarA = false;
            }

            if (componentA.getPollData() == 0 && !validarA) {
                validarCompra = false;
                comprarObjeto = false;
                menuTienda = true;
                validarA = false;
                mensajeNivel = false;
                mensajeErrorCompra = false;
                comprarOmejorar=true;
                noTienesOpciones=false;
                confirmarSwat=false;
                confirmarNinja=false;
                desplegarSelectCongelado=false;
                posicionarObjeto=false; 
                
                if(swatOninja==0){
                    game.setHada( new Sobreviviente_Healer(0,game.getRambo().getX(),game.getRambo().getY(),20,20,productos.get(2).getCantidadReal(),game ));
                     game.setHadaActivo(true);
                }
                if(swatOninja==1){
                    game.setHada( new Sobreviviente_Healer(1,game.getRocky().getX(),game.getRocky().getY(),20,20,productos.get(2).getCantidadReal(),game ));
                     game.setHadaActivo(true);
                }  
                
            }
            
        }
        }
    }

    public ArrayList<ObjetoTienda> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<ObjetoTienda> productos) {
        this.productos = productos;
    }
}
