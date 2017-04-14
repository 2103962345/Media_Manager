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
			 if(lines.length>=1)
			 switch(lines[0].toLowerCase()){
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
			 case "help" : {
				 help();
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
		 //add status <name>
		 listStatus = new Vector<Information>();
		 int k=1;
		 if(lines.length>=3 && lines[1].toLowerCase().equals("status")){
			 if(list.size()!=0){
			 name = takeName(2);
			 if(!name.equals("")){
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
			 } else System.out.println("Wrong command (not enough arguments).");
			 } else System.out.println("Add some objects");
		 }
		 else if(lines.length>=2 && !lines[1].toLowerCase().equals("status")){
			 // add <name>
			 name=takeName(1);
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
								if(j==1 && search("Book",name))
									 list.addElement(new Book(name));
								 else if(j==2 && search("Film",name)) 
									 list.addElement(new Film(name));
								 else if (j==3 && search("Music",name)) 
									 list.addElement(new Music(name));
							flag = false;
							}
							}catch (InputMismatchException e){
								System.out.println("Wrong number!");
								in.next();
								}
						catch(IllegalArgumentException e){
							System.out.println(e.getMessage());
						}
					} 
			} else System.out.println("Wrong command (not enough arguments).");
		 } else System.out.println("Wrong command.");
		 
	 }
	 
	 //delete <type> <name>
	 public void delete(){
		 boolean flag = true;
		 if(lines.length>=3){
		 name=takeName(2);
		 int i=0;
		 if(list.size()!=0){
		 if(!lines[1].equals("") && !name.equals("")){
		 while(i<list.size() && flag){
			 if(lines[1].toLowerCase().contains("book")){
				 if((list.get(i) instanceof Book)&& name.equals(list.get(i).getName())){
					 list.remove(i);
					 flag = false;
				 }
				 }
			 else if(lines[1].toLowerCase().contains("music")){
				 if((list.get(i) instanceof Music)&& name.equals(list.get(i).getName())){
					 list.remove(i);
					 flag = false;
				 }
				 }
			 else if(lines[1].toLowerCase().contains("film")){
				 if((list.get(i) instanceof Film)&& name.equals(list.get(i).getName())){
					 list.remove(i);
					 flag = false;
				 }
				 }
			 i++;
			 }
		 } else System.out.println("Wrong command.");
		 }else System.out.println("Add some objects.");
		 } else System.out.println("Wrong command (not enough arguments).");
		 if (flag) System.out.println("Wrong command.");
		 }
	 
	 //print <type>
	 public void print(){
		 int j=1;
		 if(lines.length>=2){
			 if(list.size()!=0){
		 if(!lines[1].equals("")){
		 if(lines[1].toLowerCase().contains("book")){
			 for(int i=0;i<list.size();i++)
				 if(list.get(i) instanceof Book){
					 System.out.print(j+++". ");
					 list.get(i).print();
				 }
			 }
		 else if(lines[1].toLowerCase().contains("music")){
			 for(int i=0;i<list.size();i++)
				 if(list.get(i) instanceof Music){
					 System.out.print(j+++". ");
					 list.get(i).print();
				 }
			 }
		 else if(lines[1].toLowerCase().contains("film")){
			 for(int i=0;i<list.size();i++)
				 if(list.get(i) instanceof Film){
					 System.out.print(j+++". ");
					 list.get(i).print();
				 }
			 } else System.out.println("Wrong command.");
		 }else System.out.println("Wrong command.");
		 } else System.out.println("Add some objects.");
		 } else System.out.println("Wrong command.");
}
	// printf <status>
	 public void printf(){
		 int j=1;
		 String stat ="";
		 if(lines.length>=2){
		 if(list.size()!=0){
		 if(lines.length ==2){
			 if(!lines[1].equals(""))
				 stat = lines[1].toUpperCase();
		 }
		 else if(lines.length==3){
			 if(!lines[1].equals("") && !lines[2].equals(""))
				 stat = lines[1].toUpperCase()+" "+lines[2].toUpperCase();
		 }
			 for(int i=0;i<list.size();i++){
					 if(list.get(i).getStatus().equals(stat)){
						 System.out.print(j+++". ");
						 list.get(i).print();
					 }
				 }
		 } else System.out.println("Add some objects.");
		 } else System.out.println("Wrong command (not enough arguments).");
	 }
		 
	 // status <name>
	 public void status(){
		 int j=1;
		 if(lines.length>=2)
		 name=takeName(1);
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
						System.out.println("Wrong number!");
						in.next();
						}
				catch(IllegalArgumentException e){
					System.out.println(e.getMessage());
				}
			}
		}
	 //help
	 public void help(){
		 System.out.println("Commands: ");
		 System.out.println("\t - for adding book, music or film type");
		 System.out.println("add <name>");
		 System.out.println("\t - for adding status for book, music or film type ");
		 System.out.println("add status <name>");
		 System.out.println("\t - to delete book, music or film type");
		 System.out.println("delete <type> <name>");
		 System.out.println("\t - to show all books, music or films type ");
		 System.out.println("print <type>");
		 System.out.println("\t - to show all books, music or films with specific status type ");
		 System.out.println("printf <status>");
		 System.out.println("\t - to show status for specific book, music or film with type ");
		 System.out.println("status <name>");
		 System.out.println("\t - for help type ");
		 System.out.println("help");
		 System.out.println("You can add 3 different type");
		 System.out.println("For books: ");
		 System.out.println("\t * reading ");
		 System.out.println("\t * want read (default) ");
		 System.out.println("\t * read ");
		 System.out.println("For films: ");
		 System.out.println("\t * watching ");
		 System.out.println("\t * want watch (default) ");
		 System.out.println("\t * watched ");
		 System.out.println("For music: ");
		 System.out.println("\t * listening ");
		 System.out.println("\t * want listen (default) ");
		 System.out.println("\t * listened ");
	 }
	 
	 public boolean search(String className,String name){
		 boolean response = true;
		 int i=0;
		 while(i<list.size() && response){
			 if(list.get(i).getClass().toString().contains(className) && list.get(i).getName().equals(name)){
				 response = false;
				 break;
			 } else i++;
		 }
		 return response;
	 }
}
