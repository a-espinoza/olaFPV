import java.util.Arrays;
import java.util.Scanner;

public class BetaFlight
	{
	private static String[] dump = new String [200];
	private static String UART = "no UART found";
	private static String CriticalLine = "Error in dump scanning - please check dump";
	private static String resourceID = "no resourceID found";
	private static boolean dev = false;

	public static void main(String[] args)
		{
		DevMode();
		userIn();
		findCriticalLine();
		findResourceID();
		System.out.println("Please copy the following code into Betaflight CLI:");
		System.out.println("");
		makeCode();
		}
	
	
	public static void makeCode()
		{
		System.out.println("resource SERIAL_" +UART +" NONE");
		System.out.println("resource PINIO 1 " + resourceID);
		System.out.println("set pinio_box = 40,0,0,0");
		System.out.println("aux 2 40 0 0 0 0");
		System.out.println("save");

		}
	
	
	public static void findResourceID()
		{
		resourceID = CriticalLine.substring(CriticalLine.length()-4);
		if(dev)
			System.out.println("Resource ID: "+resourceID);
			
		}

	public static void findCriticalLine()
		{
		String TempLine="error in findCriticalLine method";
		for (int i=0; i<dump.length-1; i++)
			{
			TempLine=dump[i];
			if (TempLine.contains(UART))
				CriticalLine=TempLine;
			}
		System.out.println("");
		if(dev)
			System.out.println("critical line: "+ CriticalLine);
		}

	public static void userIn()//puts Dump into array
		{
		System.out.println("Welcome to the OlaFPV PinIO Setup Utility ");
		System.out.println("this tool was designed for the MultiView and VTX Assassin");
		//System.out.println("	 and it may or may not work with other priducts");
		System.out.println("Enter your UART you wish to use (any format) ");
		userInUART();
		if(dev)
			System.out.println("converted UART: "+UART);
		
		System.out.println("Please type 'dump' into your betaflight CLI, and paste the full dump here."+'\n'+ "Make sure this is your first time setting up pinIO on this board (see olafpv.com for more info) ");
		userInDump();
		if(dev)
			{
			System.out.println("dump:");
			for(String s:dump)
				System.out.print(s);
			}

		}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void DevMode()
		{
		System.out.println("please press enter to start");
		Scanner preston = new Scanner(System.in);
		String s = " ";
		s=preston.nextLine();
		System.out.println(s);
		//CharSequence y = 'y';
		if (s.contains("y"))
			{
			dev=true;
			System.out.println("dev mode entered");
			}
		}
	public static void userInDump() //Gets Dump from user
		{
		Scanner preston = new Scanner(System.in);
			for (int c=0; c<dump.length-1; c++)
				{
				if ((preston.hasNextLine()))
					{
					dump[c]= preston.nextLine();
					//System.out.println(dump[c]);
					}
				}
		
		}
	public static void userInUART() //gets UART, formats UART
		{
		Scanner preston = new Scanner(System.in);
		UART = preston.nextLine();
		
		if(dev)
			System.out.println("OG UART: "+UART);
			
		
		
		UART= UART.replace(" ", "");
		UART = UART.toUpperCase();

		
		UART = UART.substring(0, (UART.indexOf(("X"))+1)  )+" "+(UART.substring((UART.indexOf(("X"))+1))   );
		}
	
	}
