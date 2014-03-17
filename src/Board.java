class Board{
    static final int BLACK=1;
    static final int WHITE=-1;
    static final int EMPTY=0;
    static final int WALL=2;
    static final int WIDTH=8;
    static final int HEIGHT=8;
    int currentColor;
    int turn;
    int board[][]= new int [8][8];
    Board(){
        init();
    }
    Board(int[][] board1,int currentColor1,int turn1){
        currentColor=currentColor1;
        int t[][]=new int[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                t[i][j]=board1[i][j];
            }
        }
        board=t;
        turn=turn1;
    }

    int getCurrColor(){
        return currentColor;
    }

    void setCurrColor(int color){
        currentColor=color;
    }

    void changeCurrColor(){
        int color=getCurrColor();
        if(color==BLACK){
            currentColor=WHITE;
        }else if(color==WHITE){
            currentColor=BLACK;
        }
        turn++;
    }

    int getTurns(){
        return turn;
    }

    void setTurns(int num){
        turn=num;
    }

    int getDisc(int x,int y){
        return board[x][y];
    }
    void setDisc(int x, int y, int disc){
        board[x][y]=disc;
    }
    void init(){
        currentColor=BLACK;
        turn=0;
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                board[i][j]=EMPTY;
            board[3][3]=board[4][4]=WHITE;
            board[3][4]=board[4][3]=BLACK;
    }

    boolean putDisc(int x, int y, boolean dataUpdate){
        int cnt=0,i,hanten=0,hanten1=0;
        int reverse[][]= new int [7][2];
        if(board[x][y]!=EMPTY){
            //空ではないと置けない
            return false;
        }
        else{
            //　置いて点より上を調べる
            for(i=1;(x-i)>=0;i++){
                if(board[x-i][y]==-currentColor){
                    reverse[cnt][0]=x-i;
                    reverse[cnt][1]=y;
                    cnt++;
                }
                else{
                    if(board[x-i][y]==currentColor && cnt!=0){
                        for(int j=0;j<cnt;j++){
                            if(dataUpdate==true){
                                board[reverse[j][0]][reverse[j][1]]=-board[reverse[j][0]][reverse[j][1]];
                            }
                            hanten1++;
                        }
                    }
                    hanten+=hanten1;
                    hanten1=0;
                    break;
                }
            }
            cnt=0;
            //　下
            for(i=1;(x+i)<=7;i++){
                if(board[x+i][y]==-currentColor){
                    reverse[cnt][0]=x+i;
                    reverse[cnt][1]=y;
                    cnt++;
                }
                else{
                    if(board[x+i][y]==currentColor && cnt!=0){
                        for(int j=0;j<cnt;j++){
                            if(dataUpdate==true){
                                board[reverse[j][0]][reverse[j][1]]=-board[reverse[j][0]][reverse[j][1]];
                            }
                            hanten1++;
                        }
                    }
                    hanten+=hanten1;
                    hanten1=0;
                    break;
                }
            }
            cnt=0;
            // 右
            for(i=1;(y+i)<=7;i++){
                if(board[x][y+i]==-currentColor){
                    reverse[cnt][0]=x;
                    reverse[cnt][1]=y+i;
                    cnt++;
                }
                else{
                    if(board[x][y+i]==currentColor && cnt!=0){
                        for(int j=0;j<cnt;j++){
                            if(dataUpdate==true){
                                board[reverse[j][0]][reverse[j][1]]=-board[reverse[j][0]][reverse[j][1]];
                            }
                            hanten1++;
                        }
                    }
                    hanten+=hanten1;
                    hanten1=0;
                    break;
                }
            }
            cnt=0;
            //左
            for(i=1;(y-i)>=0;i++){
                if(board[x][y-i]==-currentColor){
                    reverse[cnt][0]=x;
                    reverse[cnt][1]=y-i;
                    cnt++;
                }
                else{
                    if(board[x][y-i]==currentColor && cnt!=0){
                        for(int j=0;j<cnt;j++){
                            if(dataUpdate==true){
                                board[reverse[j][0]][reverse[j][1]]=-board[reverse[j][0]][reverse[j][1]];
                            }
                            hanten1++;
                        }
                    }
                    hanten+=hanten1;
                    hanten1=0;
                    break;
                }
            }
            cnt=0;
            //右上
            for(i=1;(y+i)<=7 && (x-i)>=0;i++){
                if(board[x-i][y+i]==-currentColor){
                    reverse[cnt][0]=x-i;
                    reverse[cnt][1]=y+i;
                    cnt++;
                }
                else{
                    if(board[x-i][y+i]==currentColor && cnt!=0){
                        for(int j=0;j<cnt;j++){
                            if(dataUpdate==true){
                                board[reverse[j][0]][reverse[j][1]]=-board[reverse[j][0]][reverse[j][1]];
                            }
                            hanten1++;
                        }
                    }
                    hanten+=hanten1;
                    hanten1=0;
                    break;
                }
            }
            cnt=0;
            //右下
            for(i=1;(y+i)<=7 && (x+i)<=7;i++){
                if(board[x+i][y+i]==-currentColor){
                    reverse[cnt][0]=x+i;
                    reverse[cnt][1]=y+i;
                    cnt++;
                }
                else{
                    if(board[x+i][y+i]==currentColor && cnt!=0){
                        for(int j=0;j<cnt;j++){
                            if(dataUpdate==true){
                                board[reverse[j][0]][reverse[j][1]]=-board[reverse[j][0]][reverse[j][1]];
                            }
                            hanten1++;
                        }
                    }
                    hanten+=hanten1;
                    hanten1=0;
                    break;
                }
            }
            cnt=0;
            //左上
            for(i=1;(y-i)>=0 && (x-i)>=0;i++){
                if(board[x-i][y-i]==-currentColor){
                    reverse[cnt][0]=x-i;
                    reverse[cnt][1]=y-i;
                    cnt++;
                }
                else{
                    if(board[x-i][y-i]==currentColor && cnt!=0){
                        for(int j=0;j<cnt;j++){
                            if(dataUpdate==true){
                                board[reverse[j][0]][reverse[j][1]]=-board[reverse[j][0]][reverse[j][1]];
                            }
                            hanten1++;
                        }
                    }
                    hanten+=hanten1;
                    hanten1=0;
                    break;
                }
            }
            cnt=0;
            //左した
            for(i=1;(y-i)>=0 && (x+i)<=7;i++){
                if(board[x+i][y-i]==-currentColor){
                    reverse[cnt][0]=x+i;
                    reverse[cnt][1]=y-i;
                    cnt++;
                }
                else{
                    if(board[x+i][y-i]==currentColor && cnt!=0){
                        for(int j=0;j<cnt;j++){
                            if(dataUpdate==true){
                                board[reverse[j][0]][reverse[j][1]]=-board[reverse[j][0]][reverse[j][1]];
                            }
                            hanten1++;
                        }
                    }
                    hanten+=hanten1;
                    hanten1=0;
                    break;
                }
            }
            cnt=0;
            // 反転が1つでもあったらその手を打てる
            if(hanten!=0){
                if(dataUpdate==true)
                    setDisc(x,y,currentColor);
                return true;               
            }
            // 反転するところがないとその手を打てない
            else
                return false;
        }
    }

    boolean checkPass(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(putDisc(i,j,false))
                    return false;
            }
        }
        return true;
    }
    public Board clone(){
        Board b2 = new Board(board,currentColor,turn);
        return b2;
    }

    int eval(int color){
        int [][] h ={
                {120,-20, 20,  5,  5, 20,-20,120},
                {-20,-40, -5, -5, -5, -5,-40,-20},
                { 20, -5, 15,  3,  3, 15, -5, 20},
                {  5, -5,  3,  3,  3,  3, -5,  5},
                {  5, -5,  3,  3,  3,  3, -5,  5},
                { 20, -5, 15,  3,  3, 15, -5, 20},
                {-20,-40, -5, -5, -5, -5,-40,-20},
                {120,-20, 20,  5,  5, 20,-20,120},
                };

        int i,j;
        int sc=0; //得点ボード
        int k=0; //石の数
        int va=0; //全部の評価
        int o=0; //可能手数

        for(i=0;i<8;i++){
            for(j=0;j<8;j++){
                if(board[i][j]==color){
                    sc=sc+h[i][j];
                    k++;
                }
                if(putDisc(i,j,false)){
                o++;
                }
            }
        }
        va=va+sc*9999/776;
        va=va+k*9999/64;
        va=va+o*9999/64;
        return va;
    }

    int eval1(int color){
        int [][] h ={
                {120,-20, 20,  5,  5, 20,-20,120},
                {-20,-40, -5, -5, -5, -5,-40,-20},
                { 20, -5, 15,  3,  3, 15, -5, 20},
                {  5, -5,  3,  3,  3,  3, -5,  5},
                {  5, -5,  3,  3,  3,  3, -5,  5},
                { 20, -5, 15,  3,  3, 15, -5, 20},
                {-20,-40, -5, -5, -5, -5,-40,-20},
                {120,-20, 20,  5,  5, 20,-20,120},
                };
        int i,j,sc=0,va=0;

        for(i=0;i<8;i++){
            for(j=0;j<8;j++){
                if(board[i][j]==color){
                    sc=sc+h[i][j];
                }
            }
        }
        va=sc*9999/776;
        return va;
    }
    int eval3(int color){

        int myColor=0,oppColor=0;
        int opp=-color;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board[i][j]==color)
                    myColor++;
                else if(board[i][j]==opp)
                    oppColor++;
            }
        }
        return ((myColor-oppColor)*5000/64);
   
    }

    int eval2(int color, int tesuu){
        int myColor=0,oppColor=0;
        int opp=-color;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board[i][j]==color)
                    myColor++;
                else if(board[i][j]==opp)
                    oppColor++;
            }
        }
        return ((myColor-oppColor)*5000/64)+(tesuu*5000/64);
    }

   
    int getDiscSum(int color){
        int count=0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if (board[i][j]==color) count++;
            }
        }
        return count;
    }

    void display(){
        System.out.println("    0  1  2  3  4  5  6  7  ");
        System.out.println("  +---+---+---+---+---+---+---+---+");
        for(int i=0;i<8;i++){
            System.out.printf(" %d |",i);
            for(int j=0;j<8;j++){
                if(board[i][j]==BLACK)
                    System.out.print(" ○");
                else if(board[i][j]==WHITE)
                    System.out.print(" ●");
                else if(board[i][j]==EMPTY)
                    System.out.print("  ");
                System.out.print("|");
            }
            System.out.println();
            System.out.println("  +---+---+---+---+---+---+---+---+");
        }
    }
}