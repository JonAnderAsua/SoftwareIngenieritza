package kodea;

import java.util.Random;
import java.util.Scanner;

public class chungo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Que resolucion quieres que tenga el tablero?");
        Scanner sc = new Scanner(System.in);
        int resol= sc.nextInt();
        char[][] tablero = new char[resol][resol];
        int maxminas= (resol-1)*(resol-1); //he sacado de aqui cuantas deberian ser de maximo http://www.minesweeper.info/custom.php
        int numminas= maxminas+1;
        while(numminas>maxminas) {
            System.out.println("Cuantas minas quieres que haya? El numero no podra ser mayor a "+ maxminas);
//            System.out.println("Se recomiendan " + (int)((maxminas/(Math.sqrt((double) resol)))-resol + Math.sqrt((double) resol)));
            numminas=sc.nextInt();
        }

        tablero=generarTablero(tablero, resol);
        char[][] partida = new char[resol][resol];
        partida = generarTablero(partida, resol);
        printTablero(partida, resol);
        System.out.println("Que casilla quieres revelar?");
        System.out.println("X:");
        int x = sc.nextInt();
        System.out.println("Y:");
        int y = sc.nextInt();
        tablero[x][y]='V';
        tablero=llenarCasillas(tablero, numminas, resol, x, y);
        tablero=ponerNumeros(tablero,resol);
        partida = revelar(partida, tablero, x, y);
//        printTablero(tablero,resol);
        boolean terminar = false;
        int opcion = 0;
        while(!terminar) {
//            printTablero(tablero, resol);
//            System.out.println("Y aqui el tablero sin resolver");
            printTablero(partida, resol);
            System.out.println("Que quieres hacer?");
            System.out.println("1: Revelar casilla (revelar una casilla ya revelada revelará las de su alrededor)");
            System.out.println("2: Marcar casilla");
            opcion = sc.nextInt();
            while(opcion!=1&&opcion!=2) {
                System.out.println("Ese numero no era valido, inserte otro numero");
                opcion = sc.nextInt();
            }if(opcion==1) {
                System.out.println("Que casilla quieres revelar?");
                System.out.println("X:");
                x = sc.nextInt();
                System.out.println("Y:");
                y = sc.nextInt();
                if(partida[x][y]!=' ') {
                    partida =revelarAlrededor(partida, tablero, x, y);
                }else {
                    partida[x][y]= tablero[x][y];
                    partida = revelar(partida, tablero, x, y);    
                }
            }else {
                System.out.println("Que casilla quieres marcar?");
                System.out.println("X:");
                x = sc.nextInt();
                System.out.println("Y:");
                y = sc.nextInt();
                partida[x][y]='?';
            }

        }
        sc.close();
    }

    
    
    private char[][] nuevoTablero(Integer neurri, Integer x, Integer y) {
        char[][] tablero = new char[neurri][neurri];
        tablero=generarTablero(tablero, neurri);
        tablero=llenarCasillas(tablero, (neurri-1)*(neurri-1), neurri, x, y);
        tablero=ponerNumeros(tablero,neurri);
        return tablero;
    }
    private static char[][] revelar(char[][] partida, char[][] tablero, int x, int y) {
        if(tablero[x][y]=='M') {
            partida[x][y]=tablero[x][y];
            printTablero(partida, partida.length);
            System.exit(0);
        }else if(tablero[x][y]=='0') {
//            System.out.println("era un hueco en blanco");
            partida[x][y]='0';
            partida=revelarAlrededor(partida, tablero, x, y);
        }else {
            partida[x][y]= tablero[x][y];
        }
        
        return partida;
    }

    private static char[][] revelarAlrededor(char[][] partida, char[][] tablero, int i, int j) {
        char AZ = getValor(i-1,j-1, tablero);
        if((AZ!='e'&&AZ!='M')&&partida[i-1][j-1]==' ') {
//            System.out.println("se va a revelar " + (i-1) + " " + (j-1));
            partida=revelar(partida, tablero, i-1, j-1);
        }
        char A = getValor(i,j-1, tablero);
        if((A!='e'&&A!='M')&&partida[i][j-1]==' ') {
            partida=revelar(partida, tablero, i, j-1);
        }
        char AD = getValor(i+1,j-1, tablero);
        if((AD!='e'&&AD!='M')&&partida[i+1][j-1]==' ') {
            partida=revelar(partida, tablero, i+1, j-1);
        }
        char IZ = getValor(i-1,j, tablero);
        if((IZ!='e'&&IZ!='M')&&partida[i-1][j]==' ') {
            partida=revelar(partida, tablero, i-1, j);
        }
        char DE = getValor(i+1,j, tablero);
        if((DE!='e'&&DE!='M')&&partida[i+1][j]==' ') {
            partida=revelar(partida, tablero, i+1, j);
        }
        char DZ = getValor(i-1,j+1, tablero);
        if((DZ!='e'&&DZ!='M')&&partida[i-1][j+1]==' ') {
            partida=revelar(partida, tablero, i-1, j+1);
        }
        char D = getValor(i,j+1, tablero);
        if((D!='e'&&D!='M')&&partida[i][j+1]==' ') {
            partida=revelar(partida, tablero, i, j+1);
        }
        char DD = getValor(i+1,j+1, tablero);
        if((DD!='e'&&DD!='M')&&partida[i+1][j+1]==' ') {
            partida=revelar(partida, tablero, i+1, j+1);
        }        return partida;
    }

    private static char[][] ponerNumeros(char[][] tablero, int size) {
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                if(tablero[i][j]!='M') {
//                    System.out.println("vamos a analizar " + i + j);
                    tablero[i][j]=numeroMinas(i, j, tablero);
//                    System.out.println("El valor de "+ i + j +" es " + tablero [i][j]);
                }
            }
        }
        return tablero;
    }

    private static char numeroMinas(int i, int j, char[][] tablero) {
        char num = '0';
        char AZ = getValor(i-1,j-1, tablero);
        if(AZ!='e'&&AZ=='M') {
            num++;
        }
        char A = getValor(i,j-1, tablero);
        if(A!='e'&&A=='M') {
            num++;
        }
        char AD = getValor(i+1,j-1, tablero);
        if(AD!='e'&&AD=='M') {
            num++;
        }
        char IZ = getValor(i-1,j, tablero);
        if(IZ!='e'&&IZ=='M') {
            num++;
        }
        char DE = getValor(i+1,j, tablero);
        if(DE!='e'&&DE=='M') {
            num++;
        }
        char DZ = getValor(i-1,j+1, tablero);
        if(DZ!='e'&&DZ=='M') {
            num++;
        }
        char D = getValor(i,j+1, tablero);
        if(D!='e'&&D=='M') {
            num++;
        }
        char DD = getValor(i+1,j+1, tablero);
        if(DD!='e'&&DD=='M') {
            num++;
        }
        return num;
    }

    private static char getValor(int i, int j, char[][] tablero) {
        if(i>=0&&j>=0&&i<=tablero.length-1&&j<=tablero[1].length-1) {
            return tablero[i][j];
        }
        return 'e';
    }

    private static void printTablero(char[][] tablero, int size) {
        String linea;
        String separar =" ";
        for(int z =0;z<size;z++) {
            separar=separar+"----";
        }
        
        for(int i=0;i<size;i++) {
            linea="";
            for(int j=0;j<size;j++) {
                linea=linea+ " | "+tablero[i][j];
            }
            System.out.println(linea+" |");
            System.out.println(separar+"-");
        }
    }

        
    private static char[][] llenarCasillas(char[][] tablero, int minas, int size, int iInicial, int jInicial) {
        int vacias = size*size;
        Random rand = new Random();
        while(minas>0) {
                int i = rand.nextInt(tablero.length);
                int j = rand.nextInt(tablero[i].length);
                boolean ARZ= i==(iInicial-1)&&j==(jInicial-1);
                boolean AR= i==(iInicial-1)&&j==jInicial;
                boolean ARD= i==(iInicial-1)&&j==(jInicial+1);
                boolean DE= i==iInicial && j==(jInicial-1);
                boolean IZ= i==iInicial && j==(jInicial+1);
                boolean ABZ= i==(iInicial+1)&&j==(jInicial-1);
                boolean AB= i==(iInicial+1)&&j==jInicial;
                boolean ABD= i==(iInicial+1)&&j==(jInicial+1);
                boolean esDeAlrededor=ARZ||AR||ARD||DE||IZ||ABZ||AB||ABD;
//                System.out.println(esDeAlrededor);
                if(tablero[i][j]!='M'&&i!=iInicial&&j!=jInicial) {
                        int numero=rand.nextInt(vacias);
                        if(minas>numero&&!esDeAlrededor) {
//                            System.out.println(i + " "+ j + " ha salido mina y el ran ha sido "+ numero);
                            tablero[i][j]='M';
                            minas--;
                            vacias--;
//                            System.out.println(tablero[i][j]);
                        }else {
                            tablero[i][j]='V';
                        }
                }
        }
        return tablero;
    }


    public static char[][] generarTablero(char[][] tablero, int size) {
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
//                System.out.println(i +" " +j);
//                System.out.println(tablero[i][j]);
                tablero[i][j]=' ';
            }
        }
        return tablero;
    }

    
    
    
    
}


