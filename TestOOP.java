
package testoop;

import java.util.*;

public class TestOOP {

    
    public static void main(String[] args) {
        // ТЕСТ КЛАССА EMPLOYEE
        // создаем пять объектов класса Employee (Сотрудник)
        Employee[] Emp = new Employee[5]; // Создание массива
        // Инициализация массива
        Emp[0] = new Employee("Александр Потапович Егоров","Директор","egorov@mail.ru","+7-645-78-92",10000,50);
        Emp[1] = new Employee("Василий Андрееви Боровик", "Зам. директора", "grib@mail.ru", "+7-748-15-00", 5000, 20);
        Emp[2] = new Employee("Фотий Акакиевич Приходько", "Завхоз", "fotyj@mail.ru", "+7-498-49-72", 1000, 30);
        Emp[3] = new Employee("Константин Сергеевич Станиславский", "Режиссер", "teatr@mail.ru", "+7-533-68-49", 1000000, 78);
        Emp[4] = new Employee("Василий Иванович Немирович-Данченко", "Стажер", "veshalka@mail.ru", "+7-644-11-11", 2000000, 90);
        // распечатка данных всех сотрудников
        for (int i = 0; i < 5; i++){
            Emp[i].printDataOfУmployee();
        }
        // распечатка данных сотрудников старше сорока:
        System.out.println("СОТРУДНИКИ СТАРШЕ СОРОКА:");
        System.out.println();
        for (int i = 0; i < 5; i++){
            if(Emp[i].getAge() > 40)
            Emp[i].printDataOfУmployee();
        }
        System.out.println();
        System.out.println();  
        
        // ТЕСТ КЛАССА TikTak
        TikTak TheGame = new TikTak();
        TheGame.go();
    }
    
}

class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;
    
    // Конструктор по умолчанию
    public Employee(String fullName, String position, String email, String phoneNumber, int salary, int age) { 
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary  = salary; 
        this.age = age; 
    }
    // Геттеры
    public String getFullName() {return fullName;}
    public String getPosition() {return position;}
    public String getEmail() {return email;}
    public String getPhoneNumber() {return phoneNumber;}
    public int getSalary(){return salary;}
    public int getAge(){return age;}
   
    // Сеттеры
    public void setFullName(String fullName) {this.fullName = fullName;}
    public void setPosition(String position) {this.position = position;}
    public void setEmail(String email) {this.email = email;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setSalary(int salary) {this.salary = salary;}
    public void setAge(int age) {this.age = age;}
    
    // Метод печатает данные сотрудника
    public void printDataOfУmployee(){
        System.out.println("ФИО: " + this.getFullName());
        System.out.println("Должность: " + this.getPosition());
        System.out.println("E-mail: " + this.getEmail());
        System.out.println("Номер телефона: " + this.getPhoneNumber());
        System.out.println("Зарплата: " + this.getSalary());
        System.out.println("Возраст: " + this.getAge());
        System.out.println();
        System.out.println();
   }
}

class TikTak{
   private final char playerDot = 'x'; // Символ хода игрока
   private final char aiDot = '0'; // Символ хода компьютера
   private final char emptyDot = '*'; // Символ пустой ячейки
   private int feildSize; // Увеличить размер поля можно тут
   private char[][] field; // = new char[feildSize][feildSize]; // Игровое поле
   
   private int numberOfGames; /* Количество побед 
   или ничьих для окончания игровой сессии*/
   private int playerTurnCoorX; /* Переменная для хранения координат 
   только что сделанного хода игрока*/
   private int playerTurnCoorY;/* Переменная для хранения координат 
   только что сделанного хода игрока*/
  
   private Scanner sc = new Scanner(System.in);
   private Random rand = new Random();
   
   // конструктор
   TikTak(){
       System.out.println("Enter size of Feild: ");
       this.feildSize = sc.nextInt(); // ввод размера игрового поля
       this.field = new char[feildSize][feildSize];
       System.out.println("Enter number of games in session: ");
       this.numberOfGames = sc.nextInt(); // ввод количества игр в серии

    }
   
   public void go(){
        
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
                       
                if(chekWin(playerDot)){
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
                if(chekWin(aiDot)){
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
   private void  turnPlayer(){
        int x,y;
        do {
            System.out.println("Enter coordinate: ");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            playerTurnCoorX = x;
            playerTurnCoorY = y;
        } while(!(isCellEmpty(x,y)));
        
        field[x][y] = playerDot;
    }
   
  /* Ход компьютера. Метод принимает в качестве параметров
    координаты хода только что сделанного игроком*/
   private void  turnAi(int playerX, int playerY){
        int x,y;
        x = 0;// Инициализация координат, для компилятора
        y = 0;// Инициализация координат, для компилятора
        int countPlayerDot = 0;// Счетчик крестиков на одной линии
        int countEmptyDot = 0;// Счетчик пустых ячеек в линии
        
        System.out.println("Computer turn: ");
 
/* ВНИМАНИЕ!!!! Здесь начало блокирующего кода Суть его в том,
        что если на вертикали или горизонтали или диагонали
        больше одного крестика, она блокируется ноликом*/
        
            // ПРОВЕРКА ГОРИЗОНТАЛИ НА НАЛИЧИЕ БОЛЕЕ ЧЕМ ОДНОГО КРЕСТИКА:
            for(int j = 0; j < feildSize; j++) {
                if(field[playerX][j]==playerDot)
                    countPlayerDot++;
                else {
                    y = j;/* В пустую ячейку предварительно 
                    прописывается нолик*/
                    countEmptyDot++;
                }
            }
            if((countPlayerDot > 1)&&(countEmptyDot>0)) {
                x = playerX; //Блокируем строку
                field[x][y] = aiDot;
                return;
            }/*Если в строке больше одного крестика и есть пустые места
            прописываем нолик и выходим из метода*/
            
            //ЕСЛИ НЕТ, ТО ПРОВЕРЯЕМ ВЕРТИКАЛЬ
            countPlayerDot = 0; // Обнуляем оба счетчика
            countEmptyDot = 0;  // Обнуляем оба счетчика
            for(int i = 0; i < feildSize; i++) {
                if(field[i][playerY]==playerDot)
                    countPlayerDot++;
                else {
                    x = i;/* В пустую ячейку предварительно 
                    прописывается нолик*/
                    countEmptyDot++;
                }
            }
            if((countPlayerDot > 1)&&(countEmptyDot>0)) {
                y = playerY; //Блокируем столбец
                field[x][y] = aiDot;
                return;
            }/*Если в строке больше одного крестика и есть пустые места,
            прописываем нолик и выходим из метода*/
            
            //ЕСЛИ НЕТ, ТО ПРОВЕРЯЕМ ГЛАВНЫЕ ДИАГОНАЛИ:
            
            countPlayerDot = 0; // Обнуляем оба счетчика
            countEmptyDot = 0;  // Обнуляем оба счетчика
            //Проверяем находится ли поставленый игроком крест на ДИАГОНАЛИ 1
            if((playerX == playerY)) {
                for(int i = 0; i < feildSize; i++) {
                    if(field[i][i]==playerDot)
                    countPlayerDot++;
                    else {
                    x = y = i;/* В пустую ячейку предварительно 
                    прописывается нолик*/
                    countEmptyDot++;
                    }
                }   
                if((countPlayerDot > 1)&&(countEmptyDot>0)) {
                field[x][y] = aiDot;
                return;
                }/*Если в строке больше одного крестика и есть пустве места
                прописываем нолик и выходим из метода*/
            }
            
            countPlayerDot = 0; // Обнуляем оба счетчика
            countEmptyDot = 0;  // Обнуляем оба счетчика
            
            //Проверяем находится ли поставленый игроком крест на ДИАГОНАЛИ 2
            if((playerX + playerY)== (feildSize - 1)) {
                for(int i = 0; i < feildSize; i++) {
                    if(field[i][i]==playerDot)
                    countPlayerDot++;
                    else {
                    x = i;
                    y = (feildSize - 1) - x;
                    /* В пустую ячейку предварительно 
                    прописывается нолик*/
                    countEmptyDot++;
                    }
                }
                if((countPlayerDot > 1)&&(countEmptyDot>0)) {
                field[x][y] = aiDot;
                return;
                }/*Если в строке больше одного крестика и есть пустве места
                прописываем нолик и выходим из метода*/
            }
 /* ВНИМАНИЕ!!!! Здесь конец блокирующего кода Искуственного Интеллекта. 
                 От начала и до этого места можно закомментить         !!!!!*/
            
            /* Если проверки всех игровых линий дали отрицательные результаты, 
            ставим нолик случайным образом: */
      do{
            x = rand.nextInt(feildSize);
            y = rand.nextInt(feildSize);
      } while(!isCellEmpty(x,y));
        field[x][y] = aiDot;
    }
   
   /* Проверка: пуста ли ячейка*/
   private boolean isCellEmpty(int x,int y){
        if(x < 0 || y < 0 || x > (feildSize-1) || y > (feildSize-1) || (field[x][y]!= emptyDot))
            return false;
        else
            return true;
    }
   
   /* Проверяет остались пустые ячейки или нет*/
   private boolean isFieldFull(){
        for(int i = 0; i < feildSize; i++){
            for(int j = 0; j < feildSize; j++)
               if(field[i][j] == emptyDot)
                   return false;
        }
        return true;
    }
   
   /* Метод проверяет выиграл ли игрок либо компьютер или ничья, 
    принимая в качестве параметра символ хода компьютера или хода игрока*/
   private boolean chekWin(char whoWin) {
        
        int flag; /* переменная "выигрыша"*/
        
        // Проверка по строкам
        for (int i = 0; i < feildSize; i++) {
            flag = 1; // Предполагаем выигрыш
            for (int j = 0; j < feildSize; j++) {
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
        for (int i = 0; i < feildSize; i++) {
            flag = 1; // Опять предполагаем выигрыш
            for (int j = 0; j < feildSize; j++) {
                if(field[j][i] != whoWin) {
                    flag = 0;
                    break;
                }
            }
            if(flag == 1)
            return true;
        }
        // Проверка по диагонали 1
        flag = 1; // Опять предполагаем выигрыш
        for (int k = 0; k < feildSize; k++) {
            if(field[k][k] != whoWin) { 
                flag = 0;
                break;
            }
        }
        if(flag == 1) // Проверка: не выигрышная ли диагональ? 
            return true;
        // Проверка по диагонали 2
        flag = 1; // Опять предполагаем выигрыш
        for (int k = 0; k < feildSize; k++) {
            if(field[((feildSize - 1) - k)][k] != (whoWin)) {
                flag = 0;
                break;
            }
        }
        if(flag == 1) // Проверка: не выигрышная ли диагональ? 
            return true;
        
        return false; 
    }
   
    /* Метод заполняет игровое поле точками - пустые клетки*/ 
   private void initField(){
        for(int i = 0; i < feildSize; i++)
            for(int j = 0; j < feildSize; j++)
                field[i][j] = emptyDot;
    }
    
     /* Метод печатает текущее состояние игрового поля*/ 
   private void printField(){
        for(int i = 0; i < feildSize; i++) {
            for(int j = 0; j < feildSize; j++)
               System.out.print(field[i][j]+ "   ");
            System.out.println();
            System.out.println();
        }
    }
   

}