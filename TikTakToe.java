/* 
* JavaLevel_1. Lessson3. Студент ТИМУР МУСТАФИН.
*/

package tiktaktoe;

import java.util.*;

public class TikTakToe {

   

   final char PLAYER_DOT = 'x'; // Символ хода игрока
   final char AI_DOT = '0'; // Символ хода компьютера
   final char EMPTY_DOT = '.'; // Символ пустой ячейки
   final int FIELD_SIZE = 3; // Увеличить размер поля можно тут
   final int numberOfGames = 2; /* Количество побед 
   или ничьих для окончания игровой сессии*/
   char[][] field = new char[FIELD_SIZE][FIELD_SIZE]; // Массив игрового поля
   
   static int playerTurnCoorX; /* Переменная для хранения координат 
   только что сделанного хода игрока*/
   static int playerTurnCoorY;/* Переменная для хранения координат 
   только что сделанного хода игрока*/
  
   Scanner sc = new Scanner(System.in);
   Random rand = new Random();
   
    public static void main(String[] args) {
    TikTakToe TheGame = new TikTakToe();
    TheGame.go();
        
        
    }
    
    // Метод содержащий игру в целом
    void go(){
        
        int countPlayerWin = 0; //Счетик побед игрока
        int countAiWin = 0; //Счетик побед компьютера
        int countOfDraw = 0; //Счетик ничьих
        int counterOfGames = 1;  //Счетик количества сыгранных игр
    
        initField();
        printField();
        /* начало игровой сесси которая длится пока одна из сторон 
        не выиграет  numberInSeria раз либо не случится столько же ничьих*/  
        while((countPlayerWin < numberOfGames)&&(countAiWin < numberOfGames)&&(countOfDraw < numberOfGames)) 
        {
            System.out.println("GAME#" + counterOfGames + ": ");
            System.out.println();
            
            while(true){
                turnPlayer();
                printField();
                       
                if(chekWin(PLAYER_DOT)){
                    countPlayerWin++;
                    System.out.println("You won!");
                    break;
                }
                if (isFieldFull()){
                    System.out.println("Sorry, draw...");
                    countOfDraw++;
                    break;
                }
                
                turnAi(playerTurnCoorX, playerTurnCoorY);
                printField();
                if(chekWin(AI_DOT)){
                    countAiWin++;
                    System.out.println("AI won!");
                    break;
                }
                if (isFieldFull()){
                    System.out.println("Sorry, draw...");
                    countOfDraw++;
                    break;
                }
            
            }
            System.out.println("You: " + countPlayerWin);
            System.out.println("Computer: " + countAiWin);
            initField();
            counterOfGames++;
            System.out.println();
            //Следуюшая игра
        } 
       
      System.out.println("End of session!");
    }
    
    // Ход игрока
    void  turnPlayer(){
        int x,y;
        do {
            System.out.println("Enter coordinate: ");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            playerTurnCoorX = x;
            playerTurnCoorY = y;
        } while(!(isCellEmpty(x,y)));
        
        field[x][y] = PLAYER_DOT;
    }
    
    /* Ход компьютера. Метод принимает в качестве параметров
    координаты хода только что сделанного игроком*/
    void  turnAi(int playerX, int playerY){
        int x,y;
        x = 0;// Инициализация координат, для компилятора
        y = 0;// Инициализация координат, для компилятора
        int countPlayerDot = 0;// Счетчик крестиков на одной линии
        int countEmptyDot = 0;// Счетчик пустых ячеек в линии
        
        System.out.println("Computer turn: ");
 
/* ВНИМАНИЕ!!!! Здесь начало блокирующего кода Искуственного Интеллекта. 
            Отсюда и до конца блокирующего кода  можно закомментить  !!!!!*/
        
            // ПРОВЕРКА СТРОКИ НА НАЛИЧИЕ БОЛЕЕ ЧЕМ ОДНОГО КРЕСТИКА:
            for(int j = 0; j < FIELD_SIZE; j++) {
                if(field[playerX][j]==PLAYER_DOT)
                    countPlayerDot++;
                else {
                    y = j;/* В пустую ячейку предварительно 
                    прописывается нолик*/
                    countEmptyDot++;
                }
            }
            if((countPlayerDot > 1)&&(countEmptyDot>0)) {
                x = playerX; //Блокируем строку
                field[x][y] = AI_DOT;
                return;
            }/*Если в строке больше одного крестика и есть пустые места
            прописываем нолик и выходим из метода*/
            
            //ЕСЛИ НЕТ, ТО ПРОВЕРЯЕМ СТОЛБЕЦ
            countPlayerDot = 0; // Обнуляем оба счетчика
            countEmptyDot = 0;  // Обнуляем оба счетчика
            for(int i = 0; i < FIELD_SIZE; i++) {
                if(field[i][playerY]==PLAYER_DOT)
                    countPlayerDot++;
                else {
                    x = i;/* В пустую ячейку предварительно 
                    прописывается нолик*/
                    countEmptyDot++;
                }
            }
            if((countPlayerDot > 1)&&(countEmptyDot>0)) {
                y = playerY; //Блокируем столбец
                field[x][y] = AI_DOT;
                return;
            }/*Если в строке больше одного крестика и есть пустые места,
            прописываем нолик и выходим из метода*/
            
            //ЕСЛИ НЕТ, ТО ПРОВЕРЯЕМ ГЛАВНЫЕ ДИАГОНАЛИ:
            
            countPlayerDot = 0; // Обнуляем оба счетчика
            countEmptyDot = 0;  // Обнуляем оба счетчика
            //Проверяем находится ли поставленый игроком крест на ДИАГОНАЛИ 1
            if((playerX == playerY)) {
                for(int i = 0; i < FIELD_SIZE; i++) {
                    if(field[i][i]==PLAYER_DOT)
                    countPlayerDot++;
                    else {
                    x = y = i;/* В пустую ячейку предварительно 
                    прописывается нолик*/
                    countEmptyDot++;
                    }
                }   
                if((countPlayerDot > 1)&&(countEmptyDot>0)) {
                field[x][y] = AI_DOT;
                return;
                }/*Если в строке больше одного крестика и есть пустве места
                прописываем нолик и выходим из метода*/
            }
            
            countPlayerDot = 0; // Обнуляем оба счетчика
            countEmptyDot = 0;  // Обнуляем оба счетчика
            
            //Проверяем находится ли поставленый игроком крест на ДИАГОНАЛИ 2
            if((playerX + playerY)== (FIELD_SIZE - 1)) {
                for(int i = 0; i < FIELD_SIZE; i++) {
                    if(field[i][i]==PLAYER_DOT)
                    countPlayerDot++;
                    else {
                    x = i;
                    y = (FIELD_SIZE - 1) - x;
                    /* В пустую ячейку предварительно 
                    прописывается нолик*/
                    countEmptyDot++;
                    }
                }
                if((countPlayerDot > 1)&&(countEmptyDot>0)) {
                field[x][y] = AI_DOT;
                return;
                }/*Если в строке больше одного крестика и есть пустве места
                прописываем нолик и выходим из метода*/
            }
 /* ВНИМАНИЕ!!!! Здесь конец блокирующего кода Искуственного Интеллекта. 
                 От начала и до этого места можно закомментить         !!!!!*/
            
            /* Если проверки всех игровых линий дали отрицательные результаты, 
            ставим нолик случайным образом: */
      do{
            x = rand.nextInt(FIELD_SIZE);
            y = rand.nextInt(FIELD_SIZE);
      } while(!isCellEmpty(x,y));
        field[x][y] = AI_DOT;
    }
  
    
    /* Проверяет пуста ли ячейка в которую ставят крестик или нолик 
    на текущем ходу*/
    boolean isCellEmpty(int x,int y){
        if(x < 0 || y < 0 || x > (FIELD_SIZE-1) || y > (FIELD_SIZE-1) || (field[x][y]!= EMPTY_DOT))
            return false;
        else
            return true;
    }
    
    /* Проверяет остались пустые ячейки или нет*/
    boolean isFieldFull(){
        for(int i = 0; i < FIELD_SIZE; i++){
            for(int j = 0; j < FIELD_SIZE; j++)
               if(field[i][j] == EMPTY_DOT)
                   return false;
        }
        return true;
    }
    
    /* Метод проверяет выиграл ли игрок либо компьютер или ничья, 
    принимая в качестве параметра символ хода компьютера или хода игрока*/
    boolean chekWin(char whoWin) {
        
        int flag; /* переменная "выигрыша", 
        если при проверки любой строки, столбца или диагонали 
        не меняет значение на ноль значит - выходим из метода
        и возвращаем TRUE, то есть ВЫИГРЫШ*/
        
        // Проверка по строкам
        for (int i = 0; i < FIELD_SIZE; i++) {
            flag = 1; // Предполагаем выигрыш
            for (int j = 0; j < FIELD_SIZE; j++) {
                if(field[i][j] != whoWin){ 
                    flag = 0;
                    break; /* Как только проверяемый символ 
                    не совпадает с выигрышным - сразу 
                    переход к следующей строке поскольку 
                    дальше проверять данную строку нет смысла*/
                }
            }
            if(flag == 1) /* Проверка: не выигрышная ли строка? 
                если да, выходим из метода с ПОБЕДОЙ*/  
            return true;
        }
           
        // Проверка по столбцам
        for (int i = 0; i < FIELD_SIZE; i++) {
            flag = 1; // Опять предполагаем выигрыш
            for (int j = 0; j < FIELD_SIZE; j++) {
                if(field[j][i] != whoWin) {
                    flag = 0;
                    break;/* Как только проверяемый символ 
                    не совпадает с выигрышным - сразу 
                    переход к следующему столбцу, поскольку 
                    дальше проверять данный столбец нет смысла*/
                }
            }
            if(flag == 1)/* Проверка: не выигрышный ли столбец? 
                если да, выходим из метода с ПОБЕДОЙ*/ 
            return true;
        }
           
         // Проверка по диагонали 1
        flag = 1; // Опять предполагаем выигрыш
        for (int k = 0; k < FIELD_SIZE; k++) {
            if(field[k][k] != whoWin) { 
                flag = 0;
                break;
            }
        }
        if(flag == 1) // Проверка: не выигрышная ли диагональ? 
            return true;
        
         // Проверка по диагонали 2
        flag = 1; // Опять предполагаем выигрыш
        for (int k = 0; k < FIELD_SIZE; k++) {
            if(field[((FIELD_SIZE - 1) - k)][k] != (whoWin)) {
                flag = 0;
                break;
            }
        }
        if(flag == 1) // Проверка: не выигрышная ли диагональ? 
            return true;
        
        return false; /* в случае если flag во всех проверках 
        менял значение на ноль - значит выигрыша не было */
    }
        
    /* Метод заполняет игровое поле точками - пустые клетки*/ 
    void initField(){
        for(int i = 0; i < FIELD_SIZE; i++)
            for(int j = 0; j < FIELD_SIZE; j++)
                field[i][j] = EMPTY_DOT;
    }
    
     /* Метод печатает текущее состояние игрового поля*/ 
    void printField(){
        for(int i = 0; i < FIELD_SIZE; i++) {
            for(int j = 0; j < FIELD_SIZE; j++)
               System.out.print(field[i][j]+ "   ");
            System.out.println();
            System.out.println();
        }
    }
}
