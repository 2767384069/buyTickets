package buyTickets;

public class buytickets{
	   public static void main(String args[]){
	      String s1="��",s2="Ǯ",s3="��",s4="��",s5="��";
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
	class Cinema implements Runnable{    //ʵ��Runable�ӿڵ��ࣨ��ӰԺ��
	   TicketSeller seller;              //��ӰԺ����ƱԱ
	   String name1,name2,name3,name4,name5;     //��Ʊ�˵����֣��̵߳����֣�
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
	class TicketSeller{                //������Ʊ���� 
	   int fiveNumber=1,tenNumber=0,twentyNumber=0;
	   public synchronized void sellTicket(int receiveMoney,int ticketNumber){
	      String s=Thread.currentThread().getName();
	      if(receiveMoney==5 && ticketNumber==1){
	         fiveNumber=fiveNumber+1;
	         System.out.println(s+"����ƱԱ5ԪǮ����ƱԱ����"+s+"һ��Ʊ����������");
	      }
	      else if(receiveMoney==10 && ticketNumber==1){
	         while(fiveNumber<1){
	            try{
	               System.out.println(s+"����ƱԱ10ԪǮ");
	               System.out.println("��ƱԱ��"+s+"���ߵ�һ��");
	               wait();               //����߳�ռ��CPU�ڼ�ִ����wait(),�ͽ����ж�״̬
	               System.out.println(s+"�����ȴ���������Ʊ");
	            }
	            catch(InterruptedException e){ }
	         }
	         fiveNumber=fiveNumber-1;
	         tenNumber=tenNumber+1;
	         System.out.println(s+"����ƱԱ10ԪǮ����ƱԱ����"+s+"һ��Ʊ������5Ԫ");
	      }
	      else if(receiveMoney==10 && ticketNumber==2){
	         tenNumber=tenNumber+1;
	         System.out.println(s+"����ƱԱ10ԪǮ����ƱԱ����"+s+"����Ʊ����������");
	      }

	      else if(receiveMoney==20 && ticketNumber==1){
	         while(fiveNumber<1||tenNumber<1){
	            try{
	               System.out.println(s+"����ƱԱ20ԪǮ");
	               System.out.println("��ƱԱ��"+s+"���ߵ�һ��");
	               wait();               //����߳�ռ��CPU�ڼ�ִ����wait(),�ͽ����ж�״̬
	               System.out.println(s+"�����ȴ���������Ʊ");
	            }
	            catch(InterruptedException e){ }
	         }
	         fiveNumber=fiveNumber-1;
	         tenNumber=tenNumber-1;
	         twentyNumber=twentyNumber+1;
	         System.out.println(s+"����ƱԱ20ԪǮ����ƱԱ����"+s+"һ��Ʊ������15Ԫ");
	      }
	      else if(receiveMoney==20 && ticketNumber==2){
	         while(tenNumber<1){
	            try{
	               System.out.println(s+"����ƱԱ20ԪǮ");
	               System.out.println("��ƱԱ��"+s+"���ߵ�һ��");
	               wait();               //����߳�ռ��CPU�ڼ�ִ����wait(),�ͽ����ж�״̬
	               System.out.println(s+"�����ȴ���������Ʊ");
	            }
	            catch(InterruptedException e){ }
	         }
	         tenNumber=tenNumber-1;
	         twentyNumber=twentyNumber+1;
	         System.out.println(s+"����ƱԱ20ԪǮ����ƱԱ����"+s+"����Ʊ������10Ԫ");
	      }
	      notifyAll();
	   }
	}
