package work;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class MenuController {
	 Vector<Information> list= new Vector<Information>(),listStatus;
	 String name =" ", nm=" ";
	 String lines[], input=null;
	 String szStr;
	 byte bKbd[] = new byte[256];
	 StringTokenizer st;
	 int j = 0;
	 
	 public void userDialog(){
		 Scanner sc = new Scanner(System.in);
		 boolean notExit = true;
		 while(notExit) {
			 input = sc.nextLine();
			 while (input.equals("")) {
				    input = sc.nextLine();
				}
			 lines = input.split(" ");
			 if(lines.length>=2)
			 switch(lines[2].toLowerCase()){
			 case "add": { 
				 add();
				 break;
				 }
			 case "print": { 
				 print();
				 break; 
				 }
			 case "delete": { 
				 delete();
				 break; 
				 }
			 case "status": { 
				 status();
				 break; 
				 }
			 case "printf": { 
				 printf();
				 break; 
				 }
			 case "exit" : {
				 notExit = false;
				 break;
				 }
			 default: {
				 System.out.println("Error! Wrong command");
				 System.out.println("Please, try again");
				 break;
			 }
			 }
			 else {
				 System.out.println("Error! Wrong command");
				 System.out.println("Please, try again");
			
			 }
			 }
		 }
	 
	 //Получить имя книги/музыки/фильма
	 public String takeName(int k){
		 nm="";
		 for(int i=k;i<lines.length;i++)
			 nm+=lines[i]+" ";
		 return nm;
	 }
	 
	 //Добавить статус или новую книгу/музыку/фильм
	 public void add(){
		 //$ manager add status <name>
		 listStatus = new Vector<Information>();
		 int k=1;
		 if(lines.length>=5 && lines[3].toLowerCase().equals("status")){
			 name = takeName(4);
			 if(!name.equals("") && list.size()!=0){
				 //вывод всех найденных объектов с введенным именем
			 for(int i=0;i<list.size();i++)
				 if(name.equals(list.get(i).getName())){
					 listStatus.add(list.get(i));
					 System.out.print(k+++". ");
					 list.get(i).printName();
					 }
			 String answer = null, answLines[];
			 j=num();
			 Scanner in = new Scanner(System.in);
			 System.out.print("Enter status ");
			 answer = in.nextLine();
			 while (answer.equals("")) {
				 answer = in.nextLine();
				 }
			 answLines= answer.split(" ");
			 if(answLines.length==1)
				 answer = answLines[0];
			 else if(answLines.length>1)
				 answer = answLines[0]+" "+answLines[1];
			 int i=0;
			 boolean fl = true;
			 while((i<list.size()) && fl)
				 if(list.get(i).equals(listStatus.get(j-1))){ 
					 list.get(i).addStatus(answer.toUpperCase());
					 fl = false;
					 }
				 else i++;
			 }
		 }
		 else if(lines.length>=4 && !lines[3].toLowerCase().equals("status")){
			 // $ manager add <name>
			 name=takeName(3);
			 if(!name.equals("")){
			 System.out.println("What is "+name+"?");
			 System.out.println("1. Book");
			 System.out.println("2. Film");
			 System.out.println("3. Music");
			 
			 Scanner in = new Scanner(System.in);
			 boolean flag = true;
					while(flag){
						System.out.println("Type answer");
						try{
							j=in.nextInt();
							if (j<1 || j>3) 
						        throw new IllegalArgumentException("Enter a number 1, 2 or 3.");
							else { 
								if(j==1)
									 list.addElement(new Book(name));
								 else if(j==2) 
									 list.addElement(new Film(name));
								 else if (j==3) 
									 list.addElement(new Music(name));
							flag = false;
							}
							}catch (InputMismatchException e){
								System.out.println("Неверное значение!");
								in.next();
								}
						catch(IllegalArgumentException e){
							System.out.println(e.getMessage());
						}
					} 
			}
		 }
		 
	 }
	 
	 //$ manager delete <type> <name>
	 public void delete(){
		 if(lines.length>=5){
		 name=takeName(4);
		 int i=0;
		 boolean flag = true;
		 if(!lines[3].equals("") && !name.equals("") && list.size()!=0){
		 while(i<list.size() && flag){
			 if(lines[3].toLowerCase().contains("book")){
				 if((list.get(i) instanceof Book)&& name.equals(list.get(i).getName())){
					 list.remove(i);
					 flag = false;
				 }
				 }
			 else if(lines[3].toLowerCase().contains("music")){
				 if((list.get(i) instanceof Music)&& name.equals(list.get(i).getName())){
					 list.remove(i);
					 flag = false;
				 }
				 }
			 else if(lines[3].toLowerCase().contains("film")){
				 if((list.get(i) instanceof Film)&& name.equals(list.get(i).getName())){
					 list.remove(i);
					 flag = false;
				 }
				 }
			 i++;
			 }
		 }
		 }
		 }
	 
	 //$ manager print <type>
	 public void print(){
		 int j=1;
		 if(lines.length>=4){
		 if(!lines[3].equals("") && list.size()!=0){
		 if(lines[3].toLowerCase().contains("book")){
			 for(int i=0;i<list.size();i++)
				 if(list.get(i) instanceof Book){
					 System.out.print(j+++". ");
					 list.get(i).print();
				 }
			 }
		 else if(lines[3].toLowerCase().contains("music")){
			 for(int i=0;i<list.size();i++)
				 if(list.get(i) instanceof Music){
					 System.out.print(j+++". ");
					 list.get(i).print();
				 }
			 }
		 else if(lines[3].toLowerCase().contains("film")){
			 for(int i=0;i<list.size();i++)
				 if(list.get(i) instanceof Film){
					 System.out.print(j+++". ");
					 list.get(i).print();
				 }
			 }
		 }
		 }
}
	// $ manager printf <type> <name>
	 public void printf(){
		 int j=1;
		 String stat ="";
		 if(lines.length>=5){
		 if(list.size()!=0){
		 if(lines.length ==5){
			 if(!lines[3].equals("")&& !lines[4].equals(""))
				 stat = lines[4].toUpperCase();
		 }
		 else if(lines.length==6){
			 if(!lines[3].equals("") && !lines[4].equals("") && !lines[5].equals(""))
				 stat = lines[4].toUpperCase()+" "+lines[5].toUpperCase();
		 }
		 
		 if(lines[3].toLowerCase().contains("book")){
			 for(int i=0;i<list.size();i++){
				 if((list.get(i) instanceof Book))
					 if(list.get(i).getStatus().equals(stat)){
						 System.out.print(j+++". ");
						 list.get(i).print();
					 }
				 }
			 }
		 else if(lines[3].toLowerCase().contains("music")){
			 for(int i=0;i<list.size();i++){
				 if((list.get(i) instanceof Music))
					 if(list.get(i).getStatus().equals(stat)){
						 System.out.print(j+++". ");
						 list.get(i).print();
					 }
				 }
			 }
		 else if(lines[3].toLowerCase().contains("film")){
			 for(int i=0;i<list.size();i++){
				 if((list.get(i) instanceof Film))
					 if(list.get(i).getStatus().equals(stat)){
						 System.out.print(j+++". ");
						 list.get(i).print();
					 }
				 }
			 }
		 }
		 }
	 }
		 
	 // $ manager status <name>
	 public void status(){
		 int j=1;
		 if(lines.length>=4)
		 name=takeName(3);
		 if(!name.equals(""))
		 for(Information element : list){
			 if(name.equals(element.getName())){
				 System.out.print(j+++". ");
				 element.print();
			 }
			 }
		 }
	 
	 public int num(){	
			Scanner in = new Scanner(System.in);
		int n;
			while(true){
				System.out.print("Enter a number ");
				try{
					n=in.nextInt();
					 if (n<0 || n>list.size()) 
					        throw new IllegalArgumentException("Error! Type a number and status");
					else return n;
					}catch (InputMismatchException e){
						System.out.println("Неверное значение!");
						in.next();
					
						}
				catch(IllegalArgumentException e){
					System.out.println(e.getMessage());
				}
			}
		}
}
