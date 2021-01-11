/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;

/**
 * clase que crea una matriz de cuanto se tiene que recorrer para el camino mas corto y otra que guarda el como llegar ahi
 * se utiliza el algoritmo floyd para esto
 * @author ALBER
 */
public final class Floyd {
    private Game game;  //game
    private double[][] masCortos = new double[41][41];  //matriz que guarda cuanto es el camino mas corto de un punto a otro
    private int[][] paths = new int[41][41];    //matriz que guarda el path de un punto a otro
    private ArrayList<Node> nodos;  //Arreglo de nodos para control de puntos
    
    /**
     *     inicializacion que manda a llamar todos los metodos de la clase para que en cuanto de inicialice quede todo listo
     * @param game 
     */
    public Floyd(Game game){
        this.game = game;
        
        //inicializar las matrices con lo necesario para trabajar
        InicializarMatrices();
        nodos = new ArrayList<>();
        
        //agregar todos los nodos que se ocupan para el mapa
        nodos.add( new Node(0,0,0,0,game));
        nodos.add( new Node( (int)(game.getWidth()*0.20625),    (int)(game.getHeight()*0.048353909),1,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.14930556), (int)(game.getHeight()*0.185185185),2,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.15208333), (int)(game.getHeight()*0.274691358),3,3,game));
        nodos.add( new Node( (int)(game.getWidth()*0.22638889), (int)(game.getHeight()*0.405349794),4,3,game));
        nodos.add( new Node( (int)(game.getWidth()*0.14722222), (int)(game.getHeight()*0.54218107),5,3,game));
        nodos.add( new Node( (int)(game.getWidth()*0.14652778), (int)(game.getHeight()*0.719135802),6,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.20833333), (int)(game.getHeight()*0.852880658),7,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.37152778), (int)(game.getHeight()*0.874485597),8,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.37222222), (int)(game.getHeight()*0.70473251),9,2,game));
        nodos.add( new Node( (int)(game.getWidth()*0.37569444), (int)(game.getHeight()*0.193415638),10,2,game));
        nodos.add( new Node( (int)(game.getWidth()*0.37638889), (int)(game.getHeight()*0.041152263),11,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.48888889), (int)(game.getHeight()*0.28),12,2,game));
        nodos.add( new Node( (int)(game.getWidth()*0.48611111), (int)(game.getHeight()*0.383744856),13,3,game));
        nodos.add( new Node( (int)(game.getWidth()*0.48888889), (int)(game.getHeight()*0.432098765),14,3,game));
        nodos.add( new Node( (int)(game.getWidth()*0.48541667), (int)(game.getHeight()*0.547325103),15,2,game));
        nodos.add( new Node( (int)(game.getWidth()*0.6),        (int)(game.getHeight()*0.616255144),16,2,game));
        nodos.add( new Node( (int)(game.getWidth()*0.60833333), (int)(game.getHeight()*0.419753086),17,2,game));
        nodos.add( new Node( (int)(game.getWidth()*0.60555556), (int)(game.getHeight()*0.236625514),18,2,game));
        nodos.add( new Node( (int)(game.getWidth()*0.7618055), (int)(game.getHeight()*0.158436),19,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.84166667), (int)(game.getHeight()*0.271604938),20,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.75),       (int)(game.getHeight()*0.430041152),21,2,game));
        nodos.add( new Node( (int)(game.getWidth()*0.86805556), (int)(game.getHeight()*0.429012346),22,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.82708333), (int)(game.getHeight()*0.610082305),23,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.74513888), (int)(game.getHeight()*0.764403),24,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.59375),    (int)(game.getHeight()*0.29),25,2,game));
        nodos.add( new Node( (int)(game.getWidth()*0.590777),   (int)(game.getHeight()*0.541152),26,2,game));
        nodos.add( new Node( (int)(game.getWidth()*0.6229166), (int)(game.getHeight()*0.050411522),27,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.586111),  (int)(game.getHeight()*0.867283),28,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.139583),   (int)(game.getHeight()*0.40329218),29,3,game));
        nodos.add( new Node( (int)(game.getWidth()*0.35486111), (int)(game.getHeight()*0.270576),30,2,game));
        nodos.add( new Node( (int)(game.getWidth()*0.33402777), (int)(game.getHeight()*0.592592),31,2,game));
        nodos.add( new Node( (int)(game.getWidth()*0.0819444),  (int)(game.getHeight()*0.06481481),32,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.09097222), (int)(game.getHeight()*0.83539094),33,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.336111),   (int)(game.getHeight()*0.4104938),34,3,game));
        nodos.add( new Node( (int)(game.getWidth()*0.47916666), (int)(game.getHeight()*0.6502056),35,2,game));
        nodos.add( new Node( (int)(game.getWidth()*0.4861111),  (int)(game.getHeight()*0.21810699),36,2,game));
        nodos.add( new Node( (int)(game.getWidth()*0.9173611),  (int)(game.getHeight()*0.197530),37,1,game));
        nodos.add( new Node( (int)(game.getWidth()*0.891666),  (int)(game.getHeight()*0.7263374),38,1,game));
        
        
        //agregas todos los caminos directos entre los nodos que se pueda
        Edges(nodos.get(1),nodos.get(2));
        Edges(nodos.get(1),nodos.get(11));
        Edges(nodos.get(2),nodos.get(3));
        Edges(nodos.get(3),nodos.get(4));
        Edges(nodos.get(3),nodos.get(5));
        Edges(nodos.get(4),nodos.get(5));
        Edges(nodos.get(4),nodos.get(13));
        Edges(nodos.get(4),nodos.get(14));
        Edges(nodos.get(5),nodos.get(6));
        Edges(nodos.get(6),nodos.get(7));
        Edges(nodos.get(7),nodos.get(8));
        Edges(nodos.get(8),nodos.get(9));
        Edges(nodos.get(9),nodos.get(15));
        Edges(nodos.get(9),nodos.get(16));
        Edges(nodos.get(10),nodos.get(11));
        Edges(nodos.get(10),nodos.get(12));
        Edges(nodos.get(10),nodos.get(18));
        Edges(nodos.get(12),nodos.get(13));
        Edges(nodos.get(12),nodos.get(18));
        Edges(nodos.get(13),nodos.get(14));
        Edges(nodos.get(14),nodos.get(15));
        Edges(nodos.get(15),nodos.get(16));
        Edges(nodos.get(16),nodos.get(17));
        Edges(nodos.get(17),nodos.get(18));
        Edges(nodos.get(17),nodos.get(21));
        Edges(nodos.get(19),nodos.get(20));
        Edges(nodos.get(19),nodos.get(21));
        Edges(nodos.get(20),nodos.get(21));
        Edges(nodos.get(20),nodos.get(22));
        Edges(nodos.get(21),nodos.get(22));
        Edges(nodos.get(21),nodos.get(23));
        Edges(nodos.get(21),nodos.get(24));
        Edges(nodos.get(22),nodos.get(23));
        Edges(nodos.get(23),nodos.get(24));
        Edges(nodos.get(25),nodos.get(12));
        Edges(nodos.get(25),nodos.get(18));
        Edges(nodos.get(25),nodos.get(17));        
        Edges(nodos.get(26),nodos.get(15));
        Edges(nodos.get(26),nodos.get(16));
        Edges(nodos.get(26),nodos.get(17));    
        Edges(nodos.get(27),nodos.get(11));    
        Edges(nodos.get(27),nodos.get(19));        
        Edges(nodos.get(28),nodos.get(8));    
        Edges(nodos.get(28),nodos.get(24));    
        Edges(nodos.get(29),nodos.get(3));  
        Edges(nodos.get(29),nodos.get(4));  
        Edges(nodos.get(29),nodos.get(5)); 
        Edges(nodos.get(30),nodos.get(10)); 
        Edges(nodos.get(30),nodos.get(12)); 
        Edges(nodos.get(31),nodos.get(15)); 
        Edges(nodos.get(31),nodos.get(9)); 
        Edges(nodos.get(32),nodos.get(1)); 
        Edges(nodos.get(32),nodos.get(2)); 
        Edges(nodos.get(33),nodos.get(6)); 
        Edges(nodos.get(33),nodos.get(7)); 
        Edges(nodos.get(34),nodos.get(4)); 
        Edges(nodos.get(34),nodos.get(13)); 
        Edges(nodos.get(34),nodos.get(14)); 
        Edges(nodos.get(34),nodos.get(3)); 
        Edges(nodos.get(34),nodos.get(5)); 
        Edges(nodos.get(35),nodos.get(31)); 
        Edges(nodos.get(35),nodos.get(9)); 
        Edges(nodos.get(35),nodos.get(15)); 
        Edges(nodos.get(35),nodos.get(26)); 
        Edges(nodos.get(35),nodos.get(16)); 
        Edges(nodos.get(36),nodos.get(10)); 
        Edges(nodos.get(36),nodos.get(30)); 
        Edges(nodos.get(36),nodos.get(12)); 
        Edges(nodos.get(36),nodos.get(25)); 
        Edges(nodos.get(36),nodos.get(18)); 
        Edges(nodos.get(37),nodos.get(19));
        Edges(nodos.get(37),nodos.get(20));
        Edges(nodos.get(37),nodos.get(22));
        Edges(nodos.get(38),nodos.get(22));
        Edges(nodos.get(38),nodos.get(23));
        Edges(nodos.get(38),nodos.get(24));
        

        
        //una vez ya todo inicializado bien busca todos los caminos
        floyd();
                
    }
    
    /**
     *     funcion que saca la distancia entre dos puntos
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @return 
     */
    public double DistanciaDosPuntos(int x1,int x2, int y1, int y2){
        double deltax = x2 - x1;
        double deltay = y2 - y1;
        
        double deltax2 = deltax * deltax;
        double deltay2 = deltay * deltay;
        
        return Math.sqrt(deltax2 + deltay2);
    }
    
    /**
     *    Inicializa matrices con valores necesarios
     */
    public void InicializarMatrices(){
        for( int i = 1 ; i <= 38 ; i++ ){
            for( int j = 1 ; j <= 38 ; j++ ){
                //toda la matriz con el maximo valor posible
                masCortos[i][j] = Integer.MAX_VALUE;
                //toda la matriz con 0
                paths[i][j] = 0; 
                
                //control de diagonal principal
                if(i == j){
                    masCortos[i][j] = 0;
                    paths[i][j] = i;
                }
            }
        }
    }
    
    /**
     * Funcion que saca la distancia entre dos puntos y agrega los caminos a paths de como llegan directo entre si los nodos
     * @param n1
     * @param n2 
     */
    public void Edges(Node n1, Node n2){
        masCortos[n1.getId()][n2.getId()] = masCortos[n2.getId()][n1.getId()] = DistanciaDosPuntos(n1.getX(),n2.getX(),n1.getY(),n2.getY());
        paths[n1.getId()][n2.getId()] = n1.getId();
        paths[n2.getId()][n1.getId()] = n2.getId();
    }
    
    /**
     * funcion que saca los caminos mas cortos entre todos los nodos y ademas guarda como llegan de un punto a otro
    */
    public void floyd(){
        for(int k = 1;k <= 38 ; k++)
            for(int i = 1 ; i <= 38 ; i++)
                for(int j = 1 ; j <= 38 ; j++)
                    if(masCortos[i][k]!=Integer.MAX_VALUE && masCortos[k][j]!= Integer.MAX_VALUE && i != j){
                        if(masCortos[i][j] > masCortos[i][k] + masCortos[k][j]){
                            masCortos[i][j] = masCortos[i][k] + masCortos[k][j];
                            paths[i][j] = k;
                        }
                    }
    }
    
    /**
     *     Funcion para mostrar matriz
     */
    public void muestramatriz(){
        for(int i = 9 ; i<= 9 ; i++){
            for(int j = 1 ; j <= 24 ; j++){
                System.out.println(paths[i][j]);
            }
            System.out.println();
        }
    }

    public double[][] getMasCortos() {
        return masCortos;
    }
    
    public void setMasCortos(double[][] masCortos) {
        this.masCortos = masCortos;
    }

    public int[][] getPaths() {
        return paths;
    }

    public void setPaths(int[][] paths) {
        this.paths = paths;
    }

    public ArrayList<Node> getNodos() {
        return nodos;
    }

    public void setNodos(ArrayList<Node> nodos) {
        this.nodos = nodos;
    }    
    
}
