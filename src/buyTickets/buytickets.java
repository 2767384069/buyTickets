package buyTickets;

public class buytickets{
	   public static void main(String args[]){
	      String s1="赵",s2="钱",s3="孙",s4="李",s5="周";
	      Cinema canema=new Cinema(s1,s2,s3,s4,s5);
	      Thread zhao,qian,sun,li,zhou;
	      zhao=new Thread(canema);
	      qian=new Thread(canema);
	      sun=new Thread(canema);
	      li=new Thread(canema);
	      zhou=new Thread(canema);
	      zhao.setName(s1);
	      qian.setName(s2);
	      sun.setName(s3);
	      li.setName(s4);
	      zhou.setName(s5);
	      zhao.start();
	      qian.start();
	      sun.start();
	      li.start();
	      zhou.start();
	   }
	}
	class Cinema implements Runnable{    //实现Runable接口的类（电影院）
	   TicketSeller seller;              //电影院的售票员
	   String name1,name2,name3,name4,name5;     //买票人的名字（线程的名字）
	   Cinema(String s1,String s2,String s3,String s4,String s5){
	      seller=new TicketSeller();
	      name1=s1;
	      name2=s2;
	      name3=s3;
	      name4=s4;
	      name5=s5;
	   }
	   public void run(){
	      if(Thread.currentThread().getName().equals(name1)){
	         seller.sellTicket(20,2);
	      }
	      if(Thread.currentThread().getName().equals(name2)){
	         seller.sellTicket(20,1);
	      }
	      if(Thread.currentThread().getName().equals(name3)){
	         seller.sellTicket(10,1);
	      }
	      if(Thread.currentThread().getName().equals(name4)){
	         seller.sellTicket(10,2);
	      }
	      if(Thread.currentThread().getName().equals(name5)){
	         seller.sellTicket(5,1);
	      }
	   }
	}
	class TicketSeller{                //负责卖票的类 
	   int fiveNumber=1,tenNumber=0,twentyNumber=0;
	   public synchronized void sellTicket(int receiveMoney,int ticketNumber){
	      String s=Thread.currentThread().getName();
	      if(receiveMoney==5 && ticketNumber==1){
	         fiveNumber=fiveNumber+1;
	         System.out.println(s+"给售票员5元钱，售票员卖给"+s+"一张票，不必找赎");
	      }
	      else if(receiveMoney==10 && ticketNumber==1){
	         while(fiveNumber<1){
	            try{
	               System.out.println(s+"给售票员10元钱");
	               System.out.println("售票员请"+s+"靠边等一会");
	               wait();               //如果线程占有CPU期间执行了wait(),就进入中断状态
	               System.out.println(s+"结束等待，继续买票");
	            }
	            catch(InterruptedException e){ }
	         }
	         fiveNumber=fiveNumber-1;
	         tenNumber=tenNumber+1;
	         System.out.println(s+"给售票员10元钱，售票员卖给"+s+"一张票，找赎5元");
	      }
	      else if(receiveMoney==10 && ticketNumber==2){
	         tenNumber=tenNumber+1;
	         System.out.println(s+"给售票员10元钱，售票员卖给"+s+"两张票，不必找赎");
	      }

	      else if(receiveMoney==20 && ticketNumber==1){
	         while(fiveNumber<1||tenNumber<1){
	            try{
	               System.out.println(s+"给售票员20元钱");
	               System.out.println("售票员请"+s+"靠边等一会");
	               wait();               //如果线程占有CPU期间执行了wait(),就进入中断状态
	               System.out.println(s+"结束等待，继续买票");
	            }
	            catch(InterruptedException e){ }
	         }
	         fiveNumber=fiveNumber-1;
	         tenNumber=tenNumber-1;
	         twentyNumber=twentyNumber+1;
	         System.out.println(s+"给售票员20元钱，售票员卖给"+s+"一张票，找赎15元");
	      }
	      else if(receiveMoney==20 && ticketNumber==2){
	         while(tenNumber<1){
	            try{
	               System.out.println(s+"给售票员20元钱");
	               System.out.println("售票员请"+s+"靠边等一会");
	               wait();               //如果线程占有CPU期间执行了wait(),就进入中断状态
	               System.out.println(s+"结束等待，继续买票");
	            }
	            catch(InterruptedException e){ }
	         }
	         tenNumber=tenNumber-1;
	         twentyNumber=twentyNumber+1;
	         System.out.println(s+"给售票员20元钱，售票员卖给"+s+"两张票，找赎10元");
	      }
	      notifyAll();
	   }
	}
