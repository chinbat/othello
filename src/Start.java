import java.util.Scanner;

public class Start{

    static int gameLevel,gameLevel2,gameType,playerSide;
    public static int clone1=0,clone2=0,clone3=0;


    /**

    * @param args

    */

    //

    public static void initial(){

    }

    public static int getGameType(){

        int type;

        System.out.println("GameType(1:plr-plr/2:plr-cpu/3:cpu-cpu)=?");

        // 1: plr-plr

        // 2: cpu-plr

        // 3: cpu-cpu

        Scanner scan= new Scanner(System.in);

        if((type=scan.nextInt())!=1 && type!=2 && type!=3)

            return -1;

        else

            return type;

    }

    //

    public static int getGameLevel(){

        int lvl;

        System.out.println("GameLevel(1:minmax/2:αβ/3:negascout)=?");

        // 1:easy (minmax)

        // 2:normal (ab)

        // 3:hard (negascout)

        Scanner scan= new Scanner(System.in);

        if((lvl=scan.nextInt())!=1 && lvl!=2 && lvl!=3)

            return -1;

        else

            return lvl;

    }

    //

    public static int getPlayerSide(){

        int side;

        System.out.println("PlayerSide(1:black/2:white)=?");

        // 1: black

        // 2: white

        Scanner scan= new Scanner(System.in);

        if((side=scan.nextInt())!=1 && side!=2)

            return -1;

        else

            return side;

    }

    public static void gameInitial(){

    while((gameType=getGameType())==-1);

        if(gameType!=1)

            while((gameLevel=getGameLevel())==-1);

        if(gameType==3)

            while((gameLevel2=getGameLevel())==-1);

        if(gameType==2)

            while((playerSide=getPlayerSide())==-1);
           

        if(playerSide==1)

        playerSide=1;

        else

        playerSide=-1;

        if(gameType!=3)
            System.out.printf("Game:%d,%d,%d\n",gameType,gameLevel,playerSide);

        else
            System.out.printf("Game:%d,%d,%d\n",gameType,gameLevel,gameLevel2);


    }

    public static int getCommand(Board gameBoard){

    if(gameBoard.checkPass())

    return -1;

    // -1    Pass hiisen

    int whoseTurn,x,y;

    System.out.printf("turn:%d\n",gameBoard.turn);

    whoseTurn=gameBoard.getCurrColor();

    if(whoseTurn==1)

    System.out.printf("Black's turn\n");

    else

    System.out.printf("White's turn\n");

    Scanner scan= new Scanner(System.in);

    //System.out.print("x=");

    //while(1<=(x=scan.nextInt()) && x<=8);

    do{

    System.out.print("x=");

    x=scan.nextInt();

    }while(x<0 || x>7 );

    //System.out.print("\ny=");

    //while(1<=(y=scan.nextInt()) && y<=8);

    do{

    System.out.print("y=");

    y=scan.nextInt();

    }while(y<0 || y>7 );

    if(gameBoard.putDisc(x, y, true))

    return 1;

    // 1  oketa

    else

    return 0;

    //0  okenakatta

    }

    public static int[][] sort(int[] a,int x){

        int number,t,max;

        int reg[][]=new int[64][2];

    for(int i=0;i<x;i++){

      number=i;

      max=-9999;

      for(int j=i;j<x;j++){

      if(a[j]>max){

          max=a[j];

          number=j;

          }

      }

      t=a[i];

      a[i]=a[number];

      a[number]=t;

      reg[i][0]=number;

      reg[i][1]=i;

        }

    return reg;

    }


    public static int NegaScout(Board board,int alpha,int beta,int
depth,int depth1){
    	clone3++;
        if(depth==0)
            return board.eval1(board.currentColor);
        int a,b,s,t,te,count=0;
        int eval[]=new int[64];
        int possiblePosition[][]=new int[64][2];
        int taio[][]=new int[64][2];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board.putDisc(i, j, false)){
                    if(depth==depth1){
                        if((i==0 && j==0) || (i==0 && j==7) || (i==7 && j==0) ||
(i==7 && j==7)){
                            return i*10+j;
                        }
                    }
                    possiblePosition[count][0]=i;
                    possiblePosition[count][1]=j;
                    Board c=board.clone();
                    c.putDisc(i, j,true);
                    c.changeCurrColor();
                    eval[count]=c.eval1(c.currentColor);
                    count++;
                }
            }
        }
        taio=sort(eval,count);
        for(int i=0;i<count;i++){
            t=possiblePosition[taio[i][0]][0];
            possiblePosition[taio[i][0]][0]=possiblePosition[taio[i][1]][0];
            possiblePosition[taio[i][1]][0]=t;
            t=possiblePosition[taio[i][0]][1];
            possiblePosition[taio[i][0]][1]=possiblePosition[taio[i][1]][1];
            possiblePosition[taio[i][1]][1]=t;
        }
        a=alpha;
        b=beta;
        if(depth!=depth1){
            if(count==0){
                board.changeCurrColor();
                return -NegaScout(board,-beta,-alpha,depth-1,depth1);
            }
        for(int n=0;n<count;n++){
            Board d;
            d=board.clone();
            d.putDisc(possiblePosition[n][0],possiblePosition[n][1],true);
            d.changeCurrColor();
            s=-NegaScout(d,-b,-a,depth-1,depth1);
            if(s>a && s<beta && depth>1 && n>0){
                a=-NegaScout(d,-beta,-s,depth-1,depth1);
            }
            if(s>a){
                a=s;
            }
            if(a>=beta){
                return a;
            }
            b=a+1;
        }
        return a;     
        }
        else{
            if(count==0)
                return -20000;
            te=possiblePosition[0][0]*10+possiblePosition[0][1];
            for(int n=0;n<count;n++){
                Board d;
                d=board.clone();
                d.putDisc(possiblePosition[n][0],possiblePosition[n][1],true);
                d.changeCurrColor();
                s=-NegaScout(d,-b,-a,depth-1,depth1);
                if(s>a && s<beta && depth>1 && n>0){
                    a=-NegaScout(d,-beta,-s,depth-1,depth1);
                    te=possiblePosition[n][0]*10+possiblePosition[n][1];
                }
                if(s>a){
                    a=s;
                    te=possiblePosition[n][0]*10+possiblePosition[n][1];
                }
                if(a>=beta){
                    return te;
                }
                b=a+1;
            }
            return te;     
        }
    }



    public static int SimpleSearch(Board board){

    for(int i=0;i<8;i++){

      for(int j=0;j<8;j++){

      if(board.putDisc(i, j, false)==true){

        return i*10+j;

      }



      }

    }

    return -1;

    }

    public static void main(String[] args) {

        // TODO Auto-generated method stub

    int stat;

        initial();

        gameInitial();

        Board gameBoard= new Board();

        gameBoard.init();

        //プレイヤー対プレイヤー

        if(gameType==1){

            gameBoard.display();

            System.out.printf("Game status: Black:%d White:%d\n",gameBoard.getDiscSum(1),gameBoard.getDiscSum(-1));

            //ゲームループ

            while(true){

                //ゲームが終わるかどうかを調べる

                if(gameBoard.checkPass()){

                gameBoard.changeCurrColor();

                if(gameBoard.checkPass())

                break;    //黒も白もパスする状態だったらゲーム終了

                gameBoard.changeCurrColor();

                }

                stat=getCommand(gameBoard);



                if(stat==(-1)){

                System.out.println("パスした");

                gameBoard.changeCurrColor();



                }

                else if(stat==0){

                System.out.println("置けない");

                }

                else if(stat==1){

                gameBoard.changeCurrColor();

                gameBoard.display();

                System.out.printf("Game status: Black:%d White:%d\n",gameBoard.getDiscSum(1),gameBoard.getDiscSum(-1));

                }

                }

        System.out.printf("Game over: Black:%d White:%d\n",gameBoard.getDiscSum(1),gameBoard.getDiscSum(-1));

        if(gameBoard.getDiscSum(1)>gameBoard.getDiscSum(-1))

        System.out.println("Black won");

        else if(gameBoard.getDiscSum(1)<gameBoard.getDiscSum(-1))

        System.out.println("White won");

        else

        System.out.println("Draw");

        }

        if(gameType==2){

              gameBoard.display();

                System.out.printf("Game status: Black:%d White:%d\n",gameBoard.getDiscSum(1),gameBoard.getDiscSum(-1));

                while(true){

                // System.out.println(gameBoard.getCurrColor()+" deed");

                  if(gameBoard.checkPass()){

                        gameBoard.changeCurrColor();

                        if(gameBoard.checkPass())

                        break;    //黒も白もパスする状態だったらゲーム終了

                        gameBoard.changeCurrColor();

                        }

                  if(gameBoard.getCurrColor()==playerSide){

                  stat=getCommand(gameBoard);

                  //System.out.println(stat);

                    if(stat==(-1)){

                              System.out.println("パスした");

                              gameBoard.changeCurrColor();



                              }

                              else if(stat==0){

                              System.out.println("置けない");

                              }

                              else if(stat==1){

                              gameBoard.changeCurrColor();

                              gameBoard.display();

                              System.out.printf("Game stat: Black:%d White:%d\n\n",gameBoard.getDiscSum(1),gameBoard.getDiscSum(-1));

                              }

                  }

                  else{

                  if(playerSide==1)

                    System.out.println("White's turn");

                  else

                    System.out.println("Black's turn");

                  stat=0;
                  if(gameLevel==1)
                      stat=minmax(gameBoard,5,gameBoard.getCurrColor());
                  else if(gameLevel==2)
                      stat=ab(gameBoard,5,-10000,10000);
                  else                   
                      stat=NegaScout(gameBoard,-10000,10000,5,5);


                  if(stat==-20000){

                    System.out.println("パスした");

                            gameBoard.changeCurrColor();



                  }

                  else{

                    System.out.printf("x=%d\ny=%d\n",stat/10,stat%10);

                    gameBoard.putDisc(stat/10, stat%10, true);

                    gameBoard.changeCurrColor();

                            gameBoard.display();

                            System.out.printf("Game stat: Black:%d White:%d\n\n",gameBoard.getDiscSum(1),gameBoard.getDiscSum(-1));

                  }

                  // System.out.println(gameBoard.getCurrColor());

                  }

                }

                System.out.printf("Game over: Black:%d White:%d\n",gameBoard.getDiscSum(1),gameBoard.getDiscSum(-1));

                if(gameBoard.getDiscSum(1)>gameBoard.getDiscSum(-1))

                  System.out.println("Black won");

                else if(gameBoard.getDiscSum(1)<gameBoard.getDiscSum(-1))

                  System.out.println("White won");

                else

                  System.out.println("Draw");

        }

        if(gameType==3){


        long time1=0,time2=0,time3=0;

        gameBoard.display();

            System.out.printf("Game status: Black:%d White:%d\n",gameBoard.getDiscSum(1),gameBoard.getDiscSum(-1));

            while(true){

              if(gameBoard.checkPass()){

                    gameBoard.changeCurrColor();

                    if(gameBoard.checkPass())

                    break;    //黒も白もパスする状態だったらゲーム終了

                    gameBoard.changeCurrColor();

                    }

              if(gameBoard.getCurrColor()==1)

              System.out.println("Black's turn");

              else

              System.out.println("White's turn");

              stat=0;

              if(gameBoard.getCurrColor()==1){
                  if(gameLevel==1){
                      long start=System.currentTimeMillis();
                      stat=minmax(gameBoard,5,gameBoard.getCurrColor());
                      long end=System.currentTimeMillis();
                      time1+=(end-start);
                  }
                  else if(gameLevel==2){
                    long start=System.currentTimeMillis();
                    stat=ab(gameBoard,5,-10000,10000);
                    long end=System.currentTimeMillis();
                    time2+=(end-start);
              }
                  else{
                    long start=System.currentTimeMillis();
                      stat=NegaScout(gameBoard,-10000,10000,5,5);
                      long end=System.currentTimeMillis();
                      time3+=(end-start);
                  }
              }

              else{
                  if(gameLevel2==1){
                      long start=System.currentTimeMillis();
                      stat=minmax(gameBoard,5,gameBoard.getCurrColor());
                      long end=System.currentTimeMillis();
                      time1+=(end-start);
                  }
                  else if(gameLevel2==2){
                        long start=System.currentTimeMillis();
                        stat=ab(gameBoard,5,-10000,10000);
                        long end=System.currentTimeMillis();
                        time2+=(end-start);
                  }
                  else{                   
                      long start=System.currentTimeMillis();
                      stat=NegaScout(gameBoard,-10000,10000,5,5);
                      long end=System.currentTimeMillis();
                      time3+=(end-start);
                  }
              }


          if(stat==-20000){

            System.out.println("パスした");

                    gameBoard.changeCurrColor();



          }

          else{

            System.out.printf("x=%d\ny=%d\n",stat/10,stat%10);

            gameBoard.putDisc(stat/10, stat%10, true);

            gameBoard.changeCurrColor();

                    gameBoard.display();

                    System.out.printf("Game stat: Black:%d White:%d\n\n",gameBoard.getDiscSum(1),gameBoard.getDiscSum(-1));

          }

            }

            System.out.printf("Game over: Black:%d White:%d\n",gameBoard.getDiscSum(1),gameBoard.getDiscSum(-1));

            if(gameBoard.getDiscSum(1)>gameBoard.getDiscSum(-1))

            System.out.println("Black won");

            else if(gameBoard.getDiscSum(1)<gameBoard.getDiscSum(-1))

            System.out.println("White won");

            else

            System.out.println("Draw");
            System.out.printf("minmax: %d %d\n",time1,clone1);
            System.out.printf("ab: %d %d\n",time2,clone2);
            System.out.printf("negascout: %d %d\n",time3,clone3);
        }

    }


    static int ab(Board bd, int depth, int a, int b){
    clone2++;    
    int c=0,max=a;
    int zahyou=0;
    int [][]possiblePosition = new int [64][2];   
   
    if(depth==0) return bd.eval1(bd.getCurrColor());

    for(int i=0;i<8;i++){                    //配置可能場所の確認
    for(int j=0;j<8;j++){
        if(bd.putDisc(i,j,false)==true){
            possiblePosition[c][0]=i;
            possiblePosition[c][1]=j;
            c++;
        }
    }
    }
   
    if(c==0&&depth==5) return -20000;
   
    if(c==0){
        int n;
        Board bcp = bd.clone();            //ボードのコピー作成   
        bcp.changeCurrColor();
        n=-ab(bcp,depth-1,-b,-max);   
        if(n>=b) return n;
        if(n>max){
            max=n;
        }
    }

    for (int k=0;k<c;k++){
        int n;
        Board bcp = bd.clone();            //ボードのコピー作成   
        bcp.putDisc(possiblePosition[k][0],possiblePosition[k][1],true);
        bcp.changeCurrColor();
        n=-ab(bcp,depth-1,-b,-max);            //ab()を再起呼び出し
        if(n>=b) return n;
        if(n>max){
            max=n;
            zahyou = possiblePosition[k][0]*10+possiblePosition[k][1];
        }
    }
   


    if(depth==5) return zahyou;
    return max;                        //最大値を返す
       

   
    }


    public static int minmax(Board bd,int depth,int curr){
            bd.currentColor=curr;
            Board bd1=bd.clone();
            //bd.display();ok
            int p=0;
            int eval,eval_max=-Integer.MAX_VALUE;
            int i,j;
            boolean pass=true;
            for(i=0;i<8;i++){
              for(j=0;j<8;j++){
              if(bd1.putDisc(i,j,true)){
                pass=false;
                eval=minlevel(bd1,depth-1,-curr);
                bd1=bd.clone();
                if(eval>eval_max){
                p=10*i+j;
                eval_max=eval;
                }
              }
              }
            }
            if(pass){
              return -20000;
            }
            else{
              return p;
            }
            }


    public static int minlevel(Board bd,int depth,int curr){
            boolean pass=true;
            clone1++;
            if(depth==0){
              return bd.eval1(bd.currentColor);
            }
            bd.currentColor=curr;
            Board bd1=bd.clone();
            int score,score_min=Integer.MAX_VALUE;
            int i,j;
            for(i=0;i<8;i++){
              for(j=0;j<8;j++){
              if(bd.putDisc(i,j,true)){
                pass=false;
                score=maxlevel(bd1,depth-1,-curr);
                bd1=bd.clone();
                if(score<score_min){
                score_min=score;
                }
              }
              }
            }
            if(pass){
              return bd.eval1(bd.currentColor);
            }
            else{
              return score_min;
            }
            }


    public static int maxlevel(Board bd,int depth,int curr){
            boolean pass=true;
            clone1++;
            if(depth==0){
              return bd.eval1(bd.currentColor);
            }
            bd.currentColor=curr;
            Board bd1=bd.clone();
            int score,score_max=-Integer.MAX_VALUE;
            int i,j;
            for(i=0;i<8;i++){
              for(j=0;j<8;j++){
              if(bd.putDisc(i,j,true)){
                pass=false;
                score=minlevel(bd1,depth-1,-curr);
                bd1=bd.clone();
                if(score>score_max){
                score_max=score;
                }
              }
              }
            }
            if(pass){
                return bd.eval1(bd.currentColor);
            }
            else{
                return score_max;
            }
            }

   
}
